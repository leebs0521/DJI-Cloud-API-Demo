package com.dji.sdk.cloudapi.device;

/**
 * 도크 페이로드 제어 소스 클래스
 * 
 * 이 클래스는 도크에 연결된 페이로드(카메라, 센서 등)의 제어 소스 정보를 관리합니다.
 * 제어 소스, 페이로드 인덱스, 시리얼 번호 등을 포함합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/18
 * @version 0.1
 */
public class DockPayloadControlSource {

    /**
     * 제어 소스 열거형
     */
    private ControlSourceEnum controlSource;

    /**
     * 페이로드 인덱스
     */
    private PayloadIndex payloadIndex;

    /**
     * 페이로드 시리얼 번호
     */
    private String sn;

    /**
     * 기본 생성자
     */
    public DockPayloadControlSource() {
    }

    @Override
    public String toString() {
        return "RcPayloadControlSource{" +
                "controlSource=" + controlSource +
                ", payloadIndex=" + payloadIndex +
                ", sn='" + sn + '\'' +
                '}';
    }

    /**
     * 제어 소스를 반환합니다.
     * 
     * @return 제어 소스 열거형
     */
    public ControlSourceEnum getControlSource() {
        return controlSource;
    }

    /**
     * 제어 소스를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param controlSource 설정할 제어 소스
     * @return 현재 DockPayloadControlSource 객체
     */
    public DockPayloadControlSource setControlSource(ControlSourceEnum controlSource) {
        this.controlSource = controlSource;
        return this;
    }

    /**
     * 페이로드 인덱스를 반환합니다.
     * 
     * @return 페이로드 인덱스
     */
    public PayloadIndex getPayloadIndex() {
        return payloadIndex;
    }

    /**
     * 페이로드 인덱스를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param payloadIndex 설정할 페이로드 인덱스
     * @return 현재 DockPayloadControlSource 객체
     */
    public DockPayloadControlSource setPayloadIndex(PayloadIndex payloadIndex) {
        this.payloadIndex = payloadIndex;
        return this;
    }

    /**
     * 페이로드 시리얼 번호를 반환합니다.
     * 
     * @return 페이로드 시리얼 번호
     */
    public String getSn() {
        return sn;
    }

    /**
     * 페이로드 시리얼 번호를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param sn 설정할 페이로드 시리얼 번호
     * @return 현재 DockPayloadControlSource 객체
     */
    public DockPayloadControlSource setSn(String sn) {
        this.sn = sn;
        return this;
    }

}