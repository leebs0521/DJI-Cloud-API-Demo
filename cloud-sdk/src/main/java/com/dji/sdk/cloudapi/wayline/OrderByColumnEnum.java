package com.dji.sdk.cloudapi.wayline;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 정렬 컬럼 열거형
 * 
 * 이 열거형은 웨이라인 파일 목록 조회 시 정렬 기준이 되는 컬럼들을 정의합니다.
 * 파일명, 업데이트 시간, 생성 시간 등을 기준으로 정렬할 수 있습니다.
 * 
 * 주요 구성 요소:
 * - NAME: 파일명
 * - UPDATE_TIME: 업데이트 시간
 * - CREATE_TIME: 생성 시간
 * 
 * 이 열거형은 웨이라인 파일 목록을 다양한 기준으로
 * 정렬하여 사용자가 원하는 순서로 조회할 수 있도록 합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/25
 */
public enum OrderByColumnEnum {

    /**
     * 파일명
     * 
     * 웨이라인 파일의 이름을 기준으로 정렬합니다.
     * 알파벳 순서로 정렬할 수 있습니다.
     */
    NAME("name"),

    /**
     * 업데이트 시간
     * 
     * 웨이라인 파일의 마지막 업데이트 시간을 기준으로 정렬합니다.
     * 최신 수정된 파일부터 또는 오래된 파일부터 정렬할 수 있습니다.
     */
    UPDATE_TIME("update_time"),

    /**
     * 생성 시간
     * 
     * 웨이라인 파일의 생성 시간을 기준으로 정렬합니다.
     * 최근 생성된 파일부터 또는 오래된 파일부터 정렬할 수 있습니다.
     */
    CREATE_TIME("create_time");

    /**
     * 정렬 컬럼 값
     * 
     * 각 정렬 컬럼을 구분하는 문자열 값입니다.
     */
    private final String column;

    /**
     * 정렬 컬럼 열거형 생성자
     * 
     * @param column 정렬 컬럼 값
     */
    OrderByColumnEnum(String column) {
        this.column = column;
    }

    /**
     * 정렬 컬럼 값을 반환합니다.
     * 
     * @return 정렬 컬럼 값
     */
    @JsonValue
    public String getColumn() {
        return column;
    }

    /**
     * 정렬 컬럼 값으로 정렬 컬럼을 찾습니다.
     * 
     * 주어진 컬럼 값에 해당하는 열거형을 반환합니다.
     * 해당하는 컬럼이 없으면 CloudSDKException을 발생시킵니다.
     * 
     * @param column 찾을 정렬 컬럼 값
     * @return 해당하는 OrderByColumnEnum 열거형
     * @throws CloudSDKException 해당하는 컬럼이 없을 경우
     */
    @JsonCreator
    public static OrderByColumnEnum find(String column) {
        return Arrays.stream(values()).filter(columnEnum -> columnEnum.column.equals(column)).findAny()
            .orElseThrow(() -> new CloudSDKException(OrderByColumnEnum.class, column));
    }
}
