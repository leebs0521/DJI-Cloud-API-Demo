package com.dji.sdk.cloudapi.flightarea;

import com.dji.sdk.common.BaseModel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 비행 구역 조회 응답 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 비행 구역 조회 요청에 대한 응답을 정의합니다.
 * 비행 구역 파일 목록을 포함하여 조회된 비행 구역 정보를 반환합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/17
 */
public class FlightAreasGetResponse extends BaseModel {

    /**
     * 파일 목록 (필수)
     * 조회된 비행 구역 파일들의 목록
     */
    @NotNull
    private List<@Valid FlightAreaGetFile> files;

    /**
     * 기본 생성자
     */
    public FlightAreasGetResponse() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "FlightAreasGetResponse{" +
                "files=" + files +
                '}';
    }

    /**
     * 파일 목록을 반환합니다.
     * 
     * @return 파일 목록
     */
    public List<FlightAreaGetFile> getFiles() {
        return files;
    }

    /**
     * 파일 목록을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param files 설정할 파일 목록
     * @return 현재 FlightAreasGetResponse 객체
     */
    public FlightAreasGetResponse setFiles(List<FlightAreaGetFile> files) {
        this.files = files;
        return this;
    }
}
