package com.dji.sdk.cloudapi.wayline;

import com.dji.sdk.common.BaseModel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 비행 작업 생성 요청 클래스
 * 
 * 이 클래스는 웨이라인 비행 작업을 생성하기 위한
 * 요청 데이터를 정의합니다.
 * 
 * 주요 구성 요소:
 * - flightId: 작업 ID
 * - type: 작업 타입 (항상 "wayline")
 * - file: 웨이라인 비행 작업 파일 객체
 * 
 * 이 클래스는 새로운 웨이라인 비행 작업을 생성할 때
 * 필요한 모든 정보를 포함합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/23
 */
public class FlighttaskCreateRequest extends BaseModel {

    /**
     * 작업 ID
     * 
     * 생성할 비행 작업의 고유 식별자입니다.
     * 작업을 구분하고 추적하는 데 사용됩니다.
     */
    @NotNull
    private String flightId;

    /**
     * 작업 타입
     * 
     * 비행 작업의 타입을 나타냅니다.
     * 웨이라인 작업의 경우 항상 "wayline"입니다.
     * 
     * 제약 조건: 정규식 패턴 "^wayline$"
     */
    @NotNull
    @Pattern(regexp = "^wayline$")
    private String type = "wayline";

    /**
     * 웨이라인 비행 작업 파일 객체
     * 
     * 웨이라인 파일의 URL과 MD5 서명을 포함하는
     * 파일 정보 객체입니다.
     */
    @NotNull
    @Valid
    private FlighttaskCreateFile file;

    public FlighttaskCreateRequest() {}

    @Override
    public String toString() {
        return "FlighttaskCreateRequest{" +
                "flightId='" + flightId + '\'' +
                ", type='" + type + '\'' +
                ", file=" + file +
                '}';
    }

    /**
     * 작업 ID를 반환합니다.
     * 
     * @return 작업 ID
     */
    public String getFlightId() {
        return flightId;
    }

    /**
     * 작업 ID를 설정합니다.
     * 
     * @param flightId 작업 ID
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FlighttaskCreateRequest setFlightId(String flightId) {
        this.flightId = flightId;
        return this;
    }

    /**
     * 작업 타입을 반환합니다.
     * 
     * @return 작업 타입
     */
    public String getType() {
        return type;
    }

    /**
     * 작업 타입을 설정합니다.
     * 
     * @param type 작업 타입
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FlighttaskCreateRequest setType(String type) {
        this.type = type;
        return this;
    }

    /**
     * 웨이라인 비행 작업 파일 객체를 반환합니다.
     * 
     * @return 웨이라인 비행 작업 파일 객체
     */
    public FlighttaskCreateFile getFile() {
        return file;
    }

    /**
     * 웨이라인 비행 작업 파일 객체를 설정합니다.
     * 
     * @param file 웨이라인 비행 작업 파일 객체
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FlighttaskCreateRequest setFile(FlighttaskCreateFile file) {
        this.file = file;
        return this;
    }
}