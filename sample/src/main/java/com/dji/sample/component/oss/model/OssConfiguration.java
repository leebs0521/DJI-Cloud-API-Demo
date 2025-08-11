package com.dji.sample.component.oss.model;

import com.dji.sdk.cloudapi.storage.OssTypeEnum;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * OSS 설정 클래스
 * 
 * 객체 저장소 서비스의 설정 정보를 관리하는 클래스입니다.
 * 다양한 OSS 제공자(Aliyun, AWS, MinIO)의 설정을 통합 관리합니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/12/9
 */
@ConfigurationProperties(prefix = "oss")
@Component
public class OssConfiguration {

    /** OSS 제공자 타입 (Aliyun, AWS, MinIO) */
    public static OssTypeEnum provider;

    /** 객체 저장소 서비스 사용 여부 */
    public static boolean enable;

    /** OSS 엔드포인트 (주소 시작에 프로토콜 포함 필요) */
    public static String endpoint;

    /** 접근 키 */
    public static String accessKey;

    /** 시크릿 키 */
    public static String secretKey;

    /** 리전 */
    public static String region;

    /** 만료 시간 (초) */
    public static Long expire;

    /** 역할 세션 이름 */
    public static String roleSessionName;

    /** 역할 ARN */
    public static String roleArn;

    /** 버킷 이름 */
    public static String bucket;

    /** 객체 디렉토리 접두사 */
    public static String objectDirPrefix;

    /**
     * OSS 제공자를 설정합니다.
     * 
     * @param provider OSS 제공자 타입
     */
    public void setProvider(OssTypeEnum provider) {
        OssConfiguration.provider = provider;
    }

    /**
     * OSS 사용 여부를 설정합니다.
     * 
     * @param enable OSS 사용 여부
     */
    public void setEnable(boolean enable) {
        OssConfiguration.enable = enable;
    }

    /**
     * OSS 엔드포인트를 설정합니다.
     * 
     * @param endpoint OSS 엔드포인트
     */
    public void setEndpoint(String endpoint) {
        OssConfiguration.endpoint = endpoint;
    }

    /**
     * 접근 키를 설정합니다.
     * 
     * @param accessKey 접근 키
     */
    public void setAccessKey(String accessKey) {
        OssConfiguration.accessKey = accessKey;
    }

    /**
     * 시크릿 키를 설정합니다.
     * 
     * @param secretKey 시크릿 키
     */
    public void setSecretKey(String secretKey) {
        OssConfiguration.secretKey = secretKey;
    }

    /**
     * 리전을 설정합니다.
     * 
     * @param region 리전
     */
    public void setRegion(String region) {
        OssConfiguration.region = region;
    }

    /**
     * 만료 시간을 설정합니다.
     * 
     * @param expire 만료 시간 (초)
     */
    public void setExpire(Long expire) {
        OssConfiguration.expire = expire;
    }

    /**
     * 역할 세션 이름을 설정합니다.
     * 
     * @param roleSessionName 역할 세션 이름
     */
    public void setRoleSessionName(String roleSessionName) {
        OssConfiguration.roleSessionName = roleSessionName;
    }

    /**
     * 역할 ARN을 설정합니다.
     * 
     * @param roleArn 역할 ARN
     */
    public void setRoleArn(String roleArn) {
        OssConfiguration.roleArn = roleArn;
    }

    /**
     * 버킷 이름을 설정합니다.
     * 
     * @param bucket 버킷 이름
     */
    public void setBucket(String bucket) {
        OssConfiguration.bucket = bucket;
    }

    /**
     * 객체 디렉토리 접두사를 설정합니다.
     * 
     * @param objectDirPrefix 객체 디렉토리 접두사
     */
    public void setObjectDirPrefix(String objectDirPrefix) {
        OssConfiguration.objectDirPrefix = objectDirPrefix;
    }
}



