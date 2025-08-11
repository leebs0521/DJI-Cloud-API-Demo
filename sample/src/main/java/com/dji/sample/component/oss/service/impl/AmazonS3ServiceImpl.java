package com.dji.sample.component.oss.service.impl;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.services.securitytoken.model.AssumeRoleRequest;
import com.amazonaws.services.securitytoken.model.AssumeRoleResult;
import com.amazonaws.services.securitytoken.model.Credentials;
import com.dji.sample.component.AuthInterceptor;
import com.dji.sample.component.oss.model.OssConfiguration;
import com.dji.sample.component.oss.service.IOssService;
import com.dji.sdk.cloudapi.storage.CredentialsToken;
import com.dji.sdk.cloudapi.storage.OssTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Amazon S3 서비스 구현 클래스
 * 
 * Amazon S3(Simple Storage Service)에 대한 구현을 제공합니다.
 * AWS STS(Security Token Service)를 사용한 임시 자격 증명과 객체 관리 기능을 제공합니다.
 * 
 * @author sean
 * @version 1.0
 * @date 2022/4/27
 */
@Slf4j
@Service
public class AmazonS3ServiceImpl implements IOssService {

    /** Amazon S3 클라이언트 */
    private AmazonS3 client;
    
    @Override
    public OssTypeEnum getOssType() {
        return OssTypeEnum.AWS;
    }

    @Override
    public CredentialsToken getCredentials() {
        // AWS STS 클라이언트 생성
        AWSSecurityTokenService stsClient = AWSSecurityTokenServiceClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(OssConfiguration.accessKey, OssConfiguration.secretKey)))
                .withRegion(OssConfiguration.region).build();

        // STS 역할 가정 요청 생성
        AssumeRoleRequest request = new AssumeRoleRequest()
                .withRoleArn(OssConfiguration.roleArn)
                .withRoleSessionName(OssConfiguration.roleSessionName)
                .withDurationSeconds(Math.toIntExact(OssConfiguration.expire));
        AssumeRoleResult result = stsClient.assumeRole(request);
        Credentials credentials = result.getCredentials();
        return new CredentialsToken(credentials.getAccessKeyId(), credentials.getSecretAccessKey(),
                credentials.getSessionToken(), (credentials.getExpiration().getTime() - System.currentTimeMillis()) / 1000);
    }

    @Override
    public URL getObjectUrl(String bucket, String objectKey) {
        return client.generatePresignedUrl(bucket, objectKey,
                new Date(System.currentTimeMillis() + OssConfiguration.expire * 1000), HttpMethod.GET);
    }

    @Override
    public Boolean deleteObject(String bucket, String objectKey) {
        if (!client.doesObjectExist(bucket, objectKey)) {
            return true;
        }
        client.deleteObject(bucket, objectKey);
        return true;
    }

    public InputStream getObject(String bucket, String objectKey) {
        return client.getObject(bucket, objectKey).getObjectContent().getDelegateStream();
    }

    @Override
    public void putObject(String bucket, String objectKey, InputStream input) {
        if (client.doesObjectExist(bucket, objectKey)) {
            throw new RuntimeException("The filename already exists.");
        }
        PutObjectResult objectResult = client.putObject(new PutObjectRequest(bucket, objectKey, input, new ObjectMetadata()));
        log.info("Upload FlighttaskCreateFile: {}", objectResult.toString());
    }

    /**
     * Amazon S3 클라이언트를 생성합니다.
     */
    public void createClient() {
        if (Objects.nonNull(this.client)) {
            return;
        }
        this.client = AmazonS3ClientBuilder.standard()
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(OssConfiguration.accessKey, OssConfiguration.secretKey)))
                .withRegion(OssConfiguration.region)
                .build();
    }

    /**
     * CORS(Cross-Origin Resource Sharing) 설정을 구성합니다.
     * 웹 브라우저에서 S3 버킷에 직접 접근할 수 있도록 허용합니다.
     */
    @PostConstruct
    private void configCORS() {
        if (!OssConfiguration.enable || !OssTypeEnum.AWS.getType().equals(OssConfiguration.provider)) {
            return;
        }
        List<CORSRule.AllowedMethods> allowedMethods = new ArrayList<>();
        allowedMethods.add(CORSRule.AllowedMethods.GET);
        allowedMethods.add(CORSRule.AllowedMethods.POST);
        allowedMethods.add(CORSRule.AllowedMethods.DELETE);

        CORSRule rule = new CORSRule()
                .withId("CORSAccessRule")
                .withAllowedOrigins(List.of("*"))
                .withAllowedHeaders(List.of(AuthInterceptor.PARAM_TOKEN))
                .withAllowedMethods(allowedMethods);

        client.setBucketCrossOriginConfiguration(OssConfiguration.bucket,
                new BucketCrossOriginConfiguration().withRules(rule));
        
    }
}
