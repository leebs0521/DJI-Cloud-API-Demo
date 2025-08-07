package com.dji.sdk.cloudapi.device;

import java.util.List;

/**
 * 드론 배터리 유지보수 정보 클래스
 * 
 * 이 클래스는 드론 배터리의 유지보수 정보를 담습니다.
 * 배터리 목록, 유지보수 상태, 남은 유지보수 시간, 가열 상태 등을 포함합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2022/11/3
 */
public class DroneBatteryMaintenanceInfo {

    /**
     * 드론 배터리 유지보수 목록
     */
    private List<DroneBatteryMaintenance> batteries;

    /**
     * 유지보수 상태
     */
    private MaintenanceStateEnum maintenanceState;

    /**
     * 배터리 유지보수 남은 시간 (시간 단위, 내림)
     */
    private Integer maintenanceTimeLeft;

    /**
     * 드론이 도크에서 전원이 꺼져 있을 때 드론 배터리의 가열 및 단열 정보를 보고합니다.
     */
    private HeatStateEnum heatState;

    /**
     * 기본 생성자
     */
    public DroneBatteryMaintenanceInfo() {
    }

    @Override
    public String toString() {
        return "DroneBatteryMaintenanceInfo{" +
                "batteries=" + batteries +
                ", maintenanceState=" + maintenanceState +
                ", maintenanceTimeLeft=" + maintenanceTimeLeft +
                ", heatState=" + heatState +
                '}';
    }

    /**
     * 드론 배터리 유지보수 목록을 반환합니다.
     * 
     * @return 드론 배터리 유지보수 목록
     */
    public List<DroneBatteryMaintenance> getBatteries() {
        return batteries;
    }

    /**
     * 드론 배터리 유지보수 목록을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param batteries 설정할 드론 배터리 유지보수 목록
     * @return 현재 DroneBatteryMaintenanceInfo 객체
     */
    public DroneBatteryMaintenanceInfo setBatteries(List<DroneBatteryMaintenance> batteries) {
        this.batteries = batteries;
        return this;
    }

    /**
     * 유지보수 상태를 반환합니다.
     * 
     * @return 유지보수 상태
     */
    public MaintenanceStateEnum getMaintenanceState() {
        return maintenanceState;
    }

    /**
     * 유지보수 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param maintenanceState 설정할 유지보수 상태
     * @return 현재 DroneBatteryMaintenanceInfo 객체
     */
    public DroneBatteryMaintenanceInfo setMaintenanceState(MaintenanceStateEnum maintenanceState) {
        this.maintenanceState = maintenanceState;
        return this;
    }

    /**
     * 배터리 유지보수 남은 시간을 반환합니다.
     * 
     * @return 배터리 유지보수 남은 시간 (시간)
     */
    public Integer getMaintenanceTimeLeft() {
        return maintenanceTimeLeft;
    }

    /**
     * 배터리 유지보수 남은 시간을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param maintenanceTimeLeft 설정할 배터리 유지보수 남은 시간 (시간)
     * @return 현재 DroneBatteryMaintenanceInfo 객체
     */
    public DroneBatteryMaintenanceInfo setMaintenanceTimeLeft(Integer maintenanceTimeLeft) {
        this.maintenanceTimeLeft = maintenanceTimeLeft;
        return this;
    }

    /**
     * 가열 상태를 반환합니다.
     * 
     * @return 가열 상태
     */
    public HeatStateEnum getHeatState() {
        return heatState;
    }

    /**
     * 가열 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param heatState 설정할 가열 상태
     * @return 현재 DroneBatteryMaintenanceInfo 객체
     */
    public DroneBatteryMaintenanceInfo setHeatState(HeatStateEnum heatState) {
        this.heatState = heatState;
        return this;
    }
}
