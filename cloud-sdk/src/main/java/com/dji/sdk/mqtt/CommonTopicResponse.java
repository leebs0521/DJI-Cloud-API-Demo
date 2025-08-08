package com.dji.sdk.mqtt;

/**
 * 통합 토픽 응답 형식
 * 
 * 이 클래스는 MQTT 토픽 응답의 표준화된 형식을 정의합니다.
 * 제네릭 타입 T를 사용하여 다양한 데이터 타입을 지원합니다.
 * 
 * 주요 구성 요소:
 * - tid: 요청 ID (요청과 응답을 매칭하는 데 사용)
 * - bid: 비즈니스 ID (비즈니스 로직 식별)
 * - data: 응답 데이터 (제네릭 타입)
 * - timestamp: 타임스탬프 (응답 시간)
 * 
 * 이 클래스는 MQTT 통신에서 요청과 응답을
 * 일관된 방식으로 처리합니다.
 * 
 * @param <T> 응답 데이터의 타입
 * @author sean.zhou
 * @date 2021/11/15
 * @version 0.1
 */
public class CommonTopicResponse<T> {

    /**
     * 요청 ID
     * 
     * 명령이 전송되고 응답은 메시지의 tid와 bid 필드로 매칭됩니다.
     * 응답은 tid와 bid를 동일하게 유지해야 합니다.
     */
    protected String tid;

    /**
     * 비즈니스 ID
     * 
     * 비즈니스 로직을 식별하는 ID입니다.
     */
    protected String bid;

    /**
     * 응답 데이터
     * 
     * 실제 응답 데이터를 포함합니다.
     */
    protected T data;

    /**
     * 타임스탬프
     * 
     * 응답이 생성된 시간을 나타냅니다.
     */
    protected Long timestamp;

    /**
     * 문자열 표현을 반환합니다.
     * 
     * @return 문자열 표현
     */
    @Override
    public String toString() {
        return "CommonTopicResponse{" +
                "tid='" + tid + '\'' +
                ", bid='" + bid + '\'' +
                ", data=" + data +
                ", timestamp=" + timestamp +
                '}';
    }

    /**
     * 기본 생성자
     * 
     * 빈 CommonTopicResponse 인스턴스를 생성합니다.
     */
    public CommonTopicResponse() {
    }

    /**
     * 요청 ID를 반환합니다.
     * 
     * @return 요청 ID
     */
    public String getTid() {
        return tid;
    }

    /**
     * 요청 ID를 설정합니다.
     * 
     * @param tid 설정할 요청 ID
     * @return 현재 인스턴스 (메서드 체이닝을 위해)
     */
    public CommonTopicResponse<T> setTid(String tid) {
        this.tid = tid;
        return this;
    }

    /**
     * 비즈니스 ID를 반환합니다.
     * 
     * @return 비즈니스 ID
     */
    public String getBid() {
        return bid;
    }

    /**
     * 비즈니스 ID를 설정합니다.
     * 
     * @param bid 설정할 비즈니스 ID
     * @return 현재 인스턴스 (메서드 체이닝을 위해)
     */
    public CommonTopicResponse<T> setBid(String bid) {
        this.bid = bid;
        return this;
    }

    /**
     * 응답 데이터를 반환합니다.
     * 
     * @return 응답 데이터
     */
    public T getData() {
        return data;
    }

    /**
     * 응답 데이터를 설정합니다.
     * 
     * @param data 설정할 응답 데이터
     * @return 현재 인스턴스 (메서드 체이닝을 위해)
     */
    public CommonTopicResponse<T> setData(T data) {
        this.data = data;
        return this;
    }

    /**
     * 타임스탬프를 반환합니다.
     * 
     * @return 타임스탬프
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * 타임스탬프를 설정합니다.
     * 
     * @param timestamp 설정할 타임스탬프
     * @return 현재 인스턴스 (메서드 체이닝을 위해)
     */
    public CommonTopicResponse<T> setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}