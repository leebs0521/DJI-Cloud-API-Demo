package com.dji.sdk.mqtt;

/**
 * 통합 토픽 요청 형식
 * 
 * 이 클래스는 MQTT 토픽 요청의 표준화된 형식을 정의합니다.
 * 제네릭 타입 T를 사용하여 다양한 데이터 타입을 지원합니다.
 * 
 * 주요 구성 요소:
 * - tid: 요청 ID (요청과 응답을 매칭하는 데 사용)
 * - bid: 비즈니스 ID (비즈니스 로직 식별)
 * - timestamp: 타임스탬프 (요청 시간)
 * - data: 요청 데이터 (제네릭 타입)
 * 
 * 이 클래스는 MQTT 통신에서 요청과 응답을
 * 일관된 방식으로 처리합니다.
 * 
 * @param <T> 요청 데이터의 타입
 * @author sean.zhou
 * @date 2021/11/10
 * @version 0.1
 */
public class CommonTopicRequest<T> {

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
     * 타임스탬프
     * 
     * 요청이 생성된 시간을 나타냅니다.
     */
    protected Long timestamp;

    /**
     * 요청 데이터
     * 
     * 실제 요청 데이터를 포함합니다.
     */
    protected T data;

    /**
     * 기본 생성자
     * 
     * 빈 CommonTopicRequest 인스턴스를 생성합니다.
     */
    public CommonTopicRequest() {
    }

    /**
     * 문자열 표현을 반환합니다.
     * 
     * @return 문자열 표현
     */
    @Override
    public String toString() {
        return "CommonTopicRequest{" +
                "tid='" + tid + '\'' +
                ", bid='" + bid + '\'' +
                ", timestamp=" + timestamp +
                ", data=" + data +
                '}';
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
    public CommonTopicRequest<T> setTid(String tid) {
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
    public CommonTopicRequest<T> setBid(String bid) {
        this.bid = bid;
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
    public CommonTopicRequest<T> setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    /**
     * 요청 데이터를 반환합니다.
     * 
     * @return 요청 데이터
     */
    public T getData() {
        return data;
    }

    /**
     * 요청 데이터를 설정합니다.
     * 
     * @param data 설정할 요청 데이터
     * @return 현재 인스턴스 (메서드 체이닝을 위해)
     */
    public CommonTopicRequest<T> setData(T data) {
        this.data = data;
        return this;
    }
}