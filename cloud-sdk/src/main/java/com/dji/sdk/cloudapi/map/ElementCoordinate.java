package com.dji.sdk.cloudapi.map;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

/**
 * 지도 요소 좌표 클래스
 * 
 * 이 클래스는 지도 요소의 좌표 정보를 정의합니다.
 * WGS84 좌표계를 사용하여 지구상의 위치를 정확하게 표현합니다.
 * 
 * 주요 구성 요소:
 * - longitude: 경도 (동경/서경, -180 ~ +180)
 * - latitude: 위도 (북위/남위, -90 ~ +90)
 * - altitude: 고도 (선택사항, 미터 단위)
 * 
 * 이 클래스는 지도에서 요소의 정확한 위치를 결정하는 데 사용됩니다.
 * 드론 비행 경로, 착륙 지점, 금지 구역 등의 위치 정보를 담습니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/11/30
 */
@Schema(description = "The coordinates of the element, the coordinate system is WGS84")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ElementCoordinate {

    /**
     * 경도 (Longitude)
     * 지구상의 동서 위치를 나타냅니다.
     * -180도(서경)에서 +180도(동경) 사이의 값입니다.
     * 예: 서울의 경도는 약 127.0도 (동경)
     */
    @Schema(description = "longitude")
    @NotNull
    private Double longitude;

    /**
     * 위도 (Latitude)
     * 지구상의 남북 위치를 나타냅니다.
     * -90도(남위)에서 +90도(북위) 사이의 값입니다.
     * 예: 서울의 위도는 약 37.5도 (북위)
     */
    @Schema(description = "latitude")
    @NotNull
    private Double latitude;

    /**
     * 고도 (Altitude)
     * 해수면 기준의 높이를 미터 단위로 나타냅니다.
     * 선택사항이며, null일 수 있습니다.
     * 드론 비행 시 고도 정보가 중요한 경우 사용됩니다.
     */
    @Schema(description = "altitude")
    private Double altitude;

    public ElementCoordinate() {
    }

    @Override
    public String toString() {
        return "ElementCoordinate{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                ", altitude=" + altitude +
                '}';
    }

    public Double getLongitude() {
        return longitude;
    }

    public ElementCoordinate setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public Double getLatitude() {
        return latitude;
    }

    public ElementCoordinate setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public Double getAltitude() {
        return altitude;
    }

    public ElementCoordinate setAltitude(Double altitude) {
        this.altitude = altitude;
        return this;
    }
}
