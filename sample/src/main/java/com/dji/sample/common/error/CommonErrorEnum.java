package com.dji.sample.common.error;

import com.dji.sdk.common.IErrorInfo;

/**
 * 공통 에러 코드 열거형 클래스
 * 
 * 이 클래스는 샘플 애플리케이션에서 사용되는 공통 에러 코드들을 정의합니다.
 * 각 에러 코드는 고유한 코드 번호와 에러 메시지를 가지며, IErrorInfo 인터페이스를 구현합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/25
 */
public enum CommonErrorEnum implements IErrorInfo {

    /** 잘못된 인수 에러 */
    ILLEGAL_ARGUMENT(200001, "illegal argument"),

    /** Redis 데이터를 찾을 수 없음 */
    REDIS_DATA_NOT_FOUND(201404, "Redis data does not exist."),

    /** 디바이스 오프라인 에러 */
    DEVICE_OFFLINE(212015, "Device is offline."),

    /** 조직 정보 조회 실패 */
    GET_ORGANIZATION_FAILED(210230, "Failed to get organization."),

    /** 디바이스 바인딩 실패 */
    DEVICE_BINDING_FAILED(210231, "Failed to bind device."),

    /** 중복 바인딩 불가 에러 */
    NON_REPEATABLE_BINDING(210232, "The device has been bound to another organization and can't be bound repeatedly."),

    /** 디바이스 바인딩 상태 조회 실패 */
    GET_DEVICE_BINDING_STATUS_FAILED(210233, "Failed to get device binding status."),

    /** 시스템 에러 */
    SYSTEM_ERROR(600500, "system error"),

    /** 시크릿 키 무효 */
    SECRET_INVALID(600100, "secret invalid"),

    /** 토큰이 null */
    NO_TOKEN(600101, "token is null"),

    /** 토큰 만료 */
    TOKEN_EXPIRED(600102, "token is expired"),

    /** 토큰 무효 */
    TOKEN_INVALID(600103, "token invalid"),

    /** 서명 무효 */
    SIGN_INVALID(600104, "sign invalid");

    /** 에러 메시지 */
    private String msg;

    /** 에러 코드 */
    private int code;

    /**
     * 공통 에러 코드 열거형 생성자
     * 
     * @param code 에러 코드
     * @param msg 에러 메시지
     */
    CommonErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 에러 메시지를 반환합니다.
     * 
     * @return 에러 메시지
     */
    @Override
    public String getMessage() {
        return this.msg;
    }

    /**
     * 에러 코드를 반환합니다.
     * 
     * @return 에러 코드
     */
    @Override
    public Integer getCode() {
        return this.code;
    }

}
