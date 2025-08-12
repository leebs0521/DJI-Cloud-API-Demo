package com.dji.sdk.cloudapi.control;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 좌표점 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 3차원 좌표점을 나타냅니다.
 * 위도, 경도, 고도 정보를 포함하며, 드론의 비행 경로나 목적지를 정의할 때 사용됩니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2023/2/14
 */
@Schema(description = "3차원 좌표점")
public class Point {

    @Schema(description = "위도 (-90 ~ 90, 남위는 음수, 북위는 양수)")
    @Min(-90)
    @Max(90)
    @NotNull
    private Float latitude;

    @Schema(description = "경도 (-180 ~ 180, 서경은 음수, 동경은 양수)")
    @NotNull
    @Min(-180)
    @Max(180)
    private Float longitude;

    @Schema(description = "고도 (WGS84 기준, 2 ~ 10000 미터)")
    @NotNull
    @Min(2)
    @Max(10000)
    private Float height;

    /**
     * 기본 생성자
     */
    public Point() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "Point{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", height=" + height +
                '}';
    }

    /**
     * 위도를 반환합니다.
     * 
     * @return 위도
     */
    public Float getLatitude() {
        return latitude;
    }

    /**
     * 위도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param latitude 설정할 위도
     * @return 현재 Point 객체
     */
    public Point setLatitude(Float latitude) {
        this.latitude = latitude;
        return this;
    }

    /**
     * 경도를 반환합니다.
     * 
     * @return 경도
     */
    public Float getLongitude() {
        return longitude;
    }

    /**
     * 경도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param longitude 설정할 경도
     * @return 현재 Point 객체
     */
    public Point setLongitude(Float longitude) {
        this.longitude = longitude;
        return this;
    }

    /**
     * 고도를 반환합니다.
     * 
     * @return 고도 (미터)
     */
    public Float getHeight() {
        return height;
    }

    /**
     * 고도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param height 설정할 고도 (미터)
     * @return 현재 Point 객체
     */
    public Point setHeight(Float height) {
        this.height = height;
        return this;
    }
}
