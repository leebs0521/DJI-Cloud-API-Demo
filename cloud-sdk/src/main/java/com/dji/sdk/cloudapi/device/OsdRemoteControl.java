package com.dji.sdk.cloudapi.device;

/**
 * 리모트 컨트롤 OSD(On-Screen Display) 정보 클래스
 * 
 * 이 클래스는 리모트 컨트롤의 실시간 상태 정보를 담습니다.
 * 위치 정보, 배터리 용량, 무선 링크 정보 등을 포함합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/23
 */
public class OsdRemoteControl {

    /**
     * 위도
     */
    private Float latitude;

    /**
     * 경도
     */
    private Float longitude;

    /**
     * 고도 (미터)
     */
    private Float height;

    /**
     * 배터리 용량 퍼센트 (%)
     */
    private Integer capacityPercent;

    /**
     * 무선 링크 정보
     */
    private WirelessLink wirelessLink;

    /**
     * 기본 생성자
     */
    public OsdRemoteControl() {
    }

    @Override
    public String toString() {
        return "OsdRemoteControl{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", height=" + height +
                ", capacityPercent=" + capacityPercent +
                ", wirelessLink=" + wirelessLink +
                '}';
    }

    /**
     * 위도를 반환합니다.
     * 
     * @return 위도
     */
    public Float getLatitude() {
        return latitude;
    }

    /**
     * 위도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param latitude 설정할 위도
     * @return 현재 OsdRemoteControl 객체
     */
    public OsdRemoteControl setLatitude(Float latitude) {
        this.latitude = latitude;
        return this;
    }

    /**
     * 경도를 반환합니다.
     * 
     * @return 경도
     */
    public Float getLongitude() {
        return longitude;
    }

    /**
     * 경도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param longitude 설정할 경도
     * @return 현재 OsdRemoteControl 객체
     */
    public OsdRemoteControl setLongitude(Float longitude) {
        this.longitude = longitude;
        return this;
    }

    /**
     * 고도를 반환합니다.
     * 
     * @return 고도 (미터)
     */
    public Float getHeight() {
        return height;
    }

    /**
     * 고도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param height 설정할 고도 (미터)
     * @return 현재 OsdRemoteControl 객체
     */
    public OsdRemoteControl setHeight(Float height) {
        this.height = height;
        return this;
    }

    /**
     * 배터리 용량 퍼센트를 반환합니다.
     * 
     * @return 배터리 용량 퍼센트 (%)
     */
    public Integer getCapacityPercent() {
        return capacityPercent;
    }

    /**
     * 배터리 용량 퍼센트를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param capacityPercent 설정할 배터리 용량 퍼센트 (%)
     * @return 현재 OsdRemoteControl 객체
     */
    public OsdRemoteControl setCapacityPercent(Integer capacityPercent) {
        this.capacityPercent = capacityPercent;
        return this;
    }

    /**
     * 무선 링크 정보를 반환합니다.
     * 
     * @return 무선 링크 정보
     */
    public WirelessLink getWirelessLink() {
        return wirelessLink;
    }

    /**
     * 무선 링크 정보를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param wirelessLink 설정할 무선 링크 정보
     * @return 현재 OsdRemoteControl 객체
     */
    public OsdRemoteControl setWirelessLink(WirelessLink wirelessLink) {
        this.wirelessLink = wirelessLink;
        return this;
    }
}