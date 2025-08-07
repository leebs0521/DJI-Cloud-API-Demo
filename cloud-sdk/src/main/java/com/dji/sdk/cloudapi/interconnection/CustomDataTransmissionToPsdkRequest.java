package com.dji.sdk.cloudapi.interconnection;

import com.dji.sdk.common.BaseModel;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 클라우드에서 PSDK로의 커스텀 데이터 전송 요청 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 클라우드에서 PSDK(Payload SDK)로 전송되는
 * 커스텀 데이터 요청을 담는 클래스입니다. 클라우드에서 PSDK 애플리케이션으로
 * 전송하는 사용자 정의 데이터의 요청 정보를 포함합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/16
 */
public class CustomDataTransmissionToPsdkRequest extends BaseModel {

    /**
     * 데이터 내용 (필수)
     * 클라우드에서 PSDK로 전송할 커스텀 데이터의 내용
     * 길이: 256자 미만
     */
    @NotNull
    @Length(max = 256)
    private String value;

    /**
     * 기본 생성자
     */
    public CustomDataTransmissionToPsdkRequest() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "CustomDataTransmissionToPsdkRequest{" +
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
     * @return 현재 CustomDataTransmissionToPsdkRequest 객체
     */
    public CustomDataTransmissionToPsdkRequest setValue(String value) {
        this.value = value;
        return this;
    }
}
