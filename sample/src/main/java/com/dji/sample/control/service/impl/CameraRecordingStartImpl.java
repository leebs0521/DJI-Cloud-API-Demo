package com.dji.sample.control.service.impl;

import com.dji.sample.control.model.param.DronePayloadParam;
import com.dji.sdk.cloudapi.device.CameraModeEnum;
import com.dji.sdk.cloudapi.device.CameraStateEnum;

/**
 * 카메라 영상 녹화 시작 핸들러 구현 클래스
 * 
 * 카메라 영상 녹화 시작 명령의 실행 조건을 검증하는 핸들러 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 영상 녹화 시작 조건 검증
 *    - 카메라 모드가 VIDEO 모드인지 확인
 *    - 녹화 상태가 IDLE인지 확인
 *    - 남은 녹화 시간이 충분한지 확인
 * 
 * 2. 카메라 상태 확인
 *    - 카메라가 영상 모드로 설정되어 있는지 확인
 *    - 녹화가 진행 중이지 않은지 확인
 *    - 저장 공간이 충분한지 확인
 * 
 * 3. 안전한 영상 녹화 보장
 *    - 사진 모드에서 녹화 시작 방지
 *    - 중복 녹화 방지
 *    - 저장 공간 부족 시 녹화 방지
 * 
 * 영상 녹화 시작은 카메라가 VIDEO 모드이고 녹화 상태가 IDLE이며
 * 남은 녹화 시간이 0보다 클 때만 가능합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/4/23
 */
public class CameraRecordingStartImpl extends PayloadCommandsHandler {

    /**
     * 카메라 영상 녹화 시작 핸들러 생성자
     * 
     * @param param 페이로드 파라미터
     */
    public CameraRecordingStartImpl(DronePayloadParam param) {
        super(param);
    }

    /**
     * 카메라 영상 녹화 시작 실행 가능 여부를 확인합니다.
     * 
     * 이 메서드는 다음 조건들을 검증하여 영상 녹화 시작이 가능한지 확인합니다:
     * 1. 기본 페이로드 조건 검증 (부모 클래스의 canPublish 호출)
     * 2. 카메라 모드가 VIDEO인지 확인
     * 3. 녹화 상태가 IDLE인지 확인
     * 4. 남은 녹화 시간이 0보다 큰지 확인
     * 
     * 모든 조건이 만족되어야 영상 녹화 시작이 가능합니다.
     * 
     * @param deviceSn 디바이스 시리얼 번호
     * @return 영상 녹화 시작 가능 여부 (true: 가능, false: 불가능)
     */
    @Override
    public boolean canPublish(String deviceSn) {
        // 기본 페이로드 조건 검증
        super.canPublish(deviceSn);
        
        // 영상 녹화 시작 조건 검증
        return CameraModeEnum.VIDEO == osdCamera.getCameraMode()
                && CameraStateEnum.IDLE == osdCamera.getRecordingState()
                && osdCamera.getRemainRecordDuration() > 0;
    }
}
