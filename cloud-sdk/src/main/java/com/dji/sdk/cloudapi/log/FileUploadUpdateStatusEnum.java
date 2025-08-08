package com.dji.sdk.cloudapi.log;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 파일 업로드 업데이트 상태를 정의하는 열거형
 * 파일 업로드 상태 업데이트 시 사용할 수 있는 상태를 정의합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/29
 */
public enum FileUploadUpdateStatusEnum {

    /** 취소 */
    CANCEL("cancel");

    /** 상태를 나타내는 문자열 값 */
    private final String status;

    /**
     * 파일 업로드 업데이트 상태 열거형 생성자
     * @param status 상태를 나타내는 문자열 값
     */
    FileUploadUpdateStatusEnum(String status) {
        this.status = status;
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
     * 문자열 값으로 파일 업로드 업데이트 상태를 찾습니다.
     * JSON 역직렬화 시 사용됩니다.
     * 
     * @param status 찾을 상태의 문자열 값
     * @return 해당하는 FileUploadUpdateStatusEnum 인스턴스
     * @throws CloudSDKException 지정된 상태가 존재하지 않을 경우
     */
    @JsonCreator
    public static FileUploadUpdateStatusEnum find(String status) {
        return Arrays.stream(values()).filter(statusEnum -> statusEnum.status.equals(status)).findAny()
                .orElseThrow(() -> new CloudSDKException(FileUploadUpdateStatusEnum.class, status));
    }
}
