package com.dji.sample.manage.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 로그 파일 인덱스 엔티티 클래스
 * 
 * DJI Cloud API의 로그 파일 인덱스 정보를 데이터베이스에서 관리하는 엔티티 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 로그 파일 인덱스 기본 정보 관리
 *    - 부팅 인덱스 및 파일 ID 관리
 *    - 시작 시간 및 종료 시간 관리
 *    - 파일 크기 및 디바이스 시리얼 번호 관리
 * 
 * 2. 로그 파일 시간 범위 관리
 *    - 로그 파일의 시작 및 종료 시간 관리
 *    - 시간 기반 로그 파일 검색 및 필터링 지원
 *    - 로그 파일의 시간 범위 추적
 * 
 * 3. 디바이스 도메인 관리
 *    - 디바이스 도메인 정보 관리
 *    - 도메인별 로그 파일 분류 및 관리
 *    - 디바이스 타입별 로그 파일 구분
 * 
 * 4. MyBatis-Plus 통합
 *    - @TableName 어노테이션으로 테이블 매핑
 *    - @TableField 어노테이션으로 필드 매핑
 *    - 자동 필드 채우기 기능 지원
 * 
 * 이 클래스는 logs_file_index 테이블과 매핑되며, 로그 파일의
 * 인덱스 정보를 데이터베이스에서 관리하는 표준화된
 * 엔티티 구조를 제공합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/9/8
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("logs_file_index")
public class LogsFileIndexEntity implements Serializable {

    /**
     * 직렬화 버전 ID
     * 객체 직렬화를 위한 버전 식별자
     */
    private static final long serialVersionUID = -12L;

    /**
     * 로그 파일 인덱스 고유 ID
     * 데이터베이스에서 자동 생성되는 기본 키
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 부팅 인덱스
     * 디바이스 부팅 시의 인덱스 번호
     */
    @TableField("boot_index")
    private Integer bootIndex;

    /**
     * 파일 ID
     * 로그 파일을 고유하게 식별하는 ID
     */
    @TableField("file_id")
    private String fileId;

    /**
     * 시작 시간
     * 로그 파일의 시작 시간 (밀리초)
     */
    @TableField("start_time")
    private Long startTime;

    /**
     * 종료 시간
     * 로그 파일의 종료 시간 (밀리초)
     */
    @TableField("end_time")
    private Long endTime;

    /**
     * 파일 크기
     * 로그 파일의 크기 (바이트 단위)
     */
    @TableField("size")
    private Long size;

    /**
     * 디바이스 시리얼 번호
     * 로그 파일이 생성된 디바이스의 시리얼 번호
     */
    @TableField("device_sn")
    private String deviceSn;

    /**
     * 디바이스 도메인
     * 디바이스가 속한 도메인 (예: AIRCRAFT=0, DOCK=1 등)
     */
    @TableField("domain")
    private Integer domain;

    /**
     * 생성 시간
     * 로그 파일 인덱스 레코드가 생성된 시간 (밀리초)
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Long createTime;

    /**
     * 수정 시간
     * 로그 파일 인덱스 레코드가 마지막으로 수정된 시간 (밀리초)
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;
}
