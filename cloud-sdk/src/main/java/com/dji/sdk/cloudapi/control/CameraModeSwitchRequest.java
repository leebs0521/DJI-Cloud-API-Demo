package com.dji.sdk.cloudapi.control;

import com.dji.sdk.cloudapi.device.CameraModeEnum;
import com.dji.sdk.cloudapi.device.PayloadIndex;
import com.dji.sdk.common.BaseModel;

import javax.validation.constraints.NotNull;

/**
 * 카메라 모드 전환 요청 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 카메라의 촬영 모드를 전환하기 위한 요청을 정의합니다.
 * 특정 페이로드 인덱스와 카메라 모드를 지정하여 카메라 모드를 전환합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/1
 */
public class CameraModeSwitchRequest extends BaseModel {

    /**
     * 페이로드 인덱스 (필수)
     * 모드를 전환할 카메라의 페이로드 인덱스
     */
    @NotNull
    private PayloadIndex payloadIndex;

    /**
     * 카메라 모드 (필수)
     * 전환할 카메라의 촬영 모드
     */
    @NotNull
    private CameraModeEnum cameraMode;

    /**
     * 기본 생성자
     */
    public CameraModeSwitchRequest() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "CameraModeSwitchRequest{" +
                "payloadIndex=" + payloadIndex +
                ", cameraMode=" + cameraMode +
                '}';
    }

    /**
     * 페이로드 인덱스를 반환합니다.
     * 
     * @return 페이로드 인덱스
     */
    public PayloadIndex getPayloadIndex() {
        return payloadIndex;
    }

    /**
     * 페이로드 인덱스를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param payloadIndex 설정할 페이로드 인덱스
     * @return 현재 CameraModeSwitchRequest 객체
     */
    public CameraModeSwitchRequest setPayloadIndex(PayloadIndex payloadIndex) {
        this.payloadIndex = payloadIndex;
        return this;
    }

    /**
     * 카메라 모드를 반환합니다.
     * 
     * @return 카메라 모드
     */
    public CameraModeEnum getCameraMode() {
        return cameraMode;
    }

    /**
     * 카메라 모드를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param cameraMode 설정할 카메라 모드
     * @return 현재 CameraModeSwitchRequest 객체
     */
    public CameraModeSwitchRequest setCameraMode(CameraModeEnum cameraMode) {
        this.cameraMode = cameraMode;
        return this;
    }
}
