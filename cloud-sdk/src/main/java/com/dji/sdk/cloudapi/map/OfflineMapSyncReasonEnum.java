package com.dji.sdk.cloudapi.map;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 오프라인 맵 동기화 결과 이유 열거형
 * 
 * 이 열거형은 오프라인 맵 동기화의 결과와 실패 이유를 정의합니다.
 * 각 이유는 숫자 코드와 설명 메시지를 가지고 있어서
 * 디버깅과 사용자 피드백에 유용합니다.
 * 
 * 지원하는 이유:
 * - SUCCESS: 동기화 성공
 * - PARSE_FILE_FAILED: 파일 정보 파싱 실패
 * - OBTAIN_DRONE_FILE_FAILED: 드론 파일 정보 획득 실패
 * - DOWNLOAD_FILE_FAILED: 파일 다운로드 실패
 * - LINK_ROLLOVER_FAILED: 링크 롤오버 실패
 * - FILE_TRANSFER_FAILED: 파일 전송 실패
 * - DISABLE_OFFLINE_MAP_FAILED: 오프라인 맵 비활성화 실패
 * - DELETE_FILE_FAILED: 파일 삭제 실패
 * - LOAD_FILE_FAILED: 파일 로드 실패
 * - ENABLE_OFFLINE_MAP_FAILED: 오프라인 맵 활성화 실패
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/20
 */
public enum OfflineMapSyncReasonEnum {

    /**
     * 동기화 성공
     * 
     * 오프라인 맵 동기화가 성공적으로 완료되었습니다.
     * 드론이나 도킹 스테이션에서 오프라인 맵을 사용할 수 있습니다.
     */
    SUCCESS(0, "success"),

    /**
     * 파일 정보 파싱 실패
     * 
     * 클라우드에서 반환된 파일 정보를 파싱하는 데 실패했습니다.
     * JSON 형식 오류나 필수 필드 누락 등의 이유로 발생할 수 있습니다.
     */
    PARSE_FILE_FAILED(1, "Failed to parse the file information returned by the cloud."),

    /**
     * 드론 파일 정보 획득 실패
     * 
     * 항공기의 파일 정보를 획득하는 데 실패했습니다.
     * 드론과의 통신 문제나 파일 시스템 접근 오류 등의 이유로 발생할 수 있습니다.
     */
    OBTAIN_DRONE_FILE_FAILED(2, "Failed to obtain aircraft file information."),

    /**
     * 파일 다운로드 실패
     * 
     * 클라우드에서 파일을 다운로드하는 데 실패했습니다.
     * 네트워크 연결 문제, 서버 오류, 저장 공간 부족 등의 이유로 발생할 수 있습니다.
     */
    DOWNLOAD_FILE_FAILED(3, "Failed to download file from cloud."),

    /**
     * 링크 롤오버 실패
     * 
     * 링크 롤오버 과정에서 실패했습니다.
     * 기존 맵에서 새 맵으로 전환하는 과정에서 문제가 발생했습니다.
     */
    LINK_ROLLOVER_FAILED(4, "Failed to rollover the link."),

    /**
     * 파일 전송 실패
     * 
     * 파일을 전송하는 데 실패했습니다.
     * 네트워크 문제, 파일 손상, 권한 문제 등의 이유로 발생할 수 있습니다.
     */
    FILE_TRANSFER_FAILED(5, "Failed to transfer file."),

    /**
     * 오프라인 맵 비활성화 실패
     * 
     * 기존 오프라인 맵을 비활성화하는 데 실패했습니다.
     * 파일 시스템 접근 오류나 권한 문제 등의 이유로 발생할 수 있습니다.
     */
    DISABLE_OFFLINE_MAP_FAILED(6, "Failed to disable offline map."),

    /**
     * 파일 삭제 실패
     * 
     * 기존 파일을 삭제하는 데 실패했습니다.
     * 파일이 사용 중이거나 권한 문제 등의 이유로 발생할 수 있습니다.
     */
    DELETE_FILE_FAILED(7, "Failed to delete file."),

    /**
     * 파일 로드 실패
     * 
     * 디바이스에서 파일을 로드하는 데 실패했습니다.
     * 파일 손상, 형식 오류, 메모리 부족 등의 이유로 발생할 수 있습니다.
     */
    LOAD_FILE_FAILED(8, "Failed to load the file on the device side."),

    /**
     * 오프라인 맵 활성화 실패
     * 
     * 새 오프라인 맵을 활성화하는 데 실패했습니다.
     * 파일 시스템 접근 오류나 설정 문제 등의 이유로 발생할 수 있습니다.
     */
    ENABLE_OFFLINE_MAP_FAILED(9, "Failed to enable offline map."),

    ;

    /**
     * 결과 이유의 숫자 코드
     */
    private final int reason;

    /**
     * 결과 이유의 설명 메시지
     */
    private final String msg;

    OfflineMapSyncReasonEnum(int reason, String msg) {
        this.reason = reason;
        this.msg = msg;
    }

    /**
     * 결과 이유의 숫자 코드를 반환합니다.
     * JSON 직렬화 시 이 값이 사용됩니다.
     * 
     * @return 결과 이유 코드
     */
    @JsonValue
    public int getReason() {
        return reason;
    }

    /**
     * 결과 이유의 설명 메시지를 반환합니다.
     * 
     * @return 설명 메시지
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 결과 이유 코드로 열거형을 찾습니다.
     * 
     * 이 메서드는 숫자 코드에 해당하는 열거형을 반환합니다.
     * JSON 역직렬화 시 사용되며, @JsonCreator 어노테이션이 적용되어 있습니다.
     * 
     * @param reason 결과 이유 코드
     * @return 해당하는 열거형
     * @throws CloudSDKException 해당하는 이유가 없는 경우
     */
    @JsonCreator
    public static OfflineMapSyncReasonEnum find(int reason) {
        return Arrays.stream(values()).filter(reasonEnum -> reasonEnum.reason == reason).findAny()
            .orElseThrow(() -> new CloudSDKException(OfflineMapSyncReasonEnum.class, reason));
    }

}
