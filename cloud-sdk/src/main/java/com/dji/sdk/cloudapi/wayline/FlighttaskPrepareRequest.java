package com.dji.sdk.cloudapi.wayline;

import com.dji.sdk.annotations.CloudSDKVersion;
import com.dji.sdk.cloudapi.device.ExitWaylineWhenRcLostEnum;
import com.dji.sdk.common.BaseModel;
import com.dji.sdk.config.version.CloudSDKVersionEnum;
import com.dji.sdk.config.version.GatewayTypeEnum;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 비행 작업 준비 요청 클래스
 * 
 * 이 클래스는 웨이라인 비행 작업을 준비하기 위한 요청 매개변수를 정의합니다.
 * 비행 작업의 실행 조건, 파일 정보, 안전 설정 등을 포함하여
 * 드론이 안전하게 웨이라인 비행을 수행할 수 있도록 준비합니다.
 * 
 * 주요 구성 요소:
 * - flightId: 작업 ID
 * - executeTime: 실행 시간
 * - taskType: 작업 타입
 * - waylineType: 웨이라인 타입
 * - file: 웨이라인 파일 객체
 * - readyConditions: 작업 준비 조건
 * - executableConditions: 작업 실행 조건
 * - breakPoint: 웨이라인 중단점 정보
 * - rthAltitude: 귀환 고도
 * - outOfControlAction: 제어 상실 시 액션
 * - exitWaylineWhenRcLost: RC 손실 시 웨이라인 종료
 * - rthMode: 귀환 모드
 * - simulateMission: 시뮬레이션 미션
 * - waylinePrecisionType: 웨이라인 정밀도 타입
 * 
 * 이 클래스는 웨이라인 비행 작업의 안전하고 정확한 실행을
 * 위한 모든 설정 정보를 포함합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/6
 */
public class FlighttaskPrepareRequest extends BaseModel {

    /**
     * 작업 ID
     * 
     * 비행 작업을 고유하게 식별하는 ID입니다.
     * 특수 문자 제한이 있으며, 파일 시스템에서 사용할 수 없는
     * 문자들은 포함할 수 없습니다.
     * 
     * 제약 조건: 특수 문자 제한 (<>:"/|?*._\\ 제외)
     */
    @NotNull
    @Pattern(regexp = "^[^<>:\"/|?*._\\\\]+$")
    private String flightId;

    /**
     * 실행 시간
     * 
     * 작업 실행 시간의 밀리초 타임스탬프입니다. 선택적 필드입니다.
     * taskType이 0 또는 1일 때는 필수이며, taskType이 2일 때는 선택사항입니다.
     * 
     * 제약 조건: 최소값 123456789012L
     */
    @Min(123456789012L)
    private Long executeTime;

    /**
     * 작업 타입
     * 
     * 즉시 작업과 예약 작업의 실행 시간은 executeTime으로 정의됩니다.
     * 조건부 작업은 readyConditions로 정의된 작업 준비 조건을 지원합니다.
     * 지정된 기간 내에 조건이 충족되면 작업을 실행할 수 있습니다.
     * 즉시 작업이 최우선 순위를 가지며, 예약 작업과 조건부 작업은 동일한 우선순위를 가집니다.
     */
    @NotNull
    private TaskTypeEnum taskType;

    /**
     * 웨이라인 타입
     * 
     * 웨이라인 비행의 타입을 정의합니다.
     * 웨이포인트 비행, 매핑 등 다양한 비행 모드를 지원합니다.
     */
    @NotNull
    private WaylineTypeEnum waylineType;

    /**
     * 웨이라인 파일 객체
     * 
     * 실행할 웨이라인 파일의 정보를 포함합니다.
     * 파일 URL, 서명 등의 정보가 포함됩니다.
     */
    @NotNull
    @Valid
    private FlighttaskFile file;

    /**
     * 작업 준비 조건
     * 
     * 작업이 실행되기 전에 충족되어야 하는 조건들입니다.
     * 날씨, 시간, 위치 등의 조건을 설정할 수 있습니다.
     */
    @Valid
    private ReadyConditions readyConditions;

    /**
     * 작업 실행 조건
     * 
     * 작업이 실제로 실행되기 위해 필요한 조건들입니다.
     * 드론 상태, 배터리 레벨 등의 조건을 확인합니다.
     */
    @Valid
    private ExecutableConditions executableConditions;

    /**
     * 웨이라인 중단점 정보
     * 
     * 웨이라인 비행에서 중단점을 설정하는 정보입니다.
     * 특정 지점에서 작업을 일시정지하거나 복구할 수 있습니다.
     */
    @Valid
    private FlighttaskBreakPoint breakPoint;

    /**
     * 귀환 고도
     * 
     * 드론이 홈포인트로 귀환할 때 사용할 고도입니다.
     * 미터 단위로 설정되며, 안전한 귀환을 위한 충분한 고도가 필요합니다.
     * 
     * 제약 조건: 20-1500 미터
     */
    @NotNull
    @Min(20)
    @Max(1500)
    private Integer rthAltitude;

    /**
     * 리모컨 제어 상실 시 액션
     * 
     * 리모컨과의 연결이 끊어졌을 때 수행할 액션입니다.
     * 현재 고정 전송 값은 0으로, 홈포인트 귀환(RTH)을 의미합니다.
     * 비행 제어 및 도크 정의와 열거형 값 정의가 일치하지 않으며,
     * 도크 측에서 변환이 존재합니다.
     */
    @NotNull
    private OutOfControlActionEnum outOfControlAction;

    /**
     * 웨이라인 제어 상실 시 액션
     * 
     * KMZ 파일과 일치하는 웨이라인 제어 상실 시 액션입니다.
     * 웨이라인 비행 중 제어가 상실되었을 때의 동작을 정의합니다.
     */
    @NotNull
    private ExitWaylineWhenRcLostEnum exitWaylineWhenRcLost;

    /**
     * 귀환 모드
     * 
     * 드론이 홈포인트로 귀환할 때 사용할 모드입니다.
     * V1_0_0 버전부터 지원됩니다.
     * 
     * 기본값: PRESET_HEIGHT (사전 설정 고도)
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_0)
    private RthModeEnum rthMode = RthModeEnum.PRESET_HEIGHT;

    /**
     * 시뮬레이션 미션
     * 
     * 웨이라인 비행을 시뮬레이션하기 위한 설정입니다.
     * V1_0_0 버전부터 지원됩니다.
     */
    @Valid
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_0)
    private SimulateMission simulateMission;

    /**
     * 웨이라인 정밀도 타입
     * 
     * 웨이라인 비행에서 사용할 위치 정밀도 타입입니다.
     * V1_0_1 버전부터 DOCK2에서 지원됩니다.
     */
    @NotNull
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_1, include = GatewayTypeEnum.DOCK2_OR_DOCK3)
    private WaylinePrecisionTypeEnum waylinePrecisionType;

    public FlighttaskPrepareRequest() {}

    @Override
    public String toString() {
        return "FlighttaskPrepareRequest{" +
                "flightId='" + flightId + '\'' +
                ", executeTime=" + executeTime +
                ", taskType=" + taskType +
                ", waylineType=" + waylineType +
                ", file=" + file +
                ", readyConditions=" + readyConditions +
                ", executableConditions=" + executableConditions +
                ", breakPoint=" + breakPoint +
                ", rthAltitude=" + rthAltitude +
                ", outOfControlAction=" + outOfControlAction +
                ", exitWaylineWhenRcLost=" + exitWaylineWhenRcLost +
                ", rthMode=" + rthMode +
                ", simulateMission=" + simulateMission +
                ", waylinePrecisionType=" + waylinePrecisionType +
                '}';
    }

    /**
     * 작업 ID를 반환합니다.
     * 
     * @return 작업 ID
     */
    public String getFlightId() {
        return flightId;
    }

    /**
     * 작업 ID를 설정합니다.
     * 
     * @param flightId 작업 ID
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FlighttaskPrepareRequest setFlightId(String flightId) {
        this.flightId = flightId;
        return this;
    }

    /**
     * 실행 시간을 반환합니다.
     * 
     * @return 실행 시간 (밀리초)
     */
    public Long getExecuteTime() {
        return executeTime;
    }

    /**
     * 실행 시간을 설정합니다.
     * 
     * @param executeTime 실행 시간 (밀리초)
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FlighttaskPrepareRequest setExecuteTime(Long executeTime) {
        this.executeTime = executeTime;
        return this;
    }

    /**
     * 작업 타입을 반환합니다.
     * 
     * @return 작업 타입
     */
    public TaskTypeEnum getTaskType() {
        return taskType;
    }

    /**
     * 작업 타입을 설정합니다.
     * 
     * @param taskType 작업 타입
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FlighttaskPrepareRequest setTaskType(TaskTypeEnum taskType) {
        this.taskType = taskType;
        return this;
    }

    /**
     * 웨이라인 타입을 반환합니다.
     * 
     * @return 웨이라인 타입
     */
    public WaylineTypeEnum getWaylineType() {
        return waylineType;
    }

    /**
     * 웨이라인 타입을 설정합니다.
     * 
     * @param waylineType 웨이라인 타입
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FlighttaskPrepareRequest setWaylineType(WaylineTypeEnum waylineType) {
        this.waylineType = waylineType;
        return this;
    }

    /**
     * 웨이라인 파일 객체를 반환합니다.
     * 
     * @return 웨이라인 파일 객체
     */
    public FlighttaskFile getFile() {
        return file;
    }

    /**
     * 웨이라인 파일 객체를 설정합니다.
     * 
     * @param file 웨이라인 파일 객체
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FlighttaskPrepareRequest setFile(FlighttaskFile file) {
        this.file = file;
        return this;
    }

    /**
     * 작업 준비 조건을 반환합니다.
     * 
     * @return 작업 준비 조건
     */
    public ReadyConditions getReadyConditions() {
        return readyConditions;
    }

    /**
     * 작업 준비 조건을 설정합니다.
     * 
     * @param readyConditions 작업 준비 조건
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FlighttaskPrepareRequest setReadyConditions(ReadyConditions readyConditions) {
        this.readyConditions = readyConditions;
        return this;
    }

    /**
     * 작업 실행 조건을 반환합니다.
     * 
     * @return 작업 실행 조건
     */
    public ExecutableConditions getExecutableConditions() {
        return executableConditions;
    }

    /**
     * 작업 실행 조건을 설정합니다.
     * 
     * @param executableConditions 작업 실행 조건
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FlighttaskPrepareRequest setExecutableConditions(ExecutableConditions executableConditions) {
        this.executableConditions = executableConditions;
        return this;
    }

    /**
     * 웨이라인 중단점 정보를 반환합니다.
     * 
     * @return 웨이라인 중단점 정보
     */
    public FlighttaskBreakPoint getBreakPoint() {
        return breakPoint;
    }

    /**
     * 웨이라인 중단점 정보를 설정합니다.
     * 
     * @param breakPoint 웨이라인 중단점 정보
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FlighttaskPrepareRequest setBreakPoint(FlighttaskBreakPoint breakPoint) {
        this.breakPoint = breakPoint;
        return this;
    }

    /**
     * 귀환 고도를 반환합니다.
     * 
     * @return 귀환 고도 (미터)
     */
    public Integer getRthAltitude() {
        return rthAltitude;
    }

    /**
     * 귀환 고도를 설정합니다.
     * 
     * @param rthAltitude 귀환 고도 (미터)
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FlighttaskPrepareRequest setRthAltitude(Integer rthAltitude) {
        this.rthAltitude = rthAltitude;
        return this;
    }

    /**
     * 제어 상실 시 액션을 반환합니다.
     * 
     * @return 제어 상실 시 액션
     */
    public OutOfControlActionEnum getOutOfControlAction() {
        return outOfControlAction;
    }

    /**
     * 제어 상실 시 액션을 설정합니다.
     * 
     * @param outOfControlAction 제어 상실 시 액션
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FlighttaskPrepareRequest setOutOfControlAction(OutOfControlActionEnum outOfControlAction) {
        this.outOfControlAction = outOfControlAction;
        return this;
    }

    /**
     * RC 손실 시 웨이라인 종료 액션을 반환합니다.
     * 
     * @return RC 손실 시 웨이라인 종료 액션
     */
    public ExitWaylineWhenRcLostEnum getExitWaylineWhenRcLost() {
        return exitWaylineWhenRcLost;
    }

    /**
     * RC 손실 시 웨이라인 종료 액션을 설정합니다.
     * 
     * @param exitWaylineWhenRcLost RC 손실 시 웨이라인 종료 액션
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FlighttaskPrepareRequest setExitWaylineWhenRcLost(ExitWaylineWhenRcLostEnum exitWaylineWhenRcLost) {
        this.exitWaylineWhenRcLost = exitWaylineWhenRcLost;
        return this;
    }

    /**
     * 귀환 모드를 반환합니다.
     * 
     * @return 귀환 모드
     */
    public RthModeEnum getRthMode() {
        return rthMode;
    }

    /**
     * 귀환 모드를 설정합니다.
     * 
     * @param rthMode 귀환 모드
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FlighttaskPrepareRequest setRthMode(RthModeEnum rthMode) {
        this.rthMode = rthMode;
        return this;
    }

    /**
     * 시뮬레이션 미션을 반환합니다.
     * 
     * @return 시뮬레이션 미션
     */
    public SimulateMission getSimulateMission() {
        return simulateMission;
    }

    /**
     * 시뮬레이션 미션을 설정합니다.
     * 
     * @param simulateMission 시뮬레이션 미션
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FlighttaskPrepareRequest setSimulateMission(SimulateMission simulateMission) {
        this.simulateMission = simulateMission;
        return this;
    }

    /**
     * 웨이라인 정밀도 타입을 반환합니다.
     * 
     * @return 웨이라인 정밀도 타입
     */
    public WaylinePrecisionTypeEnum getWaylinePrecisionType() {
        return waylinePrecisionType;
    }

    /**
     * 웨이라인 정밀도 타입을 설정합니다.
     * 
     * @param waylinePrecisionType 웨이라인 정밀도 타입
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FlighttaskPrepareRequest setWaylinePrecisionType(WaylinePrecisionTypeEnum waylinePrecisionType) {
        this.waylinePrecisionType = waylinePrecisionType;
        return this;
    }
}
