package com.dji.sdk.websocket;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * WebSocket 메시지 응답 형식 클래스
 * 
 * 이 클래스는 Pilot 클라이언트가 받을 수 있는 WebSocket 메시지의 표준 형식을 정의합니다.
 * 제네릭 타입 T를 사용하여 다양한 데이터 타입을 지원하며, 비즈니스 코드, 버전, 타임스탬프, 데이터를 포함합니다.
 * 
 * @param <T> 메시지 데이터의 타입
 * @author sean.zhou
 * @date 2021/11/17
 * @version 0.1
 */
@Schema(description = "The format of WebSocket messages that the pilot can receive.")
public class WebSocketMessageResponse<T> {

    /** WebSocket 메시지 식별자 (비즈니스 코드) */
    @JsonProperty("biz_code")
    @NotNull
    @Schema(description = "webSocket messages identity", implementation = BizCodeEnum.class)
    private String bizCode;

    /** WebSocket 메시지 버전 */
    @Schema(description = "webSocket messages version")
    private String version = "1.0";

    /** 타임스탬프 (밀리초) */
    @NotNull
    @Min(123456789012L)
    @Schema(description = "timestamp (milliseconds)")
    private Long timestamp;

    /** 비즈니스 기능에 해당하는 데이터 */
    @NotNull
    @Schema(description = "Data corresponding to business functions")
    private T data;

    /**
     * 기본 생성자
     */
    public WebSocketMessageResponse() {
    }

    @Override
    public String toString() {
        return "WebSocketMessageResponse{" +
                "bizCode=" + bizCode +
                ", version='" + version + '\'' +
                ", timestamp=" + timestamp +
                ", data=" + data +
                '}';
    }

    /**
     * 비즈니스 코드를 반환합니다.
     * 
     * @return 비즈니스 코드
     */
    public String getBizCode() {
        return bizCode;
    }

    /**
     * 비즈니스 코드를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param bizCode 설정할 비즈니스 코드
     * @return 현재 WebSocketMessageResponse 객체
     */
    public WebSocketMessageResponse<T> setBizCode(String bizCode) {
        this.bizCode = bizCode;
        return this;
    }

    /**
     * 버전을 반환합니다.
     * 
     * @return 버전 문자열
     */
    public String getVersion() {
        return version;
    }

    /**
     * 버전을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param version 설정할 버전
     * @return 현재 WebSocketMessageResponse 객체
     */
    public WebSocketMessageResponse<T> setVersion(String version) {
        this.version = version;
        return this;
    }

    /**
     * 타임스탬프를 반환합니다.
     * 
     * @return 타임스탬프 (밀리초)
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * 타임스탬프를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param timestamp 설정할 타임스탬프 (밀리초)
     * @return 현재 WebSocketMessageResponse 객체
     */
    public WebSocketMessageResponse<T> setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    /**
     * 데이터를 반환합니다.
     * 
     * @return 메시지 데이터
     */
    public T getData() {
        return data;
    }

    /**
     * 데이터를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param data 설정할 데이터
     * @return 현재 WebSocketMessageResponse 객체
     */
    public WebSocketMessageResponse<T> setData(T data) {
        this.data = data;
        return this;
    }
}