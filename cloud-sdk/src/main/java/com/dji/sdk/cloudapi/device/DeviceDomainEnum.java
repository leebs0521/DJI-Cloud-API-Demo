package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Arrays;

/**
 * 디바이스 도메인 열거형 클래스
 * 
 * 이 클래스는 DJI 디바이스의 도메인을 정의합니다.
 * 각 도메인은 정수값으로 표현되며, 디바이스의 기본 분류를 나타냅니다.
 * 
 * @author sean.zhou
 * @date 2021/11/15
 * @version 0.1
 */
@Schema(description = "device domain", enumAsRef = true)
public enum DeviceDomainEnum {

    /**
     * 드론 도메인 (정수값: 0)
     */
    DRONE(0),

    /**
     * 페이로드 도메인 (정수값: 1)
     */
    PAYLOAD(1),

    /**
     * 리모트 컨트롤 도메인 (정수값: 2)
     */
    REMOTER_CONTROL(2),

    /**
     * 도크 도메인 (정수값: 3)
     */
    DOCK (3),

    ;

    /**
     * 도메인 정수값
     */
    private final int domain;

    /**
     * 디바이스 도메인 열거형 생성자
     * 
     * @param domain 도메인 정수값
     */
    DeviceDomainEnum(int domain) {
        this.domain = domain;
    }

    /**
     * 정수값으로 디바이스 도메인을 찾습니다.
     * 
     * @param domain 찾을 도메인 정수값
     * @return 찾은 디바이스 도메인 열거형
     * @throws CloudSDKException 해당하는 도메인을 찾을 수 없는 경우
     */
    @JsonCreator
    public static DeviceDomainEnum find(int domain) {
        return Arrays.stream(values()).filter(domainEnum -> domainEnum.domain == domain).findAny()
                .orElseThrow(() -> new CloudSDKException(DeviceDomainEnum.class, domain));
    }

    /**
     * 도메인 정수값을 반환합니다.
     * 
     * @return 도메인 정수값
     */
    @JsonValue
    public int getDomain() {
        return domain;
    }
}
