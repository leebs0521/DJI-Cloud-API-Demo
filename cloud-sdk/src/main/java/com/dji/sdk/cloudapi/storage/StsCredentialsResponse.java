package com.dji.sdk.cloudapi.storage;

import com.dji.sdk.common.BaseModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * STS(Security Token Service) 임시 자격 증명 응답 클래스
 * 
 * 이 클래스는 클라우드 스토리지 서비스에 대한 임시 자격 증명 정보를 담는 응답 데이터를 정의합니다.
 * BaseModel을 상속받아 유효성 검사를 지원하며, 클라우드 스토리지 서비스에
 * 안전하게 접근하기 위한 모든 필요한 정보를 포함합니다.
 * 
 * 주요 구성 요소:
 * - bucket: 스토리지 버킷 이름
 * - credentials: 임시 자격 증명 토큰
 * - endpoint: 스토리지 서비스 엔드포인트
 * - objectKeyPrefix: 객체 키 접두사
 * - provider: 스토리지 제공업체
 * - region: 스토리지 리전
 * 
 * 이 클래스는 클라우드 스토리지 서비스에 파일을 업로드하거나 다운로드할 때
 * 필요한 모든 인증 및 연결 정보를 제공하는 데 사용됩니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/12/7
 */
@Schema(description = "임시 자격 증명 데이터")
public class StsCredentialsResponse extends BaseModel {

    /**
     * 스토리지 버킷 이름
     * 
     * 클라우드 스토리지 서비스에서 파일을 저장할 버킷의 이름입니다.
     * 각 스토리지 서비스에서 고유한 버킷 이름을 가져야 합니다.
     * 
     * 예시: "bucket-api"
     */
    @Schema(description = "버킷 이름", example = "bucket-api")
    @NotNull
    private String bucket;

    /**
     * 임시 자격 증명 토큰
     * 
     * 클라우드 스토리지 서비스에 접근하기 위한 임시 자격 증명 정보입니다.
     * 액세스 키, 시크릿 키, 보안 토큰, 만료 시간 등을 포함합니다.
     */
    @NotNull
    @Valid
    @Schema(description = "임시 자격 증명의 토큰 데이터")
    private CredentialsToken credentials;

    /**
     * 스토리지 서비스 엔드포인트
     * 
     * 클라우드 스토리지 서비스에 접근하기 위한 API 엔드포인트 URL입니다.
     * HTTPS 프로토콜을 사용하는 URL이어야 합니다.
     * 
     * 제약 조건:
     * - HTTP 또는 HTTPS 프로토콜을 사용해야 함
     * 
     * 예시: "https://oss-cn-hangzhou.aliyuncs.com"
     */
    @NotNull
    @Schema(description = "외부 서비스를 위한 액세스 도메인 이름", example = "https://oss-cn-hangzhou.aliyuncs.com")
    @Pattern(regexp = "^http[s]?://.*$")
    private String endpoint;

    /**
     * 객체 키 접두사
     * 
     * 스토리지에 저장될 객체의 폴더 경로 접두사입니다.
     * 파일을 저장할 때 이 접두사가 자동으로 추가되어 파일을 체계적으로 관리할 수 있습니다.
     * 
     * 예시: "files/wayline"
     */
    @NotNull
    @JsonProperty("object_key_prefix")
    @Schema(description = "객체가 저장될 폴더 경로", example = "files/wayline")
    private String objectKeyPrefix;

    /**
     * 스토리지 제공업체
     * 
     * 사용할 클라우드 스토리지 서비스의 제공업체를 지정합니다.
     * ALIYUN, AWS, MINIO 중 하나를 선택할 수 있습니다.
     */
    @NotNull
    private OssTypeEnum provider;

    /**
     * 스토리지 리전
     * 
     * 클라우드 스토리지 서비스의 리전 정보입니다.
     * 각 제공업체별로 다른 리전 명명 규칙을 사용합니다.
     * 
     * 예시: "us-east-1", "cn-hangzhou"
     */
    @NotNull
    @Schema(description = "버킷이 위치한 리전", example = "us-east-1")
    private String region;

    public StsCredentialsResponse() {
    }

    @Override
    public String toString() {
        return "StsCredentialsResponse{" +
                "bucket='" + bucket + '\'' +
                ", credentials=" + credentials +
                ", endpoint='" + endpoint + '\'' +
                ", objectKeyPrefix='" + objectKeyPrefix + '\'' +
                ", provider='" + provider + '\'' +
                ", region='" + region + '\'' +
                '}';
    }

    /**
     * 스토리지 버킷 이름을 반환합니다.
     * 
     * @return 스토리지 버킷 이름
     */
    public String getBucket() {
        return bucket;
    }

    /**
     * 스토리지 버킷 이름을 설정합니다.
     * 
     * @param bucket 스토리지 버킷 이름
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public StsCredentialsResponse setBucket(String bucket) {
        this.bucket = bucket;
        return this;
    }

    /**
     * 임시 자격 증명 토큰을 반환합니다.
     * 
     * @return 임시 자격 증명 토큰
     */
    public CredentialsToken getCredentials() {
        return credentials;
    }

    /**
     * 임시 자격 증명 토큰을 설정합니다.
     * 
     * @param credentials 임시 자격 증명 토큰
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public StsCredentialsResponse setCredentials(CredentialsToken credentials) {
        this.credentials = credentials;
        return this;
    }

    /**
     * 스토리지 서비스 엔드포인트를 반환합니다.
     * 
     * @return 스토리지 서비스 엔드포인트
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * 스토리지 서비스 엔드포인트를 설정합니다.
     * 
     * @param endpoint 스토리지 서비스 엔드포인트
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public StsCredentialsResponse setEndpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    /**
     * 객체 키 접두사를 반환합니다.
     * 
     * @return 객체 키 접두사
     */
    public String getObjectKeyPrefix() {
        return objectKeyPrefix;
    }

    /**
     * 객체 키 접두사를 설정합니다.
     * 
     * @param objectKeyPrefix 객체 키 접두사
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public StsCredentialsResponse setObjectKeyPrefix(String objectKeyPrefix) {
        this.objectKeyPrefix = objectKeyPrefix;
        return this;
    }

    /**
     * 스토리지 제공업체를 반환합니다.
     * 
     * @return 스토리지 제공업체
     */
    public OssTypeEnum getProvider() {
        return provider;
    }

    /**
     * 스토리지 제공업체를 설정합니다.
     * 
     * @param provider 스토리지 제공업체
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public StsCredentialsResponse setProvider(OssTypeEnum provider) {
        this.provider = provider;
        return this;
    }

    /**
     * 스토리지 리전을 반환합니다.
     * 
     * @return 스토리지 리전
     */
    public String getRegion() {
        return region;
    }

    /**
     * 스토리지 리전을 설정합니다.
     * 
     * @param region 스토리지 리전
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public StsCredentialsResponse setRegion(String region) {
        this.region = region;
        return this;
    }
}
