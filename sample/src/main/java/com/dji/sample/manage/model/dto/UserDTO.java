package com.dji.sample.manage.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 사용자 정보 전송 객체 (DTO) 클래스
 * 
 * DJI Cloud API의 사용자 정보를 전송하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 사용자 기본 정보 관리
 *    - 사용자 ID 및 사용자명 관리
 *    - 워크스페이스 ID 및 사용자 타입 관리
 *    - 사용자 계정 정보 관리
 * 
 * 2. MQTT 연결 정보 관리
 *    - MQTT 사용자명 및 비밀번호 관리
 *    - MQTT 서버 주소 관리
 *    - MQTT 연결을 위한 인증 정보 제공
 * 
 * 3. 인증 및 접근 정보 관리
 *    - 액세스 토큰 관리
 *    - 사용자 인증 정보 관리
 *    - API 접근 권한 정보 제공
 * 
 * 4. JSON 직렬화 지원
 *    - @JsonProperty 어노테이션을 통한 JSON 필드명 매핑
 *    - API 요청/응답에서 사용되는 표준 JSON 형식 지원
 *    - Lombok 어노테이션을 통한 자동 getter/setter 생성
 * 
 * 이 클래스는 사용자 인증 및 MQTT 연결에 필요한 모든 정보를
 * 포함하며, DJI Cloud API와의 통신에서 사용자 데이터를
 * 전송하는 표준화된 형식을 제공합니다.
 * 
 * @author sean
 * @version 1.0
 * @date 2022/1/1
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    /**
     * 사용자 ID
     * 사용자를 고유하게 식별하는 ID
     */
    @JsonProperty("user_id")
    private String userId;

    /**
     * 사용자명
     * 사용자의 로그인명 또는 표시명
     */
    private String username;

    /**
     * 워크스페이스 ID
     * 사용자가 속한 워크스페이스의 고유 식별자
     */
    @JsonProperty("workspace_id")
    private String workspaceId;

    /**
     * 사용자 타입
     * 사용자의 권한 레벨이나 역할을 나타내는 타입 코드
     */
    @JsonProperty("user_type")
    private Integer userType;

    /**
     * MQTT 사용자명
     * MQTT 브로커에 연결하기 위한 사용자명
     */
    @JsonProperty("mqtt_username")
    private String mqttUsername;

    /**
     * MQTT 비밀번호
     * MQTT 브로커에 연결하기 위한 비밀번호
     */
    @JsonProperty("mqtt_password")
    private String mqttPassword;

    /**
     * 액세스 토큰
     * API 접근을 위한 인증 토큰
     */
    @JsonProperty("access_token")
    private String accessToken;

    /**
     * MQTT 서버 주소
     * MQTT 브로커의 서버 주소
     */
    @JsonProperty("mqtt_addr")
    private String mqttAddr;
}
