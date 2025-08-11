package com.dji.sample.manage.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 디바이스 페이로드 엔티티 클래스
 * 
 * DJI Cloud API의 디바이스 페이로드 정보를 데이터베이스에서 관리하는 엔티티 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 페이로드 기본 정보 관리
 *    - 페이로드 시리얼 번호, 이름, 설명 관리
 *    - 페이로드 타입 및 서브타입 관리
 *    - 페이로드 인덱스 및 제어 소스 관리
 * 
 * 2. 페이로드 버전 및 연결 정보 관리
 *    - 페이로드 펌웨어 버전 관리
 *    - 디바이스 시리얼 번호와의 연결 관리
 *    - 페이로드 제어 소스 정보 관리
 * 
 * 3. MyBatis-Plus 통합
 *    - @TableName 어노테이션으로 테이블 매핑
 *    - @TableField 어노테이션으로 필드 매핑
 *    - 자동 필드 채우기 기능 지원
 * 
 * 이 클래스는 manage_device_payload 테이블과 매핑되며, 디바이스의
 * 페이로드 정보를 데이터베이스에서 관리하는 표준화된
 * 엔티티 구조를 제공합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/19
 * @version 0.1
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "manage_device_payload")
public class DevicePayloadEntity implements Serializable {

    /**
     * 페이로드 고유 ID
     * 데이터베이스에서 자동 생성되는 기본 키
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 페이로드 시리얼 번호
     * 페이로드를 고유하게 식별하는 시리얼 번호
     */
    @TableField(value = "payload_sn")
    private String payloadSn;

    /**
     * 페이로드 이름
     * 페이로드의 표시 이름
     */
    @TableField(value = "payload_name")
    private String payloadName;

    /**
     * 페이로드 타입
     * 페이로드의 주요 타입 (예: 카메라, 센서 등)
     */
    @TableField(value = "payload_type")
    private Integer payloadType;

    /**
     * 페이로드 서브타입
     * 페이로드의 세부 타입
     */
    @TableField(value = "sub_type")
    private Integer subType;

    /**
     * 펌웨어 버전
     * 페이로드에 설치된 펌웨어의 버전 정보
     */
    @TableField(value = "firmware_version")
    private String firmwareVersion;

    /**
     * 페이로드 인덱스
     * 페이로드의 순서 또는 위치 인덱스
     */
    @TableField(value = "payload_index")
    private Integer payloadIndex;

    /**
     * 디바이스 시리얼 번호
     * 페이로드가 연결된 디바이스의 시리얼 번호
     */
    @TableField(value = "device_sn")
    private String deviceSn;

    /**
     * 생성 시간
     * 페이로드 레코드가 생성된 시간 (밀리초)
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Long createTime;

    /**
     * 수정 시간
     * 페이로드 레코드가 마지막으로 수정된 시간 (밀리초)
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;

    /**
     * 페이로드 설명
     * 페이로드에 대한 추가 설명 정보
     */
    @TableField(value = "payload_desc")
    private String payloadDesc;

    /**
     * 제어 소스
     * 페이로드를 제어하는 소스 정보
     */
    @TableField(value = "control_source")
    private String controlSource;

}