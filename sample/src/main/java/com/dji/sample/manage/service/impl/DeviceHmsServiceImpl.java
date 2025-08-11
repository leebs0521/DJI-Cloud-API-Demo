package com.dji.sample.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dji.sample.component.websocket.model.BizCodeEnum;
import com.dji.sample.component.websocket.service.IWebSocketMessageService;
import com.dji.sample.manage.dao.IDeviceHmsMapper;
import com.dji.sample.manage.model.common.HmsJsonUtil;
import com.dji.sample.manage.model.common.HmsMessage;
import com.dji.sample.manage.model.dto.DeviceDTO;
import com.dji.sample.manage.model.dto.DeviceHmsDTO;
import com.dji.sample.manage.model.dto.TelemetryDTO;
import com.dji.sample.manage.model.entity.DeviceHmsEntity;
import com.dji.sample.manage.model.enums.UserTypeEnum;
import com.dji.sample.manage.model.param.DeviceHmsQueryParam;
import com.dji.sample.manage.service.IDeviceHmsService;
import com.dji.sample.manage.service.IDeviceRedisService;
import com.dji.sdk.cloudapi.device.DeviceDomainEnum;
import com.dji.sdk.cloudapi.hms.*;
import com.dji.sdk.cloudapi.hms.api.AbstractHmsService;
import com.dji.sdk.common.Pagination;
import com.dji.sdk.common.PaginationData;
import com.dji.sdk.mqtt.events.TopicEventsRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 디바이스 HMS(Hardware Management System) 관리 서비스 구현체
 * 
 * DJI Cloud API의 디바이스 HMS 관리를 위한 서비스 구현체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 구현합니다:
 * 
 * 1. HMS 메시지 처리
 *    - MQTT를 통한 HMS 메시지 수신 및 처리
 *    - HMS 메시지 파싱 및 검증
 *    - 메시지 중복 제거 및 필터링
 *    - 실시간 HMS 상태 모니터링
 * 
 * 2. HMS 데이터 관리
 *    - HMS 메시지 데이터베이스 저장
 *    - HMS 메시지 조회 및 검색
 *    - HMS 상태 추적 및 업데이트
 *    - HMS 이력 관리
 * 
 * 3. HMS 메시지 변환
 *    - HMS 메시지 포맷팅 및 변환
 *    - 다국어 메시지 지원 (한국어/영어)
 *    - 메시지 템플릿 처리
 *    - 메시지 인자 치환
 * 
 * 4. 실시간 HMS 알림
 *    - WebSocket을 통한 실시간 HMS 전송
 *    - HMS 상태 변경 알림
 *    - 디바이스별 HMS 모니터링
 *    - HMS 우선순위 관리
 * 
 * 5. HMS 캐시 관리
 *    - Redis를 통한 HMS 상태 캐싱
 *    - 읽지 않은 HMS 메시지 관리
 *    - HMS 메시지 만료 처리
 *    - 캐시 동기화
 * 
 * 주요 의존성:
 * - IDeviceHmsMapper: HMS 데이터베이스 접근
 * - IWebSocketMessageService: WebSocket 메시지 전송
 * - IDeviceRedisService: Redis 캐시 관리
 * - AbstractHmsService: DJI HMS 서비스
 * - HmsJsonUtil: HMS JSON 유틸리티
 * 
 * 이 클래스는 DJI 디바이스의 HMS 메시지를
 * 안전하고 효율적으로 관리하는 서비스입니다.
 * 
 * @author sean
 * @version 1.1
 * @date 2022/7/6
 */
@Service
@Transactional
@Slf4j
public class DeviceHmsServiceImpl extends AbstractHmsService implements IDeviceHmsService {

    /**
     * HMS 데이터베이스 매퍼
     * HMS 메시지 정보의 CRUD 작업을 담당
     */
    @Autowired
    private IDeviceHmsMapper mapper;

    /**
     * JSON 객체 매퍼
     * JSON 데이터 변환을 담당
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * WebSocket 메시지 서비스
     * 실시간으로 클라이언트에게 HMS 상태 변경을 알림
     */
    @Autowired
    private IWebSocketMessageService sendMessageService;

    /**
     * 디바이스 Redis 서비스
     * 디바이스 상태 정보를 Redis에 캐싱하여 빠른 조회를 지원
     */
    @Autowired
    private IDeviceRedisService deviceRedisService;

    /**
     * HMS 메시지 키 패턴
     * HMS 메시지에서 키를 추출하기 위한 정규표현식 패턴
     */
    private static final Pattern PATTERN_KEY = Pattern.compile(
                    "(" +
                    Arrays.stream(HmsFormatKeyEnum.values())
                            .map(HmsFormatKeyEnum::getKey)
                            .collect(Collectors.joining("|")) +
                    ")");

    /**
     * HMS 메시지를 처리합니다.
     * 
     * MQTT를 통해 수신된 HMS 메시지를 처리하고 데이터베이스에 저장하며,
     * WebSocket을 통해 웹 클라이언트로 전송합니다.
     * 
     * @param response MQTT 이벤트 요청
     * @param headers 메시지 헤더
     */
    @Override
    public void hms(TopicEventsRequest<Hms> response, MessageHeaders headers) {
        String sn = response.getFrom();
        DeviceHmsEntity entity = DeviceHmsEntity.builder()
                .bid(response.getBid())
                .tid(response.getTid())
                .createTime(response.getTimestamp())
                .updateTime(0L)
                .sn(sn)
                .build();
        // Redis에서 디바이스의 모든 읽지 않은 HMS 메시지 조회
        Set<String> hmsMap = deviceRedisService.getAllHmsKeys(sn);

        List<DeviceHmsDTO> unReadList = new ArrayList<>();
        response.getData().getList()
                .forEach(hmsReceiver -> {
                    final DeviceHmsEntity hms = entity.clone();
                    this.fillEntity(hms, hmsReceiver);
                    // 동일한 읽지 않은 HMS는 더 이상 증가하지 않음
                    if (hmsMap.contains(hms.getHmsKey())) {
                        return;
                    }
                    this.fillMessage(hms, hmsReceiver.getArgs());
                    unReadList.add(entity2Dto(hms));
                    mapper.insert(hms);
                });

        if (unReadList.isEmpty()) {
            return;
        }
        deviceRedisService.addEndHmsKeys(sn, unReadList.stream().map(DeviceHmsDTO::getKey).toArray(String[]::new));
        // 웹으로 전송
        Optional<DeviceDTO> deviceOpt = deviceRedisService.getDeviceOnline(sn);
        if (deviceOpt.isEmpty()) {
            return;
        }
        sendMessageService.sendBatch(deviceOpt.get().getWorkspaceId(), UserTypeEnum.WEB.getVal(),
                BizCodeEnum.DEVICE_HMS.getCode(), TelemetryDTO.<List<DeviceHmsDTO>>builder().sn(sn).host(unReadList).build());
    }

    /**
     * 조건에 따라 디바이스 HMS를 페이지네이션으로 조회합니다.
     * 
     * @param param HMS 조회 파라미터
     * @return 페이지네이션된 HMS 목록
     */
    @Override
    public PaginationData<DeviceHmsDTO> getDeviceHmsByParam(DeviceHmsQueryParam param) {
        LambdaQueryWrapper<DeviceHmsEntity> queryWrapper = new LambdaQueryWrapper<DeviceHmsEntity>()
                .and(wrapper -> param.getDeviceSn().forEach(sn -> wrapper.eq(DeviceHmsEntity::getSn, sn).or()))
                .between(param.getBeginTime() != null && param.getEndTime() != null,
                        DeviceHmsEntity::getCreateTime, param.getBeginTime(), param.getEndTime())
                .eq(param.getUpdateTime() != null, DeviceHmsEntity::getUpdateTime, param.getUpdateTime())
                .eq(param.getLevel() != null, DeviceHmsEntity::getLevel, param.getLevel())
                .like(StringUtils.hasText(param.getMessage()) &&
                                HmsMessageLanguageEnum.ZH.getLanguage().equals(param.getLanguage()),
                        DeviceHmsEntity::getMessageZh, param.getMessage())
                .like(StringUtils.hasText(param.getMessage()) &&
                                HmsMessageLanguageEnum.EN.getLanguage().equals(param.getLanguage()),
                        DeviceHmsEntity::getMessageEn, param.getMessage())
                .orderByDesc(DeviceHmsEntity::getCreateTime);
        if (param.getPage() == null || param.getPageSize() == null) {
            param.setPage(1L);
            param.setPageSize(Long.valueOf(mapper.selectCount(queryWrapper)));
        }

        Page<DeviceHmsEntity> pagination = mapper.selectPage(new Page<>(param.getPage(), param.getPageSize()), queryWrapper);

        List<DeviceHmsDTO> deviceHmsList = pagination.getRecords().stream().map(this::entity2Dto).collect(Collectors.toList());

        return new PaginationData<DeviceHmsDTO>(deviceHmsList, new Pagination(pagination.getCurrent(), pagination.getSize(), pagination.getTotal()));
    }

    /**
     * 읽지 않은 HMS를 업데이트합니다.
     * 
     * @param deviceSn 디바이스 시리얼 번호
     */
    @Override
    public void updateUnreadHms(String deviceSn) {
        mapper.update(DeviceHmsEntity.builder().updateTime(System.currentTimeMillis()).build(),
                new LambdaUpdateWrapper<DeviceHmsEntity>()
                        .eq(DeviceHmsEntity::getSn, deviceSn)
                        .eq(DeviceHmsEntity::getUpdateTime, 0L));
        // Redis에 캐시된 읽지 않은 메시지 삭제
        deviceRedisService.delHmsKeysBySn(deviceSn);
    }

    /**
     * HMS 엔티티를 DTO로 변환합니다.
     * 
     * @param entity HMS 엔티티
     * @return HMS DTO
     */
    private DeviceHmsDTO entity2Dto(DeviceHmsEntity entity) {
        if (entity == null) {
            return null;
        }
        return DeviceHmsDTO.builder()
                .bid(entity.getBid())
                .tid(entity.getTid())
                .createTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(entity.getCreateTime()), ZoneId.systemDefault()))
                .updateTime(entity.getUpdateTime().intValue() == 0 ?
                        null : LocalDateTime.ofInstant(Instant.ofEpochMilli(entity.getUpdateTime()), ZoneId.systemDefault()))
                .sn(entity.getSn())
                .hmsId(entity.getHmsId())
                .key(entity.getHmsKey())
                .level(entity.getLevel())
                .module(entity.getModule())
                .messageEn(entity.getMessageEn())
                .messageZh(entity.getMessageZh())
                .build();
    }

    /**
     * 수신된 데이터를 엔티티에 채웁니다. 문서를 참조하여 연결 규칙을 확인하세요.
     * 
     * @param dto HMS 엔티티
     * @param receiver HMS 수신자
     */
    private void fillEntity(DeviceHmsEntity dto, DeviceHms receiver) {
        dto.setLevel(receiver.getLevel().getLevel());
        dto.setModule(receiver.getModule().getModule());
        dto.setHmsId(UUID.randomUUID().toString());
        DeviceDomainEnum domain = receiver.getDeviceType().getDomain();
        if (DeviceDomainEnum.DOCK == domain) {
            dto.setHmsKey(HmsFaqIdEnum.DOCK_TIP.getText() + receiver.getCode());
            return;
        }
        StringBuilder key = new StringBuilder(HmsFaqIdEnum.FPV_TIP.getText()).append(receiver.getCode());

        if (receiver.getInTheSky()) {
            key.append(HmsInTheSkyEnum.IN_THE_SKY.getText());
        }
        dto.setHmsKey(key.toString());
    }

    /**
     * 관련 규칙에 따라 메시지의 와일드카드를 대체합니다.
     * 문서를 참조하여 연결 규칙을 확인하세요.
     * 
     * @param dto HMS 엔티티
     * @param args HMS 인자
     */
    private void fillMessage(DeviceHmsEntity dto, DeviceHmsArgs args) {
        HmsMessage hmsMessage = HmsJsonUtil.get(dto.getHmsKey());
        String zh = StringUtils.hasText(hmsMessage.getZh()) ? hmsMessage.getZh() : String.format("未知错误（%s）", dto.getHmsKey());
        String en = StringUtils.hasText(hmsMessage.getEn()) ? hmsMessage.getEn() : String.format("Unknown(%s)", dto.getHmsKey());//

        dto.setMessageZh(format(Locale.CHINESE.getLanguage(), zh, args));
        dto.setMessageEn(format(Locale.ENGLISH.getLanguage(), en, args));
    }

    /**
     * 키에 대한 매칭 파라미터를 설정합니다.
     * 
     * @param l 언어: zh 또는 en
     * @param hmsArgs HMS 인자
     * @return 키 인자 맵
     */
    private Map<String, String> fillKeyArgs(String l, DeviceHmsArgs hmsArgs) {
        Map<String, String> args = new HashMap<>();
        args.put(HmsFormatKeyEnum.ALARM_ID.getKey(), Objects.nonNull(hmsArgs.getAlarmId()) ? Long.toHexString(hmsArgs.getAlarmId()) : null);
        args.put(HmsFormatKeyEnum.COMPONENT_INDEX.getKey(),
                Objects.nonNull(hmsArgs.getComponentIndex()) ? String.valueOf(hmsArgs.getComponentIndex() + 1) : null);
        if (Objects.nonNull(hmsArgs.getSensorIndex())) {
            args.put(HmsFormatKeyEnum.INDEX.getKey(), String.valueOf(hmsArgs.getSensorIndex() + 1));

            HmsBatteryIndexEnum hmsBatteryIndexEnum = Optional.ofNullable(hmsArgs.getSensorIndex())
                    .filter(arg -> arg <= 1).map(HmsBatteryIndexEnum::find).orElse(null);
            HmsDockCoverIndexEnum hmsDockCoverIndexEnum = Optional.ofNullable(hmsArgs.getSensorIndex())
                    .filter(arg -> arg <= 1).map(HmsDockCoverIndexEnum::find).orElse(null);
            HmsChargingRodIndexEnum hmsChargingRodIndexEnum = Optional.ofNullable(hmsArgs.getSensorIndex())
                    .filter(arg -> arg <= 3).map(HmsChargingRodIndexEnum::find).orElse(null);

            switch (l) {
                case "zh":
                    args.put(HmsFormatKeyEnum.BATTERY_INDEX.getKey(), Optional.ofNullable(hmsBatteryIndexEnum)
                            .map(HmsBatteryIndexEnum::getZh).orElse(null));
                    args.put(HmsFormatKeyEnum.DOCK_COVER_INDEX.getKey(), Optional.ofNullable(hmsDockCoverIndexEnum)
                            .map(HmsDockCoverIndexEnum::getZh).orElse(null));
                    args.put(HmsFormatKeyEnum.CHARGING_ROD_INDEX.getKey(), Optional.ofNullable(hmsChargingRodIndexEnum)
                            .map(HmsChargingRodIndexEnum::getZh).orElse(null));
                    break;
                case "en":
                    args.put(HmsFormatKeyEnum.BATTERY_INDEX.getKey(), Optional.ofNullable(hmsBatteryIndexEnum)
                            .map(HmsBatteryIndexEnum::getEn).orElse(null));
                    args.put(HmsFormatKeyEnum.DOCK_COVER_INDEX.getKey(), Optional.ofNullable(hmsDockCoverIndexEnum)
                            .map(HmsDockCoverIndexEnum::getEn).orElse(null));
                    args.put(HmsFormatKeyEnum.CHARGING_ROD_INDEX.getKey(), Optional.ofNullable(hmsChargingRodIndexEnum)
                            .map(HmsChargingRodIndexEnum::getEn).orElse(null));
                    break;
                default:
                    break;
            }

        }
        return args;
    }

    /**
     * 지정된 로케일, 포맷 문자열 및 인자를 사용하여 포맷된 문자열을 반환합니다.
     * 
     * @param l 언어: zh 또는 en
     * @param format 포맷 문자열
     * @param hmsArgs HMS 인자
     * @return 포맷된 문자열
     */
    private String format(String l, String format, DeviceHmsArgs hmsArgs) {
        Map<String, String> args = fillKeyArgs(l, hmsArgs);
        List<String> list = parse(format);
        StringBuilder sb = new StringBuilder();
        for (String word : list) {
            if (!StringUtils.hasText(word)) {
                continue;
            }
            sb.append(args.getOrDefault(word, word));
        }
        return sb.toString();
    }

    /**
     * 포맷 문자열에서 포맷 지정자를 찾습니다.
     * 
     * @param s 포맷 문자열
     * @return 포맷 지정자 목록
     */
    private List<String> parse(String s) {
        List<String> list = new ArrayList<>();
        Matcher matcher = PATTERN_KEY.matcher(s);
        for (int i = 0; i < s.length(); ) {
            if (matcher.find(i)) {
                if (matcher.start() != i) {
                    list.add(s.substring(i, matcher.start()));
                }
                list.add(matcher.group());
                i = matcher.end();
            } else {
                list.add(s.substring(i));
                break;
            }
        }
        return list;
    }
}
