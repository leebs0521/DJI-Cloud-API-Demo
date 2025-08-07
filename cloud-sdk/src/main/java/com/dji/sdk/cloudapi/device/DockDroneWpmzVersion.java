package com.dji.sdk.cloudapi.device;

/**
 * 도크 드론 WPMZ 버전 클래스
 * 
 * 이 클래스는 도크에 연결된 드론의 WPMZ 버전 정보를 담습니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/23
 */
public class DockDroneWpmzVersion {

    /**
     * WPMZ 버전
     */
    private String wpmzVersion;

    /**
     * 기본 생성자
     */
    public DockDroneWpmzVersion() {
    }

    @Override
    public String toString() {
        return "DockDroneWpmzVersion{" +
                "wpmzVersion='" + wpmzVersion + '\'' +
                '}';
    }

    /**
     * WPMZ 버전을 반환합니다.
     * 
     * @return WPMZ 버전
     */
    public String getWpmzVersion() {
        return wpmzVersion;
    }

    /**
     * WPMZ 버전을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param wpmzVersion 설정할 WPMZ 버전
     * @return 현재 DockDroneWpmzVersion 객체
     */
    public DockDroneWpmzVersion setWpmzVersion(String wpmzVersion) {
        this.wpmzVersion = wpmzVersion;
        return this;
    }
}
