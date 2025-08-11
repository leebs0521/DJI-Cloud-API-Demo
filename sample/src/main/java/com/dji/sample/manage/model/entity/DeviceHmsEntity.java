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
 * 디바이스 HMS 엔티티 클래스
 * 
 * DJI Cloud API의 디바이스 HMS(Health Management System) 정보를 데이터베이스에서 관리하는 엔티티 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. HMS 메시지 정보 관리
 *    - HMS 메시지 ID 및 키 관리
 *    - HMS 메시지 레벨 및 모듈 정보 관리
 *    - HMS 메시지 생성 및 수정 시간 관리
 * 
 * 2. 다국어 메시지 관리
 *    - 중국어 메시지 관리
 *    - 영어 메시지 관리
 *    - 언어별 HMS 메시지 분리 및 관리
 * 
 * 3. 디바이스 연결 정보 관리
 *    - 디바이스 시리얼 번호 관리
 *    - 디바이스 바인딩 ID 및 타스크 ID 관리
 *    - 디바이스와의 연결 정보 추적
 * 
 * 4. MyBatis-Plus 통합 및 복제 기능
 *    - @TableName 어노테이션으로 테이블 매핑
 *    - @TableField 어노테이션으로 필드 매핑
 *    - Cloneable 인터페이스 구현으로 객체 복제 지원
 * 
 * 이 클래스는 manage_device_hms 테이블과 매핑되며, 디바이스의
 * 건강 상태 모니터링 정보를 데이터베이스에서 관리하는 표준화된
 * 엔티티 구조를 제공합니다.
 * 
 * @author sean
 * @version 1.1
 * @date 2022/7/6
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "manage_device_hms")
public class DeviceHmsEntity implements Serializable, Cloneable {

    /**
     * 직렬화 버전 ID
     * 객체 직렬화를 위한 버전 식별자
     */
    private static final long serialVersionUID = -12L;

    /**
     * HMS 메시지 고유 ID
     * 데이터베이스에서 자동 생성되는 기본 키
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * HMS 메시지 ID
     * HMS 메시지를 고유하게 식별하는 ID
     */
    @TableField("hms_id")
    private String hmsId;

    /**
     * 타스크 ID
     * HMS 메시지가 발생한 작업의 식별자
     */
    @TableField("tid")
    private String tid;

    /**
     * 바인딩 ID
     * 디바이스 바인딩과 관련된 식별자
     */
    @TableField("bid")
    private String bid;

    /**
     * 디바이스 시리얼 번호
     * HMS 메시지가 발생한 디바이스의 시리얼 번호
     */
    @TableField("sn")
    private String sn;

    /**
     * HMS 메시지 레벨
     * HMS 메시지의 중요도 레벨 (예: 1-경고, 2-오류, 3-심각 등)
     */
    @TableField("level")
    private Integer level;

    /**
     * HMS 모듈
     * HMS 메시지가 발생한 디바이스 모듈의 식별자
     */
    @TableField("module")
    private Integer module;

    /**
     * HMS 메시지 키
     * HMS 메시지를 식별하는 고유한 키
     */
    @TableField("hms_key")
    private String hmsKey;

    /**
     * 중국어 메시지
     * HMS 메시지의 중국어 버전
     */
    @TableField("message_zh")
    private String messageZh;

    /**
     * 영어 메시지
     * HMS 메시지의 영어 버전
     */
    @TableField("message_en")
    private String messageEn;

    /**
     * 생성 시간
     * HMS 메시지가 생성된 시간 (밀리초)
     */
    @TableField("create_time")
    private Long createTime;

    /**
     * 수정 시간
     * HMS 메시지가 마지막으로 수정된 시간 (밀리초)
     */
    @TableField("update_time")
    private Long updateTime;

    /**
     * 현재 객체의 복사본을 생성합니다.
     * 
     * 이 메서드는 Cloneable 인터페이스를 구현하여 객체의
     * 안전한 복제를 제공합니다. 복제 실패 시 기본값으로
     * 새로운 객체를 생성하여 반환합니다.
     * 
     * @return 현재 객체의 복사본
     */
    @Override
    public DeviceHmsEntity clone() {
        try {
            return (DeviceHmsEntity) super.clone();
        } catch (CloneNotSupportedException e) {
            return DeviceHmsEntity.builder()
                    .bid(this.getBid())
                    .tid(this.getTid())
                    .createTime(this.getCreateTime())
                    .updateTime(this.getUpdateTime())
                    .sn(this.getSn())
                    .build();
        }
    }
}
