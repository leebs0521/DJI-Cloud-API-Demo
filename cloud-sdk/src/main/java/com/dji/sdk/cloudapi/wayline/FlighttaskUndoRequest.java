package com.dji.sdk.cloudapi.wayline;

import com.dji.sdk.common.BaseModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 비행 작업 취소 요청 클래스
 * 
 * 이 클래스는 웨이라인 비행 작업을 취소하기 위한 요청을 정의합니다.
 * 진행 중인 비행 작업들을 안전하게 중단하고 착륙시키는
 * 기능을 제공합니다.
 * 
 * 주요 구성 요소:
 * - flightIds: 취소할 비행 작업 ID 목록
 * 
 * 이 클래스는 비행 작업의 안전한 취소를 위한
 * 요청 데이터를 포함합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/7
 */
public class FlighttaskUndoRequest extends BaseModel {

    /**
     * 취소할 비행 작업 ID 목록
     * 
     * 취소할 비행 작업들의 고유 식별자 목록입니다.
     * 최소 1개 이상의 작업 ID가 포함되어야 하며,
     * 특수 문자 제한이 있습니다.
     * 
     * 제약 조건:
     * - 최소 1개 이상의 작업 ID 포함
     * - 특수 문자 제한 (<>:"/|?*._\\ 제외)
     * 
     * 예시: ["task_001", "task_002"]
     */
    @NotNull
    @Size(min = 1)
    private List<@Pattern(regexp = "^[^<>:\"/|?*._\\\\]+$") String> flightIds;

    public FlighttaskUndoRequest() {
    }

    @Override
    public String toString() {
        return "FlighttaskUndoRequest{" +
                "flightIds=" + flightIds +
                '}';
    }

    /**
     * 취소할 비행 작업 ID 목록을 반환합니다.
     * 
     * @return 취소할 비행 작업 ID 목록
     */
    public List<String> getFlightIds() {
        return flightIds;
    }

    /**
     * 취소할 비행 작업 ID 목록을 설정합니다.
     * 
     * @param flightIds 취소할 비행 작업 ID 목록
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FlighttaskUndoRequest setFlightIds(List<String> flightIds) {
        this.flightIds = flightIds;
        return this;
    }
}
