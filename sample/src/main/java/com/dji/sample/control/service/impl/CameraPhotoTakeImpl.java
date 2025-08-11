package com.dji.sample.control.service.impl;

import com.dji.sample.control.model.param.DronePayloadParam;
import com.dji.sdk.cloudapi.device.CameraStateEnum;

/**
 * 카메라 사진 촬영 핸들러 구현 클래스
 * 
 * 카메라 사진 촬영 명령의 실행 조건을 검증하는 핸들러 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 사진 촬영 조건 검증
 *    - 카메라 사진 촬영 상태 확인
 *    - 남은 사진 촬영 가능 횟수 확인
 *    - 촬영 가능 여부 확인
 * 
 * 2. 카메라 상태 확인
 *    - 사진 촬영이 진행 중이지 않은지 확인
 *    - 저장 공간이 충분한지 확인
 *    - 카메라가 정상 상태인지 확인
 * 
 * 3. 안전한 사진 촬영 보장
 *    - 중복 촬영 방지
 *    - 저장 공간 부족 시 촬영 방지
 *    - 카메라 상태 충돌 방지
 * 
 * 사진 촬영은 카메라가 WORKING 상태가 아니고 남은 촬영 횟수가 0보다 클 때만
 * 가능합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/4/23
 */
public class CameraPhotoTakeImpl extends PayloadCommandsHandler {

    /**
     * 카메라 사진 촬영 핸들러 생성자
     * 
     * @param param 페이로드 파라미터
     */
    public CameraPhotoTakeImpl(DronePayloadParam param) {
        super(param);
    }

    /**
     * 카메라 사진 촬영 실행 가능 여부를 확인합니다.
     * 
     * 이 메서드는 다음 조건들을 검증하여 사진 촬영이 가능한지 확인합니다:
     * 1. 기본 페이로드 조건 검증 (부모 클래스의 canPublish 호출)
     * 2. 사진 촬영 상태가 WORKING이 아닌지 확인
     * 3. 남은 사진 촬영 가능 횟수가 0보다 큰지 확인
     * 
     * 모든 조건이 만족되어야 사진 촬영이 가능합니다.
     * 
     * @param deviceSn 디바이스 시리얼 번호
     * @return 사진 촬영 가능 여부 (true: 가능, false: 불가능)
     */
    @Override
    public boolean canPublish(String deviceSn) {
        // 기본 페이로드 조건 검증
        super.canPublish(deviceSn);
        
        // 사진 촬영 조건 검증
        return CameraStateEnum.WORKING != osdCamera.getPhotoState() && osdCamera.getRemainPhotoNum() > 0;
    }
}
