package com.dji.sdk.cloudapi.control;

import com.dji.sdk.common.BaseModel;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * POI 모드 진입 요청 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 Point of Interest(POI) 모드로 진입하기 위한 요청을 정의합니다.
 * 관심 지점의 위도, 경도, 고도 정보를 포함하여 POI 모드를 시작합니다.
 * 
 * @author sean
 * @version 1.9
 * @date 2023/12/12
 */
public class PoiModeEnterRequest extends BaseModel {

    /**
     * 관심 지점 위도 (필수)
     * -90 ~ 90 범위, 남위는 음수, 북위는 양수
     */
    @Min(-90)
    @Max(90)
    @NotNull
    private Float latitude;

    /**
     * 관심 지점 경도 (필수)
     * -180 ~ 180 범위, 서경은 음수, 동경은 양수
     */
    @NotNull
    @Min(-180)
    @Max(180)
    private Float longitude;

    /**
     * 관심 지점 고도 (필수)
     * 2 ~ 10000 범위, 관심 지점의 고도 (미터)
     */
    @NotNull
    @Min(2)
    @Max(10000)
    private Float height;

    /**
     * 기본 생성자
     */
    public PoiModeEnterRequest() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "PoiModeEnterRequest{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", height=" + height +
                '}';
    }

    /**
     * 관심 지점 위도를 반환합니다.
     * 
     * @return 관심 지점 위도
     */
    public Float getLatitude() {
        return latitude;
    }

    /**
     * 관심 지점 위도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param latitude 설정할 관심 지점 위도
     * @return 현재 PoiModeEnterRequest 객체
     */
    public PoiModeEnterRequest setLatitude(Float latitude) {
        this.latitude = latitude;
        return this;
    }

    /**
     * 관심 지점 경도를 반환합니다.
     * 
     * @return 관심 지점 경도
     */
    public Float getLongitude() {
        return longitude;
    }

    /**
     * 관심 지점 경도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param longitude 설정할 관심 지점 경도
     * @return 현재 PoiModeEnterRequest 객체
     */
    public PoiModeEnterRequest setLongitude(Float longitude) {
        this.longitude = longitude;
        return this;
    }

    /**
     * 관심 지점 고도를 반환합니다.
     * 
     * @return 관심 지점 고도 (미터)
     */
    public Float getHeight() {
        return height;
    }

    /**
     * 관심 지점 고도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param height 설정할 관심 지점 고도 (미터)
     * @return 현재 PoiModeEnterRequest 객체
     */
    public PoiModeEnterRequest setHeight(Float height) {
        this.height = height;
        return this;
    }
}
