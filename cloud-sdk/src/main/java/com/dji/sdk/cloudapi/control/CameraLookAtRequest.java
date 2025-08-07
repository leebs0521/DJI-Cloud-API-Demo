package com.dji.sdk.cloudapi.control;

import com.dji.sdk.cloudapi.device.PayloadIndex;
import com.dji.sdk.common.BaseModel;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 카메라 특정 지점 바라보기 요청 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 카메라가 특정 지점을 바라보도록 설정하기 위한 요청을 정의합니다.
 * 페이로드 인덱스, 잠금 상태, 목표 지점의 좌표를 포함하여 카메라의 방향을 설정합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/12
 */
public class CameraLookAtRequest extends BaseModel {

    /**
     * 페이로드 인덱스 (필수)
     * 바라볼 카메라의 페이로드 인덱스
     * 비공식 디바이스 모드 키로, 형식은 "타입-서브타입-짐벌인덱스"입니다.
     * 지원 제품은 [Product Supported](https://developer.dji.com/doc/cloud-api-tutorial/en/overview/product-support.html)를 참조하세요.
     */
    @NotNull
    private PayloadIndex payloadIndex;

    /**
     * 잠금 상태 (필수)
     * 드론 헤드와 짐벌의 상대적 위치가 잠겨있는지 여부
     */
    @NotNull
    private Boolean locked;

    /**
     * 목표 지점 위도 (필수)
     * -90 ~ 90 범위, 남위는 음수, 북위는 양수
     * 각도 값으로, 소수점 6자리까지 정확합니다.
     */
    @Min(-90)
    @Max(90)
    @NotNull
    private Float latitude;

    /**
     * 목표 지점 경도 (필수)
     * -180 ~ 180 범위, 서경은 음수, 동경은 양수
     * 각도 값으로, 소수점 6자리까지 정확합니다.
     */
    @NotNull
    @Min(-180)
    @Max(180)
    private Float longitude;

    /**
     * 타원체 고도 (필수)
     * 2 ~ 10000 범위, 목표 지점의 고도 (미터)
     */
    @NotNull
    @Min(2)
    @Max(10000)
    private Float height;

    /**
     * 기본 생성자
     */
    public CameraLookAtRequest() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "CameraLookAtRequest{" +
                "payloadIndex=" + payloadIndex +
                ", locked=" + locked +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", height=" + height +
                '}';
    }

    /**
     * 페이로드 인덱스를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param payloadIndex 설정할 페이로드 인덱스
     * @return 현재 CameraLookAtRequest 객체
     */
    public CameraLookAtRequest setPayloadIndex(PayloadIndex payloadIndex) {
        this.payloadIndex = payloadIndex;
        return this;
    }

    /**
     * 잠금 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param locked 설정할 잠금 상태
     * @return 현재 CameraLookAtRequest 객체
     */
    public CameraLookAtRequest setLocked(Boolean locked) {
        this.locked = locked;
        return this;
    }

    /**
     * 목표 지점 위도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param latitude 설정할 목표 지점 위도
     * @return 현재 CameraLookAtRequest 객체
     */
    public CameraLookAtRequest setLatitude(Float latitude) {
        this.latitude = latitude;
        return this;
    }

    /**
     * 목표 지점 경도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param longitude 설정할 목표 지점 경도
     * @return 현재 CameraLookAtRequest 객체
     */
    public CameraLookAtRequest setLongitude(Float longitude) {
        this.longitude = longitude;
        return this;
    }

    /**
     * 타원체 고도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param height 설정할 타원체 고도 (미터)
     * @return 현재 CameraLookAtRequest 객체
     */
    public CameraLookAtRequest setHeight(Float height) {
        this.height = height;
        return this;
    }
}
