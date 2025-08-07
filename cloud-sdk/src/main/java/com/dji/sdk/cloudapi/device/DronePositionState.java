package com.dji.sdk.cloudapi.device;

/**
 * 드론 위치 상태 클래스
 * 
 * 이 클래스는 드론의 위치 상태 정보를 담습니다.
 * GPS 위성 수, 위치 고정 상태, 품질, RTK 위성 수 등을 포함합니다.
 * 
 * @author sean
 * @version 0.3
 * @date 2022/1/27
 */
public class DronePositionState {

    /**
     * GPS 위성 수
     */
    private Integer gpsNumber;

    /**
     * 위치 고정 상태
     */
    private PositionFixedEnum isFixed;

    /**
     * 위치 품질
     */
    private Integer quality;

    /**
     * RTK 위성 수
     */
    private Integer rtkNumber;

    /**
     * 기본 생성자
     */
    public DronePositionState() {
    }

    @Override
    public String toString() {
        return "DronePositionState{" +
                "gpsNumber=" + gpsNumber +
                ", isFixed=" + isFixed +
                ", quality=" + quality +
                ", rtkNumber=" + rtkNumber +
                '}';
    }

    /**
     * GPS 위성 수를 반환합니다.
     * 
     * @return GPS 위성 수
     */
    public Integer getGpsNumber() {
        return gpsNumber;
    }

    /**
     * GPS 위성 수를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param gpsNumber 설정할 GPS 위성 수
     * @return 현재 DronePositionState 객체
     */
    public DronePositionState setGpsNumber(Integer gpsNumber) {
        this.gpsNumber = gpsNumber;
        return this;
    }

    /**
     * 위치 고정 상태를 반환합니다.
     * 
     * @return 위치 고정 상태
     */
    public PositionFixedEnum getIsFixed() {
        return isFixed;
    }

    /**
     * 위치 고정 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param isFixed 설정할 위치 고정 상태
     * @return 현재 DronePositionState 객체
     */
    public DronePositionState setIsFixed(PositionFixedEnum isFixed) {
        this.isFixed = isFixed;
        return this;
    }

    /**
     * 위치 품질을 반환합니다.
     * 
     * @return 위치 품질
     */
    public Integer getQuality() {
        return quality;
    }

    /**
     * 위치 품질을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param quality 설정할 위치 품질
     * @return 현재 DronePositionState 객체
     */
    public DronePositionState setQuality(Integer quality) {
        this.quality = quality;
        return this;
    }

    /**
     * RTK 위성 수를 반환합니다.
     * 
     * @return RTK 위성 수
     */
    public Integer getRtkNumber() {
        return rtkNumber;
    }

    /**
     * RTK 위성 수를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param rtkNumber 설정할 RTK 위성 수
     * @return 현재 DronePositionState 객체
     */
    public DronePositionState setRtkNumber(Integer rtkNumber) {
        this.rtkNumber = rtkNumber;
        return this;
    }
}
