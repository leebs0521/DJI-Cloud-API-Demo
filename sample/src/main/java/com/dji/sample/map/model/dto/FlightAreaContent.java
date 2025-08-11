package com.dji.sample.map.model.dto;

import com.dji.sdk.cloudapi.map.ElementGeometryType;
import com.dji.sdk.cloudapi.map.ElementProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 비행 영역 내용 전송 객체 (DTO)
 * 
 * DJI Cloud API에서 비행 영역의 상세 내용을 전송하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 비행 영역의 속성과 기하학적 정보를 포함합니다.
 * 
 * 1. 비행 영역 속성 정보
 *    - 비행 영역의 메타데이터 및 설정 정보 (properties)
 *    - 비행 영역의 기하학적 형태 정보 (geometry)
 * 
 * 2. 데이터 검증
 *    - @NotNull: 필수 필드 검증
 *    - @Valid: 중첩된 객체의 유효성 검증
 * 
 * 3. Lombok 어노테이션 활용
 *    - @Data: getter, setter, toString, equals, hashCode 자동 생성
 *    - @Builder: 빌더 패턴 지원
 *    - @AllArgsConstructor: 모든 필드를 포함한 생성자 자동 생성
 *    - @NoArgsConstructor: 기본 생성자 자동 생성
 * 
 * 주요 용도:
 * - 비행 영역 생성 및 수정 시 데이터 전송
 * - 비행 영역 상세 정보 조회
 * - 비행 영역 파일 내용 파싱
 * - 비행 영역 데이터 검증
 * 
 * 사용 예시:
 * - 비행 영역 파일 업로드 시 내용 검증
 * - 비행 영역 편집 시 데이터 전송
 * - 비행 영역 미리보기 생성
 * 
 * @author sean
 * @version 1.9
 * @date 2023/11/22
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightAreaContent {

    /**
     * 비행 영역 속성 정보
     * 
     * 비행 영역의 메타데이터, 설정, 분류 등의 속성 정보를 포함합니다.
     * 예: 비행 영역 이름, 설명, 타입, 우선순위, 유효기간 등
     * 
     * 검증 규칙:
     * - @NotNull: null 값이 허용되지 않음
     * - @Valid: 중첩된 ElementProperty 객체의 유효성 검증 수행
     */
    @NotNull
    @Valid
    private ElementProperty properties;

    /**
     * 비행 영역 기하학적 형태 정보
     * 
     * 비행 영역의 공간적 형태와 좌표 정보를 포함합니다.
     * 예: 다각형, 원형, 직사각형 등의 형태와 해당 좌표들
     * 
     * 검증 규칙:
     * - @NotNull: null 값이 허용되지 않음
     * - @Valid: 중첩된 ElementGeometryType 객체의 유효성 검증 수행
     */
    @NotNull
    @Valid
    private ElementGeometryType geometry;

}
