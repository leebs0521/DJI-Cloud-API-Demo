package com.dji.sdk.cloudapi.device;

/**
 * 도크 서브 디바이스 클래스
 * 
 * 이 클래스는 도크에 연결된 서브 디바이스의 정보를 담습니다.
 * 디바이스 시리얼 번호, 온라인 상태, 페어링 상태, 디바이스 모델 등을 포함합니다.
 * 
 * @author sean
 * @version 1.0
 * @date 2022/5/11
 */
public class DockSubDevice {

    /**
     * 디바이스 시리얼 번호
     */
    private String deviceSn;

    /**
     * 디바이스 온라인 상태
     */
    private Boolean deviceOnlineStatus;

    /**
     * 디바이스 페어링 상태
     */
    private Boolean devicePaired;

    /**
     * 디바이스 모델 키
     */
    private DeviceEnum deviceModelKey;

    /**
     * 기본 생성자
     */
    public DockSubDevice() {
    }

    @Override
    public String toString() {
        return "DockSubDevice{" +
                "deviceSn='" + deviceSn + '\'' +
                ", deviceOnlineStatus=" + deviceOnlineStatus +
                ", devicePaired=" + devicePaired +
                ", deviceModelKey=" + deviceModelKey +
                '}';
    }

    /**
     * 디바이스 시리얼 번호를 반환합니다.
     * 
     * @return 디바이스 시리얼 번호
     */
    public String getDeviceSn() {
        return deviceSn;
    }

    /**
     * 디바이스 시리얼 번호를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param deviceSn 설정할 디바이스 시리얼 번호
     * @return 현재 DockSubDevice 객체
     */
    public DockSubDevice setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
        return this;
    }

    /**
     * 디바이스 온라인 상태를 반환합니다.
     * 
     * @return 디바이스 온라인 상태
     */
    public Boolean getDeviceOnlineStatus() {
        return deviceOnlineStatus;
    }

    /**
     * 디바이스 온라인 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param deviceOnlineStatus 설정할 디바이스 온라인 상태
     * @return 현재 DockSubDevice 객체
     */
    public DockSubDevice setDeviceOnlineStatus(Boolean deviceOnlineStatus) {
        this.deviceOnlineStatus = deviceOnlineStatus;
        return this;
    }

    /**
     * 디바이스 페어링 상태를 반환합니다.
     * 
     * @return 디바이스 페어링 상태
     */
    public Boolean getDevicePaired() {
        return devicePaired;
    }

    /**
     * 디바이스 페어링 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param devicePaired 설정할 디바이스 페어링 상태
     * @return 현재 DockSubDevice 객체
     */
    public DockSubDevice setDevicePaired(Boolean devicePaired) {
        this.devicePaired = devicePaired;
        return this;
    }

    /**
     * 디바이스 모델 키를 반환합니다.
     * 
     * @return 디바이스 모델 키
     */
    public DeviceEnum getDeviceModelKey() {
        return deviceModelKey;
    }

    /**
     * 디바이스 모델 키를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param deviceModelKey 설정할 디바이스 모델 키
     * @return 현재 DockSubDevice 객체
     */
    public DockSubDevice setDeviceModelKey(DeviceEnum deviceModelKey) {
        this.deviceModelKey = deviceModelKey;
        return this;
    }
}
