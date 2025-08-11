package com.dji.sample.map.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 그룹 요소 엔티티
 * 
 * DJI Cloud API의 그룹과 맵 요소 간의 관계를 데이터베이스에 저장하기 위한 엔티티 클래스입니다.
 * 이 클래스는 그룹에 속한 맵 요소의 정보와 표시 설정을 관리합니다.
 * 
 * 1. 데이터베이스 매핑
 *    - 테이블명: map_group_element
 *    - MyBatis-Plus 어노테이션을 통한 자동 매핑
 *    - 자동 증가 기본 키 (id)
 * 
 * 2. 그룹-요소 관계 정보
 *    - 그룹 ID (groupId): 그룹 식별
 *    - 요소 ID (elementId): 맵 요소 식별
 *    - 요소 이름 (elementName): 맵 요소 표시명
 * 
 * 3. 요소 표시 설정
 *    - 표시 여부 (display): 요소 표시/숨김 설정
 *    - 요소 타입 (elementType): 맵 요소 분류 타입
 *    - 색상 (color): 요소 표시 색상
 *    - 지면 고정 (clampToGround): 지면에 고정 여부
 * 
 * 4. 메타데이터 정보
 *    - 사용자명 (username): 요소 생성자
 *    - 생성 시간 (createTime): 자동 생성
 *    - 수정 시간 (updateTime): 자동 업데이트
 * 
 * 5. MyBatis-Plus 어노테이션 활용
 *    - @TableName: 데이터베이스 테이블명 지정
 *    - @TableId: 기본 키 필드 지정 및 자동 증가 설정
 *    - @TableField: 데이터베이스 컬럼명 매핑 및 자동 필드 채우기
 * 
 * 6. Lombok 어노테이션 활용
 *    - @Data: getter, setter, toString, equals, hashCode 자동 생성
 *    - @Builder: 빌더 패턴 지원
 *    - @NoArgsConstructor: 기본 생성자 자동 생성
 *    - @AllArgsConstructor: 모든 필드를 포함한 생성자 자동 생성
 * 
 * 주요 용도:
 * - 그룹-요소 관계 데이터베이스 저장
 * - 맵 요소 그룹화 관리
 * - 요소 표시 설정 관리
 * - 그룹별 요소 분류 및 정리
 * 
 * 사용 예시:
 * - 그룹에 요소 추가/제거
 * - 요소 표시 설정 변경
 * - 그룹별 요소 목록 조회
 * 
 * @author sean
 * @version 0.2
 * @date 2021/11/29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "map_group_element")
public class GroupElementEntity implements Serializable {

    /**
     * 기본 키 ID
     * 
     * 그룹 요소 관계 레코드의 고유 식별자입니다.
     * 데이터베이스에서 자동으로 증가하는 정수 값으로 설정됩니다.
     * 
     * MyBatis-Plus 설정:
     * - @TableId(type = IdType.AUTO): 자동 증가 기본 키
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 그룹 ID
     * 
     * 맵 요소가 속한 그룹의 고유 식별자입니다.
     * 그룹-요소 관계를 식별하는 데 사용됩니다.
     * 
     * 데이터베이스 컬럼: group_id
     */
    @TableField("group_id")
    private String groupId;

    /**
     * 요소 ID
     * 
     * 그룹에 속한 맵 요소의 고유 식별자입니다.
     * 맵 요소를 식별하는 데 사용됩니다.
     * 
     * 데이터베이스 컬럼: element_id
     */
    @TableField("element_id")
    private String elementId;

    /**
     * 요소 이름
     * 
     * 맵 요소의 사용자 친화적인 이름입니다.
     * 맵에서 요소를 식별하는 표시명으로 사용됩니다.
     * 
     * 데이터베이스 컬럼: element_name
     */
    @TableField("element_name")
    private String elementName;

    /**
     * 표시 여부
     * 
     * 맵 요소의 표시/숨김 상태를 나타내는 정수 값입니다.
     * 1: 표시됨, 0: 숨겨짐
     * 
     * 데이터베이스 컬럼: display
     */
    @TableField("display")
    private Integer display;

    /**
     * 요소 타입
     * 
     * 맵 요소의 분류 타입을 나타내는 정수 값입니다.
     * 예: 1 (점), 2 (선), 3 (다각형) 등
     * 
     * 데이터베이스 컬럼: element_type
     */
    @TableField("element_type")
    private Integer elementType;

    /**
     * 사용자명
     * 
     * 해당 맵 요소를 생성한 사용자의 이름입니다.
     * 요소의 소유권 및 관리 권한을 추적하는 데 사용됩니다.
     * 
     * 데이터베이스 컬럼: username
     */
    @TableField("username")
    private String username;

    /**
     * 색상
     * 
     * 맵 요소의 표시 색상을 나타내는 문자열입니다.
     * 예: "#FF0000" (빨간색), "#00FF00" (초록색) 등
     * 
     * 데이터베이스 컬럼: color
     */
    @TableField("color")
    private String color;

    /**
     * 지면 고정 여부
     * 
     * 맵 요소가 지면에 고정되어 표시되는지 여부를 나타냅니다.
     * true: 지면에 고정됨, false: 고정되지 않음
     * 
     * 데이터베이스 컬럼: clamp_to_ground
     */
    @TableField("clamp_to_ground")
    private Boolean clampToGround;

    /**
     * 생성 시간
     * 
     * 그룹 요소 관계가 생성된 시간을 Unix 타임스탬프로 나타냅니다.
     * 밀리초 단위의 정수 값으로 저장됩니다.
     * 
     * MyBatis-Plus 설정:
     * - @TableField(fill = FieldFill.INSERT): 삽입 시 자동 생성
     * 
     * 데이터베이스 컬럼: create_time
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Long createTime;

    /**
     * 수정 시간
     * 
     * 그룹 요소 관계가 마지막으로 수정된 시간을 Unix 타임스탬프로 나타냅니다.
     * 밀리초 단위의 정수 값으로 저장됩니다.
     * 
     * MyBatis-Plus 설정:
     * - @TableField(fill = FieldFill.INSERT_UPDATE): 삽입/수정 시 자동 업데이트
     * 
     * 데이터베이스 컬럼: update_time
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;
}
