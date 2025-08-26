package com.dji.sdk.cloudapi.device;

/**
 * 네트워크 상태 클래스
 * 
 * 이 클래스는 디바이스의 네트워크 상태 정보를 담습니다.
 * 네트워크 타입, 품질, 속도 등을 포함합니다.
 * 
 * @author sean
 * @version 1.0
 * @date 2022/5/11
 */
public class NetworkState {

    /**
     * 네트워크 타입 (4G, 이더넷 등)
     */
    private NetworkStateTypeEnum type;

    /**
     * 네트워크 품질 (신호 없음, 나쁨, 보통, 좋음, 매우 좋음)
     */
    private NetworkStateQualityEnum quality;

    /**
     * 네트워크 속도 (Kbps)
     */
    private Float rate;

    /**
     * 기본 생성자
     */
    public NetworkState() {
    }

    @Override
    public String toString() {
        return "NetworkState{" +
                "type=" + type +
                ", quality=" + quality +
                ", rate=" + rate +
                '}';
    }

    /**
     * 네트워크 타입을 반환합니다.
     * 
     * @return 네트워크 타입
     */
    public NetworkStateTypeEnum getType() {
        return type;
    }

    /**
     * 네트워크 타입을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param type 설정할 네트워크 타입
     * @return 현재 NetworkState 객체
     */
    public NetworkState setType(NetworkStateTypeEnum type) {
        this.type = type;
        return this;
    }

    /**
     * 네트워크 품질을 반환합니다.
     * 
     * @return 네트워크 품질
     */
    public NetworkStateQualityEnum getQuality() {
        return quality;
    }

    /**
     * 네트워크 품질을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param quality 설정할 네트워크 품질
     * @return 현재 NetworkState 객체
     */
    public NetworkState setQuality(NetworkStateQualityEnum quality) {
        this.quality = quality;
        return this;
    }

    /**
     * 네트워크 속도를 반환합니다.
     * 
     * @return 네트워크 속도 (Mbps)
     */
    public Float getRate() {
        return rate;
    }

    /**
     * 네트워크 속도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param rate 설정할 네트워크 속도 (Mbps)
     * @return 현재 NetworkState 객체
     */
    public NetworkState setRate(Float rate) {
        this.rate = rate;
        return this;
    }
}
