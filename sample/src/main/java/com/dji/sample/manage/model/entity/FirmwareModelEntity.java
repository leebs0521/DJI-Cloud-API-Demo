package com.dji.sample.manage.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 펌웨어 모델 엔티티 클래스
 * 
 * DJI Cloud API의 펌웨어 모델 정보를 데이터베이스에서 관리하는 엔티티 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 펌웨어 모델 기본 정보 관리
 *    - 펌웨어 ID 및 디바이스 이름 관리
 *    - 펌웨어와 디바이스 간의 매핑 관계 관리
 *    - 펌웨어 모델의 생성 및 수정 시간 관리
 * 
 * 2. 펌웨어-디바이스 관계 관리
 *    - 특정 펌웨어가 지원하는 디바이스 정보 관리
 *    - 펌웨어와 디바이스 간의 호환성 정보 제공
 *    - 펌웨어 모델의 버전 관리
 * 
 * 3. MyBatis-Plus 통합
 *    - @TableName 어노테이션으로 테이블 매핑
 *    - @TableField 어노테이션으로 필드 매핑
 *    - 자동 필드 채우기 기능 지원
 * 
 * 이 클래스는 manage_firmware_model 테이블과 매핑되며, 펌웨어의
 * 모델 정보를 데이터베이스에서 관리하는 표준화된
 * 엔티티 구조를 제공합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/12/21
 */
@Data
@TableName("manage_firmware_model")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FirmwareModelEntity implements Serializable {

    /**
     * 펌웨어 모델 고유 ID
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
     * 디바이스 이름
     * 이 펌웨어가 지원하는 디바이스의 이름
     */
    @TableField("device_name")
    private String deviceName;

    /**
     * 생성 시간
     * 펌웨어 모델 레코드가 생성된 시간 (밀리초)
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Long createTime;

    /**
     * 수정 시간
     * 펌웨어 모델 레코드가 마지막으로 수정된 시간 (밀리초)
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;
}
