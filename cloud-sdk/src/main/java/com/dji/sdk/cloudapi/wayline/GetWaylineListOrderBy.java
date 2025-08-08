package com.dji.sdk.cloudapi.wayline;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.validation.constraints.NotNull;

/**
 * 웨이라인 목록 정렬 조건 클래스
 * 
 * 이 클래스는 웨이라인 파일 목록 조회 시 정렬 조건을 정의합니다.
 * 정렬 기준 컬럼과 정렬 방향(오름차순/내림차순)을 포함하여
 * 원하는 순서로 웨이라인 파일 목록을 조회할 수 있습니다.
 * 
 * 주요 구성 요소:
 * - column: 정렬 기준 컬럼
 * - desc: 내림차순 여부
 * 
 * 이 클래스는 웨이라인 파일 목록의 정렬 조건을
 * 설정하고 관리하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/25
 */
public class GetWaylineListOrderBy {

    /**
     * 정렬 기준 컬럼
     * 
     * 웨이라인 파일 목록을 정렬할 기준이 되는 컬럼입니다.
     * 파일명, 업데이트 시간, 생성 시간 등을 기준으로 정렬할 수 있습니다.
     */
    @NotNull
    private OrderByColumnEnum column;

    /**
     * 내림차순 여부
     * 
     * 정렬 방향을 나타냅니다.
     * true: 내림차순 (desc)
     * false: 오름차순 (asc)
     */
    private boolean desc;

    /**
     * 웨이라인 목록 정렬 조건 생성자
     * 
     * 문자열 형태의 정렬 조건을 파싱하여 객체를 생성합니다.
     * "column_name direction" 형태의 문자열을 받아서 처리합니다.
     * 
     * @param orderBy 정렬 조건 문자열 (예: "update_time desc")
     */
    @JsonCreator
    public GetWaylineListOrderBy(String orderBy) {
        String[] arr = orderBy.split(" ");
        this.column = OrderByColumnEnum.find(arr[0]);
        this.desc = arr.length > 1 && arr[1].contains("desc");
    }

    /**
     * 정렬 조건을 문자열로 반환합니다.
     * 
     * @return 정렬 조건 문자열 (예: "update_time desc")
     */
    @Override
    @JsonValue
    public String toString() {
        return column.getColumn() + (desc ? " desc" : " asc");
    }

    /**
     * 정렬 기준 컬럼을 반환합니다.
     * 
     * @return 정렬 기준 컬럼
     */
    public OrderByColumnEnum getColumn() {
        return column;
    }

    /**
     * 정렬 기준 컬럼을 설정합니다.
     * 
     * @param column 정렬 기준 컬럼
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public GetWaylineListOrderBy setColumn(OrderByColumnEnum column) {
        this.column = column;
        return this;
    }

    /**
     * 내림차순 여부를 반환합니다.
     * 
     * @return 내림차순 여부
     */
    public boolean isDesc() {
        return desc;
    }

    /**
     * 내림차순 여부를 설정합니다.
     * 
     * @param desc 내림차순 여부
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public GetWaylineListOrderBy setDesc(boolean desc) {
        this.desc = desc;
        return this;
    }
}
