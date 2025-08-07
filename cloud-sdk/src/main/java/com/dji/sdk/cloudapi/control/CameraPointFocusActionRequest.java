package com.dji.sdk.cloudapi.control;

import com.dji.sdk.cloudapi.device.PayloadIndex;
import com.dji.sdk.common.BaseModel;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 카메라 포인트 초점 액션 요청 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 카메라의 특정 지점 초점 기능을 실행하기 위한 요청을 정의합니다.
 * 페이로드 인덱스, 카메라 타입, 초점 좌표를 포함하여 카메라의 포인트 초점 기능을 실행합니다.
 * 
 * @author sean
 * @version 1.9
 * @date 2023/12/12
 */
public class CameraPointFocusActionRequest extends BaseModel {

    /**
     * 페이로드 인덱스 (필수)
     * 포인트 초점 기능을 실행할 카메라의 페이로드 인덱스
     * 비공식 디바이스 모드 키로, 형식은 "타입-서브타입-짐벌인덱스"입니다.
     * 지원 제품은 [Product Supported](https://developer.dji.com/doc/cloud-api-tutorial/en/overview/product-support.html)를 참조하세요.
     */
    @NotNull
    private PayloadIndex payloadIndex;

    /**
     * 카메라 타입 (필수)
     * 포인트 초점 기능을 실행할 카메라의 타입
     */
    @NotNull
    private ExposureCameraTypeEnum cameraType;

    /**
     * 초점 X 좌표 (필수)
     * 0 ~ 1 범위, 렌즈의 좌상단 모서리를 좌표 중심점으로 하며, 수평 방향이 X축
     */
    @NotNull
    @Min(0)
    @Max(1)
    private Float x;

    /**
     * 초점 Y 좌표 (필수)
     * 0 ~ 1 범위, 렌즈의 좌상단 모서리를 좌표 중심점으로 하며, 수직 방향이 Y축
     */
    @NotNull
    @Min(0)
    @Max(1)
    private Float y;

    /**
     * 기본 생성자
     */
    public CameraPointFocusActionRequest() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "CameraPointFocusActionRequest{" +
                "payloadIndex=" + payloadIndex +
                ", cameraType=" + cameraType +
                ", x=" + x +
                ", y=" + y +
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
     * @return 현재 CameraPointFocusActionRequest 객체
     */
    public CameraPointFocusActionRequest setPayloadIndex(PayloadIndex payloadIndex) {
        this.payloadIndex = payloadIndex;
        return this;
    }

    /**
     * 카메라 타입을 반환합니다.
     * 
     * @return 카메라 타입
     */
    public ExposureCameraTypeEnum getCameraType() {
        return cameraType;
    }

    /**
     * 카메라 타입을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param cameraType 설정할 카메라 타입
     * @return 현재 CameraPointFocusActionRequest 객체
     */
    public CameraPointFocusActionRequest setCameraType(ExposureCameraTypeEnum cameraType) {
        this.cameraType = cameraType;
        return this;
    }

    /**
     * 초점 X 좌표를 반환합니다.
     * 
     * @return 초점 X 좌표
     */
    public Float getX() {
        return x;
    }

    /**
     * 초점 X 좌표를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param x 설정할 초점 X 좌표
     * @return 현재 CameraPointFocusActionRequest 객체
     */
    public CameraPointFocusActionRequest setX(Float x) {
        this.x = x;
        return this;
    }

    /**
     * 초점 Y 좌표를 반환합니다.
     * 
     * @return 초점 Y 좌표
     */
    public Float getY() {
        return y;
    }

    /**
     * 초점 Y 좌표를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param y 설정할 초점 Y 좌표
     * @return 현재 CameraPointFocusActionRequest 객체
     */
    public CameraPointFocusActionRequest setY(Float y) {
        this.y = y;
        return this;
    }
}
