package com.dji.sample.map.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 비행 영역 작업 타입 열거형
 * 
 * DJI Cloud API에서 비행 영역에 대한 작업 타입을 정의하는 열거형입니다.
 * 이 열거형은 WebSocket을 통한 실시간 비행 영역 관리 작업을 분류하는 데 사용됩니다.
 * 
 * 1. 지원하는 작업 타입
 *    - ADD: 비행 영역 추가/생성
 *    - UPDATE: 비행 영역 수정/업데이트
 *    - DELETE: 비행 영역 삭제
 * 
 * 2. JSON 직렬화/역직렬화 지원
 *    - @JsonValue: JSON 직렬화 시 문자열 값 사용
 *    - @JsonCreator: JSON 역직렬화 시 문자열로부터 열거형 생성
 * 
 * 3. 작업 타입 검색 기능
 *    - find(String operation): 문자열 값으로 열거형 찾기
 *    - 지원하지 않는 작업의 경우 RuntimeException 발생
 * 
 * 주요 용도:
 * - WebSocket을 통한 실시간 비행 영역 작업 분류
 * - 비행 영역 CRUD 작업 타입 정의
 * - 실시간 협업 시 작업 타입 식별
 * - JSON API 응답/요청에서 작업 타입 처리
 * 
 * 사용 예시:
 * - WebSocket 메시지에서 작업 타입 식별
 * - 실시간 비행 영역 편집 작업 처리
 * - 다중 사용자 협업 시 작업 동기화
 * 
 * @author sean
 * @version 1.9
 * @date 2023/12/1
 */
public enum FlightAreaOpertaionEnum {

    /**
     * 비행 영역 추가/생성
     * 
     * 새로운 비행 영역을 생성하거나 기존 비행 영역을 추가하는 작업입니다.
     * ADD 작업은 다음과 같은 특징을 가집니다:
     * - 새로운 비행 영역 데이터 생성
     * - 비행 영역 속성 및 기하학적 정보 설정
     * - 워크스페이스에 비행 영역 등록
     * 
     * JSON 값: "add"
     */
    ADD("add"),

    /**
     * 비행 영역 수정/업데이트
     * 
     * 기존 비행 영역의 정보를 수정하거나 업데이트하는 작업입니다.
     * UPDATE 작업은 다음과 같은 특징을 가집니다:
     * - 기존 비행 영역 데이터 수정
     * - 비행 영역 속성 변경
     * - 기하학적 정보 업데이트
     * 
     * JSON 값: "update"
     */
    UPDATE("update"),

    /**
     * 비행 영역 삭제
     * 
     * 기존 비행 영역을 삭제하는 작업입니다.
     * DELETE 작업은 다음과 같은 특징을 가집니다:
     * - 비행 영역 데이터 완전 삭제
     * - 관련된 모든 정보 제거
     * - 워크스페이스에서 비행 영역 제거
     * 
     * JSON 값: "delete"
     */
    DELETE("delete")

    ;

    /**
     * 작업 타입의 문자열 값
     * 
     * JSON 직렬화/역직렬화에 사용되는 문자열 값입니다.
     * 각 열거형 상수는 고유한 문자열 값을 가집니다.
     */
    private final String operation;

    /**
     * 생성자
     * 
     * @param operation 작업 타입의 문자열 값
     */
    FlightAreaOpertaionEnum(String operation) {
        this.operation = operation;
    }

    /**
     * 작업 타입의 문자열 값을 반환합니다.
     * 
     * JSON 직렬화 시 이 메서드가 호출되어 문자열 값이 사용됩니다.
     * 
     * @return 작업 타입의 문자열 값
     */
    @JsonValue
    public String getOperation() {
        return operation;
    }

    /**
     * 문자열 값으로 해당하는 작업 타입 열거형을 찾습니다.
     * 
     * JSON 역직렬화 시 이 메서드가 호출되어 문자열로부터 열거형이 생성됩니다.
     * 지원하지 않는 작업의 경우 RuntimeException이 발생합니다.
     * 
     * @param operation 찾을 작업 타입의 문자열 값
     * @return 해당하는 FlightAreaOpertaionEnum 열거형
     * @throws RuntimeException 지원하지 않는 작업인 경우
     */
    @JsonCreator
    public static FlightAreaOpertaionEnum find(String operation) {
        return Arrays.stream(values()).filter(operationEnum -> operationEnum.operation.equals(operation)).findAny()
            .orElseThrow(() -> new RuntimeException("This operation(" + operation + ") is not supported."));
    }
}
