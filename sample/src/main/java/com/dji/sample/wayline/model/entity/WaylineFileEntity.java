package com.dji.sample.wayline.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DJI Cloud API 웨이라인 파일 엔티티 클래스
 * 
 * 이 클래스는 웨이라인 파일 정보를 데이터베이스에 저장하기 위한 엔티티 클래스입니다.
 * MyBatis-Plus 프레임워크를 사용하여 데이터베이스 테이블과 매핑되며,
 * 웨이라인 파일의 모든 메타데이터와 속성 정보를 포함합니다.
 * 
 * 주요 기능:
 * - 웨이라인 파일의 기본 정보 저장 (이름, ID, 드론 모델 등)
 * - 웨이라인 파일의 메타데이터 저장 (페이로드 모델, 템플릿 타입 등)
 * - 웨이라인 파일의 사용자 관련 정보 저장 (업로더, 즐겨찾기 상태 등)
 * - 웨이라인 파일의 스토리지 정보 저장 (객체 키, 서명 등)
 * 
 * 데이터베이스 테이블: wayline_file
 * 
 * MyBatis-Plus 어노테이션:
 * - @TableName: 데이터베이스 테이블명 지정
 * - @TableId: 기본 키 필드 지정
 * - @TableField: 데이터베이스 컬럼명 매핑
 * - @FieldFill: 자동 필드 채우기 설정
 * 
 * @author sean
 * @version 0.3
 * @date 2021/12/22
 */
@Data
@TableName("wayline_file")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WaylineFileEntity implements Serializable {

    /**
     * 웨이라인 파일 고유 ID (기본 키)
     * 데이터베이스에서 자동 생성되는 웨이라인 파일의 고유 식별자
     * 
     * MyBatis-Plus 설정:
     * - IdType.AUTO: 데이터베이스에서 자동 증가하는 ID 사용
     * - 데이터베이스 컬럼: id (INTEGER, AUTO_INCREMENT)
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 웨이라인 파일명
     * 사용자가 업로드한 웨이라인 파일의 원본 파일명
     * 
     * 데이터베이스 컬럼: name (VARCHAR)
     * 예시: "정찰_미션_001.kmz", "측량_구역_A.kmz"
     */
    @TableField("name")
    private String name;

    /**
     * 웨이라인 파일 고유 ID (외부 식별자)
     * 시스템에서 생성하는 웨이라인 파일의 고유 식별자
     * API 응답에서 사용되는 외부 ID
     * 
     * 데이터베이스 컬럼: wayline_id (VARCHAR)
     * 예시: "wayline_1234567890"
     */
    @TableField("wayline_id")
    private String waylineId;

    /**
     * 드론 모델 키
     * 이 웨이라인이 지원하는 드론 모델의 식별자
     * 
     * 데이터베이스 컬럼: drone_model_key (VARCHAR)
     * 예시: "0-67-0" (M30 Series), "0-67-1" (M300 RTK)
     */
    @TableField("drone_model_key")
    private String droneModelKey;

    /**
     * 페이로드 모델 키 목록 (JSON 문자열)
     * 이 웨이라인이 지원하는 페이로드 장비들의 식별자 목록
     * JSON 배열 형태로 저장됨
     * 
     * 데이터베이스 컬럼: payload_model_keys (TEXT)
     * 예시: "[\"0-67-0\", \"0-67-1\"]" (카메라, 센서 등)
     */
    @TableField("payload_model_keys")
    private String payloadModelKeys;

    /**
     * 웨이라인 파일 서명 (해시값)
     * 파일의 무결성을 검증하기 위한 해시값
     * 파일 내용이 변경되었는지 확인하는 용도
     * 
     * 데이터베이스 컬럼: sign (VARCHAR)
     * 예시: "sha256_hash_value_of_file_content"
     */
    @TableField("sign")
    private String sign;

    /**
     * 소속 워크스페이스 ID
     * 이 웨이라인 파일이 속한 워크스페이스의 고유 식별자
     * 다중 테넌트 환경에서 데이터 격리를 위해 사용
     * 
     * 데이터베이스 컬럼: workspace_id (VARCHAR)
     * 예시: "workspace_123"
     */
    @TableField("workspace_id")
    private String workspaceId;

    /**
     * 즐겨찾기 여부
     * 현재 사용자가 이 웨이라인을 즐겨찾기로 설정했는지 여부
     * 
     * 데이터베이스 컬럼: favorited (BOOLEAN)
     * - true: 즐겨찾기 설정됨
     * - false: 즐겨찾기 해제됨
     */
    @TableField("favorited")
    private Boolean favorited;

    /**
     * 템플릿 타입 목록 (JSON 문자열)
     * 이 웨이라인이 지원하는 템플릿 타입들의 식별자 목록
     * JSON 배열 형태로 저장됨
     * 
     * 데이터베이스 컬럼: template_types (TEXT)
     * 예시: "[1, 2, 3]" (정찰, 측량, 모니터링 등)
     */
    @TableField("template_types")
    private String templateTypes;

    /**
     * 클라우드 스토리지 객체 키
     * 웨이라인 파일이 저장된 클라우드 스토리지의 객체 키
     * 파일 다운로드 시 이 키를 사용하여 파일에 접근
     * 
     * 데이터베이스 컬럼: object_key (VARCHAR)
     * 예시: "waylines/2023/12/wayline_1234567890.kmz"
     */
    @TableField("object_key")
    private String objectKey;

    /**
     * 업로드한 사용자명
     * 이 웨이라인 파일을 업로드한 사용자의 이름
     * 
     * 데이터베이스 컬럼: user_name (VARCHAR)
     * 예시: "admin", "pilot_001"
     */
    @TableField("user_name")
    private String username;

    /**
     * 생성 시간 (Unix timestamp)
     * 웨이라인 파일이 데이터베이스에 생성된 시간
     * 
     * MyBatis-Plus 설정:
     * - FieldFill.INSERT: 새 레코드 생성 시 자동으로 현재 시간 설정
     * - 밀리초 단위의 타임스탬프 값
     * 
     * 데이터베이스 컬럼: create_time (BIGINT)
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Long createTime;

    /**
     * 최종 수정 시간 (Unix timestamp)
     * 웨이라인 파일 정보가 마지막으로 수정된 시간
     * 
     * MyBatis-Plus 설정:
     * - FieldFill.INSERT_UPDATE: 레코드 생성 및 수정 시 자동으로 현재 시간 설정
     * - 밀리초 단위의 타임스탬프 값
     * 
     * 데이터베이스 컬럼: update_time (BIGINT)
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;
}
