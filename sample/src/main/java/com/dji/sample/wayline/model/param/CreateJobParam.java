package com.dji.sample.wayline.model.param;

import com.dji.sdk.cloudapi.wayline.OutOfControlActionEnum;
import com.dji.sdk.cloudapi.wayline.TaskTypeEnum;
import com.dji.sdk.cloudapi.wayline.WaylineTypeEnum;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * DJI Cloud API 웨이라인 작업 생성 파라미터 클래스
 * 
 * 이 클래스는 새로운 웨이라인 작업을 생성할 때 사용되는 파라미터들을 정의합니다.
 * 웨이라인 작업의 기본 정보, 실행 조건, 안전 설정 등을 포함하며,
 * Bean Validation 어노테이션을 통해 입력값 검증을 수행합니다.
 * 
 * 주요 기능:
 * - 웨이라인 작업 생성에 필요한 모든 파라미터 정의
 * - 입력값 유효성 검증 (Bean Validation)
 * - 안전 설정 및 실행 조건 관리
 * - 반복 작업 및 예약 작업 지원
 * 
 * 사용 시나리오:
 * - 웨이라인 작업 생성 API 호출 시 요청 본문으로 사용
 * - DJI Pilot에서 웨이라인 작업 생성 시
 * - 자동화된 웨이라인 작업 스케줄링 시
 * 
 * @author sean
 * @version 1.1
 * @date 2022/6/1
 */
@Data
public class CreateJobParam {

    /**
     * 웨이라인 작업명
     * 사용자가 지정하는 웨이라인 작업의 이름
     * 
     * 검증 규칙:
     * - @NotBlank: null, 빈 문자열, 공백만 있는 문자열 불허
     * - 필수 입력 항목
     * 
     * 예시: "정찰_미션_001", "측량_구역_A_작업"
     */
    @NotBlank
    private String name;

    /**
     * 웨이라인 파일 ID
     * 실행할 웨이라인 파일의 고유 식별자
     * 
     * 검증 규칙:
     * - @NotBlank: null, 빈 문자열, 공백만 있는 문자열 불허
     * - 필수 입력 항목
     * 
     * 연관 관계:
     * - WaylineFileEntity의 wayline_id와 연결
     * - 실제 웨이라인 파일(.kmz)을 참조
     */
    @NotBlank
    private String fileId;

    /**
     * Dock 시리얼 번호
     * 웨이라인 작업을 실행할 DJI Dock의 고유 시리얼 번호
     * 
     * 검증 규칙:
     * - @NotBlank: null, 빈 문자열, 공백만 있는 문자열 불허
     * - 필수 입력 항목
     * 
     * 특징:
     * - 하드웨어 레벨의 고유 식별자
     * - 물리적 장비 구분
     * - 동일 Dock에서의 동시 작업 제어
     */
    @NotBlank
    private String dockSn;

    /**
     * 웨이라인 타입
     * 웨이라인 파일의 타입을 나타내는 열거형
     * 
     * 검증 규칙:
     * - @NotNull: null 값 불허
     * - 필수 입력 항목
     * 
     * 값 의미:
     * - 정찰, 측량, 모니터링 등
     * - 웨이라인 파일의 용도에 따라 분류
     */
    @NotNull
    private WaylineTypeEnum waylineType;

    /**
     * 작업 타입
     * 웨이라인 작업의 타입을 나타내는 열거형
     * 
     * 검증 규칙:
     * - @NotNull: null 값 불허
     * - 필수 입력 항목
     * 
     * 값 의미:
     * - 일반 작업: 즉시 실행
     * - 반복 작업: 주기적으로 반복 실행
     * - 예약 작업: 지정된 시간에 실행
     */
    @NotNull
    private TaskTypeEnum taskType;

    /**
     * 귀환 고도 (Return to Home Altitude)
     * 비상 상황 시 드론이 귀환할 고도 (미터 단위)
     * 
     * 검증 규칙:
     * - @NotNull: null 값 불허
     * - @Range(min = 20, max = 500): 20미터 ~ 500미터 범위
     * - 필수 입력 항목
     * 
     * 안전 고려사항:
     * - 장애물보다 높은 고도로 설정 필요
     * - 지역 규제에 따른 고도 제한 준수
     * - 기상 조건을 고려한 적절한 고도 설정
     */
    @Range(min = 20, max = 500)
    @NotNull
    private Integer rthAltitude;

    /**
     * 통신 끊김 시 동작
     * 드론과의 통신이 끊어졌을 때 수행할 동작을 나타내는 열거형
     * 
     * 검증 규칙:
     * - @NotNull: null 값 불허
     * - 필수 입력 항목
     * 
     * 동작 옵션:
     * - 즉시 귀환 (Return to Home)
     * - 현재 위치에서 대기 (Hover)
     * - 작업 계속 (Continue Mission)
     */
    @NotNull
    private OutOfControlActionEnum outOfControlAction;

    /**
     * 최소 배터리 용량
     * 작업 실행을 위한 최소 배터리 용량 (퍼센트)
     * 
     * 검증 규칙:
     * - @Range(min = 50, max = 90): 50% ~ 90% 범위
     * - 선택 입력 항목 (null 허용)
     * 
     * 안전 고려사항:
     * - 배터리 잔량이 이 값보다 낮으면 작업 실행 안함
     * - 안전한 귀환을 위한 충분한 배터리 확보
     * - 기본값: 시스템 기본 설정 사용
     */
    @Range(min = 50, max = 90)
    private Integer minBatteryCapacity;

    /**
     * 최소 저장 용량
     * 작업 실행을 위한 최소 저장 용량 (MB 단위)
     * 
     * 검증 규칙:
     * - 선택 입력 항목 (null 허용)
     * - 양수 값 권장
     * 
     * 용도:
     * - 미디어 파일 저장을 위한 충분한 공간 확보
     * - 작업 중 생성될 데이터 저장 공간 확인
     * - 기본값: 시스템 기본 설정 사용
     */
    private Integer minStorageCapacity;

    /**
     * 작업 실행 요일 목록
     * 반복 작업에서 실행할 요일들을 나타내는 리스트
     * 
     * 데이터 형식:
     * - Long 타입의 요일 값들 (1: 월요일 ~ 7: 일요일)
     * - null 또는 빈 리스트: 매일 실행
     * 
     * 사용 예시:
     * - [1, 3, 5]: 월, 수, 금요일에만 실행
     * - [6, 7]: 주말에만 실행
     * - null: 매일 실행
     */
    private List<Long> taskDays;

    /**
     * 작업 실행 시간대 목록
     * 반복 작업에서 실행할 시간대들을 나타내는 2차원 리스트
     * 
     * 데이터 형식:
     * - List<List<Long>> 형태
     * - 각 내부 리스트는 [시작 시간, 종료 시간] (Unix timestamp)
     * - null 또는 빈 리스트: 24시간 실행 가능
     * 
     * 사용 예시:
     * - [[9시, 12시], [14시, 18시]]: 오전 9-12시, 오후 2-6시에만 실행
     * - null: 24시간 언제든지 실행 가능
     */
    private List<List<Long>> taskPeriods;
}
