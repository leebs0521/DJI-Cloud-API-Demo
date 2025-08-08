package com.dji.sdk.mqtt;

/**
 * 프로젝트에서 사용되는 모든 토픽 상수
 * 
 * 이 클래스는 MQTT 토픽 구성에 사용되는
 * 모든 상수들을 정의합니다.
 * 
 * 주요 구성 요소:
 * - 기본 접두사: BASIC_PRE, THING_MODEL_PRE, PRODUCT
 * - 상태 관련 접미사: STATUS_SUF, STATE_SUF
 * - 서비스 관련 접미사: SERVICES_SUF, _REPLY_SUF
 * - 데이터 관련 접미사: OSD_SUF, REQUESTS_SUF, EVENTS_SUF, PROPERTY_SUF
 * - 방향 관련: UP, DOWN
 * - 특수 기능: DRC, SET_SUF
 * 
 * 이 클래스는 MQTT 토픽의 표준화된 구성을
 * 제공합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/10
 * @version 0.1
 */
public class TopicConst {

    /**
     * 기본 접두사
     * 
     * 시스템 관련 토픽의 기본 접두사입니다.
     */
    public static final String BASIC_PRE = "sys/";

    /**
     * Thing 모델 접두사
     * 
     * Thing 모델 관련 토픽의 접두사입니다.
     */
    public static final String THING_MODEL_PRE = "thing/";

    /**
     * 제품 접두사
     * 
     * 제품 관련 토픽의 접두사입니다.
     */
    public static final String PRODUCT = "product/";

    /**
     * 상태 접미사
     * 
     * 상태 관련 토픽의 접미사입니다.
     */
    public static final String STATUS_SUF = "/status";

    /**
     * 응답 접미사
     * 
     * 응답 관련 토픽의 접미사입니다.
     */
    public static final String _REPLY_SUF = "_reply";

    /**
     * 상태 접미사
     * 
     * 상태 관련 토픽의 접미사입니다.
     */
    public static final String STATE_SUF = "/state";

    /**
     * 서비스 접미사
     * 
     * 서비스 관련 토픽의 접미사입니다.
     */
    public static final String SERVICES_SUF = "/services";

    /**
     * OSD 접미사
     * 
     * OSD(On Screen Display) 관련 토픽의 접미사입니다.
     */
    public static final String OSD_SUF = "/osd";

    /**
     * 요청 접미사
     * 
     * 요청 관련 토픽의 접미사입니다.
     */
    public static final String REQUESTS_SUF = "/requests";

    /**
     * 이벤트 접미사
     * 
     * 이벤트 관련 토픽의 접미사입니다.
     */
    public static final String EVENTS_SUF = "/events";

    /**
     * 속성 접미사
     * 
     * 속성 관련 토픽의 접미사입니다.
     */
    public static final String PROPERTY_SUF = "/property";

    /**
     * 설정 접미사
     * 
     * 설정 관련 토픽의 접미사입니다.
     */
    public static final String SET_SUF = "/set";

    /**
     * 시리얼 번호 정규식
     * 
     * 시리얼 번호 패턴을 정의하는 정규식입니다.
     */
    public static final String REGEX_SN = "[A-Za-z0-9]+";

    /**
     * DRC 접미사
     * 
     * DRC(Direct Remote Control) 관련 토픽의 접미사입니다.
     */
    public static final String DRC = "/drc";

    /**
     * 업로드 접미사
     * 
     * 업로드 관련 토픽의 접미사입니다.
     */
    public static final String UP = "/up";

    /**
     * 다운로드 접미사
     * 
     * 다운로드 관련 토픽의 접미사입니다.
     */
    public static final String DOWN = "/down";
}