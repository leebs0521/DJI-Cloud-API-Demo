package com.dji.sdk.cloudapi.flightarea;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 비행 구역 동기화 이유 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 비행 구역 동기화의 결과 이유를 정의합니다.
 * 클라우드에서 디바이스로 비행 구역 파일을 동기화할 때 발생할 수 있는
 * 다양한 성공/실패 상황과 그에 대한 상세한 이유를 포함합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/17
 */
public enum FlightAreaSyncReasonEnum {

    /**
     * 성공
     * 비행 구역 동기화가 성공적으로 완료됨
     */
    SUCCESS(0, "success"),

    /**
     * 파일 파싱 실패
     * 클라우드에서 반환된 파일 정보를 파싱하는 과정에서 실패
     */
    PARSE_FILE_FAILED(1, "Failed to parse file information returned from the cloud."),

    /**
     * 파일 검색 실패
     * 항공기에서 파일 정보를 검색하는 과정에서 실패
     */
    RETRIEVE_FILE_FAILED(2, "Failed to retrieve file information from the aircraft's end."),

    /**
     * 파일 다운로드 실패
     * 클라우드에서 파일을 다운로드하는 과정에서 실패
     */
    DOWNLOAD_FILE_FAILED(3, "Failed to download the file from the cloud."),

    /**
     * 링크 전환 실패
     * 네트워크 링크 전환 과정에서 실패
     */
    LINK_FLIPPING_FAILED(4, "Link flipping failed."),

    /**
     * 파일 전송 실패
     * 파일을 디바이스로 전송하는 과정에서 실패
     */
    FILE_TRANSMISSION_FAILED(5, "File transmission failed."),

    /**
     * 비활성화 실패
     * 비행 구역 기능을 비활성화하는 과정에서 실패
     */
    DISABLE_FAILED(6, "Filed to disable."),

    /**
     * 파일 삭제 실패
     * 기존 파일을 삭제하는 과정에서 실패
     */
    FILE_DELETION_FAILED(7, "File deletion failed."),

    /**
     * 파일 로딩 실패
     * 드론에서 파일을 로딩하는 과정에서 실패
     */
    FILE_LOADING_FAILED(8, "Failed to load file on drone."),

    /**
     * 활성화 실패
     * 비행 구역 기능을 활성화하는 과정에서 실패
     */
    ENABLE_FAILED(9, "Filed to enable."),

    /**
     * 향상된 이미지 전송 끄기 실패
     * 향상된 이미지 전송 기능을 끄는 과정에서 실패
     */
    TURN_OFF_ENHANCED_FAILED(10, "Failed to turn off enhanced image transmission."),

    /**
     * 전원 켜기 실패
     * 드론의 전원을 켜는 과정에서 실패
     */
    POWER_ON_FAILED(11, "Failed to power on the drone."),

    /**
     * 체크섬 검증 실패
     * 파일의 무결성을 검증하는 체크섬 확인 과정에서 실패
     */
    CHECK_FAILED(12, "The checksum check failed."),

    /**
     * 동기화 타임아웃
     * 동기화 과정에서 예외가 발생하여 타임아웃됨
     */
    SYNCHRONIZATION_TIMED_OUT(13, "Synchronization exception timed out."),

    ;

    /**
     * 동기화 이유 코드
     * 각 동기화 결과에 대한 고유한 숫자 코드
     */
    private final int reason;

    /**
     * 동기화 이유 메시지
     * 각 동기화 결과에 대한 상세한 설명 메시지
     */
    private final String msg;

    /**
     * 비행 구역 동기화 이유 열거형 생성자
     * 
     * @param reason 동기화 이유 코드
     * @param msg 동기화 이유 메시지
     */
    FlightAreaSyncReasonEnum(int reason, String msg) {
        this.reason = reason;
        this.msg = msg;
    }

    /**
     * 동기화 이유 코드를 반환합니다.
     * JSON 직렬화 시 사용됩니다.
     * 
     * @return 동기화 이유 코드
     */
    @JsonValue
    public int getReason() {
        return reason;
    }

    /**
     * 동기화 이유 메시지를 반환합니다.
     * 
     * @return 동기화 이유 메시지
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 코드로 비행 구역 동기화 이유를 찾습니다.
     * JSON 역직렬화 시 사용됩니다.
     * 
     * @param reason 찾을 동기화 이유 코드
     * @return 찾은 비행 구역 동기화 이유 열거형
     * @throws CloudSDKException 해당하는 이유를 찾을 수 없는 경우
     */
    @JsonCreator
    public static FlightAreaSyncReasonEnum find(int reason) {
        return Arrays.stream(values()).filter(reasonEnum -> reasonEnum.reason == reason).findAny()
            .orElseThrow(() -> new CloudSDKException(FlightAreaSyncReasonEnum.class, reason));
    }

}
