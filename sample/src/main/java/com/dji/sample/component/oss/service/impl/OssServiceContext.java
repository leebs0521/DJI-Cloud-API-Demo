package com.dji.sample.component.oss.service.impl;

import com.dji.sample.component.oss.model.OssConfiguration;
import com.dji.sample.component.oss.service.IOssService;
import com.dji.sdk.cloudapi.storage.CredentialsToken;
import com.dji.sdk.cloudapi.storage.OssTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * OSS 서비스 컨텍스트 클래스
 * 
 * 다양한 OSS 제공자(Aliyun, AWS, MinIO) 중 설정에 따라 적절한 서비스를 선택하고
 * 해당 서비스의 기능을 제공하는 컨텍스트 클래스입니다.
 * 
 * @author sean
 * @version 1.0
 * @date 2022/5/30
 */
@Service
public class OssServiceContext {

    /** 선택된 OSS 서비스 */
    private IOssService ossService;

    /**
     * OSS 서비스 컨텍스트 생성자
     * 
     * 설정된 OSS 제공자에 따라 적절한 서비스를 선택합니다.
     * 
     * @param ossServices 사용 가능한 모든 OSS 서비스 목록
     * @param configuration OSS 설정
     */
    @Autowired
    public OssServiceContext(List<IOssService> ossServices, OssConfiguration configuration) {
        if (!OssConfiguration.enable) {
            return;
        }
        this.ossService = ossServices.stream()
                .filter(ossService -> ossService.getOssType() == OssConfiguration.provider)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Oss provider is illegal. Optional: " +
                        Arrays.toString(Arrays.stream(OssTypeEnum.values()).map(OssTypeEnum::getType).toArray())));
    }

    /**
     * OSS 서비스를 반환합니다.
     * 
     * @return 선택된 OSS 서비스
     */
    IOssService getOssService() {
        return this.ossService;
    }

    /**
     * 임시 자격 증명을 가져옵니다.
     * 
     * @return 임시 자격 증명 토큰
     */
    public CredentialsToken getCredentials() {
        return this.ossService.getCredentials();
    }

    /**
     * 객체의 URL을 가져옵니다.
     * 
     * @param bucket 버킷 이름
     * @param objectKey 객체 이름
     * @return 객체 URL
     * @throws IllegalArgumentException 버킷 이름이나 객체 이름이 비어있는 경우
     */
    public URL getObjectUrl(String bucket, String objectKey) {
        if (!StringUtils.hasText(bucket) || !StringUtils.hasText(objectKey)) {
            throw new IllegalArgumentException();
        }
        return this.ossService.getObjectUrl(bucket, objectKey);
    }

    /**
     * 객체를 삭제합니다.
     * 
     * @param bucket 버킷 이름
     * @param objectKey 객체 이름
     * @return 삭제 성공 여부
     */
    public Boolean deleteObject(String bucket, String objectKey) {
        return this.ossService.deleteObject(bucket, objectKey);
    }

    /**
     * 객체의 내용을 가져옵니다.
     * 
     * @param bucket 버킷 이름
     * @param objectKey 객체 이름
     * @return 객체 내용 스트림
     */
    public InputStream getObject(String bucket, String objectKey) {
        return this.ossService.getObject(bucket, objectKey);
    }

    /**
     * 객체를 업로드합니다.
     * 
     * @param bucket 버킷 이름
     * @param objectKey 객체 이름
     * @param stream 업로드할 데이터 스트림
     */
    public void putObject(String bucket, String objectKey, InputStream stream) {
        this.ossService.putObject(bucket, objectKey, stream);
    }

    /**
     * OSS 클라이언트를 생성합니다.
     */
    void createClient() {
        this.ossService.createClient();
    }
}
