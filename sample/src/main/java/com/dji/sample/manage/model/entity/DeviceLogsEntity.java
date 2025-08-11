package com.dji.sample.manage.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 디바이스 로그 엔티티 클래스
 * 
 * DJI Cloud API의 디바이스 로그 정보를 데이터베이스에서 관리하는 엔티티 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 디바이스 로그 기본 정보 관리
 *    - 로그 ID 및 사용자명 관리
 *    - 로그 정보 및 디바이스 시리얼 번호 관리
 *    - 로그 발생 시간 및 상태 관리
 * 
 * 2. 로그 상태 및 시간 관리
 *    - 로그의 현재 상태 관리 (예: 업로드 중, 완료, 실패 등)
 *    - 로그 발생 시간 및 생성/수정 시간 관리
 *    - 로그 처리 과정 추적
 * 
 * 3. MyBatis-Plus 통합
 *    - @TableName 어노테이션으로 테이블 매핑
 *    - @TableField 어노테이션으로 필드 매핑
 *    - 자동 필드 채우기 기능 지원
 * 
 * 이 클래스는 manage_device_logs 테이블과 매핑되며, 디바이스의
 * 로그 정보를 데이터베이스에서 관리하는 표준화된
 * 엔티티 구조를 제공합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/9/7
 */
@TableName("manage_device_logs")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceLogsEntity implements Serializable {

    /**
     * 직렬화 버전 ID
     * 객체 직렬화를 위한 버전 식별자
     */
    private static final long serialVersionUID = -12L;

    /**
     * 로그 고유 ID
     * 데이터베이스에서 자동 생성되는 기본 키
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 로그 ID
     * 디바이스 로그를 고유하게 식별하는 ID
     */
    @TableField("logs_id")
    private String logsId;

    /**
     * 사용자명
     * 로그를 생성한 사용자의 이름
     */
    @TableField("username")
    private String username;

    /**
     * 로그 정보
     * 디바이스 로그의 상세 정보 내용
     */
    @TableField("logs_info")
    private String logsInfo;

    /**
     * 디바이스 시리얼 번호
     * 로그가 발생한 디바이스의 시리얼 번호
     */
    @TableField("device_sn")
    private String deviceSn;

    /**
     * 발생 시간
     * 로그가 발생한 시간 (밀리초)
     */
    @TableField("happen_time")
    private Long happenTime;

    /**
     * 로그 상태
     * 로그의 현재 상태 (예: 0-대기, 1-처리 중, 2-완료, 3-실패 등)
     */
    @TableField("status")
    private Integer status;

    /**
     * 생성 시간
     * 로그 레코드가 생성된 시간 (밀리초)
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Long createTime;

    /**
     * 수정 시간
     * 로그 레코드가 마지막으로 수정된 시간 (밀리초)
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;

}
