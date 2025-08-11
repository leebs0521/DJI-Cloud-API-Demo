package com.dji.sample.manage.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 디바이스 엔티티 클래스
 * 
 * DJI Cloud API의 디바이스 정보를 데이터베이스에서 관리하는 엔티티 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 디바이스 기본 정보 관리
 *    - 디바이스 시리얼 번호, 이름, 설명 관리
 *    - 디바이스 타입, 서브타입, 도메인 정보 관리
 *    - 디바이스 버전 및 인덱스 정보 관리
 * 
 * 2. 디바이스 연결 및 상태 관리
 *    - 디바이스 바인딩 상태 및 시간 관리
 *    - 디바이스 로그인 시간 관리
 *    - 디바이스 호환성 상태 관리
 * 
 * 3. 디바이스 계층 구조 관리
 *    - 부모-자식 디바이스 관계 관리
 *    - 워크스페이스 ID 관리
 *    - 사용자 ID 및 닉네임 관리
 * 
 * 4. MyBatis-Plus 통합
 *    - @TableName 어노테이션으로 테이블 매핑
 *    - @TableField 어노테이션으로 필드 매핑
 *    - 자동 필드 채우기 기능 지원
 * 
 * 이 클래스는 manage_device 테이블과 매핑되며, 디바이스의
 * 모든 정보를 데이터베이스에서 관리하는 표준화된
 * 엔티티 구조를 제공합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "manage_device")
public class DeviceEntity implements Serializable {

    /**
     * 디바이스 고유 ID
     * 데이터베이스에서 자동 생성되는 기본 키
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 디바이스 시리얼 번호
     * 디바이스를 고유하게 식별하는 시리얼 번호
     */
    @TableField(value = "device_sn")
    private String deviceSn;

    /**
     * 디바이스 이름
     * 사용자가 설정한 디바이스의 표시 이름
     */
    @TableField(value = "device_name")
    private String deviceName;

    /**
     * 워크스페이스 ID
     * 디바이스가 속한 워크스페이스의 고유 식별자
     */
    @TableField(value = "workspace_id")
    private String workspaceId;

    /**
     * 디바이스 타입
     * 디바이스의 주요 타입 (예: AIRCRAFT=0, DOCK=1, RC=2 등)
     */
    @TableField(value = "device_type")
    private Integer deviceType;

    /**
     * 디바이스 서브타입
     * 디바이스의 세부 타입 (예: M30=0, M300=1 등)
     */
    @TableField(value = "sub_type")
    private Integer subType;

    /**
     * 디바이스 도메인
     * 디바이스가 속한 도메인 (예: AIRCRAFT=0, DOCK=1 등)
     */
    @TableField(value = "domain")
    private Integer domain;

    /**
     * 디바이스 버전
     * 디바이스의 현재 버전 정보
     */
    @TableField(value = "version")
    private String version;

    /**
     * 디바이스 인덱스
     * 디바이스의 순서 또는 위치 인덱스
     */
    @TableField(value = "device_index")
    private String deviceIndex;

    /**
     * 자식 디바이스 시리얼 번호
     * 현재 디바이스에 연결된 자식 디바이스의 시리얼 번호
     */
    @TableField(value = "child_sn")
    private String childSn;

    /**
     * 생성 시간
     * 디바이스 레코드가 생성된 시간 (밀리초)
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Long createTime;

    /**
     * 수정 시간
     * 디바이스 레코드가 마지막으로 수정된 시간 (밀리초)
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;

    /**
     * 디바이스 설명
     * 디바이스에 대한 추가 설명 정보
     */
    @TableField(value = "device_desc")
    private String deviceDesc;

    /**
     * 일반 URL
     * 디바이스의 일반적인 접근 URL
     */
    @TableField(value = "url_normal")
    private String urlNormal;

    /**
     * 선택 URL
     * 디바이스의 선택적 접근 URL
     */
    @TableField(value = "url_select")
    private String urlSelect;

    /**
     * 사용자 ID
     * 디바이스를 사용하는 사용자의 고유 식별자
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 닉네임
     * 디바이스의 별칭 또는 닉네임
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 펌웨어 버전
     * 디바이스에 설치된 펌웨어의 버전 정보
     */
    @TableField(value = "firmware_version")
    private String firmwareVersion;

    /**
     * 호환성 상태
     * 디바이스의 호환성 상태 (true: 호환됨, false: 호환되지 않음)
     */
    @TableField(value = "compatible_status")
    private Boolean compatibleStatus;

    /**
     * 바인딩 상태
     * 디바이스의 바인딩 여부 (true: 바인딩됨, false: 바인딩되지 않음)
     */
    @TableField(value = "bound_status")
    private Boolean boundStatus;

    /**
     * 바인딩 시간
     * 디바이스가 바인딩된 시간 (밀리초)
     */
    @TableField(value = "bound_time")
    private Long boundTime;

    /**
     * 로그인 시간
     * 디바이스가 마지막으로 로그인한 시간 (밀리초)
     */
    @TableField(value = "login_time")
    private Long loginTime;

}