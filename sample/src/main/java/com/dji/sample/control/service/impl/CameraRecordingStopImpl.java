package com.dji.sample.control.service.impl;

import com.dji.sample.control.model.param.DronePayloadParam;
import com.dji.sdk.cloudapi.device.CameraStateEnum;

/**
 * 카메라 영상 녹화 중지 핸들러 구현 클래스
 * 
 * 카메라 영상 녹화 중지 명령의 실행 조건을 검증하는 핸들러 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 영상 녹화 중지 조건 검증
 *    - 카메라 녹화 상태가 WORKING인지 확인
 *    - 녹화 중지 가능 여부 확인
 * 
 * 2. 카메라 상태 확인
 *    - 녹화가 실제로 진행 중인지 확인
 *    - 녹화 중지 명령의 유효성 검증
 * 
 * 3. 안전한 영상 녹화 중지 보장
 *    - 녹화 중이지 않을 때 중지 명령 방지
 *    - 불필요한 중지 명령 방지
 * 
 * 영상 녹화 중지는 녹화 상태가 WORKING일 때만 가능합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/4/23
 */
public class CameraRecordingStopImpl extends PayloadCommandsHandler {

    /**
     * 카메라 영상 녹화 중지 핸들러 생성자
     * 
     * @param param 페이로드 파라미터
     */
    public CameraRecordingStopImpl(DronePayloadParam param) {
        super(param);
    }

    /**
     * 카메라 영상 녹화 중지 실행 가능 여부를 확인합니다.
     * 
     * 이 메서드는 다음 조건들을 검증하여 영상 녹화 중지가 가능한지 확인합니다:
     * 1. 기본 페이로드 조건 검증 (부모 클래스의 canPublish 호출)
     * 2. 녹화 상태가 WORKING인지 확인
     * 
     * 녹화가 진행 중일 때만 중지가 가능합니다.
     * 
     * @param deviceSn 디바이스 시리얼 번호
     * @return 영상 녹화 중지 가능 여부 (true: 가능, false: 불가능)
     */
    @Override
    public boolean canPublish(String deviceSn) {
        // 기본 페이로드 조건 검증
        super.canPublish(deviceSn);
        
        // 영상 녹화 중지 조건 검증
        return CameraStateEnum.WORKING == osdCamera.getRecordingState();
    }
}
