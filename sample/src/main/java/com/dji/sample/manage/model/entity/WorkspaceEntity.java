package com.dji.sample.manage.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

/**
 * 워크스페이스 엔티티 클래스
 * 
 * DJI Cloud API의 워크스페이스 정보를 데이터베이스에서 관리하는 엔티티 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 워크스페이스 기본 정보 관리
 *    - 워크스페이스 ID 및 이름 관리
 *    - 워크스페이스 설명 및 플랫폼 이름 관리
 *    - 워크스페이스 바인딩 코드 관리
 * 
 * 2. 워크스페이스 조직 관리
 *    - 워크스페이스별 사용자 및 디바이스 관리
 *    - 워크스페이스별 권한 및 접근 제어
 *    - 워크스페이스별 리소스 분리 및 관리
 * 
 * 3. 워크스페이스 시간 관리
 *    - 워크스페이스 생성 및 수정 시간 관리
 *    - 워크스페이스 활동 추적 및 모니터링
 *    - 워크스페이스 변경 이력 관리
 * 
 * 4. MyBatis-Plus 통합
 *    - @TableName 어노테이션으로 테이블 매핑
 *    - @TableField 어노테이션으로 필드 매핑
 *    - 자동 필드 채우기 기능 지원
 * 
 * 이 클래스는 manage_workspace 테이블과 매핑되며, 워크스페이스의
 * 모든 정보를 데이터베이스에서 관리하는 표준화된
 * 엔티티 구조를 제공합니다.
 * 
 * @author sean
 * @version 1.0
 * @date 2021/11/10
 */
@TableName(value = "manage_workspace")
@Data
public class WorkspaceEntity implements Serializable {

    /**
     * 워크스페이스 고유 ID
     * 데이터베이스에서 자동 생성되는 기본 키
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 워크스페이스 ID
     * 워크스페이스를 고유하게 식별하는 ID
     */
    @TableField(value = "workspace_id")
    private String workspaceId;

    /**
     * 워크스페이스 이름
     * 워크스페이스의 표시 이름
     */
    @TableField(value = "workspace_name")
    private String workspaceName;

    /**
     * 워크스페이스 설명
     * 워크스페이스에 대한 추가 설명 정보
     */
    @TableField(value = "workspace_desc")
    private String workspaceDesc;

    /**
     * 플랫폼 이름
     * 워크스페이스가 속한 플랫폼의 이름
     */
    @TableField(value = "platform_name")
    private String platformName;

    /**
     * 생성 시간
     * 워크스페이스 레코드가 생성된 시간 (밀리초)
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Long createTime;

    /**
     * 수정 시간
     * 워크스페이스 레코드가 마지막으로 수정된 시간 (밀리초)
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;

    /**
     * 바인딩 코드
     * 워크스페이스 바인딩을 위한 고유 코드
     */
    @TableField(value = "bind_code")
    private String bindCode;
}
