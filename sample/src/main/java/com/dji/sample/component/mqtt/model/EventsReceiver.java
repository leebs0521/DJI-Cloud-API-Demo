package com.dji.sample.component.mqtt.model;

import com.dji.sdk.mqtt.events.EventsDataRequest;
import com.dji.sdk.mqtt.events.EventsErrorCode;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

/**
 * MQTT 이벤트 수신자 클래스
 * 이벤트 데이터 요청을 확장하여 비즈니스 ID와 시리얼 번호를 추가로 관리
 * 
 * @author sean
 * @version 1.1
 * @date 2022/6/9
 */
@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventsReceiver<T> extends EventsDataRequest<T> {

    /** 비즈니스 ID */
    private String bid;

    /** 디바이스 시리얼 번호 */
    private String sn;

    /**
     * 이벤트 처리 결과 코드를 반환합니다.
     * @return 이벤트 처리 결과 코드
     */
    @Override
    public EventsErrorCode getResult() {
        return super.getResult();
    }

    /**
     * 이벤트 처리 결과 코드를 설정합니다.
     * @param result 이벤트 처리 결과 코드
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    @Override
    public EventsReceiver<T> setResult(EventsErrorCode result) {
        super.setResult(result);
        return this;
    }

    /**
     * 이벤트 응답 데이터를 반환합니다.
     * @return 이벤트 응답 데이터
     */
    @Override
    public T getOutput() {
        return super.getOutput();
    }

    /**
     * 이벤트 응답 데이터를 설정합니다.
     * @param output 이벤트 응답 데이터
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    @Override
    public EventsReceiver<T> setOutput(T output) {
        super.setOutput(output);
        return this;
    }

    /**
     * 비즈니스 ID를 반환합니다.
     * @return 비즈니스 ID
     */
    public String getBid() {
        return bid;
    }

    /**
     * 비즈니스 ID를 설정합니다.
     * @param bid 비즈니스 ID
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public EventsReceiver<T> setBid(String bid) {
        this.bid = bid;
        return this;
    }

    /**
     * 디바이스 시리얼 번호를 반환합니다.
     * @return 디바이스 시리얼 번호
     */
    public String getSn() {
        return sn;
    }

    /**
     * 디바이스 시리얼 번호를 설정합니다.
     * @param sn 디바이스 시리얼 번호
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public EventsReceiver<T> setSn(String sn) {
        this.sn = sn;
        return this;
    }
}
