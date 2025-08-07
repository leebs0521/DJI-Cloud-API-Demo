package com.dji.sdk.cloudapi.device;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

/**
 * 디바이스 OSD 호스트 클래스
 * 
 * 이 클래스는 디바이스의 OSD(On-Screen Display) 호스트 정보를 관리합니다.
 * 위치, 고도, 속도, 방향 등의 디바이스 상태 정보를 포함합니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/12/8
 */
public class DeviceOsdHost {

    /**
     * 디바이스 위도
     */
    @Schema(description = "device latitude")
    @NotNull
    private Float latitude;

    /**
     * 디바이스 경도
     */
    @Schema(description = "device longitude")
    @NotNull
    private Float longitude;

    /**
     * 디바이스 타원체 고도
     */
    @Schema(description = "device ellipsoid height")
    @NotNull
    private Float height;

    /**
     * 디바이스 헤딩 각도
     */
    @Schema(description = "device head facing angle")
    @NotNull
    @JsonProperty("attitude_head")
    private Float attitudeHead;

    /**
     * 이륙 지점 대비 상대 고도
     */
    @Schema(description = "height relative to the takeoff point")
    @NotNull
    private Float elevation;

    /**
     * 수평 속도
     */
    @Schema(description = "horizontal speed")
    @NotNull
    @JsonProperty("horizontal_speed")
    private Float horizontalSpeed;

    /**
     * 수직 속도
     */
    @Schema(description = "vertical speed")
    @NotNull
    @JsonProperty("vertical_speed")
    private Float verticalSpeed;

    /**
     * 기본 생성자
     */
    public DeviceOsdHost() {
    }

    @Override
    public String toString() {
        return "DeviceOsdHost{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", height=" + height +
                ", attitudeHead=" + attitudeHead +
                ", elevation=" + elevation +
                ", horizontalSpeed=" + horizontalSpeed +
                ", verticalSpeed=" + verticalSpeed +
                '}';
    }

    /**
     * 디바이스 위도를 반환합니다.
     * 
     * @return 디바이스 위도
     */
    public Float getLatitude() {
        return latitude;
    }

    /**
     * 디바이스 위도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param latitude 설정할 디바이스 위도
     * @return 현재 DeviceOsdHost 객체
     */
    public DeviceOsdHost setLatitude(Float latitude) {
        this.latitude = latitude;
        return this;
    }

    /**
     * 디바이스 경도를 반환합니다.
     * 
     * @return 디바이스 경도
     */
    public Float getLongitude() {
        return longitude;
    }

    /**
     * 디바이스 경도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param longitude 설정할 디바이스 경도
     * @return 현재 DeviceOsdHost 객체
     */
    public DeviceOsdHost setLongitude(Float longitude) {
        this.longitude = longitude;
        return this;
    }

    /**
     * 디바이스 타원체 고도를 반환합니다.
     * 
     * @return 디바이스 타원체 고도
     */
    public Float getHeight() {
        return height;
    }

    /**
     * 디바이스 타원체 고도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param height 설정할 디바이스 타원체 고도
     * @return 현재 DeviceOsdHost 객체
     */
    public DeviceOsdHost setHeight(Float height) {
        this.height = height;
        return this;
    }

    /**
     * 디바이스 헤딩 각도를 반환합니다.
     * 
     * @return 디바이스 헤딩 각도
     */
    public Float getAttitudeHead() {
        return attitudeHead;
    }

    /**
     * 디바이스 헤딩 각도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param attitudeHead 설정할 디바이스 헤딩 각도
     * @return 현재 DeviceOsdHost 객체
     */
    public DeviceOsdHost setAttitudeHead(Float attitudeHead) {
        this.attitudeHead = attitudeHead;
        return this;
    }

    /**
     * 이륙 지점 대비 상대 고도를 반환합니다.
     * 
     * @return 이륙 지점 대비 상대 고도
     */
    public Float getElevation() {
        return elevation;
    }

    /**
     * 이륙 지점 대비 상대 고도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param elevation 설정할 이륙 지점 대비 상대 고도
     * @return 현재 DeviceOsdHost 객체
     */
    public DeviceOsdHost setElevation(Float elevation) {
        this.elevation = elevation;
        return this;
    }

    /**
     * 수평 속도를 반환합니다.
     * 
     * @return 수평 속도
     */
    public Float getHorizontalSpeed() {
        return horizontalSpeed;
    }

    /**
     * 수평 속도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param horizontalSpeed 설정할 수평 속도
     * @return 현재 DeviceOsdHost 객체
     */
    public DeviceOsdHost setHorizontalSpeed(Float horizontalSpeed) {
        this.horizontalSpeed = horizontalSpeed;
        return this;
    }

    /**
     * 수직 속도를 반환합니다.
     * 
     * @return 수직 속도
     */
    public Float getVerticalSpeed() {
        return verticalSpeed;
    }

    /**
     * 수직 속도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param verticalSpeed 설정할 수직 속도
     * @return 현재 DeviceOsdHost 객체
     */
    public DeviceOsdHost setVerticalSpeed(Float verticalSpeed) {
        this.verticalSpeed = verticalSpeed;
        return this;
    }
}
