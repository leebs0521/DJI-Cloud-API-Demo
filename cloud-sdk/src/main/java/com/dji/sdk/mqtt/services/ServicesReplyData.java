package com.dji.sdk.mqtt.services;

/**
 * MQTT 서비스 응답 데이터 클래스
 * 서비스 요청에 대한 응답 데이터를 담는 제네릭 클래스
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/22
 */
public class ServicesReplyData<T> {

    /** 서비스 처리 결과 코드 */
    private ServicesErrorCode result;

    /** 서비스 응답 데이터 */
    private T output;

    /**
     * 기본 생성자
     */
    public ServicesReplyData() {
    }

    @Override
    public String toString() {
        return "DrcUpData{" +
                "result=" + result +
                ", output=" + output +
                '}';
    }

    /**
     * 서비스 처리 결과 코드를 반환합니다.
     * @return 서비스 처리 결과 코드
     */
    public ServicesErrorCode getResult() {
        return result;
    }

    /**
     * 서비스 처리 결과 코드를 설정합니다.
     * @param result 서비스 처리 결과 코드
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public ServicesReplyData<T> setResult(ServicesErrorCode result) {
        this.result = result;
        return this;
    }

    /**
     * 서비스 응답 데이터를 반환합니다.
     * @return 서비스 응답 데이터
     */
    public T getOutput() {
        return output;
    }

    /**
     * 서비스 응답 데이터를 설정합니다.
     * @param output 서비스 응답 데이터
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public ServicesReplyData<T> setOutput(T output) {
        this.output = output;
        return this;
    }
}