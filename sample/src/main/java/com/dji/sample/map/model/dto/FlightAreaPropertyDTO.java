package com.dji.sample.map.model.dto;

import com.dji.sdk.cloudapi.flightarea.GeofenceTypeEnum;
import com.dji.sdk.cloudapi.flightarea.GeometrySubTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 비행 영역 속성 전송 객체 (DTO)
 * 
 * DJI Cloud API에서 비행 영역의 상세 속성 정보를 전송하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 비행 영역의 타입, 상태, 기하학적 속성 등을 포함합니다.
 * 
 * 1. 요소 식별 정보
 *    - 요소 ID (elementId)
 * 
 * 2. 비행 영역 상태 및 타입
 *    - 활성화 상태 (status)
 *    - 비행 영역 타입 (type)
 * 
 * 3. 기하학적 속성
 *    - 반지름 (radius)
 *    - 기하학적 하위 타입 (subType)
 * 
 * 4. Lombok 어노테이션 활용
 *    - @Data: getter, setter, toString, equals, hashCode 자동 생성
 *    - @Builder: 빌더 패턴 지원
 *    - @AllArgsConstructor: 모든 필드를 포함한 생성자 자동 생성
 *    - @NoArgsConstructor: 기본 생성자 자동 생성
 * 
 * 주요 용도:
 * - 비행 영역 속성 정보 전송
 * - 비행 영역 설정 관리
 * - 비행 영역 타입별 속성 정의
 * - 비행 영역 기하학적 속성 관리
 * 
 * 사용 예시:
 * - 비행 영역 생성 시 속성 설정
 * - 비행 영역 편집 시 속성 수정
 * - 비행 영역 타입별 동작 정의
 * 
 * @author sean
 * @version 1.9
 * @date 2023/11/22
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightAreaPropertyDTO {

    /**
     * 요소 ID
     * 
     * 비행 영역 요소의 고유 식별자입니다.
     * 비행 영역 내의 개별 요소를 구분하는 데 사용됩니다.
     */
    private String elementId;

    /**
     * 활성화 상태
     * 
     * 비행 영역 요소의 현재 활성화 상태를 나타냅니다.
     * true: 활성화됨 (비행 제한 적용)
     * false: 비활성화됨 (비행 제한 미적용)
     */
    private Boolean status;

    /**
     * 비행 영역 타입
     * 
     * 비행 영역의 분류 타입을 나타냅니다.
     * 예: RESTRICTED_AREA (제한 구역), WARNING_AREA (경고 구역) 등
     */
    private GeofenceTypeEnum type;

    /**
     * 반지름
     * 
     * 원형 비행 영역의 반지름을 미터 단위로 나타냅니다.
     * 원형 또는 구형 비행 영역에서만 사용됩니다.
     */
    private Float radius;

    /**
     * 기하학적 하위 타입
     * 
     * 비행 영역의 세부 기하학적 형태를 나타냅니다.
     * 예: CIRCLE (원형), POLYGON (다각형), CYLINDER (원통형) 등
     */
    private GeometrySubTypeEnum subType;
}
