package com.dji.sdk.cloudapi.media;

import com.dji.sdk.common.BaseModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 비행 작업 미디어 우선순위 업로드 요청 클래스
 * 
 * 이 클래스는 특정 비행 작업의 미디어 파일을 우선적으로 업로드하기 위한
 * 요청 데이터를 정의합니다. BaseModel을 상속받아 유효성 검사를 지원합니다.
 * 
 * 주요 구성 요소:
 * - flightId: 비행 작업 ID (파일명에 사용할 수 없는 문자 제외)
 * 
 * 이 클래스는 중요한 비행 작업의 미디어 파일을 다른 파일보다
 * 먼저 업로드하도록 우선순위를 설정하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/27
 */
public class UploadFlighttaskMediaPrioritize extends BaseModel {

    /**
     * 비행 작업 ID
     * 
     * 우선순위를 설정할 비행 작업의 고유 식별자입니다.
     * 파일명에 사용할 수 없는 특수문자(< > : " / | ? * . _ \)를 제외한
     * 문자열만 허용됩니다.
     * 
     * 정규식 패턴: ^[^<>:"/|?*._\\]+$
     */
    @NotNull
    @Pattern(regexp = "^[^<>:\"/|?*._\\\\]+$")
    private String flightId;

    public UploadFlighttaskMediaPrioritize() {
    }

    @Override
    public String toString() {
        return "UploadFlighttaskMediaPrioritize{" +
                "flightId='" + flightId + '\'' +
                '}';
    }

    /**
     * 비행 작업 ID를 반환합니다.
     * 
     * @return 비행 작업 ID
     */
    public String getFlightId() {
        return flightId;
    }

    /**
     * 비행 작업 ID를 설정합니다.
     * 
     * @param flightId 비행 작업 ID
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public UploadFlighttaskMediaPrioritize setFlightId(String flightId) {
        this.flightId = flightId;
        return this;
    }
}
