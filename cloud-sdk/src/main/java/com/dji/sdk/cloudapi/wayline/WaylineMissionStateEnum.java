package com.dji.sdk.cloudapi.wayline;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 웨이라인 미션 상태 열거형
 * 
 * 이 열거형은 웨이라인 비행 미션의 다양한 상태를 정의합니다.
 * 웨이라인 파일 업로드부터 실행 완료까지의 전체 과정에서
 * 발생할 수 있는 모든 상태를 관리합니다.
 * 
 * 주요 구성 요소:
 * - DISCONNECT: 연결 끊김
 * - NOT_SUPPORTED_WAYPOINT: 지원되지 않는 웨이포인트
 * - WAYLINE_PREPARING: 웨이라인 준비 중
 * - WAYLINE_UPLOADING: 웨이라인 파일 업로드 중
 * - DRONE_PREPARING: 드론 준비 중
 * - ARRIVE_FIRST_WAYPOINT: 첫 번째 웨이포인트 도착
 * - WAYLINE_EXECUTING: 웨이라인 실행 중
 * - WAYLINE_BROKEN: 웨이라인 중단
 * - WAYLINE_RECOVER: 웨이라인 복구
 * - WAYLINE_END: 웨이라인 종료
 * 
 * 이 열거형은 웨이라인 비행 미션의 현재 상태를 추적하고
 * 적절한 처리 로직을 적용하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/11
 */
public enum WaylineMissionStateEnum {

    /**
     * 연결 끊김
     * 
     * 드론과의 연결이 끊어진 상태입니다.
     * 통신 문제나 네트워크 오류로 인해 발생할 수 있습니다.
     */
    DISCONNECT(0, "연결 끊김"),

    /**
     * 지원되지 않는 웨이포인트
     * 
     * 현재 드론이 지원하지 않는 웨이포인트가 포함된 상태입니다.
     * 드론의 하드웨어나 펌웨어 제한으로 인해 발생할 수 있습니다.
     */
    NOT_SUPPORTED_WAYPOINT(1, "이 웨이포인트를 지원하지 않습니다"),

    /**
     * 웨이라인 준비 중
     * 
     * 웨이라인이 준비된 상태입니다. 파일을 업로드할 수 있고
     * 업로드된 파일을 실행할 수 있습니다.
     */
    WAYLINE_PREPARING(2, "웨이라인이 준비되었습니다. 파일을 업로드할 수 있고 업로드된 파일을 실행할 수 있습니다."),

    /**
     * 웨이라인 파일 업로드 중
     * 
     * 웨이라인 파일이 현재 업로드되고 있는 상태입니다.
     */
    WAYLINE_UPLOADING(3, "웨이라인 파일이 업로드 중입니다"),

    /**
     * 드론 준비 중
     * 
     * 시작 명령이 트리거되었습니다. 드론이 웨이라인을 읽고 있습니다.
     * 아직 시작하지 않았으며 준비 중입니다.
     */
    DRONE_PREPARING(4, "시작 명령이 트리거되었습니다. 드론이 웨이라인을 읽고 있습니다. 아직 시작하지 않았으며 준비 중입니다."),

    /**
     * 첫 번째 웨이포인트 도착
     * 
     * 웨이라인에 진입하여 첫 번째 웨이포인트에 도착한 상태입니다.
     */
    ARRIVE_FIRST_WAYPOINT(5, "웨이라인에 진입하여 첫 번째 웨이포인트에 도착했습니다"),

    /**
     * 웨이라인 실행 중
     * 
     * 웨이라인이 현재 실행되고 있는 상태입니다.
     */
    WAYLINE_EXECUTING(6, "웨이라인을 실행 중입니다"),

    /**
     * 웨이라인 중단
     * 
     * 웨이라인이 중단된 상태입니다. 트리거 이유:
     * 1. 사용자가 웨이라인을 일시정지함
     * 2. 비행 제어가 비정상임
     */
    WAYLINE_BROKEN(7, "웨이라인이 중단되었습니다. 트리거 이유: 1. 사용자가 웨이라인을 일시정지함 2. 비행 제어가 비정상임"),

    /**
     * 웨이라인 복구
     * 
     * 웨이라인이 복구되고 있는 상태입니다.
     */
    WAYLINE_RECOVER(8, "웨이라인이 복구 중입니다"),

    /**
     * 웨이라인 종료
     * 
     * 웨이라인이 종료된 상태입니다.
     */
    WAYLINE_END(9, "웨이라인이 종료되었습니다");

    /**
     * 상태 값
     * 
     * 각 상태를 구분하는 정수 값입니다.
     */
    private final int state;

    /**
     * 상태 메시지
     * 
     * 각 상태에 대한 설명 메시지입니다.
     */
    private final String msg;

    /**
     * 웨이라인 미션 상태 열거형 생성자
     * 
     * @param state 상태 값
     * @param msg 상태 메시지
     */
    WaylineMissionStateEnum(int state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    /**
     * 상태 값을 반환합니다.
     * 
     * @return 상태 값
     */
    @JsonValue
    public int getState() {
        return state;
    }

    /**
     * 상태 메시지를 반환합니다.
     * 
     * @return 상태 메시지
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 상태 값으로 웨이라인 미션 상태를 찾습니다.
     * 
     * 주어진 상태 값에 해당하는 열거형을 반환합니다.
     * 해당하는 상태가 없으면 CloudSDKException을 발생시킵니다.
     * 
     * @param state 찾을 상태 값
     * @return 해당하는 WaylineMissionStateEnum 열거형
     * @throws CloudSDKException 해당하는 상태가 없을 경우
     */
    @JsonCreator
    public static WaylineMissionStateEnum find(int state) {
        return Arrays.stream(values()).filter(stateEnum -> stateEnum.state == state).findAny()
            .orElseThrow(() -> new CloudSDKException(WaylineMissionStateEnum.class, state));
    }

}
