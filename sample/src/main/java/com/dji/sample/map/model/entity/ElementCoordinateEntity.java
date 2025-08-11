package com.dji.sample.map.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 요소 좌표 엔티티
 * 
 * DJI Cloud API의 맵 요소 좌표 정보를 데이터베이스에 저장하기 위한 엔티티 클래스입니다.
 * 이 클래스는 맵 요소의 지리적 위치 정보를 관리합니다.
 * 
 * 1. 데이터베이스 매핑
 *    - 테이블명: map_element_coordinate
 *    - MyBatis-Plus 어노테이션을 통한 자동 매핑
 *    - 자동 증가 기본 키 (id)
 * 
 * 2. 요소 식별 정보
 *    - 요소 ID (elementId): 맵 요소 식별
 * 
 * 3. 지리적 좌표 정보
 *    - 경도 (longitude): WGS84 좌표계의 경도 값
 *    - 위도 (latitude): WGS84 좌표계의 위도 값
 *    - 고도 (altitude): 해수면 기준 고도 값 (미터)
 * 
 * 4. MyBatis-Plus 어노테이션 활용
 *    - @TableName: 데이터베이스 테이블명 지정
 *    - @TableId: 기본 키 필드 지정 및 자동 증가 설정
 *    - @TableField: 데이터베이스 컬럼명 매핑
 * 
 * 5. Lombok 어노테이션 활용
 *    - @Data: getter, setter, toString, equals, hashCode 자동 생성
 *    - @Builder: 빌더 패턴 지원
 *    - @NoArgsConstructor: 기본 생성자 자동 생성
 *    - @AllArgsConstructor: 모든 필드를 포함한 생성자 자동 생성
 * 
 * 주요 용도:
 * - 맵 요소 좌표 정보 데이터베이스 저장
 * - 지리적 위치 기반 맵 요소 관리
 * - 좌표계 변환 및 투영 지원
 * - 지리적 계산 및 분석 지원
 * 
 * 사용 예시:
 * - 맵 요소 위치 저장 및 조회
 * - 지리적 범위 검색
 * - 좌표 기반 요소 필터링
 * 
 * @author sean
 * @version 0.2
 * @date 2021/11/29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "map_element_coordinate")
public class ElementCoordinateEntity implements Serializable {

    /**
     * 기본 키 ID
     * 
     * 요소 좌표 레코드의 고유 식별자입니다.
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
     * 좌표가 속한 맵 요소의 고유 식별자입니다.
     * 맵 요소를 식별하는 데 사용됩니다.
     * 
     * 데이터베이스 컬럼: element_id
     */
    @TableField("element_id")
    private String elementId;

    /**
     * 경도
     * 
     * WGS84 좌표계의 경도 값을 나타냅니다.
     * -180.0 ~ 180.0 범위의 실수 값입니다.
     * 동쪽이 양수, 서쪽이 음수입니다.
     * 
     * 데이터베이스 컬럼: longitude
     */
    @TableField("longitude")
    private Double longitude;

    /**
     * 위도
     * 
     * WGS84 좌표계의 위도 값을 나타냅니다.
     * -90.0 ~ 90.0 범위의 실수 값입니다.
     * 북쪽이 양수, 남쪽이 음수입니다.
     * 
     * 데이터베이스 컬럼: latitude
     */
    @TableField("latitude")
    private Double latitude;

    /**
     * 고도
     * 
     * 해수면 기준 고도를 미터 단위로 나타냅니다.
     * 양수: 해수면 위, 음수: 해수면 아래
     * 
     * 데이터베이스 컬럼: altitude
     */
    @TableField("altitude")
    private Double altitude;

}
