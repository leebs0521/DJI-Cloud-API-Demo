package com.dji.sample.manage.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

/**
 * 사용자 엔티티 클래스
 * 
 * DJI Cloud API의 사용자 정보를 데이터베이스에서 관리하는 엔티티 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 사용자 기본 정보 관리
 *    - 사용자 ID 및 사용자명 관리
 *    - 사용자 비밀번호 및 타입 관리
 *    - 워크스페이스 ID 관리
 * 
 * 2. MQTT 연결 정보 관리
 *    - MQTT 사용자명 및 비밀번호 관리
 *    - MQTT 브로커 연결을 위한 인증 정보 제공
 *    - 실시간 통신을 위한 MQTT 설정 관리
 * 
 * 3. 사용자 권한 및 타입 관리
 *    - 사용자 타입별 권한 관리
 *    - 워크스페이스별 사용자 접근 권한 관리
 *    - 사용자 역할 및 권한 분류
 * 
 * 4. MyBatis-Plus 통합
 *    - @TableName 어노테이션으로 테이블 매핑
 *    - @TableField 어노테이션으로 필드 매핑
 *    - 자동 필드 채우기 기능 지원
 * 
 * 이 클래스는 manage_user 테이블과 매핑되며, 사용자의
 * 모든 정보를 데이터베이스에서 관리하는 표준화된
 * 엔티티 구조를 제공합니다.
 * 
 * @author sean
 * @version 1.0
 * @date 2021/11/10
 */
@TableName(value = "manage_user")
@Data
public class UserEntity implements Serializable {

    /**
     * 사용자 고유 ID
     * 데이터베이스에서 자동 생성되는 기본 키
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 사용자 ID
     * 사용자를 고유하게 식별하는 ID
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 사용자명
     * 사용자의 로그인 이름
     */
    @TableField(value = "username")
    private String username;

    /**
     * 비밀번호
     * 사용자의 로그인 비밀번호 (암호화된 형태로 저장)
     */
    @TableField(value = "password")
    private String password;

    /**
     * 워크스페이스 ID
     * 사용자가 속한 워크스페이스의 고유 식별자
     */
    @TableField(value = "workspace_id")
    private String workspaceId;

    /**
     * 사용자 타입
     * 사용자의 타입 (예: 관리자, 일반 사용자 등)
     */
    @TableField(value = "user_type")
    private Integer userType;

    /**
     * MQTT 사용자명
     * MQTT 브로커 연결을 위한 사용자명
     */
    @TableField(value = "mqtt_username")
    private String mqttUsername;

    /**
     * MQTT 비밀번호
     * MQTT 브로커 연결을 위한 비밀번호
     */
    @TableField(value = "mqtt_password")
    private String mqttPassword;

    /**
     * 생성 시간
     * 사용자 레코드가 생성된 시간 (밀리초)
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Long createTime;

    /**
     * 수정 시간
     * 사용자 레코드가 마지막으로 수정된 시간 (밀리초)
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;
}
