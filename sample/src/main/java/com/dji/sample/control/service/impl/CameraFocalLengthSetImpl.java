package com.dji.sample.control.service.impl;

import com.dji.sample.control.model.param.DronePayloadParam;
import com.dji.sdk.cloudapi.control.CameraTypeEnum;
import com.dji.sdk.cloudapi.device.CameraStateEnum;

import java.util.Objects;

/**
 * 카메라 초점 거리 설정 핸들러 구현 클래스
 * 
 * 카메라 초점 거리(줌) 설정 명령의 실행 조건을 검증하는 핸들러 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 카메라 초점 거리 설정 파라미터 검증
 *    - 카메라 타입 파라미터 검증 (ZOOM 또는 IR)
 *    - 줌 팩터 파라미터 검증
 *    - 지원되는 카메라 타입 확인
 * 
 * 2. 카메라 상태 확인
 *    - 사진 촬영 상태 확인 (WORKING 상태가 아닌지)
 *    - 현재 줌 팩터와 목표 줌 팩터 비교
 *    - 카메라 타입별 줌 팩터 검증
 * 
 * 3. 안전한 초점 거리 설정 보장
 *    - 사진 촬영 중 줌 변경 방지
 *    - 불필요한 줌 변경 방지 (동일 값인 경우)
 *    - 지원되지 않는 카메라 타입에서 줌 변경 방지
 * 
 * 초점 거리 설정은 ZOOM 또는 IR 카메라에서만 가능하며,
 * 사진 촬영 중이 아니고 현재 줌 팩터와 다른 값일 때만 가능합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/4/23
 */
public class CameraFocalLengthSetImpl extends PayloadCommandsHandler {

    /**
     * 카메라 초점 거리 설정 핸들러 생성자
     * 
     * @param param 페이로드 파라미터 (카메라 타입, 줌 팩터 포함)
     */
    public CameraFocalLengthSetImpl(DronePayloadParam param) {
        super(param);
    }

    /**
     * 카메라 초점 거리 설정 파라미터의 유효성을 검사합니다.
     * 
     * 이 메서드는 카메라 초점 거리 설정 명령에 필요한 파라미터가
     * 올바르게 설정되어 있는지 확인합니다:
     * - 카메라 타입이 null이 아닌지 확인
     * - 줌 팩터가 null이 아닌지 확인
     * - 카메라 타입이 ZOOM 또는 IR인지 확인
     * 
     * @return 파라미터 유효성 여부 (모든 조건을 만족하는 경우 true)
     */
    @Override
    public boolean valid() {
        return Objects.nonNull(param.getCameraType()) && Objects.nonNull(param.getZoomFactor())
                && (CameraTypeEnum.ZOOM == param.getCameraType()
                || CameraTypeEnum.IR == param.getCameraType());
    }

    /**
     * 카메라 초점 거리 설정 실행 가능 여부를 확인합니다.
     * 
     * 이 메서드는 다음 조건들을 검증하여 초점 거리 설정이 가능한지 확인합니다:
     * 1. 기본 페이로드 조건 검증 (부모 클래스의 canPublish 호출)
     * 2. 사진 촬영 상태가 WORKING이 아닌지 확인
     * 3. 카메라 타입별 줌 팩터 검증:
     *    - IR 카메라: IR 줌 팩터가 설정되어 있고 현재 값과 다른지 확인
     *    - ZOOM 카메라: 현재 줌 팩터와 다른지 확인
     * 
     * @param deviceSn 디바이스 시리얼 번호
     * @return 초점 거리 설정 가능 여부 (true: 가능, false: 불가능)
     */
    @Override
    public boolean canPublish(String deviceSn) {
        // 기본 페이로드 조건 검증
        super.canPublish(deviceSn);
        
        // 사진 촬영 중인지 확인
        if (CameraStateEnum.WORKING == osdCamera.getPhotoState()) {
            return false;
        }
        
        // 카메라 타입별 줌 팩터 검증
        switch (param.getCameraType()) {
            case IR:
                return Objects.nonNull(osdCamera.getIrZoomFactor())
                        && param.getZoomFactor().intValue() != osdCamera.getIrZoomFactor();
            case ZOOM:
                return param.getZoomFactor().intValue() != osdCamera.getZoomFactor();
        }
        return false;
    }
}
