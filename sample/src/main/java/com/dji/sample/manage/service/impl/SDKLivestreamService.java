package com.dji.sample.manage.service.impl;

import com.dji.sample.manage.model.receiver.CapacityDeviceReceiver;
import com.dji.sample.manage.service.ICapacityCameraService;
import com.dji.sdk.cloudapi.livestream.DockLivestreamAbilityUpdate;
import com.dji.sdk.cloudapi.livestream.RcLivestreamAbilityUpdate;
import com.dji.sdk.cloudapi.livestream.api.AbstractLivestreamService;
import com.dji.sdk.mqtt.state.TopicStateRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SDK 라이브스트림 서비스 구현체
 * 
 * DJI Cloud API의 라이브스트림 관리를 위한 SDK 서비스 구현체입니다.
 * 이 클래스는 AbstractLivestreamService를 상속받아 DJI 디바이스의
 * 라이브스트림 능력 업데이트를 처리합니다.
 * 
 * 주요 기능:
 * 1. 도킹 스테이션 라이브스트림 능력 관리
 *    - 도킹 스테이션 라이브스트림 능력 업데이트 처리
 *    - 도킹 스테이션 카메라 용량 관리
 *    - 라이브스트림 능력 데이터 저장
 * 
 * 2. RC 라이브스트림 능력 관리
 *    - RC 라이브스트림 능력 업데이트 처리
 *    - RC 카메라 용량 관리
 *    - 라이브스트림 능력 데이터 저장
 * 
 * 3. 라이브스트림 용량 관리
 *    - 디바이스별 라이브스트림 용량 저장
 *    - 카메라 리스트 관리
 *    - 용량 데이터 변환 및 처리
 * 
 * 4. 데이터 변환 및 매핑
 *    - JSON 데이터를 객체로 변환
 *    - 용량 디바이스 수신자 처리
 *    - 카메라 리스트 매핑
 * 
 * 주요 의존성:
 * - AbstractLivestreamService: DJI 라이브스트림 서비스 기본 클래스
 * - ICapacityCameraService: 카메라 용량 관리 서비스
 * - ObjectMapper: JSON 직렬화/역직렬화
 * - DockLivestreamAbilityUpdate: 도킹 스테이션 라이브스트림 능력 업데이트
 * - RcLivestreamAbilityUpdate: RC 라이브스트림 능력 업데이트
 * 
 * 이 클래스는 DJI 디바이스의 라이브스트림 능력을
 * 효율적으로 관리하는 SDK 서비스입니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/7/6
 */
@Service
public class SDKLivestreamService extends AbstractLivestreamService {

    /**
     * 카메라 용량 관리 서비스
     * 카메라 용량 및 설정을 관리
     */
    @Autowired
    private ICapacityCameraService capacityCameraService;

    /**
     * JSON 객체 매퍼
     * JSON 데이터 변환을 담당
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 도킹 스테이션 라이브스트림 능력 업데이트를 처리합니다.
     * 
     * 도킹 스테이션의 라이브스트림 능력 정보를 업데이트하고
     * 관련 카메라 용량 정보를 저장합니다.
     * 
     * @param request 도킹 스테이션 라이브스트림 능력 업데이트 요청
     * @param headers 메시지 헤더 정보
     */
    @Override
    public void dockLivestreamAbilityUpdate(TopicStateRequest<DockLivestreamAbilityUpdate> request, MessageHeaders headers) {
        // 도킹 스테이션의 라이브스트림 용량 정보를 저장
        saveLiveCapacity(request.getData().getLiveCapacity().getDeviceList());
    }

    /**
     * RC 라이브스트림 능력 업데이트를 처리합니다.
     * 
     * 리모트 컨트롤의 라이브스트림 능력 정보를 업데이트하고
     * 관련 카메라 용량 정보를 저장합니다.
     * 
     * @param request RC 라이브스트림 능력 업데이트 요청
     * @param headers 메시지 헤더 정보
     */
    @Override
    public void rcLivestreamAbilityUpdate(TopicStateRequest<RcLivestreamAbilityUpdate> request, MessageHeaders headers) {
        // RC의 라이브스트림 용량 정보를 저장
        saveLiveCapacity(request.getData().getLiveCapacity().getDeviceList());
    }

    /**
     * 라이브스트림 용량을 저장합니다.
     * 
     * 디바이스 리스트의 라이브스트림 용량 정보를 처리하고 저장합니다.
     * JSON 데이터를 CapacityDeviceReceiver 객체로 변환하여
     * 각 디바이스의 카메라 리스트 정보를 저장합니다.
     * 
     * @param data 라이브스트림 용량 데이터 (JSON 형태)
     */
    private void saveLiveCapacity(Object data) {
        // JSON 데이터를 CapacityDeviceReceiver 리스트로 변환
        List<CapacityDeviceReceiver> devices = objectMapper.convertValue(
                data, new TypeReference<List<CapacityDeviceReceiver>>() {});
        
        // 각 디바이스의 카메라 용량 정보를 저장
        for (CapacityDeviceReceiver capacityDeviceReceiver : devices) {
            capacityCameraService.saveCapacityCameraReceiverList(
                    capacityDeviceReceiver.getCameraList(), capacityDeviceReceiver.getSn());
        }
    }
}
