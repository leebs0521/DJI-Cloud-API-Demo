package com.dji.sdk.cloudapi.wayline;

import com.dji.sdk.common.BaseModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 비행 작업 실행 요청 클래스
 * 
 * 이 클래스는 웨이라인 비행 작업을 실행하기 위한
 * 요청 데이터를 정의합니다.
 * 
 * 주요 구성 요소:
 * - flightId: 작업 ID
 * 
 * 이 클래스는 기존에 생성된 웨이라인 비행 작업을
 * 실행할 때 필요한 정보를 포함합니다.
 * 
 * @author sean
 * @version 1.1
 * @date 2022/6/1
 */
public class FlighttaskExecuteRequest extends BaseModel {

    /**
     * 작업 ID
     * 
     * 실행할 비행 작업의 고유 식별자입니다.
     * 작업을 구분하고 추적하는 데 사용됩니다.
     * 
     * 제약 조건: 특수 문자 < > : " / | ? * . _ \ 를 포함할 수 없음
     */
    @NotNull
    @Pattern(regexp = "^[^<>:\"/|?*._\\\\]+$")
    private String flightId;

    public FlighttaskExecuteRequest() {
    }

    @Override
    public String toString() {
        return "FlighttaskExecuteRequest{" +
                "flightId='" + flightId + '\'' +
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
    public FlighttaskExecuteRequest setFlightId(String flightId) {
        this.flightId = flightId;
        return this;
    }
}
