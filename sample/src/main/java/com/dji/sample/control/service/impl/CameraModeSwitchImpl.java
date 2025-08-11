package com.dji.sample.control.service.impl;

import com.dji.sample.control.model.param.DronePayloadParam;
import com.dji.sdk.cloudapi.device.CameraStateEnum;

import java.util.Objects;

/**
 * 카메라 모드 전환 핸들러 구현 클래스
 * 
 * 카메라 모드 전환 명령의 실행 조건을 검증하는 핸들러 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 카메라 모드 전환 조건 검증
 *    - 카메라 모드 파라미터 유효성 검사
 *    - 현재 카메라 상태 확인
 *    - 모드 전환 가능 여부 확인
 * 
 * 2. 카메라 상태 확인
 *    - 사진 촬영 상태 확인 (IDLE 상태여야 함)
 *    - 영상 녹화 상태 확인 (IDLE 상태여야 함)
 *    - 현재 카메라 모드와 목표 모드 비교
 * 
 * 3. 안전한 모드 전환 보장
 *    - 작업 중인 상태에서 모드 전환 방지
 *    - 불필요한 모드 전환 방지 (동일 모드인 경우)
 *    - 카메라 상태 충돌 방지
 * 
 * 카메라 모드 전환은 사진 촬영이나 영상 녹화가 진행 중이지 않을 때만
 * 가능하며, 현재 모드와 다른 모드로만 전환할 수 있습니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/4/23
 */
public class CameraModeSwitchImpl extends PayloadCommandsHandler {

    /**
     * 카메라 모드 전환 핸들러 생성자
     * 
     * @param param 페이로드 파라미터 (카메라 모드 정보 포함)
     */
    public CameraModeSwitchImpl(DronePayloadParam param) {
        super(param);
    }

    /**
     * 카메라 모드 전환 파라미터의 유효성을 검사합니다.
     * 
     * 이 메서드는 카메라 모드 전환 명령에 필요한 파라미터가
     * 올바르게 설정되어 있는지 확인합니다.
     * 
     * @return 파라미터 유효성 여부 (카메라 모드가 null이 아닌 경우 true)
     */
    @Override
    public boolean valid() {
        return Objects.nonNull(param.getCameraMode());
    }

    /**
     * 카메라 모드 전환 실행 가능 여부를 확인합니다.
     * 
     * 이 메서드는 다음 조건들을 검증하여 카메라 모드 전환이 가능한지 확인합니다:
     * 1. 기본 페이로드 조건 검증 (부모 클래스의 canPublish 호출)
     * 2. 현재 카메라 모드와 목표 모드가 다른지 확인
     * 3. 사진 촬영 상태가 IDLE인지 확인
     * 4. 영상 녹화 상태가 IDLE인지 확인
     * 
     * 모든 조건이 만족되어야 카메라 모드 전환이 가능합니다.
     * 
     * @param deviceSn 디바이스 시리얼 번호
     * @return 카메라 모드 전환 가능 여부 (true: 가능, false: 불가능)
     */
    @Override
    public boolean canPublish(String deviceSn) {
        // 기본 페이로드 조건 검증
        super.canPublish(deviceSn);
        
        // 카메라 모드 전환 조건 검증
        return param.getCameraMode() != osdCamera.getCameraMode()
                && CameraStateEnum.IDLE == osdCamera.getPhotoState()
                && CameraStateEnum.IDLE == osdCamera.getRecordingState();
    }
}
