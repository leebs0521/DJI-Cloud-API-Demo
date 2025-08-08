package com.dji.sdk.cloudapi.log;

import com.dji.sdk.cloudapi.storage.CredentialsToken;
import com.dji.sdk.cloudapi.storage.OssTypeEnum;
import com.dji.sdk.cloudapi.storage.StsCredentialsResponse;
import com.dji.sdk.common.BaseModel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 파일 업로드 시작 요청을 나타내는 클래스
 * 파일 업로드를 시작하기 위한 요청 정보를 관리합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/9/8
 */
public class FileUploadStartRequest extends BaseModel {

    /** 스토리지 버킷명 */
    @NotNull
    private String bucket;

    /** 인증 토큰 정보 */
    @NotNull
    @Valid
    private CredentialsToken credentials;

    /** 스토리지 엔드포인트 */
    @NotNull
    private String endpoint;

    /** 파일 저장 디렉토리 */
    @NotNull
    private String fileStoreDir;

    /** OSS 제공자 타입 */
    @NotNull
    private OssTypeEnum provider;

    /** 파일 타입 (기본값: text_log) */
    @NotNull
    private String fileType = "text_log";

    /** 파일 업로드 시작 매개변수 */
    @NotNull
    @Valid
    private FileUploadStartParam params;

    /** 스토리지 리전 */
    @NotNull
    private String region;

    /**
     * STS 자격 증명 응답으로부터 FileUploadStartRequest를 생성합니다.
     * 
     * @param sts STS 자격 증명 응답
     */
    public FileUploadStartRequest(StsCredentialsResponse sts) {
        this.bucket = sts.getBucket();
        long expire = sts.getCredentials().getExpire();
        sts.getCredentials().setExpire(System.currentTimeMillis() + (expire - 60) * 1000);
        this.credentials = sts.getCredentials();
        this.endpoint = sts.getEndpoint();
        this.fileStoreDir = sts.getObjectKeyPrefix();
        this.provider = sts.getProvider();
        this.region = sts.getRegion();
    }

    /**
     * 기본 생성자
     */
    public FileUploadStartRequest() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "FileUploadStartRequest{" +
                "bucket='" + bucket + '\'' +
                ", credentials=" + credentials +
                ", endpoint='" + endpoint + '\'' +
                ", fileStoreDir='" + fileStoreDir + '\'' +
                ", provider=" + provider +
                ", fileType='" + fileType + '\'' +
                ", params=" + params +
                ", region='" + region + '\'' +
                '}';
    }

    /**
     * 스토리지 버킷명을 반환합니다.
     * 
     * @return 스토리지 버킷명
     */
    public String getBucket() {
        return bucket;
    }

    /**
     * 스토리지 버킷명을 설정합니다.
     * 
     * @param bucket 설정할 스토리지 버킷명
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FileUploadStartRequest setBucket(String bucket) {
        this.bucket = bucket;
        return this;
    }

    /**
     * 인증 토큰 정보를 반환합니다.
     * 
     * @return 인증 토큰 정보
     */
    public CredentialsToken getCredentials() {
        return credentials;
    }

    /**
     * 인증 토큰 정보를 설정합니다.
     * 
     * @param credentials 설정할 인증 토큰 정보
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FileUploadStartRequest setCredentials(CredentialsToken credentials) {
        this.credentials = credentials;
        return this;
    }

    /**
     * 스토리지 엔드포인트를 반환합니다.
     * 
     * @return 스토리지 엔드포인트
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * 스토리지 엔드포인트를 설정합니다.
     * 
     * @param endpoint 설정할 스토리지 엔드포인트
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FileUploadStartRequest setEndpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    /**
     * 파일 저장 디렉토리를 반환합니다.
     * 
     * @return 파일 저장 디렉토리
     */
    public String getFileStoreDir() {
        return fileStoreDir;
    }

    /**
     * 파일 저장 디렉토리를 설정합니다.
     * 
     * @param fileStoreDir 설정할 파일 저장 디렉토리
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FileUploadStartRequest setFileStoreDir(String fileStoreDir) {
        this.fileStoreDir = fileStoreDir;
        return this;
    }

    /**
     * OSS 제공자 타입을 반환합니다.
     * 
     * @return OSS 제공자 타입
     */
    public OssTypeEnum getProvider() {
        return provider;
    }

    /**
     * OSS 제공자 타입을 설정합니다.
     * 
     * @param provider 설정할 OSS 제공자 타입
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FileUploadStartRequest setProvider(OssTypeEnum provider) {
        this.provider = provider;
        return this;
    }

    /**
     * 파일 타입을 반환합니다.
     * 
     * @return 파일 타입
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * 파일 업로드 시작 매개변수를 반환합니다.
     * 
     * @return 파일 업로드 시작 매개변수
     */
    public FileUploadStartParam getParams() {
        return params;
    }

    /**
     * 파일 업로드 시작 매개변수를 설정합니다.
     * 
     * @param params 설정할 파일 업로드 시작 매개변수
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FileUploadStartRequest setParams(FileUploadStartParam params) {
        this.params = params;
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
     * @param region 설정할 스토리지 리전
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FileUploadStartRequest setRegion(String region) {
        this.region = region;
        return this;
    }
}
