package com.dji.sdk.mqtt;

import java.util.Arrays;
import java.util.regex.Pattern;

import static com.dji.sdk.mqtt.TopicConst.*;

/**
 * Cloud API 토픽 열거형
 * 
 * 이 열거형은 Cloud API에서 사용되는 다양한 MQTT 토픽 패턴을 정의합니다.
 * 각 토픽은 정규식 패턴과 해당하는 채널 이름을 포함합니다.
 * 
 * 주요 구성 요소:
 * - STATUS: 상태 관련 토픽
 * - STATE: 상태 관련 토픽
 * - SERVICE_REPLY: 서비스 응답 토픽
 * - OSD: OSD 관련 토픽
 * - REQUESTS: 요청 관련 토픽
 * - EVENTS: 이벤트 관련 토픽
 * - PROPERTY_SET_REPLY: 속성 설정 응답 토픽
 * - DRC_UP: DRC 업로드 토픽
 * - UNKNOWN: 알 수 없는 토픽
 * 
 * 이 열거형은 MQTT 토픽 라우팅을 위한
 * 패턴 매칭 시스템을 제공합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/10/28
 */
public enum CloudApiTopicEnum {

    /**
     * 상태 토픽
     * 
     * 디바이스 상태 정보를 포함하는 토픽입니다.
     */
    STATUS(Pattern.compile("^" + BASIC_PRE + PRODUCT + REGEX_SN + STATUS_SUF + "$"), ChannelName.INBOUND_STATUS),

    /**
     * 상태 토픽
     * 
     * Thing 모델 상태 정보를 포함하는 토픽입니다.
     */
    STATE(Pattern.compile("^" + THING_MODEL_PRE + PRODUCT + REGEX_SN + STATE_SUF + "$"), ChannelName.INBOUND_STATE),

    /**
     * 서비스 응답 토픽
     * 
     * 서비스 요청에 대한 응답을 포함하는 토픽입니다.
     */
    SERVICE_REPLY(Pattern.compile("^" + THING_MODEL_PRE + PRODUCT + REGEX_SN + SERVICES_SUF + _REPLY_SUF + "$"), ChannelName.INBOUND_SERVICES_REPLY),

    /**
     * OSD 토픽
     * 
     * On Screen Display 정보를 포함하는 토픽입니다.
     */
    OSD(Pattern.compile("^" + THING_MODEL_PRE + PRODUCT + REGEX_SN + OSD_SUF + "$"), ChannelName.INBOUND_OSD),

    /**
     * 요청 토픽
     * 
     * 디바이스 요청 정보를 포함하는 토픽입니다.
     */
    REQUESTS(Pattern.compile("^" + THING_MODEL_PRE + PRODUCT + REGEX_SN + REQUESTS_SUF + "$"), ChannelName.INBOUND_REQUESTS),

    /**
     * 이벤트 토픽
     * 
     * 디바이스 이벤트 정보를 포함하는 토픽입니다.
     */
    EVENTS(Pattern.compile("^" + THING_MODEL_PRE + PRODUCT + REGEX_SN + EVENTS_SUF + "$"), ChannelName.INBOUND_EVENTS),

    /**
     * 속성 설정 응답 토픽
     * 
     * 속성 설정 요청에 대한 응답을 포함하는 토픽입니다.
     */
    PROPERTY_SET_REPLY(Pattern.compile("^" + THING_MODEL_PRE + PRODUCT + REGEX_SN + PROPERTY_SUF + SET_SUF + _REPLY_SUF + "$"), ChannelName.INBOUND_PROPERTY_SET_REPLY),

    /**
     * DRC 업로드 토픽
     * 
     * Direct Remote Control 업로드 정보를 포함하는 토픽입니다.
     */
    DRC_UP(Pattern.compile("^" + THING_MODEL_PRE + PRODUCT + REGEX_SN + DRC + UP + "$"), ChannelName.INBOUND_DRC_UP),

    /**
     * 알 수 없는 토픽
     * 
     * 매칭되지 않는 모든 토픽을 처리합니다.
     */
    UNKNOWN(Pattern.compile("^.*$"), ChannelName.DEFAULT);

    /**
     * 토픽 패턴
     * 
     * 각 토픽을 식별하는 정규식 패턴입니다.
     */
    private final Pattern pattern;

    /**
     * 빈 이름
     * 
     * 해당 토픽을 처리할 빈의 이름입니다.
     */
    private final String beanName;

    /**
     * Cloud API 토픽 열거형 생성자
     * 
     * @param pattern 토픽 패턴
     * @param beanName 빈 이름
     */
    CloudApiTopicEnum(Pattern pattern, String beanName) {
        this.pattern = pattern;
        this.beanName = beanName;
    }

    /**
     * 토픽 패턴을 반환합니다.
     * 
     * @return 토픽 패턴
     */
    public Pattern getPattern() {
        return pattern;
    }

    /**
     * 빈 이름을 반환합니다.
     * 
     * @return 빈 이름
     */
    public String getBeanName() {
        return beanName;
    }

    /**
     * 토픽 문자열로 Cloud API 토픽을 찾습니다.
     * 
     * 주어진 토픽 문자열에 매칭되는 열거형을 반환합니다.
     * 매칭되는 토픽이 없으면 UNKNOWN을 반환합니다.
     * 
     * @param topic 찾을 토픽 문자열
     * @return 해당하는 CloudApiTopicEnum 열거형
     */
    public static CloudApiTopicEnum find(String topic) {
        return Arrays.stream(CloudApiTopicEnum.values()).filter(topicEnum -> topicEnum.pattern.matcher(topic).matches()).findAny().orElse(UNKNOWN);
    }
}
