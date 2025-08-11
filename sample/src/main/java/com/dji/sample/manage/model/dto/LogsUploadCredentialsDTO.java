package com.dji.sample.manage.model.dto;

import com.dji.sdk.cloudapi.log.FileUploadStartParam;
import com.dji.sdk.cloudapi.storage.CredentialsToken;
import com.dji.sdk.cloudapi.storage.OssTypeEnum;
import com.dji.sdk.cloudapi.storage.StsCredentialsResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 로그 업로드 인증 정보 전송 객체 (DTO) 클래스
 * 
 * DJI Cloud API의 로그 파일 업로드를 위한 인증 정보를 전송하는 데이터 전송 객체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 클라우드 스토리지 인증 정보 관리
 *    - STS(Security Token Service) 인증 토큰 관리
 *    - 클라우드 스토리지 버킷 및 엔드포인트 관리
 *    - 인증 정보 만료 시간 관리
 * 
 * 2. 파일 업로드 설정 관리
 *    - 파일 저장 디렉토리 및 객체 키 접두사 관리
 *    - 파일 타입 및 업로드 파라미터 관리
 *    - 지역별 스토리지 설정 관리
 * 
 * 3. 다중 클라우드 스토리지 지원
 *    - 다양한 OSS(Object Storage Service) 제공자 지원
 *    - AWS S3, Alibaba Cloud OSS 등 호환성 제공
 *    - 스토리지 제공자별 설정 관리
 * 
 * 4. STS 응답 변환 기능
 *    - StsCredentialsResponse를 LogsUploadCredentialsDTO로 변환
 *    - 인증 토큰 만료 시간 자동 조정
 *    - 안전한 인증 정보 전달
 * 
 * 이 클래스는 로그 파일을 클라우드 스토리지에 안전하게 업로드하기 위한
 * 모든 인증 및 설정 정보를 포함하며, STS 기반의 임시 인증을
 * 통한 보안된 파일 업로드를 지원합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/9/8
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogsUploadCredentialsDTO {

    /**
     * 스토리지 버킷
     * 로그 파일이 저장될 클라우드 스토리지 버킷명
     */
    private String bucket;

    /**
     * 인증 토큰
     * 클라우드 스토리지 접근을 위한 STS 인증 토큰
     */
    private CredentialsToken credentials;

    /**
     * 스토리지 엔드포인트
     * 클라우드 스토리지 서비스의 엔드포인트 URL
     */
    private String endpoint;

    /**
     * 파일 저장 디렉토리
     * 로그 파일이 저장될 객체 키의 접두사 경로
     */
    @JsonProperty("file_store_dir")
    private String objectKeyPrefix;

    /**
     * OSS 제공자
     * 클라우드 스토리지 서비스 제공자 (AWS, Alibaba Cloud 등)
     */
    private OssTypeEnum provider;

    /**
     * 파일 타입
     * 업로드할 파일의 타입 (기본값: "text_log")
     */
    @Builder.Default
    private String fileType = "text_log";

    /**
     * 파일 업로드 파라미터
     * 파일 업로드 시작을 위한 추가 파라미터
     */
    private FileUploadStartParam params;

    /**
     * 스토리지 지역
     * 클라우드 스토리지의 지역 정보
     */
    private String region;

    /**
     * STS 응답을 기반으로 LogsUploadCredentialsDTO를 생성합니다.
     * 
     * 이 생성자는 StsCredentialsResponse를 받아서 필요한 정보를 추출하고,
     * 인증 토큰의 만료 시간을 현재 시간 기준으로 조정합니다.
     * 
     * @param sts STS 응답 객체
     */
    public LogsUploadCredentialsDTO(StsCredentialsResponse sts) {
        this.bucket = sts.getBucket();
        long expire = sts.getCredentials().getExpire();
        sts.getCredentials().setExpire(System.currentTimeMillis() + (expire - 60) * 1000);
        this.credentials = sts.getCredentials();
        this.endpoint = sts.getEndpoint();
        this.objectKeyPrefix = sts.getObjectKeyPrefix();
        this.provider = sts.getProvider();
        this.region = sts.getRegion();
    }
}
