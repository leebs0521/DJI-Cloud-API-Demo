package com.dji.sdk.cloudapi.device;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 무선 링크 정보 클래스
 * 
 * 이 클래스는 디바이스의 무선 통신 링크 정보를 담습니다.
 * 4G 통신과 SDR(Software Defined Radio) 통신의 상태, 품질, 주파수 대역 등을 포함합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/3
 */
public class WirelessLink {

    /**
     * 4G 주파수 대역 (GHz)
     */
    @JsonProperty("4g_freq_band")
    private Float fourthGenerationFreqBand;

    /**
     * 4G 지상 품질
     */
    @JsonProperty("4g_gnd_quality")
    private Integer fourthGenerationGndQuality;

    /**
     * 4G 링크 상태
     */
    @JsonProperty("4g_link_state")
    private Boolean fourthGenerationLinkState;

    /**
     * 4G 전체 품질
     */
    @JsonProperty("4g_quality")
    private Integer fourthGenerationQuality;

    /**
     * 4G UAV 품질
     */
    @JsonProperty("4g_uav_quality")
    private Integer fourthGenerationUavQuality;

    /**
     * 동글 번호
     */
    private Integer dongleNumber;

    /**
     * 링크 작업 모드
     */
    private LinkWorkModeEnum linkWorkmode;

    /**
     * SDR 주파수 대역 (GHz)
     */
    private Float sdrFreqBand;

    /**
     * SDR 링크 상태
     */
    private Boolean sdrLinkState;

    /**
     * SDR 품질
     */
    private Integer sdrQuality;

    /**
     * 기본 생성자
     */
    public WirelessLink() {
    }

    @Override
    public String toString() {
        return "WirelessLink{" +
                "fourthGenerationFreqBand=" + fourthGenerationFreqBand +
                ", fourthGenerationGndQuality=" + fourthGenerationGndQuality +
                ", fourthGenerationLinkState=" + fourthGenerationLinkState +
                ", fourthGenerationQuality=" + fourthGenerationQuality +
                ", fourthGenerationUavQuality=" + fourthGenerationUavQuality +
                ", dongleNumber=" + dongleNumber +
                ", linkWorkmode=" + linkWorkmode +
                ", sdrFreqBand=" + sdrFreqBand +
                ", sdrLinkState=" + sdrLinkState +
                ", sdrQuality=" + sdrQuality +
                '}';
    }

    /**
     * 4G 주파수 대역을 반환합니다.
     * 
     * @return 4G 주파수 대역 (GHz)
     */
    public Float getFourthGenerationFreqBand() {
        return fourthGenerationFreqBand;
    }

    /**
     * 4G 주파수 대역을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param fourthGenerationFreqBand 설정할 4G 주파수 대역 (GHz)
     * @return 현재 WirelessLink 객체
     */
    public WirelessLink setFourthGenerationFreqBand(Float fourthGenerationFreqBand) {
        this.fourthGenerationFreqBand = fourthGenerationFreqBand;
        return this;
    }

    /**
     * 4G 지상 품질을 반환합니다.
     * 
     * @return 4G 지상 품질
     */
    public Integer getFourthGenerationGndQuality() {
        return fourthGenerationGndQuality;
    }

    /**
     * 4G 지상 품질을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param fourthGenerationGndQuality 설정할 4G 지상 품질
     * @return 현재 WirelessLink 객체
     */
    public WirelessLink setFourthGenerationGndQuality(Integer fourthGenerationGndQuality) {
        this.fourthGenerationGndQuality = fourthGenerationGndQuality;
        return this;
    }

    /**
     * 4G 링크 상태를 반환합니다.
     * 
     * @return 4G 링크 상태
     */
    public Boolean getFourthGenerationLinkState() {
        return fourthGenerationLinkState;
    }

    /**
     * 4G 링크 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param fourthGenerationLinkState 설정할 4G 링크 상태
     * @return 현재 WirelessLink 객체
     */
    public WirelessLink setFourthGenerationLinkState(Boolean fourthGenerationLinkState) {
        this.fourthGenerationLinkState = fourthGenerationLinkState;
        return this;
    }

    /**
     * 4G 전체 품질을 반환합니다.
     * 
     * @return 4G 전체 품질
     */
    public Integer getFourthGenerationQuality() {
        return fourthGenerationQuality;
    }

    /**
     * 4G 전체 품질을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param fourthGenerationQuality 설정할 4G 전체 품질
     * @return 현재 WirelessLink 객체
     */
    public WirelessLink setFourthGenerationQuality(Integer fourthGenerationQuality) {
        this.fourthGenerationQuality = fourthGenerationQuality;
        return this;
    }

    /**
     * 4G UAV 품질을 반환합니다.
     * 
     * @return 4G UAV 품질
     */
    public Integer getFourthGenerationUavQuality() {
        return fourthGenerationUavQuality;
    }

    /**
     * 4G UAV 품질을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param fourthGenerationUavQuality 설정할 4G UAV 품질
     * @return 현재 WirelessLink 객체
     */
    public WirelessLink setFourthGenerationUavQuality(Integer fourthGenerationUavQuality) {
        this.fourthGenerationUavQuality = fourthGenerationUavQuality;
        return this;
    }

    public Integer getDongleNumber() {
        return dongleNumber;
    }

    public WirelessLink setDongleNumber(Integer dongleNumber) {
        this.dongleNumber = dongleNumber;
        return this;
    }

    public LinkWorkModeEnum getLinkWorkmode() {
        return linkWorkmode;
    }

    public WirelessLink setLinkWorkmode(LinkWorkModeEnum linkWorkmode) {
        this.linkWorkmode = linkWorkmode;
        return this;
    }

    public Float getSdrFreqBand() {
        return sdrFreqBand;
    }

    public WirelessLink setSdrFreqBand(Float sdrFreqBand) {
        this.sdrFreqBand = sdrFreqBand;
        return this;
    }

    public Boolean getSdrLinkState() {
        return sdrLinkState;
    }

    public WirelessLink setSdrLinkState(Boolean sdrLinkState) {
        this.sdrLinkState = sdrLinkState;
        return this;
    }

    public Integer getSdrQuality() {
        return sdrQuality;
    }

    public WirelessLink setSdrQuality(Integer sdrQuality) {
        this.sdrQuality = sdrQuality;
        return this;
    }
}
