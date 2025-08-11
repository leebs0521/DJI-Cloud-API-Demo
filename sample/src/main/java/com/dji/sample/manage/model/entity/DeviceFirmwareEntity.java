package com.dji.sample.manage.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 디바이스 펌웨어 엔티티 클래스
 * 
 * DJI Cloud API의 디바이스 펌웨어 정보를 데이터베이스에서 관리하는 엔티티 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 펌웨어 기본 정보 관리
 *    - 펌웨어 ID 및 파일명 관리
 *    - 펌웨어 버전 정보 관리
 *    - 펌웨어 릴리즈 노트 및 날짜 관리
 * 
 * 2. 펌웨어 파일 정보 관리
 *    - 펌웨어 파일 크기 및 MD5 해시 관리
 *    - 펌웨어 파일 객체 키 관리
 *    - 펌웨어 파일 무결성 검증 정보 제공
 * 
 * 3. 펌웨어 상태 및 소유권 관리
 *    - 펌웨어 활성/비활성 상태 관리
 *    - 워크스페이스 ID 관리
 *    - 펌웨어 업로드 사용자 정보 관리
 * 
 * 4. MyBatis-Plus 통합
 *    - @TableName 어노테이션으로 테이블 매핑
 *    - @TableField 어노테이션으로 필드 매핑
 *    - 자동 필드 채우기 기능 지원
 * 
 * 이 클래스는 manage_device_firmware 테이블과 매핑되며, 펌웨어의
 * 모든 정보를 데이터베이스에서 관리하는 표준화된
 * 엔티티 구조를 제공합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/8/16
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("manage_device_firmware")
public class DeviceFirmwareEntity implements Serializable {

    /**
     * 직렬화 버전 ID
     * 객체 직렬화를 위한 버전 식별자
     */
    private static final long serialVersionUID = -12L;

    /**
     * 펌웨어 고유 ID
     * 데이터베이스에서 자동 생성되는 기본 키
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 펌웨어 ID
     * 펌웨어를 고유하게 식별하는 ID
     */
    @TableField("firmware_id")
    private String firmwareId;

    /**
     * 펌웨어 파일명
     * 펌웨어 파일의 원본 파일명
     */
    @TableField("file_name")
    private String fileName;

    /**
     * 펌웨어 버전
     * 펌웨어의 버전 정보
     */
    @TableField("firmware_version")
    private String firmwareVersion;

    /**
     * 객체 키
     * 클라우드 스토리지에서 펌웨어 파일을 식별하는 키
     */
    @TableField("object_key")
    private String objectKey;

    /**
     * 파일 크기
     * 펌웨어 파일의 크기 (바이트 단위)
     */
    @TableField("file_size")
    private Long fileSize;

    /**
     * 파일 MD5 해시
     * 펌웨어 파일의 무결성을 검증하기 위한 MD5 해시값
     */
    @TableField("file_md5")
    private String fileMd5;

    /**
     * 디바이스 이름
     * 이 펌웨어가 지원하는 디바이스의 이름 (테이블에 존재하지 않는 필드)
     */
    @TableField(exist = false)
    private String deviceName;

    /**
     * 릴리즈 노트
     * 펌웨어 업데이트 내용에 대한 설명
     */
    @TableField("release_note")
    private String releaseNote;

    /**
     * 릴리즈 날짜
     * 펌웨어가 공개된 날짜 (밀리초)
     */
    @TableField("release_date")
    private Long releaseDate;

    /**
     * 펌웨어 상태
     * 펌웨어의 현재 상태 (true: 활성, false: 비활성)
     */
    @TableField("status")
    private Boolean status;

    /**
     * 워크스페이스 ID
     * 펌웨어가 속한 워크스페이스의 고유 식별자
     */
    @TableField("workspace_id")
    private String workspaceId;

    /**
     * 사용자명
     * 펌웨어를 업로드한 사용자의 이름
     */
    @TableField("user_name")
    private String username;

    /**
     * 생성 시간
     * 펌웨어 레코드가 생성된 시간 (밀리초)
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Long createTime;

    /**
     * 수정 시간
     * 펌웨어 레코드가 마지막으로 수정된 시간 (밀리초)
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;

}
