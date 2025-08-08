package com.dji.sdk.cloudapi.wayline;

import com.dji.sdk.cloudapi.control.Point;

import java.util.List;

/**
 * 홈포인트 귀환 정보 클래스
 * 
 * 이 클래스는 드론의 홈포인트 귀환에 대한 정보를 정의합니다.
 * 계획된 귀환 경로와 궤적의 마지막 점 타입을 포함하여
 * 귀환 상태를 모니터링하고 표시하는 데 사용됩니다.
 * 
 * 주요 구성 요소:
 * - plannedPathPoints: 계획된 귀환 경로
 * - lastPointType: 궤적 마지막 점 타입
 * - flightId: 현재 작업 중인 웨이라인 미션 ID
 * 
 * 이 클래스는 드론이 홈포인트로 귀환할 때의
 * 경로 정보와 상태를 제공합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/11
 */
public class ReturnHomeInfo {

    /**
     * 항공기의 실시간 계획된 귀환 경로
     * 
     * 각 푸시는 경로의 완전한 업데이트입니다.
     * 배열에는 완전한 귀환 경로가 포함되어 있습니다.
     */
    private List<Point> plannedPathPoints;

    /**
     * 궤적의 마지막 점 표시 모드를 결정하는 데 사용할 수 있는 필드
     * 
     * 0: 궤적의 마지막 점이 지상의 귀환점 위에 위치함
     *     터미널은 궤적의 마지막 점을 귀환점에 연결하는 선을 표시할 수 있음
     * 1: 궤적의 마지막 점이 귀환점이 아님
     *     터미널은 궤적의 마지막 점을 귀환점에 연결하는 선을 표시하지 않아야 함
     *     귀환점에 도달할 수 없는 이유는 귀환점이 제한 구역이나 장애물 내부에 있을 수 있음
     */
    private LastPointTypeEnum lastPointType;

    /**
     * 현재 작업 중인 웨이라인 미션 ID
     * 
     * 현재 실행 중인 웨이라인 비행 작업의 고유 식별자입니다.
     */
    private String flightId;

    public ReturnHomeInfo() {
    }

    @Override
    public String toString() {
        return "ReturnHomeInfo{" +
                "plannedPathPoints=" + plannedPathPoints +
                ", lastPointType=" + lastPointType +
                ", flightId='" + flightId + '\'' +
                '}';
    }

    /**
     * 계획된 귀환 경로 점들을 반환합니다.
     * 
     * @return 계획된 귀환 경로 점들
     */
    public List<Point> getPlannedPathPoints() {
        return plannedPathPoints;
    }

    /**
     * 계획된 귀환 경로 점들을 설정합니다.
     * 
     * @param plannedPathPoints 계획된 귀환 경로 점들
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public ReturnHomeInfo setPlannedPathPoints(List<Point> plannedPathPoints) {
        this.plannedPathPoints = plannedPathPoints;
        return this;
    }

    /**
     * 궤적 마지막 점 타입을 반환합니다.
     * 
     * @return 궤적 마지막 점 타입
     */
    public LastPointTypeEnum getLastPointType() {
        return lastPointType;
    }

    /**
     * 궤적 마지막 점 타입을 설정합니다.
     * 
     * @param lastPointType 궤적 마지막 점 타입
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public ReturnHomeInfo setLastPointType(LastPointTypeEnum lastPointType) {
        this.lastPointType = lastPointType;
        return this;
    }

    /**
     * 현재 작업 중인 웨이라인 미션 ID를 반환합니다.
     * 
     * @return 웨이라인 미션 ID
     */
    public String getFlightId() {
        return flightId;
    }

    /**
     * 현재 작업 중인 웨이라인 미션 ID를 설정합니다.
     * 
     * @param flightId 웨이라인 미션 ID
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public ReturnHomeInfo setFlightId(String flightId) {
        this.flightId = flightId;
        return this;
    }
}
