package com.dji.sdk.mqtt.drc;

import com.dji.sdk.cloudapi.wayline.WaylineErrorCodeEnum;

/**
 * DRC 업로드 데이터 클래스
 * 
 * 이 클래스는 DRC(Direct Remote Control) 업로드 데이터의
 * 표준화된 형식을 정의합니다.
 * 
 * 주요 구성 요소:
 * - result: 결과 코드 (WaylineErrorCodeEnum)
 * - output: 출력 데이터 (제네릭 타입)
 * 
 * 이 클래스는 DRC 통신에서 업로드 데이터를
 * 일관된 방식으로 처리합니다.
 * 
 * @param <T> 출력 데이터의 타입
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/22
 */
public class DrcUpData<T> {

    /**
     * 결과 코드
     * 
     * DRC 업로드 작업의 결과를 나타내는 코드입니다.
     */
    private WaylineErrorCodeEnum result;

    /**
     * 출력 데이터
     * 
     * DRC 업로드 작업의 결과 데이터입니다.
     */
    private T output;

    /**
     * 기본 생성자
     * 
     * 빈 DrcUpData 인스턴스를 생성합니다.
     */
    public DrcUpData() {
    }

    /**
     * 문자열 표현을 반환합니다.
     * 
     * @return 문자열 표현
     */
    @Override
    public String toString() {
        return "DrcUpData{" +
                "result=" + result +
                ", output=" + output +
                '}';
    }

    /**
     * 결과 코드를 반환합니다.
     * 
     * @return 결과 코드
     */
    public WaylineErrorCodeEnum getResult() {
        return result;
    }

    /**
     * 결과 코드를 설정합니다.
     * 
     * @param result 설정할 결과 코드
     * @return 현재 인스턴스 (메서드 체이닝을 위해)
     */
    public DrcUpData<T> setResult(WaylineErrorCodeEnum result) {
        this.result = result;
        return this;
    }

    /**
     * 출력 데이터를 반환합니다.
     * 
     * @return 출력 데이터
     */
    public T getOutput() {
        return output;
    }

    /**
     * 출력 데이터를 설정합니다.
     * 
     * @param output 설정할 출력 데이터
     * @return 현재 인스턴스 (메서드 체이닝을 위해)
     */
    public DrcUpData<T> setOutput(T output) {
        this.output = output;
        return this;
    }
}