package com.dji.sample.media.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 미디어 파일 데이터 전송 객체
 * 미디어 파일의 기본 정보를 담는 DTO입니다.
 * 클라이언트와 서버 간의 미디어 파일 정보 전송에 사용되며,
 * 파일의 메타데이터와 관련 정보를 포함합니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/12/9
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "MediaFile", description = "미디어 파일의 기본 정보를 담는 DTO")
public class MediaFileDTO {

    /**
     * 파일 ID
     * 미디어 파일을 식별하는 고유한 ID입니다.
     */
    @Schema(description = "미디어 파일 고유 ID")
    private String fileId;

    /**
     * 파일명
     * 미디어 파일의 원본 파일명입니다.
     */
    @Schema(description = "원본 파일명")
    private String fileName;

    /**
     * 파일 경로
     * 미디어 파일이 저장된 로컬 경로입니다.
     */
    @Schema(description = "서버 저장 경로")
    private String filePath;

    /**
     * 객체 키
     * 클라우드 스토리지에서 파일을 식별하는 키 값입니다.
     */
    @Schema(description = "클라우드 스토리지 객체 키")
    private String objectKey;

    /**
     * 서브 파일 타입
     * 미디어 파일의 세부 유형을 나타내는 값입니다.
     * 예: 사진, 비디오, 썸네일 등
     */
    @Schema(description = "서브 파일 타입(예: PHOTO, VIDEO, THUMBNAIL)")
    private String subFileType;

    /**
     * 원본 파일 여부
     * 해당 파일이 원본 파일인지 여부를 나타냅니다.
     * true: 원본 파일, false: 처리된 파일(썸네일, 압축본 등)
     */
    @Schema(description = "원본 파일 여부")
    private Boolean isOriginal;

    /**
     * 드론 정보
     * 미디어 파일을 촬영한 드론의 정보입니다.
     */
    @Schema(description = "드론 기체 정보")
    private String drone;

    /**
     * 페이로드 정보
     * 미디어 파일을 촬영한 카메라나 센서의 정보입니다.
     */
    @Schema(description = "카메라/센서(페이로드) 정보")
    private String payload;

    /**
     * 작은 지문 (Tiny Fingerprint)
     * 파일의 빠른 중복 검사를 위한 축약된 해시 값입니다.
     */
    @Schema(description = "축약된 해시(중복 검사 용)")
    private String tinnyFingerprint;

    /**
     * 파일 지문 (Fingerprint)
     * 파일의 무결성을 검증하기 위한 전체 해시 값입니다.
     */
    @Schema(description = "전체 파일 해시(무결성 검증 용)")
    private String fingerprint;

    /**
     * 생성 시간
     * 미디어 파일이 생성된 시간입니다.
     */
    @Schema(description = "생성 시각")
    private LocalDateTime createTime;

    /**
     * 작업 ID
     * 해당 미디어 파일이 속한 작업의 ID입니다.
     */
    @Schema(description = "작업(미션) ID")
    private String jobId;
}
