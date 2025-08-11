package com.dji.sample.map.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 비행 영역 기하학적 타입 열거형
 * 
 * DJI Cloud API에서 비행 영역의 기하학적 형태를 정의하는 열거형입니다.
 * 이 열거형은 비행 영역의 공간적 형태를 분류하고 관리하는 데 사용됩니다.
 * 
 * 1. 지원하는 기하학적 타입
 *    - CIRCLE: 원형 비행 영역
 *    - POLYGON: 다각형 비행 영역
 * 
 * 2. JSON 직렬화/역직렬화 지원
 *    - @JsonValue: JSON 직렬화 시 문자열 값 사용
 *    - @JsonCreator: JSON 역직렬화 시 문자열로부터 열거형 생성
 * 
 * 3. 타입 검색 기능
 *    - find(String type): 문자열 값으로 열거형 찾기
 *    - 지원하지 않는 타입의 경우 RuntimeException 발생
 * 
 * 주요 용도:
 * - 비행 영역의 기하학적 형태 분류
 * - 비행 영역 생성 시 형태 지정
 * - 비행 영역 데이터 검증
 * - JSON API 응답/요청에서 타입 처리
 * 
 * 사용 예시:
 * - 비행 영역 생성 시 형태 선택
 * - 비행 영역 데이터 파싱 및 검증
 * - API 응답에서 기하학적 타입 표시
 * 
 * @author sean
 * @version 1.9
 * @date 2023/11/24
 */
public enum FlightAreaGeometryTypeEnum {
    
    /**
     * 원형 비행 영역
     * 
     * 중심점과 반지름으로 정의되는 원형 비행 영역입니다.
     * 원형 비행 영역은 다음과 같은 특징을 가집니다:
     * - 중심점 좌표 (위도, 경도)
     * - 반지름 (미터 단위)
     * - 고도 범위 (최소/최대 고도)
     * 
     * JSON 값: "Circle"
     */
    CIRCLE("Circle"),

    /**
     * 다각형 비행 영역
     * 
     * 여러 개의 좌표점으로 정의되는 다각형 비행 영역입니다.
     * 다각형 비행 영역은 다음과 같은 특징을 가집니다:
     * - 다각형을 구성하는 좌표점들의 배열
     * - 고도 범위 (최소/최대 고도)
     * - 복잡한 형태의 비행 제한 영역 정의 가능
     * 
     * JSON 값: "Polygon"
     */
    POLYGON("Polygon"),

    ;

    /**
     * 기하학적 타입의 문자열 값
     * 
     * JSON 직렬화/역직렬화에 사용되는 문자열 값입니다.
     * 각 열거형 상수는 고유한 문자열 값을 가집니다.
     */
    private final String type;

    /**
     * 생성자
     * 
     * @param type 기하학적 타입의 문자열 값
     */
    FlightAreaGeometryTypeEnum(String type) {
        this.type = type;
    }

    /**
     * 기하학적 타입의 문자열 값을 반환합니다.
     * 
     * JSON 직렬화 시 이 메서드가 호출되어 문자열 값이 사용됩니다.
     * 
     * @return 기하학적 타입의 문자열 값
     */
    @JsonValue
    public String getType() {
        return type;
    }

    /**
     * 문자열 값으로 해당하는 기하학적 타입 열거형을 찾습니다.
     * 
     * JSON 역직렬화 시 이 메서드가 호출되어 문자열로부터 열거형이 생성됩니다.
     * 지원하지 않는 타입의 경우 RuntimeException이 발생합니다.
     * 
     * @param type 찾을 기하학적 타입의 문자열 값
     * @return 해당하는 FlightAreaGeometryTypeEnum 열거형
     * @throws RuntimeException 지원하지 않는 타입인 경우
     */
    @JsonCreator
    public static FlightAreaGeometryTypeEnum find(String type) {
        return Arrays.stream(values()).filter(typeEnum -> typeEnum.type.equals(type)).findAny()
                .orElseThrow(() -> new RuntimeException("This type(" + type + ") is not supported."));
    }
}
