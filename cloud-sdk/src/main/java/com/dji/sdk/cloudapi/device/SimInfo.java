package com.dji.sdk.cloudapi.device;

/**
 * SIM 카드 정보 클래스
 * 
 * 이 클래스는 물리 SIM 카드의 정보를 담습니다.
 * 통신사, SIM 카드 타입, ICCID 등을 포함합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/20
 */
public class SimInfo {

    /**
     * SIM 카드가 지원하는 통신사
     */
    private TelecomOperatorEnum telecomOperator;

    /**
     * 물리 SIM 카드의 타입
     */
    private SimTypeEnum simType;

    /**
     * SIM 카드의 고유 식별 번호 (물리 SIM 카드 패키지 구매에 사용 가능)
     */
    private String iccid;

    /**
     * 기본 생성자
     */
    public SimInfo() {
    }

    @Override
    public String toString() {
        return "SimInfo{" +
                "telecomOperator=" + telecomOperator +
                ", simType=" + simType +
                ", iccid='" + iccid + '\'' +
                '}';
    }

    /**
     * 통신사를 반환합니다.
     * 
     * @return 통신사
     */
    public TelecomOperatorEnum getTelecomOperator() {
        return telecomOperator;
    }

    /**
     * 통신사를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param telecomOperator 설정할 통신사
     * @return 현재 SimInfo 객체
     */
    public SimInfo setTelecomOperator(TelecomOperatorEnum telecomOperator) {
        this.telecomOperator = telecomOperator;
        return this;
    }

    /**
     * SIM 카드 타입을 반환합니다.
     * 
     * @return SIM 카드 타입
     */
    public SimTypeEnum getSimType() {
        return simType;
    }

    /**
     * SIM 카드 타입을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param simType 설정할 SIM 카드 타입
     * @return 현재 SimInfo 객체
     */
    public SimInfo setSimType(SimTypeEnum simType) {
        this.simType = simType;
        return this;
    }

    /**
     * SIM 카드의 ICCID를 반환합니다.
     * 
     * @return SIM 카드의 고유 식별 번호
     */
    public String getIccid() {
        return iccid;
    }

    /**
     * SIM 카드의 ICCID를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param iccid 설정할 SIM 카드의 고유 식별 번호
     * @return 현재 SimInfo 객체
     */
    public SimInfo setIccid(String iccid) {
        this.iccid = iccid;
        return this;
    }
}
