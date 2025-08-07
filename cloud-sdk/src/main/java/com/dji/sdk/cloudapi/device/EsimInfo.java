package com.dji.sdk.cloudapi.device;

/**
 * eSIM 정보 클래스
 * 
 * 이 클래스는 eSIM의 정보를 담습니다.
 * 통신사, 활성화 상태, ICCID 등을 포함합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/20
 */
public class EsimInfo {

    /**
     * eSIM이 지원하는 통신사
     */
    private TelecomOperatorEnum telecomOperator;

    /**
     * eSIM 정보에서 동시에 하나의 eSIM만 활성화할 수 있습니다.
     */
    private Boolean enabled;

    /**
     * SIM 카드의 고유 식별 번호 (물리 SIM 카드 패키지 구매에 사용 가능)
     */
    private String iccid;

    /**
     * 기본 생성자
     */
    public EsimInfo() {
    }

    @Override
    public String toString() {
        return "EsimInfo{" +
                "telecomOperator=" + telecomOperator +
                ", enabled=" + enabled +
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
     * @return 현재 EsimInfo 객체
     */
    public EsimInfo setTelecomOperator(TelecomOperatorEnum telecomOperator) {
        this.telecomOperator = telecomOperator;
        return this;
    }

    /**
     * 활성화 상태를 반환합니다.
     * 
     * @return 활성화 상태
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 활성화 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param enabled 설정할 활성화 상태
     * @return 현재 EsimInfo 객체
     */
    public EsimInfo setEnabled(Boolean enabled) {
        this.enabled = enabled;
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
     * @return 현재 EsimInfo 객체
     */
    public EsimInfo setIccid(String iccid) {
        this.iccid = iccid;
        return this;
    }
}
