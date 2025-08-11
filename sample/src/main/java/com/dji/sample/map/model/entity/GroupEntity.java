package com.dji.sample.map.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 그룹 엔티티
 * 
 * DJI Cloud API의 맵 그룹 정보를 데이터베이스에 저장하기 위한 엔티티 클래스입니다.
 * 이 클래스는 맵 요소들을 논리적으로 그룹화하여 관리하는 그룹 정보를 담습니다.
 * 
 * 1. 데이터베이스 매핑
 *    - 테이블명: map_group
 *    - MyBatis-Plus 어노테이션을 통한 자동 매핑
 *    - 자동 증가 기본 키 (id)
 * 
 * 2. 그룹 기본 정보
 *    - 그룹 ID (groupId): 그룹의 고유 식별자
 *    - 그룹 이름 (groupName): 그룹의 사용자 친화적인 이름
 *    - 그룹 타입 (groupType): 그룹의 분류 타입
 * 
 * 3. 그룹 관리 정보
 *    - 워크스페이스 ID (workspaceId): 그룹이 속한 워크스페이스
 *    - 분산 여부 (isDistributed): 그룹의 분산 처리 여부
 *    - 잠금 여부 (isLock): 그룹의 편집 잠금 상태
 * 
 * 4. 메타데이터 정보
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
 * - 맵 그룹 정보 데이터베이스 저장
 * - 맵 요소 그룹화 관리
 * - 그룹별 접근 제어
 * - 워크스페이스별 그룹 관리
 * 
 * 사용 예시:
 * - 맵 요소 그룹 생성/수정/삭제
 * - 그룹별 요소 분류 및 정리
 * - 그룹 권한 및 잠금 관리
 * 
 * @author sean
 * @version 0.2
 * @date 2021/11/29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "map_group")
public class GroupEntity implements Serializable {

    /**
     * 기본 키 ID
     * 
     * 그룹 레코드의 고유 식별자입니다.
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
     * 그룹의 고유 식별자입니다.
     * 시스템에서 그룹을 구분하는 데 사용되는 문자열 식별자입니다.
     * 
     * 데이터베이스 컬럼: group_id
     */
    @TableField("group_id")
    private String groupId;

    /**
     * 그룹 이름
     * 
     * 그룹의 사용자 친화적인 이름입니다.
     * 사용자가 그룹을 식별하고 관리하는 데 사용되는 표시명입니다.
     * 
     * 데이터베이스 컬럼: group_name
     */
    @TableField("group_name")
    private String groupName;

    /**
     * 그룹 타입
     * 
     * 그룹의 분류 타입을 나타내는 정수 값입니다.
     * 예: 1 (기본 그룹), 2 (시스템 그룹), 3 (사용자 정의 그룹) 등
     * 
     * 데이터베이스 컬럼: group_type
     */
    @TableField("group_type")
    private Integer groupType;

    /**
     * 워크스페이스 ID
     * 
     * 그룹이 속한 워크스페이스의 고유 식별자입니다.
     * 워크스페이스별로 그룹을 관리할 수 있습니다.
     * 
     * 데이터베이스 컬럼: workspace_id
     */
    @TableField("workspace_id")
    private String workspaceId;

    /**
     * 분산 여부
     * 
     * 그룹이 분산 처리되는지 여부를 나타냅니다.
     * true: 분산 처리됨, false: 단일 처리
     * 
     * 데이터베이스 컬럼: is_distributed
     */
    @TableField("is_distributed")
    private Boolean isDistributed;

    /**
     * 잠금 여부
     * 
     * 그룹의 편집 잠금 상태를 나타냅니다.
     * true: 잠겨있음 (편집 불가), false: 잠금 해제됨 (편집 가능)
     * 
     * 데이터베이스 컬럼: is_lock
     */
    @TableField("is_lock")
    private Boolean isLock;

    /**
     * 생성 시간
     * 
     * 그룹이 생성된 시간을 Unix 타임스탬프로 나타냅니다.
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
     * 그룹이 마지막으로 수정된 시간을 Unix 타임스탬프로 나타냅니다.
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
