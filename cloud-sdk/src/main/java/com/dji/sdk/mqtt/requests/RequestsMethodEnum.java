package com.dji.sdk.mqtt.requests;

import com.dji.sdk.cloudapi.config.RequestsConfigRequest;
import com.dji.sdk.cloudapi.flightarea.FlightAreasGetRequest;
import com.dji.sdk.cloudapi.map.OfflineMapGetRequest;
import com.dji.sdk.cloudapi.media.StorageConfigGet;
import com.dji.sdk.cloudapi.organization.AirportBindStatusRequest;
import com.dji.sdk.cloudapi.organization.AirportOrganizationBindRequest;
import com.dji.sdk.cloudapi.organization.AirportOrganizationGetRequest;
import com.dji.sdk.cloudapi.wayline.FlighttaskResourceGetRequest;
import com.dji.sdk.mqtt.ChannelName;

import java.util.Arrays;

/**
 * MQTT 요청 메서드 열거형
 * 다양한 요청 타입과 해당하는 채널명, 클래스 타입을 정의
 * 
 * @author sean
 * @version 1.0
 * @date 2022/5/25
 */
public enum RequestsMethodEnum {

    /** 스토리지 설정 조회 */
    STORAGE_CONFIG_GET("storage_config_get", ChannelName.INBOUND_REQUESTS_STORAGE_CONFIG_GET, StorageConfigGet.class),

    /** 공항 바인딩 상태 */
    AIRPORT_BIND_STATUS("airport_bind_status", ChannelName.INBOUND_REQUESTS_AIRPORT_BIND_STATUS, AirportBindStatusRequest.class),

    /** 공항 조직 바인딩 */
    AIRPORT_ORGANIZATION_BIND("airport_organization_bind", ChannelName.INBOUND_REQUESTS_AIRPORT_ORGANIZATION_BIND, AirportOrganizationBindRequest.class),

    /** 공항 조직 조회 */
    AIRPORT_ORGANIZATION_GET("airport_organization_get", ChannelName.INBOUND_REQUESTS_AIRPORT_ORGANIZATION_GET, AirportOrganizationGetRequest.class),

    /** 비행 작업 리소스 조회 */
    FLIGHT_TASK_RESOURCE_GET("flighttask_resource_get", ChannelName.INBOUND_REQUESTS_FLIGHTTASK_RESOURCE_GET, FlighttaskResourceGetRequest.class),

    /** 설정 */
    CONFIG("config", ChannelName.INBOUND_REQUESTS_CONFIG, RequestsConfigRequest.class),

    /** 비행 구역 조회 */
    FLIGHT_AREAS_GET("flight_areas_get", ChannelName.INBOUND_REQUESTS_FLIGHT_AREAS_GET, FlightAreasGetRequest.class),

    /** 오프라인 맵 조회 */
    OFFLINE_MAP_GET("offline_map_get", ChannelName.INBOUND_REQUESTS_OFFLINE_MAP_GET, OfflineMapGetRequest.class),

    /** 알 수 없는 메서드 */
    UNKNOWN("", ChannelName.DEFAULT, Object.class);

    /** 요청 메서드명 */
    private final String method;

    /** 해당 요청의 채널명 */
    private final String channelName;

    /** 해당 요청의 클래스 타입 */
    private final Class classType;

    /**
     * RequestsMethodEnum 생성자
     * 
     * @param method 요청 메서드명
     * @param channelName 채널명
     * @param classType 클래스 타입
     */
    RequestsMethodEnum(String method, String channelName, Class classType) {
        this.method = method;
        this.channelName = channelName;
        this.classType = classType;
    }

    /**
     * 요청 메서드명을 반환합니다.
     * @return 요청 메서드명
     */
    public String getMethod() {
        return method;
    }

    /**
     * 채널명을 반환합니다.
     * @return 채널명
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * 클래스 타입을 반환합니다.
     * @return 클래스 타입
     */
    public Class getClassType() {
        return classType;
    }

    /**
     * 메서드명으로 해당하는 RequestsMethodEnum을 찾습니다.
     * 
     * @param method 요청 메서드명
     * @return 해당하는 RequestsMethodEnum, 없으면 UNKNOWN
     */
    public static RequestsMethodEnum find(String method) {
        return Arrays.stream(RequestsMethodEnum.values())
                .filter(methodEnum -> methodEnum.method.equals(method))
                .findAny().orElse(UNKNOWN);
    }
}
