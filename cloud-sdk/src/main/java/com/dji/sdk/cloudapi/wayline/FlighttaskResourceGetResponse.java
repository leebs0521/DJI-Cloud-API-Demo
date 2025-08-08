package com.dji.sdk.cloudapi.wayline;

import com.dji.sdk.common.BaseModel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 비행 작업 리소스 조회 응답 클래스
 * 
 * 이 클래스는 웨이라인 비행 작업에 필요한 리소스 조회 결과를 정의합니다.
 * 웨이라인 파일 객체를 포함하여 비행 작업 실행에 필요한
 * 모든 리소스 정보를 제공합니다.
 * 
 * 주요 구성 요소:
 * - file: 웨이라인 파일 객체
 * 
 * 이 클래스는 비행 작업 실행에 필요한 리소스 정보를
 * 클라이언트에게 제공하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/6
 */
public class FlighttaskResourceGetResponse extends BaseModel {

    /**
     * 웨이라인 파일 객체
     * 
     * 비행 작업 실행에 필요한 웨이라인 파일의 정보를 포함합니다.
     * 파일 URL, 서명 등의 정보가 포함되어 안전한 파일 다운로드를 보장합니다.
     */
    @NotNull
    @Valid
    private FlighttaskFile file;

    public FlighttaskResourceGetResponse() {}

    @Override
    public String toString() {
        return "FlighttaskResourceGetResponse{" +
                "file=" + file +
                '}';
    }

    /**
     * 웨이라인 파일 객체를 반환합니다.
     * 
     * @return 웨이라인 파일 객체
     */
    public FlighttaskFile getFile() {
        return file;
    }

    /**
     * 웨이라인 파일 객체를 설정합니다.
     * 
     * @param file 웨이라인 파일 객체
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FlighttaskResourceGetResponse setFile(FlighttaskFile file) {
        this.file = file;
        return this;
    }
}