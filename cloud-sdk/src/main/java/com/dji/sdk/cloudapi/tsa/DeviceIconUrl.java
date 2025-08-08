package com.dji.sdk.cloudapi.tsa;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

/**
 * 디바이스 아이콘 URL 클래스
 * 
 * 이 클래스는 TSA(Threat and Situation Awareness) 시스템에서 디바이스의 아이콘 URL을 정의합니다.
 * 지도상에 표시될 디바이스 아이콘의 일반 상태와 선택 상태 URL을 관리합니다.
 * 
 * 주요 구성 요소:
 * - normalIconUrl: 일반 상태 아이콘 URL
 * - selectIconUrl: 선택 상태 아이콘 URL
 * 
 * 이 클래스는 웹에서 아이콘을 사용할 수 있으며, 앱 내부에서 이러한 아이콘을 다운로드하고
 * 캐시하여 고정 크기(28dp)로 지도에 표시합니다.
 * 
 * @author sean
 * @version 0.3
 * @date 2022/1/5
 */
@Schema(description = "디바이스 아이콘 URL. <br/>웹에서 아이콘을 사용할 수 있으며, 앱 내부에서 이러한 아이콘을 다운로드하고 캐시하여 고정 크기(28dp)로 지도에 표시합니다. 예시: http://r56978dr7.hn-bkt.clouddn.com/tsa_equipment_normal.png",
        anyOf = IconUrlEnum.class)
public class DeviceIconUrl {

    /**
     * 일반 상태 아이콘 URL
     * 
     * 디바이스가 일반 상태일 때 지도에 표시될 아이콘의 URL입니다.
     * 리소스 URL이나 웹 URL을 사용할 수 있습니다.
     * 
     * 예시: "resource://pilot/drawable/tsa_car_normal"
     */
    @JsonProperty("normal_icon_url")
    @NotNull
    @Schema(description = "일반 상태에서 표시되는 아이콘", example = "resource://pilot/drawable/tsa_car_normal")
    private String normalIconUrl;

    /**
     * 선택 상태 아이콘 URL
     * 
     * 디바이스가 선택된 상태일 때 지도에 표시될 아이콘의 URL입니다.
     * 일반 상태와 구분되는 시각적 피드백을 제공합니다.
     * 
     * 예시: "resource://pilot/drawable/tsa_car_select"
     */
    @JsonProperty("selected_icon_url")
    @NotNull
    @Schema(description = "선택 상태에서 표시되는 아이콘", example = "resource://pilot/drawable/tsa_car_select")
    private String selectIconUrl;

    public DeviceIconUrl() {
    }

    @Override
    public String toString() {
        return "DeviceIconUrl{" +
                "normalIconUrl='" + normalIconUrl + '\'' +
                ", selectIconUrl='" + selectIconUrl + '\'' +
                '}';
    }

    /**
     * 일반 상태 아이콘 URL을 반환합니다.
     * 
     * @return 일반 상태 아이콘 URL
     */
    public String getNormalIconUrl() {
        return normalIconUrl;
    }

    /**
     * 일반 상태 아이콘 URL을 설정합니다.
     * 
     * @param normalIconUrl 일반 상태 아이콘 URL
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public DeviceIconUrl setNormalIconUrl(String normalIconUrl) {
        this.normalIconUrl = normalIconUrl;
        return this;
    }

    /**
     * 선택 상태 아이콘 URL을 반환합니다.
     * 
     * @return 선택 상태 아이콘 URL
     */
    public String getSelectIconUrl() {
        return selectIconUrl;
    }

    /**
     * 선택 상태 아이콘 URL을 설정합니다.
     * 
     * @param selectIconUrl 선택 상태 아이콘 URL
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public DeviceIconUrl setSelectIconUrl(String selectIconUrl) {
        this.selectIconUrl = selectIconUrl;
        return this;
    }
}
