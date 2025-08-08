package com.dji.sdk.mqtt;

import com.dji.sdk.common.IErrorInfo;

/**
 * MQTT 응답 클래스
 * 
 * 이 클래스는 MQTT 통신에서 사용되는 응답 형식을 정의합니다.
 * 제네릭 타입 T를 사용하여 다양한 응답 데이터 타입을 지원합니다.
 * 
 * 주요 구성 요소:
 * - result: 응답 결과 코드 (성공: 0, 오류: -1)
 * - output: 응답 데이터 (제네릭 타입)
 * 
 * 이 클래스는 MQTT 통신에서 성공과 오류 응답을
 * 일관된 방식으로 처리합니다.
 * 
 * @param <T> 응답 데이터의 타입
 * @author sean
 * @version 1.1
 * @date 2022/6/13
 */
public class MqttReply<T> {

    /**
     * 오류 코드
     * 
     * 응답이 오류를 나타낼 때 사용되는 코드입니다.
     */
    public static final int CODE_ERROR = -1;

    /**
     * 성공 코드
     * 
     * 응답이 성공을 나타낼 때 사용되는 코드입니다.
     */
    public static final int CODE_SUCCESS = 0;

    /**
     * 응답 결과 코드
     * 
     * 응답의 성공 또는 오류를 나타내는 코드입니다.
     */
    private Integer result;

    /**
     * 응답 데이터
     * 
     * 실제 응답 데이터를 포함합니다.
     */
    private T output;

    /**
     * 기본 생성자
     * 
     * 빈 MqttReply 인스턴스를 생성합니다.
     */
    private MqttReply() {
    }

    /**
     * 문자열 표현을 반환합니다.
     * 
     * @return 문자열 표현
     */
    @Override
    public String toString() {
        return "MqttReply{" +
                "result=" + result +
                ", output=" + output +
                '}';
    }

    /**
     * 데이터를 포함한 생성자
     * 
     * @param output 응답 데이터
     */
    private MqttReply(T output) {
        this.output = output;
    }

    /**
     * 결과 코드와 데이터를 포함한 생성자
     * 
     * @param result 응답 결과 코드
     * @param output 응답 데이터
     */
    private MqttReply(Integer result, T output) {
        this.result = result;
        this.output = output;
    }

    /**
     * 오류 응답을 생성합니다.
     * 
     * @param errorInfo 오류 정보
     * @return 오류 응답
     */
    public static MqttReply error(IErrorInfo errorInfo) {
        return new MqttReply<String>(errorInfo.getCode(), errorInfo.getMessage());
    }

    /**
     * 오류 응답을 생성합니다.
     * 
     * @param message 오류 메시지
     * @return 오류 응답
     */
    public static MqttReply error(String message) {
        return new MqttReply<String>(CODE_ERROR, message);
    }

    /**
     * 성공 응답을 생성합니다.
     * 
     * @param data 응답 데이터
     * @return 성공 응답
     */
    public static <T> MqttReply<T> success(T data) {
        return new MqttReply<T>(CODE_SUCCESS, data);
    }

    /**
     * 성공 응답을 생성합니다.
     * 
     * @return 성공 응답 (데이터 없음)
     */
    public static MqttReply success() {
        return new MqttReply().setResult(CODE_SUCCESS);
    }

    /**
     * 응답 결과 코드를 반환합니다.
     * 
     * @return 응답 결과 코드
     */
    public Integer getResult() {
        return result;
    }

    /**
     * 응답 결과 코드를 설정합니다.
     * 
     * @param result 설정할 응답 결과 코드
     * @return 현재 인스턴스 (메서드 체이닝을 위해)
     */
    public MqttReply<T> setResult(Integer result) {
        this.result = result;
        return this;
    }

    /**
     * 응답 데이터를 설정합니다.
     * 
     * @param output 설정할 응답 데이터
     * @return 현재 인스턴스 (메서드 체이닝을 위해)
     */
    public MqttReply<T> setOutput(T output) {
        this.output = output;
        return this;
    }

    /**
     * 응답 데이터를 반환합니다.
     * 
     * @return 응답 데이터
     */
    public T getOutput() {
        return output;
    }
}
