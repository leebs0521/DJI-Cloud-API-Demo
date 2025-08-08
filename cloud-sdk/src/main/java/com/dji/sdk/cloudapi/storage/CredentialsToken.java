package com.dji.sdk.cloudapi.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/**
 * 임시 자격 증명 토큰 데이터 클래스
 * 
 * 이 클래스는 클라우드 스토리지 서비스에 접근하기 위한 임시 자격 증명 토큰을 정의합니다.
 * OSS(Object Storage Service)나 S3 호환 스토리지에 안전하게 접근하기 위한
 * 인증 정보를 관리합니다.
 * 
 * 주요 구성 요소:
 * - accessKeyId: 액세스 키 ID
 * - accessKeySecret: 액세스 키 시크릿
 * - securityToken: 보안 토큰
 * - expire: 토큰 만료 시간 (초)
 * 
 * 이 클래스는 클라우드 스토리지 서비스에 대한 임시 접근 권한을 제공하는 데 사용됩니다.
 * 보안을 위해 토큰은 일정 시간 후 자동으로 만료되며, 만료 시간보다 300초 일찍 만료되도록 설정됩니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/12/7
 */
@Schema(description = "임시 자격 증명의 토큰 데이터")
public class CredentialsToken {

    /**
     * 토큰 만료 시간 조정값 (초)
     * 
     * 실제 만료 시간보다 300초 일찍 만료되도록 조정하는 값입니다.
     * 네트워크 지연이나 시간 동기화 문제로 인한 토큰 만료를 방지합니다.
     */
    private static final int DELAY = 300;

    /**
     * 액세스 키 ID
     * 
     * 클라우드 스토리지 서비스에 접근하기 위한 고유 식별자입니다.
     * 임시 자격 증명을 통해 발급받은 액세스 키의 ID입니다.
     * 
     * 예시: "3POX6W77L1EF4C86L2RE"
     */
    @NotNull
    @Schema(description = "액세스 키 ID", example = "3POX6W77L1EF4C86L2RE")
    @JsonProperty("access_key_id")
    private String accessKeyId;

    /**
     * 액세스 키 시크릿
     * 
     * 클라우드 스토리지 서비스에 접근하기 위한 비밀 키입니다.
     * 임시 자격 증명을 통해 발급받은 액세스 키의 시크릿 값입니다.
     * 
     * 예시: "9NG2P2yJaUrck576CkdRoRbchKssJiZygi5D93CBsduY"
     */
    @NotNull
    @Schema(description = "액세스 키 시크릿", example = "9NG2P2yJaUrck576CkdRoRbchKssJiZygi5D93CBsduY")
    @JsonProperty("access_key_secret")
    private String accessKeySecret;

    /**
     * 토큰 만료 시간
     * 
     * 임시 자격 증명 토큰의 유효 시간을 초 단위로 설정합니다.
     * 최소 1초 이상의 값이어야 하며, 실제 만료 시간은 DELAY 값만큼 조정됩니다.
     * 
     * 제약 조건:
     * - 최소값: 1초
     * 
     * 예시: 3600 (1시간)
     */
    @NotNull
    @Min(1)
    @Schema(description = "토큰의 유효 시간 (초)", example = "3600")
    private Long expire;

    /**
     * 보안 토큰
     * 
     * 클라우드 스토리지 서비스에 접근하기 위한 보안 토큰입니다.
     * JWT(JSON Web Token) 형태로 제공되며, 임시 자격 증명의 권한 정보를 포함합니다.
     * 
     * 예시: "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9..."
     */
    @NotNull
    @JsonProperty("security_token")
    @Schema(description = "보안 토큰", example = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJhY2Nlc3NLZXkiOiIzUE9YNlc3N0wxRUY0Qzg2TDJSRSIsImV4cCI6MTY4NjgxOTgyOSwicGFyZW50IjoibWluaW8ifQ.cWJXI90UidrBOTD0gWxKt8PT5Qp_6dEK5wNfJuE6lR9dH6Us7jtSB8vcttRDwPhpCNrAGsv91ydw6NLMyjqAOw")
    private String securityToken;

    /**
     * 임시 자격 증명 토큰을 생성합니다.
     * 
     * @param accessKeyId 액세스 키 ID
     * @param accessKeySecret 액세스 키 시크릿
     * @param securityToken 보안 토큰
     * @param expire 토큰 만료 시간 (초)
     */
    public CredentialsToken(@NotNull String accessKeyId, @NotNull String accessKeySecret, @NotNull String securityToken, @NotNull @Min(1) Long expire) {
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
        this.securityToken = securityToken;
        this.expire = expire - DELAY;
    }

    public CredentialsToken() {
    }

    @Override
    public String toString() {
        return "CredentialsToken{" +
                "accessKeyId='" + accessKeyId + '\'' +
                ", accessKeySecret='" + accessKeySecret + '\'' +
                ", expire=" + expire +
                ", securityToken='" + securityToken + '\'' +
                '}';
    }

    /**
     * 액세스 키 ID를 반환합니다.
     * 
     * @return 액세스 키 ID
     */
    public String getAccessKeyId() {
        return accessKeyId;
    }

    /**
     * 액세스 키 ID를 설정합니다.
     * 
     * @param accessKeyId 액세스 키 ID
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public CredentialsToken setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
        return this;
    }

    /**
     * 액세스 키 시크릿을 반환합니다.
     * 
     * @return 액세스 키 시크릿
     */
    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    /**
     * 액세스 키 시크릿을 설정합니다.
     * 
     * @param accessKeySecret 액세스 키 시크릿
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public CredentialsToken setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
        return this;
    }

    /**
     * 토큰 만료 시간을 반환합니다.
     * 
     * @return 토큰 만료 시간 (초)
     */
    public Long getExpire() {
        return expire;
    }

    /**
     * 토큰 만료 시간을 설정합니다.
     * 
     * @param expire 토큰 만료 시간 (초)
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public CredentialsToken setExpire(Long expire) {
        this.expire = expire;
        return this;
    }

    /**
     * 보안 토큰을 반환합니다.
     * 
     * @return 보안 토큰
     */
    public String getSecurityToken() {
        return securityToken;
    }

    /**
     * 보안 토큰을 설정합니다.
     * 
     * @param securityToken 보안 토큰
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public CredentialsToken setSecurityToken(String securityToken) {
        this.securityToken = securityToken;
        return this;
    }
}
