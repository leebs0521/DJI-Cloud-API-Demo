package com.dji.sample.wayline.model.dto;

import lombok.Data;

/**
 * DJI Cloud API 웨이라인 작업 진행률 수신 데이터 전송 객체 (DTO)
 * 
 * 이 클래스는 DJI Dock에서 웨이라인 작업의 진행 상황을 보고할 때 사용되는 데이터 전송 객체입니다.
 * 웨이라인 작업의 실시간 진행률, 상태, 그리고 상세한 진행 정보를 포함합니다.
 * 
 * 주요 기능:
 * - 웨이라인 작업 진행률 정보 수신
 * - 웨이라인 작업 상태 정보 수신
 * - 웨이라인 작업 상세 진행 정보 수신
 * - 실시간 작업 모니터링 지원
 * 
 * 수신 정보:
 * - ext: 웨이라인 작업의 확장 정보 (현재 웨이포인트, 미디어 개수 등)
 * - progress: 웨이라인 작업의 진행률 정보 (현재 단계, 진행률 등)
 * - status: 웨이라인 작업의 현재 상태 (실행 중, 완료, 실패 등)
 * 
 * 사용 시나리오:
 * - DJI Dock에서 웨이라인 작업 진행 상황 보고
 * - 실시간 웨이라인 작업 모니터링
 * - 웨이라인 작업 진행률 업데이트
 * - 웨이라인 작업 상태 변경 알림
 * 
 * @author sean
 * @version 1.1
 * @date 2022/6/9
 */
@Data
public class WaylineTaskProgressReceiver {

    /**
     * 웨이라인 작업 확장 정보
     * 웨이라인 작업의 상세한 진행 정보를 포함하는 확장 데이터
     * 
     * 포함 정보:
     * - currentWaypointIndex: 현재 실행 중인 웨이포인트 인덱스
     * - mediaCount: 현재까지 생성된 미디어 파일 개수
     * - flightId: 비행 작업 고유 ID
     * - trackId: 비행 경로 추적 ID
     */
    private FlighttaskProgressExt ext;

    /**
     * 웨이라인 작업 진행률 정보
     * 웨이라인 작업의 진행 단계와 진행률을 나타내는 정보
     * 
     * 포함 정보:
     * - currentStep: 현재 실행 중인 작업 단계
     * - percent: 전체 작업의 진행률 (0-100%)
     */
    private FlighttaskProgressProgress progress;

    /**
     * 웨이라인 작업 현재 상태
     * 웨이라인 작업의 현재 실행 상태를 나타내는 문자열
     * 
     * 상태 값:
     * - "RUNNING": 작업이 실행 중
     * - "COMPLETED": 작업이 완료됨
     * - "FAILED": 작업이 실패함
     * - "CANCELLED": 작업이 취소됨
     * - "PAUSED": 작업이 일시 정지됨
     */
    private String status;
}
