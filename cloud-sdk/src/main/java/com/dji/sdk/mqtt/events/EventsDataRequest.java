package com.dji.sdk.mqtt.events;

/**
 * MQTT 이벤트 데이터 요청 클래스
 * 이벤트 요청에 대한 응답 데이터를 담는 제네릭 클래스
 * 
 * @author sean
 * @version 1.1
 * @date 2022/6/9
 */
public class EventsDataRequest<T> {

    /** 이벤트 처리 결과 코드 */
    private EventsErrorCode result;

    /** 이벤트 응답 데이터 */
    private T output;

    /**
     * 기본 생성자
     */
    public EventsDataRequest() {
    }

    @Override
    public String toString() {
        return "EventsDataRequest{" +
                "result=" + result +
                ", output=" + output +
                '}';
    }

    /**
     * 이벤트 처리 결과 코드를 반환합니다.
     * @return 이벤트 처리 결과 코드
     */
    public EventsErrorCode getResult() {
        return result;
    }

    /**
     * 이벤트 처리 결과 코드를 설정합니다.
     * @param result 이벤트 처리 결과 코드
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public EventsDataRequest<T> setResult(EventsErrorCode result) {
        this.result = result;
        return this;
    }

    /**
     * 이벤트 응답 데이터를 반환합니다.
     * @return 이벤트 응답 데이터
     */
    public T getOutput() {
        return output;
    }

    /**
     * 이벤트 응답 데이터를 설정합니다.
     * @param output 이벤트 응답 데이터
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public EventsDataRequest<T> setOutput(T output) {
        this.output = output;
        return this;
    }
}
