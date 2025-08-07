package com.dji.sdk.cloudapi.control;

import com.dji.sdk.cloudapi.device.CameraModeEnum;

/**
 * 사진 촬영 진행 상황 확장 정보 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 사진 촬영 진행 상황의 확장 정보를 담는 클래스입니다.
 * 카메라 모드 정보를 포함하여 사진 촬영의 추가 정보를 관리합니다.
 * 
 * @author sean
 * @version 1.9
 * @date 2023/12/12
 */
public class PhotoTakeProgressExt {

    /**
     * 카메라 모드
     * 사진 촬영 시 사용된 카메라의 모드
     */
    private CameraModeEnum cameraMode;

    /**
     * 기본 생성자
     */
    public PhotoTakeProgressExt() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "PhotoTakeProgressExt{" +
                "cameraMode=" + cameraMode +
                '}';
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
     * @return 현재 PhotoTakeProgressExt 객체
     */
    public PhotoTakeProgressExt setCameraMode(CameraModeEnum cameraMode) {
        this.cameraMode = cameraMode;
        return this;
    }
}
