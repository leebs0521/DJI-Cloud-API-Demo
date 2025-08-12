package com.dji.sample.wayline.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DJI Cloud API 웨이라인 작업 생성 데이터 전송 객체 (DTO)
 * 
 * 이 클래스는 새로운 웨이라인 작업을 생성할 때 사용되는 데이터 전송 객체입니다.
 * DJI Dock에서 웨이라인 기반 자동 비행 작업을 생성하기 위해 필요한 모든 정보를 포함합니다.
 * 
 * 주요 기능:
 * - 웨이라인 작업 생성에 필요한 기본 정보 전송 (작업 타입, 웨이라인 타입 등)
 * - 웨이라인 작업 실행 조건 설정 (배터리, 시간, 저장공간 등)
 * - 웨이라인 작업 안전 설정 (귀환 고도, 통신 끊김 시 동작 등)
 * - 웨이라인 파일 정보 전송 (다운로드 URL, 파일 무결성 검증 등)
 * 
 * 사용 시나리오:
 * - 웨이라인 작업 생성 API 호출 시 요청 데이터로 사용
 * - DJI Dock에 웨이라인 작업 발행 시 사용
 * - 웨이라인 작업 예약 생성 시 사용
 * 
 * @author sean
 * @version 1.1
 * @date 2022/6/1
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WaylineTaskCreateDTO {

    /**
     * 비행 작업 고유 ID
     * 시스템에서 생성하는 비행 작업의 고유 식별자
     * 웨이라인 작업과 1:1 매핑되는 비행 작업을 식별
     */
    private String flightId;

    /**
     * 작업 타입
     * 웨이라인 작업의 타입을 나타내는 정수값
     * 0: 일반 작업, 1: 반복 작업, 2: 예약 작업 등
     */
    private Integer taskType;

    /**
     * 웨이라인 타입
     * 웨이라인 파일의 타입을 나타내는 정수값
     * 1: 정찰, 2: 측량, 3: 모니터링 등
     */
    private Integer waylineType;

    /**
     * 예약 실행 시간
     * 웨이라인 작업이 실행될 예약 시간 (Unix timestamp)
     * 0 또는 null인 경우 즉시 실행을 의미
     * 밀리초 단위의 타임스탬프 값
     */
    private Long executeTime;

    /**
     * 웨이라인 파일 정보
     * 실행할 웨이라인 파일의 다운로드 URL과 무결성 검증 정보
     */
    private WaylineTaskFileDTO file;

    /**
     * 귀환 고도 (Return to Home Altitude)
     * 비상 상황 시 드론이 귀환할 고도 (미터 단위)
     * 기본값: 50미터
     */
    private Integer rthAltitude;

    /**
     * 통신 끊김 시 동작
     * 드론과의 통신이 끊어졌을 때 수행할 동작을 나타내는 정수값
     * 0: 즉시 귀환, 1: 현재 위치에서 대기, 2: 작업 계속 등
     */
    private Integer outOfControlAction;

    /**
     * 작업 준비 조건
     * 웨이라인 작업 실행을 위한 준비 조건 (배터리, 시간 등)
     * 이 조건들이 만족되어야 작업이 실행될 수 있음
     */
    private WaylineTaskReadyConditionDTO readyConditions;

    /**
     * 작업 실행 조건
     * 웨이라인 작업 실행을 위한 실행 조건 (저장공간 등)
     * 이 조건들이 만족되어야 작업이 계속 실행될 수 있음
     */
    private WaylineTaskExecutableConditionDTO executableConditions;
}
