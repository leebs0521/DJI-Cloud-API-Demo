package com.dji.sdk.cloudapi.media;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

/**
 * 위치 정보 데이터 클래스
 * 
 * 이 클래스는 지리적 위치 정보를 나타내는 데이터 클래스입니다.
 * 위도(latitude)와 경도(longitude) 정보를 포함합니다.
 * 
 * 주요 구성 요소:
 * - lat: 위도 (latitude)
 * - lng: 경도 (longitude)
 * 
 * 이 클래스는 미디어 파일의 촬영 위치나 드론의 위치 정보를 저장하는 데 사용됩니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/12/7
 */
public class Position {

    /**
     * 위도 (latitude)
     * 
     * 지리적 위치의 위도 정보입니다.
     * -90.0에서 90.0 사이의 값으로 표현됩니다.
     * 양수는 북반구, 음수는 남반구를 나타냅니다.
     * 예: 22.577666000000001 (서울 근처)
     */
    @Schema(description = "latitude", example = "22.577666000000001")
    @NotNull
    private Double lat;

    /**
     * 경도 (longitude)
     * 
     * 지리적 위치의 경도 정보입니다.
     * -180.0에서 180.0 사이의 값으로 표현됩니다.
     * 양수는 동반구, 음수는 서반구를 나타냅니다.
     * 예: 113.9431940000000 (홍콩 근처)
     */
    @Schema(description = "longitude", example = "113.9431940000000")
    @NotNull
    private Double lng;

    public Position() {
    }

    @Override
    public String toString() {
        return "Position{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }

    public Double getLat() {
        return lat;
    }

    public Position setLat(Double lat) {
        this.lat = lat;
        return this;
    }

    public Double getLng() {
        return lng;
    }

    public Position setLng(Double lng) {
        this.lng = lng;
        return this;
    }
}
