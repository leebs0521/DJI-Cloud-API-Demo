package com.dji.sample.media.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 미디어 파일 엔티티
 * 데이터베이스의 미디어 파일 테이블과 매핑되는 엔티티 클래스입니다.
 * MyBatis-Plus를 사용하여 데이터베이스 CRUD 작업을 수행하며,
 * 미디어 파일의 모든 메타데이터와 관련 정보를 저장합니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/12/9
 */
@TableName(value = "media_file")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MediaFileEntity implements Serializable {

    /**
     * 데이터베이스 기본 키 (자동 증가)
     * 테이블의 고유 식별자로 사용됩니다.
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 파일 ID
     * 미디어 파일을 식별하는 고유한 ID입니다.
     */
    @TableField("file_id")
    private String fileId;

    /**
     * 파일명
     * 미디어 파일의 원본 파일명입니다.
     */
    @TableField("file_name")
    private String fileName;

    /**
     * 파일 경로
     * 미디어 파일이 저장된 로컬 경로입니다.
     */
    @TableField("file_path")
    private String filePath;

    /**
     * 워크스페이스 ID
     * 해당 미디어 파일이 속한 워크스페이스를 식별하는 ID입니다.
     */
    @TableField("workspace_id")
    private String workspaceId;

    /**
     * 파일 지문 (Fingerprint)
     * 파일의 무결성을 검증하기 위한 전체 해시 값입니다.
     * 파일 내용이 변경되었는지 확인하는데 사용됩니다.
     */
    @TableField("fingerprint")
    private String fingerprint;

    /**
     * 작은 지문 (Tiny Fingerprint)
     * 파일의 빠른 중복 검사를 위한 축약된 해시 값입니다.
     * 동일한 파일이 이미 업로드되었는지 빠르게 확인하는데 사용됩니다.
     */
    @TableField("tinny_fingerprint")
    private String tinnyFingerprint;

    /**
     * 객체 키
     * 클라우드 스토리지에서 파일을 식별하는 키 값입니다.
     * OSS나 S3 등의 클라우드 스토리지에서 파일에 접근할 때 사용됩니다.
     */
    @TableField("object_key")
    private String objectKey;

    /**
     * 서브 파일 타입
     * 미디어 파일의 세부 유형을 나타내는 정수 값입니다.
     * 예: 1-사진, 2-비디오, 3-썸네일 등
     */
    @TableField("sub_file_type")
    private Integer subFileType;

    /**
     * 원본 파일 여부
     * 해당 파일이 원본 파일인지 여부를 나타냅니다.
     * true: 원본 파일, false: 처리된 파일(썸네일, 압축본 등)
     */
    @TableField("is_original")
    private Boolean isOriginal;

    /**
     * 드론 정보
     * 미디어 파일을 촬영한 드론의 정보입니다.
     * 드론의 모델명이나 시리얼 번호 등을 포함할 수 있습니다.
     */
    @TableField("drone")
    private String drone;

    /**
     * 페이로드 정보
     * 미디어 파일을 촬영한 카메라나 센서의 정보입니다.
     * 카메라 모델명이나 센서 타입 등을 포함할 수 있습니다.
     */
    @TableField("payload")
    private String payload;

    /**
     * 작업 ID
     * 해당 미디어 파일이 속한 작업의 ID입니다.
     * 비행 작업이나 미디어 처리 작업을 식별하는데 사용됩니다.
     */
    @TableField("job_id")
    private String jobId;

    /**
     * 생성 시간
     * 레코드가 데이터베이스에 생성된 시간입니다.
     * MyBatis-Plus의 자동 필드 채우기 기능을 사용합니다.
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Long createTime;

    /**
     * 수정 시간
     * 레코드가 마지막으로 수정된 시간입니다.
     * MyBatis-Plus의 자동 필드 채우기 기능을 사용합니다.
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;
}

