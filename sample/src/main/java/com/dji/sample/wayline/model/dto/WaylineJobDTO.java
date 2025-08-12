package com.dji.sample.wayline.model.dto;

import com.dji.sdk.cloudapi.wayline.OutOfControlActionEnum;
import com.dji.sdk.cloudapi.wayline.TaskTypeEnum;
import com.dji.sdk.cloudapi.wayline.WaylineTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DJI Cloud API 웨이라인 작업 데이터 전송 객체 (DTO)
 * 
 * 이 클래스는 웨이라인 작업의 정보를 클라이언트와 서버 간에 전송하기 위한 데이터 전송 객체입니다.
 * 웨이라인 작업은 미리 정의된 웨이라인 파일을 기반으로 드론이 자동 비행을 수행하는 작업으로,
 * 작업의 생성부터 완료까지의 전체 라이프사이클 정보를 포함합니다.
 * 
 * 주요 기능:
 * - 웨이라인 작업의 기본 정보 전송 (작업 ID, 이름, 파일 정보 등)
 * - 웨이라인 작업의 실행 정보 전송 (Dock, 드론, 실행 시간 등)
 * - 웨이라인 작업의 상태 정보 전송 (상태, 진행률, 오류 코드 등)
 * - 웨이라인 작업의 미디어 정보 전송 (미디어 개수, 업로드 상태 등)
 * - 웨이라인 작업의 조건 정보 전송 (실행 조건, 안전 설정 등)
 * 
 * 사용 시나리오:
 * - 웨이라인 작업 목록 조회 시 응답 데이터로 사용
 * - 웨이라인 작업 상세 정보 조회 시 사용
 * - 웨이라인 작업 상태 업데이트 시 사용
 * - 웨이라인 작업 진행률 모니터링 시 사용
 * 
 * @author sean
 * @version 1.1
 * @date 2022/6/1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WaylineJobDTO {

    /**
     * 웨이라인 작업 고유 ID
     * 시스템에서 생성하는 웨이라인 작업의 고유 식별자
     */
    private String jobId;

    /**
     * 웨이라인 작업명
     * 사용자가 지정한 웨이라인 작업의 이름
     */
    private String jobName;

    /**
     * 연관된 웨이라인 파일 ID
     * 이 작업이 실행할 웨이라인 파일의 고유 식별자
     */
    private String fileId;

    /**
     * 연관된 웨이라인 파일명
     * 이 작업이 실행할 웨이라인 파일의 이름
     */
    private String fileName;

    /**
     * 실행할 Dock의 시리얼 번호
     * 웨이라인 작업을 실행할 DJI Dock의 고유 시리얼 번호
     */
    private String dockSn;

    /**
     * 실행할 Dock의 이름
     * 웨이라인 작업을 실행할 DJI Dock의 사용자 지정 이름
     */
    private String dockName;

    /**
     * 소속 워크스페이스 ID
     * 이 웨이라인 작업이 속한 워크스페이스의 고유 식별자
     */
    private String workspaceId;

    /**
     * 웨이라인 타입
     * 웨이라인 파일의 타입 (정찰, 측량, 모니터링 등)
     */
    private WaylineTypeEnum waylineType;

    /**
     * 작업 타입
     * 웨이라인 작업의 타입 (일반 작업, 반복 작업 등)
     */
    private TaskTypeEnum taskType;

    /**
     * 예약 실행 시간
     * 웨이라인 작업이 예약된 실행 시간
     * null인 경우 즉시 실행을 의미
     */
    private LocalDateTime executeTime;

    /**
     * 실제 시작 시간
     * 웨이라인 작업이 실제로 시작된 시간
     */
    private LocalDateTime beginTime;

    /**
     * 작업 종료 시간
     * 웨이라인 작업이 종료된 시간 (성공/실패/취소 모두 포함)
     */
    private LocalDateTime endTime;

    /**
     * 작업 완료 시간
     * 웨이라인 작업이 성공적으로 완료된 시간
     * 실패한 경우 null
     */
    private LocalDateTime completedTime;

    /**
     * 작업 상태
     * 웨이라인 작업의 현재 상태
     * 0: 대기 중, 1: 실행 중, 2: 완료, 3: 실패, 4: 취소 등
     */
    private Integer status;

    /**
     * 작업 진행률
     * 웨이라인 작업의 현재 진행률 (0-100%)
     * 0: 시작 전, 100: 완료
     */
    private Integer progress;

    /**
     * 작업 생성자 사용자명
     * 이 웨이라인 작업을 생성한 사용자의 이름
     */
    private String username;

    /**
     * 오류 코드
     * 작업 실행 중 오류가 발생한 경우의 오류 코드
     * 정상 실행 시 null 또는 0
     */
    private Integer code;

    /**
     * 귀환 고도 (Return to Home Altitude)
     * 비상 상황 시 드론이 귀환할 고도 (미터 단위)
     */
    private Integer rthAltitude;

    /**
     * 통신 끊김 시 동작
     * 드론과의 통신이 끊어졌을 때 수행할 동작
     * 예: 즉시 귀환, 현재 위치에서 대기, 작업 계속 등
     */
    private OutOfControlActionEnum outOfControlAction;

    /**
     * 생성된 미디어 파일 총 개수
     * 웨이라인 작업 실행 중 생성된 미디어 파일(사진, 비디오)의 총 개수
     */
    private Integer mediaCount;

    /**
     * 업로드 완료된 미디어 파일 개수
     * 클라우드 스토리지에 업로드가 완료된 미디어 파일의 개수
     */
    private Integer uploadedCount;

    /**
     * 미디어 업로드 중 여부
     * 현재 미디어 파일이 업로드 중인지 여부
     * true: 업로드 중, false: 업로드 완료 또는 업로드 없음
     */
    private Boolean uploading;

    /**
     * 웨이라인 작업 실행 조건
     * 웨이라인 작업 실행을 위한 준비 조건과 실행 조건 정보
     */
    private WaylineTaskConditionDTO conditions;

    /**
     * 부모 작업 ID
     * 이 작업이 다른 작업의 하위 작업인 경우 부모 작업의 ID
     * 독립적인 작업인 경우 null
     */
    private String parentId;
}
