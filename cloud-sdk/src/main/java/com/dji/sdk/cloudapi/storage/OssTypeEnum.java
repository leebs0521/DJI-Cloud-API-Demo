package com.dji.sdk.cloudapi.storage;

import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * OSS(Object Storage Service) 타입 열거형
 * 
 * 이 열거형은 지원되는 클라우드 스토리지 서비스의 타입을 정의합니다.
 * 다양한 클라우드 스토리지 제공업체의 서비스를 구분하여 관리합니다.
 * 
 * 주요 구성 요소:
 * - ALIYUN: 알리바바 클라우드 OSS
 * - AWS: 아마존 웹 서비스 S3
 * - MINIO: MinIO 오픈소스 스토리지
 * 
 * 이 열거형은 클라우드 스토리지 서비스의 타입을 식별하고
 * 각 서비스에 맞는 적절한 처리 로직을 적용하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.0
 * @date 2022/5/30
 */
@Schema(description = "OSS 타입", example = "minio", enumAsRef = true)
public enum OssTypeEnum {

    /**
     * 알리바바 클라우드 OSS
     * 
     * 알리바바 클라우드의 Object Storage Service입니다.
     * 중국 및 글로벌 지역에서 사용 가능한 클라우드 스토리지 서비스입니다.
     */
    ALIYUN("ali"),

    /**
     * 아마존 웹 서비스 S3
     * 
     * 아마존 웹 서비스의 Simple Storage Service입니다.
     * 글로벌에서 가장 널리 사용되는 클라우드 스토리지 서비스입니다.
     */
    AWS("aws"),

    /**
     * MinIO 오픈소스 스토리지
     * 
     * S3 호환 오픈소스 객체 스토리지 서비스입니다.
     * 자체 호스팅 환경에서 사용할 수 있는 스토리지 솔루션입니다.
     */
    MINIO("minio");

    /**
     * OSS 타입 값
     * 
     * 각 OSS 타입을 구분하는 문자열 값입니다.
     */
    private String type;

    /**
     * OSS 타입 열거형 생성자
     * 
     * @param type OSS 타입 값
     */
    OssTypeEnum(String type) {
        this.type = type;
    }

    /**
     * OSS 타입 값을 반환합니다.
     * 
     * @return OSS 타입 값
     */
    @JsonValue
    public String getType() {
        return type;
    }
}
