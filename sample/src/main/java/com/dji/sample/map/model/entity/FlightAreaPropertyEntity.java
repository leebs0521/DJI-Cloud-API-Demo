package com.dji.sample.map.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 비행 영역 속성 엔티티
 * 
 * DJI Cloud API의 비행 영역 속성 정보를 데이터베이스에 저장하기 위한 엔티티 클래스입니다.
 * 이 클래스는 비행 영역의 상세 속성과 설정 정보를 관리합니다.
 * 
 * 1. 데이터베이스 매핑
 *    - 테이블명: flight_area_property
 *    - MyBatis-Plus 어노테이션을 통한 자동 매핑
 *    - 자동 증가 기본 키 (id)
 * 
 * 2. 비행 영역 속성 정보
 *    - 요소 ID (elementId): 비행 영역 요소 식별
 *    - 타입 (type): 비행 영역 분류 타입
 *    - 활성화 상태 (enable): 비행 영역 활성화 여부
 *    - 하위 타입 (subType): 세부 기하학적 형태
 *    - 반지름 (radius): 원형 비행 영역의 반지름
 * 
 * 3. MyBatis-Plus 어노테이션 활용
 *    - @TableName: 데이터베이스 테이블명 지정
 *    - @TableId: 기본 키 필드 지정 및 자동 증가 설정
 *    - @TableField: 데이터베이스 컬럼명 매핑
 * 
 * 4. Lombok 어노테이션 활용
 *    - @Data: getter, setter, toString, equals, hashCode 자동 생성
 *    - @Builder: 빌더 패턴 지원
 *    - @NoArgsConstructor: 기본 생성자 자동 생성
 *    - @AllArgsConstructor: 모든 필드를 포함한 생성자 자동 생성
 * 
 * 주요 용도:
 * - 비행 영역 속성 정보 데이터베이스 저장
 * - 비행 영역 설정 관리
 * - 비행 영역 타입별 속성 정의
 * - 비행 영역 기하학적 속성 관리
 * 
 * 사용 예시:
 * - 비행 영역 생성 시 속성 저장
 * - 비행 영역 편집 시 속성 수정
 * - 비행 영역 타입별 동작 정의
 * 
 * @author sean
 * @version 1.9
 * @date 2023/11/21
 */
@TableName("flight_area_property")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightAreaPropertyEntity implements Serializable {

    /**
     * 기본 키 ID
     * 
     * 비행 영역 속성 레코드의 고유 식별자입니다.
     * 데이터베이스에서 자동으로 증가하는 정수 값으로 설정됩니다.
     * 
     * MyBatis-Plus 설정:
     * - @TableId(type = IdType.AUTO): 자동 증가 기본 키
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 요소 ID
     * 
     * 비행 영역 요소의 고유 식별자입니다.
     * 비행 영역 내의 개별 요소를 구분하는 데 사용됩니다.
     * 
     * 데이터베이스 컬럼: element_id
     */
    @TableField("element_id")
    private String elementId;

    /**
     * 비행 영역 타입
     * 
     * 비행 영역의 분류 타입을 나타내는 문자열입니다.
     * 예: RESTRICTED_AREA (제한 구역), WARNING_AREA (경고 구역) 등
     * 
     * 데이터베이스 컬럼: type
     */
    @TableField("type")
    private String type;

    /**
     * 활성화 상태
     * 
     * 비행 영역의 활성화 상태를 나타냅니다.
     * true: 활성화됨 (비행 제한 적용)
     * false: 비활성화됨 (비행 제한 미적용)
     * 
     * 데이터베이스 컬럼: enable
     */
    @TableField("enable")
    private Boolean enable;

    /**
     * 하위 타입
     * 
     * 비행 영역의 세부 기하학적 형태를 나타내는 문자열입니다.
     * 예: CIRCLE (원형), POLYGON (다각형), CYLINDER (원통형) 등
     * 
     * 데이터베이스 컬럼: sub_type
     */
    @TableField("sub_type")
    private String subType;

    /**
     * 반지름
     * 
     * 원형 비행 영역의 반지름을 미터 단위로 나타냅니다.
     * 원형 또는 구형 비행 영역에서만 사용됩니다.
     * 
     * 데이터베이스 컬럼: radius
     */
    @TableField("radius")
    private Integer radius;

}
