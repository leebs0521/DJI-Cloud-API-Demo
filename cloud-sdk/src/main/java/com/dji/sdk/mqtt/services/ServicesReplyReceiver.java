package com.dji.sdk.mqtt.services;

/**
 * MQTT 서비스 응답 수신자 클래스
 * 서비스 응답 데이터를 받아서 처리하는 제네릭 클래스
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/22
 */
public class ServicesReplyReceiver<T> {

    /** 서비스 처리 결과 코드 */
    private ServicesErrorCode result;

    /** 서비스 정보 데이터 */
    private T info;

    /** 서비스 출력 데이터 */
    private T output;

    /** 서비스 파일 데이터 */
    private T files;

    /**
     * 기본 생성자
     */
    public ServicesReplyReceiver() {
    }

    @Override
    public String toString() {
        return "ServicesReplyReceiver{" +
                "result=" + result +
                ", info=" + info +
                ", output=" + output +
                ", files=" + files +
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
    public ServicesReplyReceiver<T> setResult(ServicesErrorCode result) {
        this.result = result;
        return this;
    }

    /**
     * 서비스 정보 데이터를 반환합니다.
     * @return 서비스 정보 데이터
     */
    public T getInfo() {
        return info;
    }

    /**
     * 서비스 정보 데이터를 설정합니다.
     * @param info 서비스 정보 데이터
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public ServicesReplyReceiver<T> setInfo(T info) {
        this.info = info;
        return this;
    }

    /**
     * 서비스 출력 데이터를 반환합니다.
     * @return 서비스 출력 데이터
     */
    public T getOutput() {
        return output;
    }

    /**
     * 서비스 출력 데이터를 설정합니다.
     * @param output 서비스 출력 데이터
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public ServicesReplyReceiver<T> setOutput(T output) {
        this.output = output;
        return this;
    }

    /**
     * 서비스 파일 데이터를 반환합니다.
     * @return 서비스 파일 데이터
     */
    public T getFiles() {
        return files;
    }

    /**
     * 서비스 파일 데이터를 설정합니다.
     * @param files 서비스 파일 데이터
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public ServicesReplyReceiver<T> setFiles(T files) {
        this.files = files;
        return this;
    }
}