package com.dji.sdk.cloudapi.hms;

import com.dji.sdk.cloudapi.device.DeviceDomainEnum;
import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * HMS FAQ ID 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 HMS(Health Management System) FAQ의 ID를 정의합니다.
 * 도크 팁과 FPV 팁 두 가지 타입의 FAQ를 지원하며, 각각 도크와 드론 도메인에 속합니다.
 * 
 * @author sean
 * @version 1.1
 * @date 2022/7/7
 */
public enum HmsFaqIdEnum {

    /**
     * 도크 팁
     * 도크 관련 HMS FAQ
     */
    DOCK_TIP("dock_tip_", DeviceDomainEnum.DOCK),

    /**
     * FPV 팁
     * FPV(First Person View) 관련 HMS FAQ
     */
    FPV_TIP("fpv_tip_", DeviceDomainEnum.DRONE);

    /**
     * FAQ 텍스트
     * 각 FAQ에 대한 고유한 텍스트 식별자
     */
    private final String text;

    /**
     * 도메인
     * FAQ가 속한 디바이스 도메인 (도크 또는 드론)
     */
    private final DeviceDomainEnum domain;

    /**
     * HMS FAQ ID 열거형 생성자
     * 
     * @param text FAQ 텍스트
     * @param domain 도메인
     */
    HmsFaqIdEnum(String text, DeviceDomainEnum domain) {
        this.text = text;
        this.domain = domain;
    }

    /**
     * FAQ 텍스트를 반환합니다.
     * JSON 직렬화 시 사용됩니다.
     * 
     * @return FAQ 텍스트
     */
    @JsonValue
    public String getText() {
        return text;
    }

    /**
     * 도메인을 반환합니다.
     * 
     * @return 도메인
     */
    public DeviceDomainEnum getDomain() {
        return domain;
    }

    /**
     * 도메인으로 HMS FAQ ID를 찾습니다.
     * 
     * @param domain 찾을 도메인
     * @return 찾은 HMS FAQ ID 열거형
     * @throws CloudSDKException 해당하는 도메인을 찾을 수 없는 경우
     */
    public static HmsFaqIdEnum find(DeviceDomainEnum domain) {
        return Arrays.stream(values()).filter(faqIdEnum -> faqIdEnum.domain == domain).findAny()
                .orElseThrow(() -> new CloudSDKException(HmsFaqIdEnum.class, domain));
    }
}

