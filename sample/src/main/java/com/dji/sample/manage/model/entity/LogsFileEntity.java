package com.dji.sample.manage.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 로그 파일 엔티티 클래스
 * 
 * DJI Cloud API의 로그 파일 정보를 데이터베이스에서 관리하는 엔티티 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 로그 파일 기본 정보 관리
 *    - 파일 ID 및 이름 관리
 *    - 파일 크기 및 지문(fingerprint) 관리
 *    - 로그 ID 및 디바이스 시리얼 번호 관리
 * 
 * 2. 로그 파일 스토리지 관리
 *    - 클라우드 스토리지 객체 키 관리
 *    - 파일 상태 관리 (업로드 중, 완료, 실패 등)
 *    - 파일 무결성 검증을 위한 지문 관리
 * 
 * 3. MyBatis-Plus 통합
 *    - @TableName 어노테이션으로 테이블 매핑
 *    - @TableField 어노테이션으로 필드 매핑
 *    - 자동 필드 채우기 기능 지원
 * 
 * 이 클래스는 logs_file 테이블과 매핑되며, 로그 파일의
 * 모든 정보를 데이터베이스에서 관리하는 표준화된
 * 엔티티 구조를 제공합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/9/7
 */
@TableName("logs_file")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogsFileEntity implements Serializable {

    /**
     * 직렬화 버전 ID
     * 객체 직렬화를 위한 버전 식별자
     */
    private static final long serialVersionUID = -12L;

    /**
     * 로그 파일 고유 ID
     * 데이터베이스에서 자동 생성되는 기본 키
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 파일 ID
     * 로그 파일을 고유하게 식별하는 ID
     */
    @TableField("file_id")
    private String fileId;

    /**
     * 파일 이름
     * 로그 파일의 원본 파일명
     */
    @TableField("name")
    private String name;

    /**
     * 파일 크기
     * 로그 파일의 크기 (바이트 단위)
     */
    @TableField("size")
    private Long size;

    /**
     * 로그 ID
     * 이 파일이 속한 로그의 ID
     */
    @TableField("logs_id")
    private String logsId;

    /**
     * 디바이스 시리얼 번호
     * 로그 파일이 생성된 디바이스의 시리얼 번호
     */
    @TableField("device_sn")
    private String deviceSn;

    /**
     * 파일 지문
     * 파일의 무결성을 검증하기 위한 지문(fingerprint) 값
     */
    @TableField("fingerprint")
    private String fingerprint;

    /**
     * 객체 키
     * 클라우드 스토리지에서 로그 파일을 식별하는 키
     */
    @TableField("object_key")
    private String objectKey;

    /**
     * 파일 상태
     * 로그 파일의 현재 상태 (true: 활성, false: 비활성)
     */
    @TableField("status")
    private Boolean status;

    /**
     * 생성 시간
     * 로그 파일 레코드가 생성된 시간 (밀리초)
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Long createTime;

    /**
     * 수정 시간
     * 로그 파일 레코드가 마지막으로 수정된 시간 (밀리초)
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;

}
