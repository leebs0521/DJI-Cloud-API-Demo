package com.dji.sdk.mqtt.drc;

import com.dji.sdk.cloudapi.control.*;
import com.dji.sdk.mqtt.ChannelName;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Arrays;

/**
 * DRC 업로드 메서드 열거형
 * 
 * 이 열거형은 DRC(Direct Remote Control) 업로드에서 사용되는
 * 다양한 메서드들을 정의합니다.
 * 
 * 주요 구성 요소:
 * - DRONE_CONTROL: 드론 제어
 * - DRONE_EMERGENCY_STOP: 드론 비상 정지
 * - HEART_BEAT: 하트비트
 * - HSI_INFO_PUSH: HSI 정보 푸시
 * - DELAY_INFO_PUSH: 지연 정보 푸시
 * - OSD_INFO_PUSH: OSD 정보 푸시
 * - UNKNOWN: 알 수 없는 메서드
 * 
 * 각 메서드는 해당하는 채널 이름과 클래스 타입을 포함합니다.
 * 
 * @author sean
 * @version 1.1
 * @date 2022/6/1
 */
public enum DrcUpMethodEnum {

    /**
     * 드론 제어
     * 
     * 드론 제어 명령을 처리하는 메서드입니다.
     */
    DRONE_CONTROL("drone_control", ChannelName.INBOUND_DRC_UP_DRONE_CONTROL, new TypeReference<DrcUpData<DroneControlResponse>>() {}),

    /**
     * 드론 비상 정지
     * 
     * 드론 비상 정지 명령을 처리하는 메서드입니다.
     */
    DRONE_EMERGENCY_STOP("drone_emergency_stop", ChannelName.INBOUND_DRC_UP_DRONE_EMERGENCY_STOP, new TypeReference<DrcUpData>() {}),

    /**
     * 하트비트
     * 
     * 연결 상태를 확인하는 하트비트 메시지를 처리하는 메서드입니다.
     */
    HEART_BEAT("heart_beat", ChannelName.INBOUND_DRC_UP_HEART_BEAT, new TypeReference<HeartBeatRequest>() {}),

    /**
     * HSI 정보 푸시
     * 
     * HSI(Horizontal Situation Indicator) 정보를 푸시하는 메서드입니다.
     */
    HSI_INFO_PUSH("hsi_info_push", ChannelName.INBOUND_DRC_UP_HSI_INFO_PUSH, new TypeReference<HsiInfoPush>() {}),

    /**
     * 지연 정보 푸시
     * 
     * 네트워크 지연 정보를 푸시하는 메서드입니다.
     */
    DELAY_INFO_PUSH("delay_info_push", ChannelName.INBOUND_DRC_UP_DELAY_INFO_PUSH, new TypeReference<DelayInfoPush>() {}),

    /**
     * OSD 정보 푸시
     * 
     * OSD(On Screen Display) 정보를 푸시하는 메서드입니다.
     */
    OSD_INFO_PUSH("osd_info_push", ChannelName.INBOUND_DRC_UP_OSD_INFO_PUSH, new TypeReference<OsdInfoPush>() {}),

    /**
     * 알 수 없는 메서드
     * 
     * 매칭되지 않는 메서드를 처리합니다.
     */
    UNKNOWN("", ChannelName.DEFAULT, new TypeReference<>() {});

    /**
     * 메서드 이름
     * 
     * DRC 업로드 메서드의 식별자입니다.
     */
    private final String method;

    /**
     * 채널 이름
     * 
     * 해당 메서드를 처리할 채널의 이름입니다.
     */
    private final String channelName;

    /**
     * 클래스 타입
     * 
     * 해당 메서드의 데이터 타입을 정의하는 TypeReference입니다.
     */
    private final TypeReference classType;

    /**
     * DRC 업로드 메서드 열거형 생성자
     * 
     * @param method 메서드 이름
     * @param channelName 채널 이름
     * @param classType 클래스 타입
     */
    DrcUpMethodEnum(String method, String channelName, TypeReference classType) {
        this.method = method;
        this.channelName = channelName;
        this.classType = classType;
    }

    /**
     * 메서드 이름을 반환합니다.
     * 
     * @return 메서드 이름
     */
    public String getMethod() {
        return method;
    }

    /**
     * 채널 이름을 반환합니다.
     * 
     * @return 채널 이름
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * 클래스 타입을 반환합니다.
     * 
     * @return 클래스 타입
     */
    public TypeReference getClassType() {
        return classType;
    }

    /**
     * 메서드 이름으로 DRC 업로드 메서드를 찾습니다.
     * 
     * 주어진 메서드 이름에 매칭되는 열거형을 반환합니다.
     * 매칭되는 메서드가 없으면 UNKNOWN을 반환합니다.
     * 
     * @param method 찾을 메서드 이름
     * @return 해당하는 DrcUpMethodEnum 열거형
     */
    public static DrcUpMethodEnum find(String method) {
        return Arrays.stream(DrcUpMethodEnum.values())
                .filter(methodEnum -> methodEnum.method.equals(method))
                .findAny().orElse(UNKNOWN);
    }
}
