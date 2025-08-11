package com.dji.sample.component.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.dji.sample.component.oss.model.OssConfiguration;
import com.dji.sample.component.oss.service.IOssService;
import com.dji.sdk.cloudapi.storage.CredentialsToken;
import com.dji.sdk.cloudapi.storage.OssTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Objects;

/**
 * Aliyun OSS 서비스 구현 클래스
 * 
 * Aliyun OSS(객체 저장소 서비스)에 대한 구현을 제공합니다.
 * STS(Security Token Service)를 사용한 임시 자격 증명과 객체 관리 기능을 제공합니다.
 * 
 * @author sean
 * @version 0.3
 * @date 2021/12/23
 */
@Service
@Slf4j
public class AliyunOssServiceImpl implements IOssService {

    /** Aliyun OSS 클라이언트 */
    private OSS ossClient;
    
    @Override
    public OssTypeEnum getOssType() {
        return OssTypeEnum.ALIYUN;
    }

    @Override
    public CredentialsToken getCredentials() {

        try {
            // Aliyun STS 클라이언트 생성
            DefaultProfile profile = DefaultProfile.getProfile(
                    OssConfiguration.region, OssConfiguration.accessKey, OssConfiguration.secretKey);
            IAcsClient client = new DefaultAcsClient(profile);

            // STS 역할 가정 요청 생성
            AssumeRoleRequest request = new AssumeRoleRequest();
            request.setDurationSeconds(OssConfiguration.expire);
            request.setRoleArn(OssConfiguration.roleArn);
            request.setRoleSessionName(OssConfiguration.roleSessionName);

            // STS 응답에서 자격 증명 추출
            AssumeRoleResponse.Credentials response = client.getAcsResponse(request).getCredentials();
            return new CredentialsToken(response.getAccessKeyId(), response.getAccessKeySecret(), response.getSecurityToken(), OssConfiguration.expire);

        } catch (ClientException e) {
            log.debug("Failed to obtain sts.");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public URL getObjectUrl(String bucket, String objectKey) {
        // 먼저 객체가 존재하는지 확인
        boolean isExist = ossClient.doesObjectExist(bucket, objectKey);
        if (!isExist) {
            throw new OSSException("The object does not exist.");
        }

        // 서명된 URL 생성
        return ossClient.generatePresignedUrl(bucket, objectKey,
                new Date(System.currentTimeMillis() + OssConfiguration.expire * 1000));
    }

    @Override
    public Boolean deleteObject(String bucket, String objectKey) {
        if (!ossClient.doesObjectExist(bucket, objectKey)) {
            return true;
        }
        ossClient.deleteObject(bucket, objectKey);
        return true;
    }

    @Override
    public InputStream getObject(String bucket, String objectKey) {
        return ossClient.getObject(bucket, objectKey).getObjectContent();
    }

    @Override
    public void putObject(String bucket, String objectKey, InputStream input) {
        if (ossClient.doesObjectExist(bucket, objectKey)) {
            throw new RuntimeException("The filename already exists.");
        }
        PutObjectResult objectResult = ossClient.putObject(new PutObjectRequest(bucket, objectKey, input, new ObjectMetadata()));
        log.info("Upload FlighttaskCreateFile: {}", objectResult.getETag());
    }

    /**
     * Aliyun OSS 클라이언트를 생성합니다.
     */
    public void createClient() {
        if (Objects.nonNull(this.ossClient)) {
            return;
        }
        this.ossClient = new OSSClientBuilder()
                .build(OssConfiguration.endpoint, OssConfiguration.accessKey, OssConfiguration.secretKey);
    }
}
