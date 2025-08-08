package com.dji.sdk.cloudapi.map;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 오프라인 맵 동기화 상태 열거형
 * 
 * 이 열거형은 오프라인 맵 파일 동기화의 상태를 정의합니다.
 * 각 상태는 문자열 값으로 표현되며, JSON 직렬화/역직렬화를 지원합니다.
 * 
 * 지원하는 상태:
 * - WAIT_SYNC: 동기화 대기 상태
 * - SYNCHRONIZING: 동기화 진행 중
 * - SYNCHRONIZED: 동기화 완료
 * - FAIL: 동기화 실패
 * - SWITCH_FAIL: 전환 실패
 * 
 * 이 열거형은 오프라인 맵 동기화 과정을 모니터링하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/20
 */
public enum OfflineMapSyncStatusEnum {

    /**
     * 동기화 대기
     * 
     * 오프라인 맵 동기화가 대기 중인 상태입니다.
     * 동기화 작업이 예약되었지만 아직 시작되지 않았습니다.
     */
    WAIT_SYNC("wait_sync"),

    /**
     * 동기화 진행 중
     * 
     * 오프라인 맵 파일을 다운로드하고 적용하는 과정이 진행 중인 상태입니다.
     * 이 상태에서는 진행률을 모니터링할 수 있습니다.
     */
    SYNCHRONIZING("synchronizing"),

    /**
     * 동기화 완료
     * 
     * 오프라인 맵 동기화가 성공적으로 완료된 상태입니다.
     * 드론이나 도킹 스테이션에서 오프라인 맵을 사용할 수 있습니다.
     */
    SYNCHRONIZED("synchronized"),

    /**
     * 동기화 실패
     * 
     * 오프라인 맵 동기화가 실패한 상태입니다.
     * 네트워크 문제, 파일 손상, 저장 공간 부족 등의 이유로 실패할 수 있습니다.
     */
    FAIL("fail"),

    /**
     * 전환 실패
     * 
     * 오프라인 맵 전환 과정에서 실패한 상태입니다.
     * 기존 맵에서 새 맵으로 전환하는 과정에서 문제가 발생했습니다.
     */
    SWITCH_FAIL("switch_fail"),

    ;

    /**
     * 상태의 문자열 값
     */
    private final String status;

    OfflineMapSyncStatusEnum(String status) {
        this.status = status;
    }

    /**
     * 상태의 문자열 값을 반환합니다.
     * JSON 직렬화 시 이 값이 사용됩니다.
     * 
     * @return 상태 문자열 값
     */
    @JsonValue
    public String getStatus() {
        return status;
    }

    /**
     * 상태 문자열로 열거형을 찾습니다.
     * 
     * 이 메서드는 문자열 값에 해당하는 열거형을 반환합니다.
     * JSON 역직렬화 시 사용되며, @JsonCreator 어노테이션이 적용되어 있습니다.
     * 
     * @param status 상태 문자열 값
     * @return 해당하는 열거형
     * @throws CloudSDKException 해당하는 상태가 없는 경우
     */
    @JsonCreator
    public static OfflineMapSyncStatusEnum find(String status) {
        return Arrays.stream(values()).filter(statusEnum -> statusEnum.status.equals(status)).findAny()
            .orElseThrow(() -> new CloudSDKException(OfflineMapSyncStatusEnum.class, status));
    }
}
