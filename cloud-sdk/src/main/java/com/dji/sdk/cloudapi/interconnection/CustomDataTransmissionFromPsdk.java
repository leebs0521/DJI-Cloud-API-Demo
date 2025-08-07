package com.dji.sdk.cloudapi.interconnection;

/**
 * PSDK에서 클라우드로의 커스텀 데이터 전송 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 PSDK(Payload SDK)에서 클라우드로 전송되는
 * 커스텀 데이터를 담는 클래스입니다. PSDK 애플리케이션에서 클라우드로
 * 전송하는 사용자 정의 데이터의 내용을 포함합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/16
 */
public class CustomDataTransmissionFromPsdk {

    /**
     * 데이터 내용
     * PSDK에서 전송되는 커스텀 데이터의 내용
     * 길이: 256자 미만
     */
    private String value;

    /**
     * 기본 생성자
     */
    public CustomDataTransmissionFromPsdk() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "CustomDataTransmissionFromPsdk{" +
                "value='" + value + '\'' +
                '}';
    }

    /**
     * 데이터 내용을 반환합니다.
     * 
     * @return 데이터 내용
     */
    public String getValue() {
        return value;
    }

    /**
     * 데이터 내용을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param value 설정할 데이터 내용
     * @return 현재 CustomDataTransmissionFromPsdk 객체
     */
    public CustomDataTransmissionFromPsdk setValue(String value) {
        this.value = value;
        return this;
    }
}
