package com.dji.sdk.cloudapi.livestream;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 라이브스트림 URL 타입을 정의하는 열거형
 * 다양한 스트리밍 프로토콜을 지원합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/22
 */
public enum UrlTypeEnum {

    /** Agora 스트리밍 서비스 */
    AGORA(0),

    /** RTMP (Real-Time Messaging Protocol) 스트리밍 */
    RTMP(1),

    /** RTSP (Real-Time Streaming Protocol) 스트리밍 */
    RTSP(2),

    /** GB28181 표준 스트리밍 프로토콜 */
    GB28181(3),

    /** WHIP (WebRTC-HTTP Ingestion Protocol) 스트리밍 */
    WHIP(4),
    ;

    /** URL 타입을 나타내는 정수 값 */
    private final int type;

    /**
     * URL 타입 열거형 생성자
     * @param type URL 타입을 나타내는 정수 값
     */
    UrlTypeEnum(int type) {
        this.type = type;
    }

    /**
     * URL 타입 값을 반환합니다.
     * JSON 직렬화 시 사용됩니다.
     * 
     * @return URL 타입 정수 값
     */
    @JsonValue
    public int getType() {
        return type;
    }

    /**
     * 정수 값으로 URL 타입을 찾습니다.
     * JSON 역직렬화 시 사용됩니다.
     * 
     * @param type 찾을 URL 타입의 정수 값
     * @return 해당하는 UrlTypeEnum 인스턴스
     * @throws CloudSDKException 지정된 타입이 존재하지 않을 경우
     */
    @JsonCreator
    public static UrlTypeEnum find(int type) {
        return Arrays.stream(values()).filter(typeEnum -> typeEnum.type == type).findAny()
                .orElseThrow(() -> new CloudSDKException(UrlTypeEnum.class, type));
    }
}
