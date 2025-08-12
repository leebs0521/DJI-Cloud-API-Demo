package com.dji.sample.wayline.model.dto;

import com.dji.sdk.cloudapi.wayline.ExecutableConditions;
import com.dji.sdk.cloudapi.wayline.ReadyConditions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DJI Cloud API 웨이라인 작업 조건 데이터 전송 객체 (DTO)
 * 
 * 이 클래스는 웨이라인 작업 실행을 위한 조건 정보를 전송하기 위한 데이터 전송 객체입니다.
 * 웨이라인 작업이 안전하고 정상적으로 실행되기 위해 필요한 모든 조건들을 포함합니다.
 * 
 * 주요 기능:
 * - 웨이라인 작업 실행 준비 조건 전송 (배터리, 시간 등)
 * - 웨이라인 작업 실행 조건 전송 (저장공간 등)
 * - 웨이라인 작업 안전 조건 관리
 * - 조건 만족 여부 검증 지원
 * 
 * 조건 분류:
 * - ReadyConditions: 작업 실행 전 만족되어야 하는 준비 조건
 * - ExecutableConditions: 작업 실행 중 계속 만족되어야 하는 실행 조건
 * 
 * 사용 시나리오:
 * - 웨이라인 작업 생성 시 조건 설정
 * - 웨이라인 작업 실행 전 조건 검증
 * - 웨이라인 작업 실행 중 조건 모니터링
 * 
 * @author sean
 * @version 1.3
 * @date 2023/2/16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WaylineTaskConditionDTO {

    /**
     * 웨이라인 작업 실행 준비 조건
     * 웨이라인 작업이 실행되기 전에 만족되어야 하는 조건들
     * 
     * 포함 조건:
     * - 배터리 용량: 최소 배터리 용량 요구사항
     * - 시간 조건: 작업 실행 가능 시간 범위
     * - 기상 조건: 바람, 비, 안개 등의 기상 조건
     * - GPS 신호: 최소 GPS 신호 강도 요구사항
     */
    private ReadyConditions readyConditions;

    /**
     * 웨이라인 작업 실행 조건
     * 웨이라인 작업이 실행 중에 계속 만족되어야 하는 조건들
     * 
     * 포함 조건:
     * - 저장공간: 미디어 파일 저장을 위한 최소 저장공간
     * - 메모리: 작업 실행을 위한 최소 메모리 용량
     * - 네트워크: 실시간 통신을 위한 네트워크 연결 상태
     * - 센서 상태: GPS, IMU 등 센서들의 정상 동작 상태
     */
    private ExecutableConditions executableConditions;
}
