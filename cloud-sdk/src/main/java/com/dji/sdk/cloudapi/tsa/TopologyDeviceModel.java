package com.dji.sdk.cloudapi.tsa;

import com.dji.sdk.cloudapi.device.DeviceDomainEnum;
import com.dji.sdk.cloudapi.device.DeviceEnum;
import com.dji.sdk.cloudapi.device.DeviceSubTypeEnum;
import com.dji.sdk.cloudapi.device.DeviceTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

/**
 * 토폴로지 디바이스 모델 클래스
 * 
 * 이 클래스는 TSA(Threat and Situation Awareness) 시스템에서 디바이스의 모델 정보를 정의합니다.
 * 디바이스의 도메인, 타입, 서브타입 등의 분류 정보를 포함하여
 * 디바이스를 체계적으로 분류하고 관리합니다.
 * 
 * 주요 구성 요소:
 * - deviceModelKey: 디바이스 모델 키
 * - domain: 디바이스 도메인
 * - type: 디바이스 타입
 * - subType: 디바이스 서브타입
 * 
 * 이 클래스는 TSA 시스템에서 디바이스를 분류하고 식별하는 데 사용됩니다.
 * 디바이스의 특성에 따라 적절한 아이콘과 처리 로직을 적용할 수 있습니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/12/8
 */
@Schema(description = "토폴로지 디바이스 모델")
public class TopologyDeviceModel {

    /**
     * 디바이스 모델 키
     * 
     * 디바이스의 모델을 고유하게 식별하는 키입니다.
     * DeviceEnum을 사용하여 다양한 디바이스 모델을 구분합니다.
     */
    @NotNull
    @JsonProperty("device_model_key")
    private DeviceEnum deviceModelKey;

    /**
     * 디바이스 도메인
     * 
     * 디바이스가 속한 도메인을 나타냅니다.
     * DeviceDomainEnum을 사용하여 디바이스의 사용 영역을 분류합니다.
     */
    @NotNull
    private DeviceDomainEnum domain;

    /**
     * 디바이스 타입
     * 
     * 디바이스의 주요 타입을 나타냅니다.
     * DeviceTypeEnum을 사용하여 디바이스의 기본 분류를 정의합니다.
     */
    @NotNull
    private DeviceTypeEnum type;

    /**
     * 디바이스 서브타입
     * 
     * 디바이스의 세부 서브타입을 나타냅니다.
     * DeviceSubTypeEnum을 사용하여 디바이스의 세부 분류를 정의합니다.
     */
    @NotNull
    @JsonProperty("sub_type")
    private DeviceSubTypeEnum subType;

    public TopologyDeviceModel() {
    }

    @Override
    public String toString() {
        return "TopologyDeviceModel{" +
                "deviceModelKey=" + deviceModelKey +
                ", domain=" + domain +
                ", type=" + type +
                ", subType=" + subType +
                '}';
    }

    /**
     * 디바이스 모델 키를 반환합니다.
     * 
     * @return 디바이스 모델 키
     */
    public DeviceEnum getDeviceModelKey() {
        return deviceModelKey;
    }

    /**
     * 디바이스 모델 키를 설정합니다.
     * 
     * @param deviceModelKey 디바이스 모델 키
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public TopologyDeviceModel setDeviceModelKey(DeviceEnum deviceModelKey) {
        this.deviceModelKey = deviceModelKey;
        return this;
    }

    /**
     * 디바이스 도메인을 반환합니다.
     * 
     * @return 디바이스 도메인
     */
    public DeviceDomainEnum getDomain() {
        return domain;
    }

    /**
     * 디바이스 도메인을 설정합니다.
     * 
     * @param domain 디바이스 도메인
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public TopologyDeviceModel setDomain(DeviceDomainEnum domain) {
        this.domain = domain;
        return this;
    }

    /**
     * 디바이스 타입을 반환합니다.
     * 
     * @return 디바이스 타입
     */
    public DeviceTypeEnum getType() {
        return type;
    }

    /**
     * 디바이스 타입을 설정합니다.
     * 
     * @param type 디바이스 타입
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public TopologyDeviceModel setType(DeviceTypeEnum type) {
        this.type = type;
        return this;
    }

    /**
     * 디바이스 서브타입을 반환합니다.
     * 
     * @return 디바이스 서브타입
     */
    public DeviceSubTypeEnum getSubType() {
        return subType;
    }

    /**
     * 디바이스 서브타입을 설정합니다.
     * 
     * @param subType 디바이스 서브타입
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public TopologyDeviceModel setSubType(DeviceSubTypeEnum subType) {
        this.subType = subType;
        return this;
    }
}
