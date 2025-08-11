package com.dji.sample.manage.service.impl;

import com.dji.sample.common.error.CommonErrorEnum;
import com.dji.sample.manage.model.dto.DeviceDTO;
import com.dji.sample.manage.model.dto.DeviceDictionaryDTO;
import com.dji.sample.manage.model.dto.WorkspaceDTO;
import com.dji.sample.manage.service.IDeviceDictionaryService;
import com.dji.sample.manage.service.IDeviceService;
import com.dji.sample.manage.service.IWorkspaceService;
import com.dji.sdk.cloudapi.device.DeviceDomainEnum;
import com.dji.sdk.cloudapi.device.DeviceEnum;
import com.dji.sdk.cloudapi.organization.*;
import com.dji.sdk.cloudapi.organization.api.AbstractOrganizationService;
import com.dji.sdk.cloudapi.tsa.DeviceIconUrl;
import com.dji.sdk.cloudapi.tsa.IconUrlEnum;
import com.dji.sdk.mqtt.MqttReply;
import com.dji.sdk.mqtt.requests.TopicRequestsRequest;
import com.dji.sdk.mqtt.requests.TopicRequestsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * SDK 조직 관리 서비스 구현체
 * 
 * DJI Cloud API의 조직 관리를 위한 SDK 서비스 구현체입니다.
 * 이 클래스는 AbstractOrganizationService를 상속받아 DJI 디바이스의
 * 조직 바인딩, 상태 관리, 조직 정보 조회 등을 처리합니다.
 * 
 * 주요 기능:
 * 1. 공항 바인딩 상태 관리
 *    - 디바이스 바인딩 상태 조회
 *    - 바인딩 상태 응답 처리
 *    - 디바이스별 바인딩 정보 관리
 * 
 * 2. 공항 조직 정보 관리
 *    - 조직 정보 조회 및 응답
 *    - 바인딩 코드 검증
 *    - 워크스페이스 정보 제공
 * 
 * 3. 공항 조직 바인딩 관리
 *    - 도킹 스테이션과 드론 바인딩
 *    - 디바이스 모델 키 처리
 *    - 바인딩 결과 관리
 *    - 디바이스 저장 및 업데이트
 * 
 * 4. 디바이스 데이터 변환
 *    - 바인딩 디바이스를 DTO로 변환
 *    - DTO를 바인딩 상태로 변환
 *    - 디바이스 메타데이터 매핑
 * 
 * 5. 오류 처리 및 검증
 *    - 바인딩 코드 유효성 검증
 *    - 조직 정보 존재 여부 확인
 *    - 디바이스 바인딩 상태 검증
 * 
 * 주요 의존성:
 * - IDeviceService: 디바이스 관리 서비스
 * - IDeviceDictionaryService: 디바이스 사전 관리
 * - IWorkspaceService: 워크스페이스 관리 서비스
 * - AbstractOrganizationService: DJI 조직 서비스 기본 클래스
 * 
 * 이 클래스는 DJI 디바이스의 조직 관리를
 * 안전하고 효율적으로 처리하는 SDK 서비스입니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/7/7
 */
@Service
public class SDKOrganizationService extends AbstractOrganizationService {

    /**
     * 디바이스 관리 서비스
     * 디바이스 정보 조회, 저장, 업데이트 등을 담당
     */
    @Autowired
    private IDeviceService deviceService;

    /**
     * 디바이스 사전 관리 서비스
     * 디바이스 타입, 모델 정보 등을 관리
     */
    @Autowired
    private IDeviceDictionaryService dictionaryService;

    /**
     * 워크스페이스 관리 서비스
     * 조직 및 워크스페이스 정보를 관리
     */
    @Autowired
    private IWorkspaceService workspaceService;

    /**
     * 공항 바인딩 상태를 처리합니다.
     * 
     * 디바이스들의 바인딩 상태를 조회하고 응답합니다.
     * 각 디바이스의 시리얼 번호를 기반으로 바인딩 상태를 확인하고,
     * 바인딩된 디바이스는 상세 정보를, 바인딩되지 않은 디바이스는
     * 기본 정보만 포함하여 응답합니다.
     * 
     * @param request 공항 바인딩 상태 요청 (디바이스 시리얼 번호 목록 포함)
     * @param headers 메시지 헤더 정보
     * @return 바인딩 상태 응답 (각 디바이스의 바인딩 상태 정보 포함)
     */
    @Override
    public TopicRequestsResponse<MqttReply<AirportBindStatusResponse>> airportBindStatus(TopicRequestsRequest<AirportBindStatusRequest> request, MessageHeaders headers) {
        // 요청에서 디바이스 목록 추출
        List<BindStatusResponseDevice> data = request.getData().getDevices();

        // 바인딩 상태 결과를 저장할 리스트
        List<BindStatusRequestDevice> bindStatusResult = new ArrayList<>();
        
        // 각 디바이스의 바인딩 상태 확인
        for (BindStatusResponseDevice bindStatus : data) {
            // 시리얼 번호로 디바이스 정보 조회
            Optional<DeviceDTO> deviceOpt = deviceService.getDeviceBySn(bindStatus.getSn());
            
            // 디바이스가 존재하면 상세 정보로, 없으면 기본 정보로 응답
            bindStatusResult.add(deviceOpt.isPresent() ? dto2BindStatus(deviceOpt.get()) :
                    new BindStatusRequestDevice().setSn(bindStatus.getSn()).setDeviceBindOrganization(false));
        }
        
        // 성공 응답 생성
        return new TopicRequestsResponse<MqttReply<AirportBindStatusResponse>>()
                .setData(MqttReply.success(new AirportBindStatusResponse().setBindStatus(bindStatusResult)));
    }

    /**
     * 공항 조직 정보를 조회합니다.
     * 
     * 바인딩 코드를 통해 조직 정보를 조회하고 응답합니다.
     * 바인딩 코드의 유효성을 검증하고, 해당 코드에 연결된
     * 워크스페이스 정보를 반환합니다.
     * 
     * @param request 공항 조직 정보 요청 (바인딩 코드 포함)
     * @param headers 메시지 헤더 정보
     * @return 조직 정보 응답 (조직명 포함)
     */
    @Override
    public TopicRequestsResponse<MqttReply<AirportOrganizationGetResponse>> airportOrganizationGet(TopicRequestsRequest<AirportOrganizationGetRequest> request, MessageHeaders headers) {
        AirportOrganizationGetRequest organizationGet = request.getData();
        
        // 바인딩 코드 유효성 검증
        if (!StringUtils.hasText(organizationGet.getDeviceBindingCode())) {
            return new TopicRequestsResponse().setData(MqttReply.error(CommonErrorEnum.ILLEGAL_ARGUMENT));
        }

        // 바인딩 코드로 워크스페이스 정보 조회
        Optional<WorkspaceDTO> workspace = workspaceService.getWorkspaceNameByBindCode(organizationGet.getDeviceBindingCode());
        
        // 워크스페이스가 존재하지 않으면 오류 응답
        if (workspace.isEmpty()) {
            return new TopicRequestsResponse().setData(MqttReply.error(CommonErrorEnum.GET_ORGANIZATION_FAILED));
        }

        // 성공 응답 생성 (조직명 포함)
        return new TopicRequestsResponse<MqttReply<AirportOrganizationGetResponse>>()
                .setData(MqttReply.success(new AirportOrganizationGetResponse()
                        .setOrganizationName(workspace.get().getWorkspaceName())));
    }

    /**
     * 공항 조직 바인딩을 처리합니다.
     * 
     * 도킹 스테이션과 드론을 조직에 바인딩합니다.
     * 디바이스 모델 키를 기반으로 도킹 스테이션과 드론을 구분하고,
     * 각각을 데이터베이스에 저장하며 바인딩 결과를 반환합니다.
     * 
     * @param request 공항 조직 바인딩 요청 (바인딩할 디바이스 목록 포함)
     * @param headers 메시지 헤더 정보
     * @return 바인딩 결과 응답 (각 디바이스의 바인딩 성공/실패 정보 포함)
     */
    @Override
    public TopicRequestsResponse<MqttReply<AirportOrganizationBindResponse>> airportOrganizationBind(TopicRequestsRequest<AirportOrganizationBindRequest> request, MessageHeaders headers) {
        List<OrganizationBindDevice> devices = request.getData().getBindDevices();
        OrganizationBindDevice dock = null;
        OrganizationBindDevice drone = null;
        
        // 디바이스 타입별로 분류 (도킹 스테이션과 드론)
        for (OrganizationBindDevice device : devices) {
            DeviceDomainEnum val = device.getDeviceModelKey().getDomain();
            if (val == DeviceDomainEnum.DOCK) {
                dock = device;
            }
            if (val == DeviceDomainEnum.DRONE) {
                drone = device;
            }
        }

        // 디바이스를 DTO로 변환
        Optional<DeviceDTO> dockOpt = bindDevice2Dto(dock);
        Optional<DeviceDTO> droneOpt = bindDevice2Dto(drone);
        List<OrganizationBindInfo> bindResult = new ArrayList<>();

        // 드론이 존재하면 도킹 스테이션의 자식 디바이스로 설정하고 저장
        droneOpt.ifPresent(droneDto -> {
            dockOpt.get().setChildDeviceSn(droneDto.getDeviceSn());
            boolean success = deviceService.saveOrUpdateDevice(droneDto);
            bindResult.add(success ?
                    OrganizationBindInfo.success(droneDto.getDeviceSn()) :
                    new OrganizationBindInfo(droneDto.getDeviceSn(),
                            CommonErrorEnum.DEVICE_BINDING_FAILED.getCode())
            );
        });
        
        // 도킹 스테이션 저장
        boolean success = deviceService.saveOrUpdateDevice(dockOpt.get());

        // 바인딩 결과 추가
        bindResult.add(success ?
                OrganizationBindInfo.success(dock.getSn()) :
                new OrganizationBindInfo(dock.getSn(),
                        CommonErrorEnum.DEVICE_BINDING_FAILED.getCode()));

        // 성공 응답 생성
        return new TopicRequestsResponse<MqttReply<AirportOrganizationBindResponse>>()
                .setData(MqttReply.success(new AirportOrganizationBindResponse().setErrInfos(bindResult)));
    }

    /**
     * 바인딩 디바이스 데이터를 데이터베이스 엔티티 객체로 변환합니다.
     * 
     * OrganizationBindDevice 객체를 DeviceDTO로 변환하여
     * 데이터베이스에 저장할 수 있는 형태로 만듭니다.
     * 디바이스 사전 정보와 워크스페이스 정보를 포함하여
     * 완전한 디바이스 정보를 구성합니다.
     *
     * @param receiver 바인딩할 디바이스 정보
     * @return 변환된 DeviceDTO 객체 (Optional)
     */
    public Optional<DeviceDTO> bindDevice2Dto(OrganizationBindDevice receiver) {
        if (receiver == null) {
            return Optional.empty();
        }

        // 디바이스 모델 키에서 디바이스 정보 조회
        DeviceEnum deviceModelKey = receiver.getDeviceModelKey();
        Optional<DeviceDictionaryDTO> dictionaryOpt = dictionaryService.getOneDictionaryInfoByTypeSubType(
                deviceModelKey.getDomain().getDomain(), deviceModelKey.getType().getType(),
                deviceModelKey.getSubType().getSubType());
        
        // DeviceDTO 빌더 생성
        DeviceDTO.DeviceDTOBuilder builder = DeviceDTO.builder();

        // 디바이스 사전 정보가 있으면 기본 정보 설정
        dictionaryOpt.ifPresent(entity ->
                builder.deviceName(entity.getDeviceName())
                        .nickname(entity.getDeviceName())
                        .deviceDesc(entity.getDeviceDesc()));
        
        // 바인딩 코드로 워크스페이스 정보 조회
        Optional<WorkspaceDTO> workspace = workspaceService.getWorkspaceNameByBindCode(receiver.getDeviceBindingCode());

        // DeviceDTO 객체 생성
        DeviceDTO dto = builder
                .workspaceId(workspace.map(WorkspaceDTO::getWorkspaceId).orElse(receiver.getOrganizationId()))
                .domain(deviceModelKey.getDomain())
                .type(deviceModelKey.getType())
                .subType(deviceModelKey.getSubType())
                .deviceSn(receiver.getSn())
                .boundStatus(true)
                .loginTime(LocalDateTime.now())
                .boundTime(LocalDateTime.now())
                .iconUrl(new DeviceIconUrl()
                        .setSelectIconUrl(IconUrlEnum.SELECT_EQUIPMENT.getUrl())
                        .setNormalIconUrl(IconUrlEnum.NORMAL_EQUIPMENT.getUrl()))
                .build();
        
        // 디바이스 호출명 설정 (있으면 사용, 없으면 기존 닉네임 사용)
        if (StringUtils.hasText(receiver.getDeviceCallsign())) {
            dto.setNickname(receiver.getDeviceCallsign());
        } else {
            Optional<DeviceDTO> deviceOpt = deviceService.getDeviceBySn(receiver.getSn());
            dto.setNickname(deviceOpt.map(DeviceDTO::getNickname).orElse(dto.getNickname()));
        }
        
        return Optional.of(dto);
    }

    /**
     * 디바이스 데이터 전송 객체를 디바이스 바인딩 상태 데이터 객체로 변환합니다.
     * 
     * DeviceDTO 객체를 BindStatusRequestDevice로 변환하여
     * 바인딩 상태 응답에 사용할 수 있는 형태로 만듭니다.
     * 
     * @param device 변환할 디바이스 DTO 객체
     * @return 바인딩 상태 디바이스 객체 (null이 아닌 경우)
     */
    private BindStatusRequestDevice dto2BindStatus(DeviceDTO device) {
        if (device == null) {
            return null;
        }
        
        // 바인딩 상태 디바이스 객체 생성 및 정보 설정
        return new BindStatusRequestDevice()
                .setSn(device.getDeviceSn())                    // 디바이스 시리얼 번호
                .setDeviceCallsign(device.getNickname())        // 디바이스 호출명
                .setDeviceBindOrganization(device.getBoundStatus()) // 바인딩 상태
                .setOrganizationId(device.getWorkspaceId())     // 조직 ID
                .setOrganizationName(device.getWorkspaceName()); // 조직명
    }
}
