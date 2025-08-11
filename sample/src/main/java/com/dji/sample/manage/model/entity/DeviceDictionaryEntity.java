package com.dji.sample.manage.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 디바이스 사전 엔티티 클래스
 * 
 * DJI Cloud API의 디바이스 사전 정보를 데이터베이스에서 관리하는 엔티티 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 디바이스 사전 정보 관리
 *    - 디바이스 도메인, 타입, 서브타입 관리
 *    - 디바이스 이름 및 설명 관리
 *    - 디바이스 분류 및 카테고리 관리
 * 
 * 2. 디바이스 표준화 관리
 *    - 디바이스 타입별 표준 정보 제공
 *    - 디바이스 호환성 정보 관리
 *    - 디바이스 사양 및 특성 정보 관리
 * 
 * 3. 디바이스 분류 체계 관리
 *    - 도메인별 디바이스 분류 (예: AIRCRAFT, DOCK 등)
 *    - 타입별 디바이스 분류 (예: M30, M300 등)
 *    - 서브타입별 세부 분류 및 관리
 * 
 * 4. MyBatis-Plus 통합
 *    - @TableName 어노테이션으로 테이블 매핑
 *    - @TableField 어노테이션으로 필드 매핑
 *    - 디바이스 사전 데이터의 표준화된 관리
 * 
 * 이 클래스는 manage_device_dictionary 테이블과 매핑되며, 디바이스의
 * 사전 정보를 데이터베이스에서 관리하는 표준화된
 * 엔티티 구조를 제공합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/15
 * @version 0.1
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("manage_device_dictionary")
public class DeviceDictionaryEntity implements Serializable {

    /**
     * 디바이스 사전 고유 ID
     * 데이터베이스에서 자동 생성되는 기본 키
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 디바이스 도메인
     * 디바이스가 속한 도메인 (예: AIRCRAFT=0, DOCK=1 등)
     */
    @TableField(value = "domain")
    private Integer domain;

    /**
     * 디바이스 타입
     * 디바이스의 주요 타입 (예: M30=0, M300=1 등)
     */
    @TableField(value = "device_type")
    private Integer deviceType;

    /**
     * 디바이스 서브타입
     * 디바이스의 세부 타입
     */
    @TableField(value = "sub_type")
    private Integer subType;

    /**
     * 디바이스 이름
     * 디바이스의 표준화된 이름
     */
    @TableField(value = "device_name")
    private String deviceName;

    /**
     * 디바이스 설명
     * 디바이스에 대한 상세 설명 정보
     */
    @TableField(value = "device_desc")
    private String deviceDesc;

}