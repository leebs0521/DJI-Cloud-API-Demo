package com.dji.sdk.cloudapi.log;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 파일 업로드 상태를 정의하는 열거형
 * 로그 파일 업로드 과정의 다양한 상태를 정의합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/29
 */
public enum FileUploadStatusEnum {

    /** 파일 가져오기 중 */
    FILE_PULL("file_pull", false),

    /** 파일 압축 중 */
    FILE_ZIP("file_zip", false),

    /** 파일 업로드 중 */
    FILE_UPLOADING("file_uploading", false),

    /** 전송됨 */
    SENT("sent", false),

    /** 진행 중 */
    IN_PROGRESS("in_progress", false),

    /** 성공 */
    OK("ok", true),

    /** 일시정지 */
    PAUSED("paused", false),

    /** 거부됨 */
    REJECTED("rejected", true),

    /** 실패 */
    FAILED("failed", true),

    /** 취소됨 */
    CANCELED("canceled", true),

    /** 시간 초과 */
    TIMEOUT("timeout", true);

    /** 상태를 나타내는 문자열 값 */
    private final String status;

    /** 종료 상태 여부 */
    private final boolean end;

    /**
     * 파일 업로드 상태 열거형 생성자
     * @param status 상태를 나타내는 문자열 값
     * @param end 종료 상태 여부
     */
    FileUploadStatusEnum(String status, boolean end) {
        this.status = status;
        this.end = end;
    }

    /**
     * 종료 상태 여부를 반환합니다.
     * 
     * @return 종료 상태 여부
     */
    public boolean isEnd() {
        return end;
    }

    /**
     * 상태 값을 반환합니다.
     * JSON 직렬화 시 사용됩니다.
     * 
     * @return 상태 문자열 값
     */
    @JsonValue
    public String getStatus() {
        return status;
    }

    /**
     * 문자열 값으로 파일 업로드 상태를 찾습니다.
     * JSON 역직렬화 시 사용됩니다.
     * 
     * @param status 찾을 상태의 문자열 값
     * @return 해당하는 FileUploadStatusEnum 인스턴스
     * @throws CloudSDKException 지정된 상태가 존재하지 않을 경우
     */
    @JsonCreator
    public static FileUploadStatusEnum find(String status) {
        return Arrays.stream(values()).filter(statusEnum -> statusEnum.status.equals(status)).findAny()
                .orElseThrow(() -> new CloudSDKException(FileUploadStatusEnum.class, status));
    }
}
