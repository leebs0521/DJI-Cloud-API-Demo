package com.dji.sdk.cloudapi.device;

/**
 * RC 페이로드 제어 소스 클래스
 * 
 * 이 클래스는 RC(Remote Controller)에 연결된 페이로드(카메라, 센서 등)의 제어 소스 정보를 관리합니다.
 * 제어 소스, 페이로드 인덱스, 시리얼 번호, 펌웨어 버전 등을 포함합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/18
 * @version 0.1
 */
public class RcPayloadControlSource {

    /**
     * 제어 소스 열거형 (A, B, UNKNOWN)
     * 페이로드의 제어 권한을 나타냅니다.
     */
    private ControlSourceEnum controlSource;

    /**
     * 페이로드 인덱스
     * 페이로드의 타입, 서브타입, 위치 정보를 조합한 고유 식별자
     * 형식: "타입-서브타입-위치" (예: "0-0-0")
     */
    private PayloadIndex payloadIndex;

    /**
     * 페이로드 시리얼 번호
     * 페이로드의 고유 식별 번호
     */
    private String sn;

    /**
     * 페이로드 펌웨어 버전
     * 페이로드의 현재 펌웨어 버전 정보
     */
    private String firmwareVersion;

    /**
     * 기본 생성자
     */
    public RcPayloadControlSource() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "RcPayloadControlSource{" +
                "controlSource=" + controlSource +
                ", payloadIndex=" + payloadIndex +
                ", sn='" + sn + '\'' +
                ", firmwareVersion='" + firmwareVersion + '\'' +
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
     * @return 현재 RcPayloadControlSource 객체
     */
    public RcPayloadControlSource setControlSource(ControlSourceEnum controlSource) {
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
     * @return 현재 RcPayloadControlSource 객체
     */
    public RcPayloadControlSource setPayloadIndex(PayloadIndex payloadIndex) {
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
     * @return 현재 RcPayloadControlSource 객체
     */
    public RcPayloadControlSource setSn(String sn) {
        this.sn = sn;
        return this;
    }

    /**
     * 페이로드 펌웨어 버전을 반환합니다.
     * 
     * @return 페이로드 펌웨어 버전
     */
    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    /**
     * 페이로드 펌웨어 버전을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param firmwareVersion 설정할 페이로드 펌웨어 버전
     * @return 현재 RcPayloadControlSource 객체
     */
    public RcPayloadControlSource setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
        return this;
    }
}