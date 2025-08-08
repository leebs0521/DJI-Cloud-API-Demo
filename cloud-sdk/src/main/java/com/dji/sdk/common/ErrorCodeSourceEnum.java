
package com.dji.sdk.common;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 오류 코드 소스 열거형
 * 
 * 이 열거형은 SDK에서 발생하는 오류의 소스를 정의합니다.
 * 각 소스는 고유한 정수 값으로 식별됩니다.
 * 
 * 주요 구성 요소:
 * - DEVICE(3): 디바이스 관련 오류
 * - DOCK(5): 도크 관련 오류
 * - PILOT(6): 파일럿 관련 오류
 * 
 * 이 열거형은 오류의 출처를 명확히 구분하여
 * 적절한 오류 처리 로직을 적용하는 데 사용됩니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/25
 */
public enum ErrorCodeSourceEnum {

    /**
     * 디바이스 관련 오류
     * 
     * 드론, 카메라 등 디바이스에서 발생하는 오류입니다.
     */
    DEVICE(3),

    /**
     * 도크 관련 오류
     * 
     * 도크 시스템에서 발생하는 오류입니다.
     */
    DOCK(5),

    /**
     * 파일럿 관련 오류
     * 
     * 파일럿 앱에서 발생하는 오류입니다.
     */
    PILOT(6);

    /**
     * 오류 소스 값
     * 
     * 각 오류 소스를 구분하는 정수 값입니다.
     */
    private final int source;

    /**
     * 오류 코드 소스 열거형 생성자
     * 
     * @param source 오류 소스 값
     */
    ErrorCodeSourceEnum(int source) {
        this.source = source;
    }

    /**
     * 오류 소스 값을 반환합니다.
     * 
     * @return 오류 소스 값
     */
    @JsonValue
    public int getSource() {
        return source;
    }

    /**
     * 오류 소스 값으로 오류 코드 소스를 찾습니다.
     * 
     * 주어진 소스 값에 해당하는 열거형을 반환합니다.
     * 해당하는 소스가 없으면 CloudSDKException을 발생시킵니다.
     * 
     * @param source 찾을 오류 소스 값
     * @return 해당하는 ErrorCodeSourceEnum 열거형
     * @throws CloudSDKException 해당하는 소스가 없을 경우
     */
    @JsonCreator
    public static ErrorCodeSourceEnum find(int source) {
        return Arrays.stream(values()).filter(error -> error.source == source).findAny()
                .orElseThrow(() -> new CloudSDKException(ErrorCodeSourceEnum.class, source));
    }
}
