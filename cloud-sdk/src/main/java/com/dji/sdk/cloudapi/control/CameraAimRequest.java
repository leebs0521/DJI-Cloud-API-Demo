package com.dji.sdk.cloudapi.control;

import com.dji.sdk.cloudapi.device.PayloadIndex;
import com.dji.sdk.common.BaseModel;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 카메라 조준 요청 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 카메라를 특정 지점에 조준하기 위한 요청을 정의합니다.
 * 페이로드 인덱스, 카메라 타입, 잠금 상태, 조준 좌표를 포함하여 카메라를 조준합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/1
 */
public class CameraAimRequest extends BaseModel {

    /**
     * 페이로드 인덱스 (필수)
     * 조준할 카메라의 페이로드 인덱스
     */
    @NotNull
    private PayloadIndex payloadIndex;

    /**
     * 카메라 타입 (필수)
     * 조준할 카메라의 타입
     */
    @NotNull
    private CameraTypeEnum cameraType;

    /**
     * 잠금 상태 (필수)
     * true: 짐벌을 잠그고, 짐벌과 드론이 함께 회전
     * false: 짐벌만 회전하고, 드론은 회전하지 않음
     */
    @NotNull
    private Boolean locked;

    /**
     * 조준 X 좌표 (선택)
     * 0 ~ 1 범위, 좌상단 모서리를 중심점으로 하는 상대 좌표
     */
    @Min(0)
    @Max(1)
    private Float x;

    /**
     * 조준 Y 좌표 (선택)
     * 0 ~ 1 범위, 좌상단 모서리를 중심점으로 하는 상대 좌표
     */
    @Min(0)
    @Max(1)
    private Float y;

    /**
     * 기본 생성자
     */
    public CameraAimRequest() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "CameraAimRequest{" +
                "payloadIndex=" + payloadIndex +
                ", cameraType=" + cameraType +
                ", locked=" + locked +
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
     * @return 현재 CameraAimRequest 객체
     */
    public CameraAimRequest setPayloadIndex(PayloadIndex payloadIndex) {
        this.payloadIndex = payloadIndex;
        return this;
    }

    /**
     * 카메라 타입을 반환합니다.
     * 
     * @return 카메라 타입
     */
    public CameraTypeEnum getCameraType() {
        return cameraType;
    }

    /**
     * 카메라 타입을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param cameraType 설정할 카메라 타입
     * @return 현재 CameraAimRequest 객체
     */
    public CameraAimRequest setCameraType(CameraTypeEnum cameraType) {
        this.cameraType = cameraType;
        return this;
    }

    /**
     * 잠금 상태를 반환합니다.
     * 
     * @return 잠금 상태
     */
    public Boolean getLocked() {
        return locked;
    }

    /**
     * 잠금 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param locked 설정할 잠금 상태
     * @return 현재 CameraAimRequest 객체
     */
    public CameraAimRequest setLocked(Boolean locked) {
        this.locked = locked;
        return this;
    }

    /**
     * 조준 X 좌표를 반환합니다.
     * 
     * @return 조준 X 좌표
     */
    public Float getX() {
        return x;
    }

    /**
     * 조준 X 좌표를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param x 설정할 조준 X 좌표
     * @return 현재 CameraAimRequest 객체
     */
    public CameraAimRequest setX(Float x) {
        this.x = x;
        return this;
    }

    /**
     * 조준 Y 좌표를 반환합니다.
     * 
     * @return 조준 Y 좌표
     */
    public Float getY() {
        return y;
    }

    /**
     * 조준 Y 좌표를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param y 설정할 조준 Y 좌표
     * @return 현재 CameraAimRequest 객체
     */
    public CameraAimRequest setY(Float y) {
        this.y = y;
        return this;
    }
}
