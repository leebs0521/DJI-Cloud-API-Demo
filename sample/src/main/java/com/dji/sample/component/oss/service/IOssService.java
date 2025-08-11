package com.dji.sample.component.oss.service;

import com.dji.sdk.cloudapi.storage.CredentialsToken;
import com.dji.sdk.cloudapi.storage.OssTypeEnum;

import java.io.InputStream;
import java.net.URL;

/**
 * OSS 서비스 인터페이스
 * 
 * 객체 저장소 서비스의 기본 기능을 정의하는 인터페이스입니다.
 * 다양한 OSS 제공자(Aliyun, AWS, MinIO)에 대한 공통 인터페이스를 제공합니다.
 * 
 * @author sean
 * @version 0.3
 * @date 2021/12/23
 */
public interface IOssService {

    /**
     * OSS 타입을 반환합니다.
     * 
     * @return OSS 타입 열거형
     */
    OssTypeEnum getOssType();

    /**
     * 임시 자격 증명을 가져옵니다.
     * 
     * @return 임시 자격 증명 토큰
     */
    CredentialsToken getCredentials();

    /**
     * 버킷 이름과 객체 이름을 기반으로 객체의 주소를 가져옵니다.
     * 
     * @param bucket 버킷 이름
     * @param objectKey 객체 이름
     * @return 다운로드 링크
     */
    URL getObjectUrl(String bucket, String objectKey);

    /**
     * 저장소 버킷의 객체를 삭제합니다.
     * 
     * @param bucket 버킷 이름
     * @param objectKey 객체 이름
     * @return 삭제 성공 여부
     */
    Boolean deleteObject(String bucket, String objectKey);

    /**
     * 객체의 내용을 가져옵니다.
     * 
     * @param bucket 버킷 이름
     * @param objectKey 객체 이름
     * @return 객체 내용 스트림
     */
    InputStream getObject(String bucket, String objectKey);

    /**
     * 객체를 저장소에 업로드합니다.
     * 
     * @param bucket 버킷 이름
     * @param objectKey 객체 이름
     * @param input 업로드할 데이터 스트림
     */
    void putObject(String bucket, String objectKey, InputStream input);

    /**
     * OSS 클라이언트를 생성합니다.
     */
    void createClient();
}
