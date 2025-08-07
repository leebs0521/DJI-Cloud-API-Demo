package com.dji.sdk.cloudapi.device;

import java.util.List;

/**
 * 동글 정보 클래스
 * 
 * 이 클래스는 디바이스에 연결된 동글(무선 통신 모듈)의 정보를 담습니다.
 * IMEI, 동글 타입, eSIM 정보, 물리 SIM 카드 정보 등을 포함합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/19
 */
public class DongleInfo {

    /**
     * 동글의 고유 식별 번호 (IMEI)
     */
    private String imei;

    /**
     * 동글 타입
     */
    private DongleTypeEnum dongleType;

    /**
     * eSIM의 고유 식별 번호 (공개 계정 쿼리 패키지 및 구매 서비스용)
     */
    private String eid;

    /**
     * eSIM 활성화 상태
     */
    private EsimActivateStateEnum esimActivateState;

    /**
     * 동글 내 물리 SIM 카드의 상태
     */
    private SimCardStateEnum simCardState;

    /**
     * 동글이 현재 사용 중인 SIM 카드 슬롯을 식별
     */
    private SimSlotEnum simSlot;

    /**
     * eSIM 정보 목록
     */
    private List<EsimInfo> esimInfos;

    /**
     * 동글에 삽입 가능한 물리 SIM 카드 정보
     */
    private SimInfo simInfo;

    /**
     * 기본 생성자
     */
    public DongleInfo() {
    }

    @Override
    public String toString() {
        return "DongleInfo{" +
                "imei='" + imei + '\'' +
                ", dongleType=" + dongleType +
                ", eid='" + eid + '\'' +
                ", esimActivateState=" + esimActivateState +
                ", simCardState=" + simCardState +
                ", simSlot=" + simSlot +
                ", esimInfos=" + esimInfos +
                ", simInfo=" + simInfo +
                '}';
    }

    /**
     * 동글의 IMEI를 반환합니다.
     * 
     * @return 동글의 고유 식별 번호
     */
    public String getImei() {
        return imei;
    }

    /**
     * 동글의 IMEI를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param imei 설정할 동글의 고유 식별 번호
     * @return 현재 DongleInfo 객체
     */
    public DongleInfo setImei(String imei) {
        this.imei = imei;
        return this;
    }

    /**
     * 동글 타입을 반환합니다.
     * 
     * @return 동글 타입
     */
    public DongleTypeEnum getDongleType() {
        return dongleType;
    }

    /**
     * 동글 타입을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param dongleType 설정할 동글 타입
     * @return 현재 DongleInfo 객체
     */
    public DongleInfo setDongleType(DongleTypeEnum dongleType) {
        this.dongleType = dongleType;
        return this;
    }

    /**
     * eSIM의 고유 식별 번호를 반환합니다.
     * 
     * @return eSIM의 고유 식별 번호
     */
    public String getEid() {
        return eid;
    }

    /**
     * eSIM의 고유 식별 번호를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param eid 설정할 eSIM의 고유 식별 번호
     * @return 현재 DongleInfo 객체
     */
    public DongleInfo setEid(String eid) {
        this.eid = eid;
        return this;
    }

    /**
     * eSIM 활성화 상태를 반환합니다.
     * 
     * @return eSIM 활성화 상태
     */
    public EsimActivateStateEnum getEsimActivateState() {
        return esimActivateState;
    }

    /**
     * eSIM 활성화 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param esimActivateState 설정할 eSIM 활성화 상태
     * @return 현재 DongleInfo 객체
     */
    public DongleInfo setEsimActivateState(EsimActivateStateEnum esimActivateState) {
        this.esimActivateState = esimActivateState;
        return this;
    }

    /**
     * 물리 SIM 카드 상태를 반환합니다.
     * 
     * @return 물리 SIM 카드 상태
     */
    public SimCardStateEnum getSimCardState() {
        return simCardState;
    }

    /**
     * 물리 SIM 카드 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param simCardState 설정할 물리 SIM 카드 상태
     * @return 현재 DongleInfo 객체
     */
    public DongleInfo setSimCardState(SimCardStateEnum simCardState) {
        this.simCardState = simCardState;
        return this;
    }

    /**
     * 현재 사용 중인 SIM 카드 슬롯을 반환합니다.
     * 
     * @return 현재 사용 중인 SIM 카드 슬롯
     */
    public SimSlotEnum getSimSlot() {
        return simSlot;
    }

    /**
     * 현재 사용 중인 SIM 카드 슬롯을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param simSlot 설정할 SIM 카드 슬롯
     * @return 현재 DongleInfo 객체
     */
    public DongleInfo setSimSlot(SimSlotEnum simSlot) {
        this.simSlot = simSlot;
        return this;
    }

    /**
     * eSIM 정보 목록을 반환합니다.
     * 
     * @return eSIM 정보 목록
     */
    public List<EsimInfo> getEsimInfos() {
        return esimInfos;
    }

    /**
     * eSIM 정보 목록을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param esimInfos 설정할 eSIM 정보 목록
     * @return 현재 DongleInfo 객체
     */
    public DongleInfo setEsimInfos(List<EsimInfo> esimInfos) {
        this.esimInfos = esimInfos;
        return this;
    }

    /**
     * 물리 SIM 카드 정보를 반환합니다.
     * 
     * @return 물리 SIM 카드 정보
     */
    public SimInfo getSimInfo() {
        return simInfo;
    }

    /**
     * 물리 SIM 카드 정보를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param simInfo 설정할 물리 SIM 카드 정보
     * @return 현재 DongleInfo 객체
     */
    public DongleInfo setSimInfo(SimInfo simInfo) {
        this.simInfo = simInfo;
        return this;
    }
}
