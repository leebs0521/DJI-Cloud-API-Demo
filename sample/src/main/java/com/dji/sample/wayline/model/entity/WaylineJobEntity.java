package com.dji.sample.wayline.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DJI Cloud API 웨이라인 작업 엔티티 클래스
 * 
 * 이 클래스는 웨이라인 작업 정보를 데이터베이스에 저장하기 위한 엔티티 클래스입니다.
 * MyBatis-Plus 프레임워크를 사용하여 데이터베이스 테이블과 매핑되며,
 * 웨이라인 작업의 생성부터 완료까지의 전체 라이프사이클 정보를 포함합니다.
 * 
 * 주요 기능:
 * - 웨이라인 작업의 기본 정보 저장 (작업 ID, 이름, 파일 정보 등)
 * - 웨이라인 작업의 실행 정보 저장 (Dock, 드론, 실행 시간 등)
 * - 웨이라인 작업의 상태 정보 저장 (상태, 진행률, 오류 코드 등)
 * - 웨이라인 작업의 미디어 정보 저장 (미디어 개수, 업로드 상태 등)
 * - 웨이라인 작업의 안전 설정 저장 (귀환 고도, 통신 끊김 시 동작 등)
 * 
 * 데이터베이스 테이블: wayline_job
 * 
 * MyBatis-Plus 어노테이션:
 * - @TableName: 데이터베이스 테이블명 지정
 * - @TableId: 기본 키 필드 지정
 * - @TableField: 데이터베이스 컬럼명 매핑
 * - @FieldFill: 자동 필드 채우기 설정
 * 
 * @author sean
 * @version 1.1
 * @date 2022/6/1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wayline_job")
public class WaylineJobEntity implements Serializable {

    /**
     * 웨이라인 작업 고유 ID (기본 키)
     * 데이터베이스에서 자동 생성되는 웨이라인 작업의 고유 식별자
     * 
     * MyBatis-Plus 설정:
     * - IdType.AUTO: 데이터베이스에서 자동 증가하는 ID 사용
     * - 데이터베이스 컬럼: id (INTEGER, AUTO_INCREMENT)
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 웨이라인 작업 고유 ID (외부 식별자)
     * 시스템에서 생성하는 웨이라인 작업의 고유 식별자
     * API 응답에서 사용되는 외부 ID
     * 
     * 데이터베이스 컬럼: job_id (VARCHAR)
     * 예시: "job_1234567890"
     */
    @TableField("job_id")
    private String jobId;

    /**
     * 웨이라인 작업명
     * 사용자가 지정한 웨이라인 작업의 이름
     * 
     * 데이터베이스 컬럼: name (VARCHAR)
     * 예시: "정찰_미션_001", "측량_구역_A_작업"
     */
    @TableField("name")
    private String name;

    /**
     * 연관된 웨이라인 파일 ID
     * 이 작업이 실행할 웨이라인 파일의 고유 식별자
     * WaylineFileEntity의 wayline_id와 연결
     * 
     * 데이터베이스 컬럼: file_id (VARCHAR)
     * 예시: "wayline_1234567890"
     */
    @TableField("file_id")
    private String fileId;

    /**
     * 실행할 Dock의 시리얼 번호
     * 웨이라인 작업을 실행할 DJI Dock의 고유 시리얼 번호
     * 
     * 데이터베이스 컬럼: dock_sn (VARCHAR)
     * 예시: "dock_1234567890"
     */
    @TableField("dock_sn")
    private String dockSn;

    /**
     * 소속 워크스페이스 ID
     * 이 웨이라인 작업이 속한 워크스페이스의 고유 식별자
     * 다중 테넌트 환경에서 데이터 격리를 위해 사용
     * 
     * 데이터베이스 컬럼: workspace_id (VARCHAR)
     * 예시: "workspace_123"
     */
    @TableField("workspace_id")
    private String workspaceId;

    /**
     * 작업 타입
     * 웨이라인 작업의 타입을 나타내는 정수값
     * 
     * 데이터베이스 컬럼: task_type (INTEGER)
     * 값 의미:
     * - 0: 일반 작업
     * - 1: 반복 작업
     * - 2: 예약 작업
     */
    @TableField("task_type")
    private Integer taskType;

    /**
     * 웨이라인 타입
     * 웨이라인 파일의 타입을 나타내는 정수값
     * 
     * 데이터베이스 컬럼: wayline_type (INTEGER)
     * 값 의미:
     * - 1: 정찰
     * - 2: 측량
     * - 3: 모니터링
     */
    @TableField("wayline_type")
    private Integer waylineType;

    /**
     * 작업 생성자 사용자명
     * 이 웨이라인 작업을 생성한 사용자의 이름
     * 
     * 데이터베이스 컬럼: username (VARCHAR)
     * 예시: "admin", "pilot_001"
     */
    @TableField("username")
    private String username;

    /**
     * 예약 실행 시간 (Unix timestamp)
     * 웨이라인 작업이 예약된 실행 시간
     * 
     * 데이터베이스 컬럼: execute_time (BIGINT)
     * 값 의미:
     * - 0 또는 null: 즉시 실행
     * - 양수 값: 예약된 실행 시간 (밀리초 단위)
     */
    @TableField("execute_time")
    private Long executeTime;

    /**
     * 작업 종료 시간 (Unix timestamp)
     * 웨이라인 작업이 종료된 시간 (성공/실패/취소 모두 포함)
     * 
     * 데이터베이스 컬럼: end_time (BIGINT)
     * 밀리초 단위의 타임스탬프 값
     */
    @TableField("end_time")
    private Long endTime;

    /**
     * 오류 코드
     * 작업 실행 중 오류가 발생한 경우의 오류 코드
     * 
     * 데이터베이스 컬럼: error_code (INTEGER)
     * 값 의미:
     * - 0: 정상 실행
     * - 양수: 오류 코드 (DJI SDK에서 정의된 오류 코드)
     * - null: 오류 없음
     */
    @TableField("error_code")
    private Integer errorCode;

    /**
     * 작업 상태
     * 웨이라인 작업의 현재 상태를 나타내는 정수값
     * 
     * 데이터베이스 컬럼: status (INTEGER)
     * 값 의미:
     * - 0: 대기 중 (WAITING)
     * - 1: 실행 중 (RUNNING)
     * - 2: 완료 (COMPLETED)
     * - 3: 실패 (FAILED)
     * - 4: 취소 (CANCELLED)
     * - 5: 일시 정지 (PAUSED)
     */
    @TableField("status")
    private Integer status;

    /**
     * 귀환 고도 (Return to Home Altitude)
     * 비상 상황 시 드론이 귀환할 고도 (미터 단위)
     * 
     * 데이터베이스 컬럼: rth_altitude (INTEGER)
     * 기본값: 50미터
     * 안전 고려사항: 장애물보다 높은 고도로 설정 필요
     */
    @TableField("rth_altitude")
    private Integer rthAltitude;

    /**
     * 통신 끊김 시 동작
     * 드론과의 통신이 끊어졌을 때 수행할 동작을 나타내는 정수값
     * 
     * 데이터베이스 컬럼: out_of_control (INTEGER)
     * 값 의미:
     * - 0: 즉시 귀환 (Return to Home)
     * - 1: 현재 위치에서 대기 (Hover)
     * - 2: 작업 계속 (Continue Mission)
     */
    @TableField("out_of_control")
    private Integer outOfControlAction;

    /**
     * 생성된 미디어 파일 총 개수
     * 웨이라인 작업 실행 중 생성된 미디어 파일(사진, 비디오)의 총 개수
     * 
     * 데이터베이스 컬럼: media_count (INTEGER)
     * 값 의미:
     * - 0: 미디어 파일 없음
     * - 양수: 생성된 미디어 파일 개수
     */
    @TableField("media_count")
    private Integer mediaCount;

    /**
     * 생성 시간 (Unix timestamp)
     * 웨이라인 작업이 데이터베이스에 생성된 시간
     * 
     * MyBatis-Plus 설정:
     * - FieldFill.INSERT: 새 레코드 생성 시 자동으로 현재 시간 설정
     * - 밀리초 단위의 타임스탬프 값
     * 
     * 데이터베이스 컬럼: create_time (BIGINT)
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Long createTime;

    /**
     * 최종 수정 시간 (Unix timestamp)
     * 웨이라인 작업 정보가 마지막으로 수정된 시간
     * 
     * MyBatis-Plus 설정:
     * - FieldFill.INSERT_UPDATE: 레코드 생성 및 수정 시 자동으로 현재 시간 설정
     * - 밀리초 단위의 타임스탬프 값
     * 
     * 데이터베이스 컬럼: update_time (BIGINT)
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;

    /**
     * 실제 시작 시간 (Unix timestamp)
     * 웨이라인 작업이 실제로 시작된 시간
     * 
     * 데이터베이스 컬럼: begin_time (BIGINT)
     * 밀리초 단위의 타임스탬프 값
     * execute_time과 다름: 예약 시간과 실제 시작 시간을 구분
     */
    @TableField("begin_time")
    private Long beginTime;

    /**
     * 작업 완료 시간 (Unix timestamp)
     * 웨이라인 작업이 성공적으로 완료된 시간
     * 
     * 데이터베이스 컬럼: completed_time (BIGINT)
     * 값 의미:
     * - null: 아직 완료되지 않음 또는 실패함
     * - 양수: 성공적으로 완료된 시간 (밀리초 단위)
     */
    @TableField("completed_time")
    private Long completedTime;

    /**
     * 부모 작업 ID
     * 이 작업이 다른 작업의 하위 작업인 경우 부모 작업의 ID
     * 
     * 데이터베이스 컬럼: parent_id (VARCHAR)
     * 값 의미:
     * - null: 독립적인 작업
     * - 문자열: 부모 작업의 job_id
     * 
     * 사용 목적:
     * - 작업 체인 구성
     * - 순차 작업 실행
     * - 작업 의존성 관리
     */
    @TableField("parent_id")
    private String parentId;
}
