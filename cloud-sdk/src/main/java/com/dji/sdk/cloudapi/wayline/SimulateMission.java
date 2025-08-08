package com.dji.sdk.cloudapi.wayline;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 시뮬레이션 미션 클래스
 * 
 * 이 클래스는 웨이라인 비행 작업의 시뮬레이션 설정을 정의합니다.
 * 시뮬레이션 활성화 여부와 시뮬레이션 위치 정보를 포함하여
 * 안전한 테스트 환경을 제공합니다.
 * 
 * 주요 구성 요소:
 * - isEnable: 시뮬레이션 활성화 여부
 * - latitude: 시뮬레이션 위치 위도
 * - longitude: 시뮬레이션 위치 경도
 * 
 * 이 클래스는 실제 비행 없이 웨이라인 작업을
 * 시뮬레이션하여 테스트하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/8/4
 */
public class SimulateMission {

    /**
     * 시뮬레이션 활성화 여부
     * 
     * 시뮬레이션 모드의 활성화 상태를 나타냅니다.
     */
    @NotNull
    private SimulateSwitchEnum isEnable;

    /**
     * 시뮬레이션 위치 위도
     * 
     * 시뮬레이션을 수행할 위치의 위도 좌표입니다.
     * 
     * 제약 조건: -90 ~ 90 사이의 값
     */
    @NotNull
    @Min(-90)
    @Max(90)
    private Float latitude;

    /**
     * 시뮬레이션 위치 경도
     * 
     * 시뮬레이션을 수행할 위치의 경도 좌표입니다.
     * 
     * 제약 조건: -180 ~ 180 사이의 값
     */
    @NotNull
    @Min(-180)
    @Max(180)
    private Float longitude;

    public SimulateMission() {
    }

    @Override
    public String toString() {
        return "SimulateMission{" +
                "isEnable=" + isEnable +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    /**
     * 시뮬레이션 활성화 여부를 반환합니다.
     * 
     * @return 시뮬레이션 활성화 여부
     */
    public SimulateSwitchEnum getIsEnable() {
        return isEnable;
    }

    /**
     * 시뮬레이션 활성화 여부를 설정합니다.
     * 
     * @param isEnable 시뮬레이션 활성화 여부
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public SimulateMission setIsEnable(SimulateSwitchEnum isEnable) {
        this.isEnable = isEnable;
        return this;
    }

    /**
     * 시뮬레이션 위치 위도를 반환합니다.
     * 
     * @return 시뮬레이션 위치 위도
     */
    public Float getLatitude() {
        return latitude;
    }

    /**
     * 시뮬레이션 위치 위도를 설정합니다.
     * 
     * @param latitude 시뮬레이션 위치 위도
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public SimulateMission setLatitude(Float latitude) {
        this.latitude = latitude;
        return this;
    }

    /**
     * 시뮬레이션 위치 경도를 반환합니다.
     * 
     * @return 시뮬레이션 위치 경도
     */
    public Float getLongitude() {
        return longitude;
    }

    /**
     * 시뮬레이션 위치 경도를 설정합니다.
     * 
     * @param longitude 시뮬레이션 위치 경도
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public SimulateMission setLongitude(Float longitude) {
        this.longitude = longitude;
        return this;
    }
}
