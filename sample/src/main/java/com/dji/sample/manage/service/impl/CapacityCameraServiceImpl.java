package com.dji.sample.manage.service.impl;

import com.dji.sample.component.redis.RedisConst;
import com.dji.sample.component.redis.RedisOpsUtils;
import com.dji.sample.manage.model.dto.CapacityCameraDTO;
import com.dji.sample.manage.model.dto.DeviceDictionaryDTO;
import com.dji.sample.manage.model.receiver.CapacityCameraReceiver;
import com.dji.sample.manage.service.ICameraVideoService;
import com.dji.sample.manage.service.ICapacityCameraService;
import com.dji.sample.manage.service.IDeviceDictionaryService;
import com.dji.sdk.cloudapi.device.DeviceDomainEnum;
import com.dji.sdk.cloudapi.device.PayloadIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 카메라 용량 관리 서비스 구현체
 * 
 * DJI Cloud API의 카메라 용량 관리를 위한 서비스 구현체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 구현합니다:
 * 
 * 1. 카메라 용량 정보 관리
 *    - 디바이스별 카메라 용량 정보 조회
 *    - 카메라 용량 정보 삭제
 *    - 카메라 용량 정보 저장 및 업데이트
 *    - Redis를 통한 카메라 용량 캐싱
 * 
 * 2. 카메라 데이터 변환
 *    - CapacityCameraReceiver를 CapacityCameraDTO로 변환
 *    - 카메라 인덱스 및 비디오 리스트 처리
 *    - 페이로드 인덱스 정보 매핑
 *    - 디바이스 사전 정보 연동
 * 
 * 3. Redis 캐시 관리
 *    - 카메라 용량 정보 Redis 저장
 *    - Redis에서 카메라 용량 정보 조회
 *    - Redis에서 카메라 용량 정보 삭제
 *    - 캐시 동기화 및 관리
 * 
 * 4. 페이로드 인덱스 처리
 *    - 카메라 인덱스 구성 요소 처리 (타입-서브타입-인덱스)
 *    - 페이로드 타입 및 서브타입 매핑
 *    - 디바이스 사전 정보 조회
 *    - 카메라 이름 정보 설정
 * 
 * 5. 비디오 리스트 관리
 *    - 카메라별 비디오 리스트 처리
 *    - 비디오 데이터 변환 및 필터링
 *    - 비디오 서비스 연동
 *    - 비디오 메타데이터 관리
 * 
 * 주요 의존성:
 * - ICameraVideoService: 카메라 비디오 서비스
 * - IDeviceDictionaryService: 디바이스 사전 관리 서비스
 * - RedisOpsUtils: Redis 작업 유틸리티
 * - CapacityCameraDTO: 카메라 용량 데이터 전송 객체
 * - CapacityCameraReceiver: 카메라 용량 수신자 객체
 * 
 * 이 클래스는 DJI 디바이스의 카메라 용량 정보를
 * 효율적으로 관리하는 서비스입니다.
 * 
 * @author sean.zhou
 * @date 2021/11/19
 * @version 0.1
 */
@Service
public class CapacityCameraServiceImpl implements ICapacityCameraService {

    /**
     * 카메라 비디오 서비스
     * 카메라 비디오 정보 변환 및 관리를 담당
     */
    @Autowired
    private ICameraVideoService cameraVideoService;

    /**
     * 디바이스 사전 관리 서비스
     * 디바이스 타입, 모델 정보 등을 관리
     */
    @Autowired
    private IDeviceDictionaryService dictionaryService;

    /**
     * 디바이스 시리얼 번호로 카메라 용량 정보를 조회합니다.
     * 
     * Redis 캐시에서 해당 디바이스의 카메라 용량 정보를 조회합니다.
     * 
     * @param deviceSn 디바이스 시리얼 번호
     * @return 카메라 용량 정보 리스트
     */
    @Override
    public List<CapacityCameraDTO> getCapacityCameraByDeviceSn(String deviceSn) {
        return (List<CapacityCameraDTO>) RedisOpsUtils.hashGet(RedisConst.LIVE_CAPACITY, deviceSn);
    }

    /**
     * 디바이스 시리얼 번호로 카메라 용량 정보를 삭제합니다.
     * 
     * Redis 캐시에서 해당 디바이스의 카메라 용량 정보를 삭제합니다.
     * 
     * @param deviceSn 삭제할 디바이스 시리얼 번호
     * @return 삭제 성공 여부
     */
    @Override
    public Boolean deleteCapacityCameraByDeviceSn(String deviceSn) {
        return RedisOpsUtils.hashDel(RedisConst.LIVE_CAPACITY, new String[]{deviceSn});
    }

    /**
     * 카메라 용량 수신자 리스트를 저장합니다.
     * 
     * 카메라 용량 수신자 리스트를 DTO로 변환한 후
     * Redis에 저장하여 캐싱합니다.
     * 
     * @param capacityCameraReceivers 카메라 용량 수신자 리스트
     * @param deviceSn 디바이스 시리얼 번호
     */
    @Override
    public void saveCapacityCameraReceiverList(List<CapacityCameraReceiver> capacityCameraReceivers, String deviceSn) {
        // 카메라 용량 수신자 리스트를 DTO로 변환
        List<CapacityCameraDTO> capacity = capacityCameraReceivers.stream()
                .map(this::receiver2Dto).collect(Collectors.toList());
        
        // Redis에 카메라 용량 정보 저장
        RedisOpsUtils.hashSet(RedisConst.LIVE_CAPACITY, deviceSn, capacity);
    }

    /**
     * 카메라 용량 수신자 객체를 DTO로 변환합니다.
     * 
     * CapacityCameraReceiver 객체를 CapacityCameraDTO로 변환하여
     * 카메라 용량 정보를 처리할 수 있는 형태로 만듭니다.
     * 카메라 인덱스, 비디오 리스트, 디바이스 사전 정보 등을 포함하여
     * 완전한 카메라 용량 정보를 구성합니다.
     * 
     * @param receiver 변환할 카메라 용량 수신자 객체
     * @return 변환된 CapacityCameraDTO 객체
     */
    public CapacityCameraDTO receiver2Dto(CapacityCameraReceiver receiver) {
        // CapacityCameraDTO 빌더 생성
        CapacityCameraDTO.CapacityCameraDTOBuilder builder = CapacityCameraDTO.builder();
        
        // 수신자 객체가 null이면 빈 객체 반환
        if (receiver == null) {
            return builder.build();
        }
        
        // 카메라 인덱스 정보 추출
        PayloadIndex cameraIndex = receiver.getCameraIndex();
        
        // 카메라 인덱스는 타입, 서브타입, 드론에 장착된 페이로드의 인덱스로 구성됩니다.
        // 형식: type-subType-index
        Optional<DeviceDictionaryDTO> dictionaryOpt = dictionaryService.getOneDictionaryInfoByTypeSubType(
                DeviceDomainEnum.PAYLOAD.getDomain(), cameraIndex.getType().getType(), cameraIndex.getSubType().getSubType());
        
        // 디바이스 사전 정보가 있으면 카메라 이름 설정
        dictionaryOpt.ifPresent(dictionary -> builder.name(dictionary.getDeviceName()));

        // 완성된 CapacityCameraDTO 객체 생성 및 반환
        return builder
                .id(UUID.randomUUID().toString())        // 고유 ID 생성
                .videosList(receiver.getVideoList()      // 비디오 리스트 변환
                        .stream()
                        .map(cameraVideoService::receiver2Dto)  // 각 비디오를 DTO로 변환
                        .filter(Objects::nonNull)               // null이 아닌 비디오만 필터링
                        .collect(Collectors.toList()))
                .index(receiver.getCameraIndex().toString())    // 카메라 인덱스 설정
                .build();
    }
}