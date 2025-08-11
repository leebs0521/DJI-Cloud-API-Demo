package com.dji.sample.manage.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 사용자 목록 정보 전송 객체 (DTO) 클래스
 * 
 * DJI Cloud API의 사용자 목록 정보를 전송하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 사용자 기본 정보 관리
 *    - 사용자 ID 및 사용자명 관리
 *    - 사용자 타입 및 워크스페이스 정보 관리
 *    - 사용자 계정 정보 관리
 * 
 * 2. MQTT 연결 정보 관리
 *    - MQTT 사용자명 및 비밀번호 관리
 *    - MQTT 연결을 위한 인증 정보 제공
 *    - MQTT 브로커 접근 정보 관리
 * 
 * 3. 사용자 생성 정보 관리
 *    - 사용자 계정 생성 시간 관리
 *    - 사용자 등록 이력 추적
 *    - 사용자 계정 라이프사이클 관리
 * 
 * 4. API 통신 지원
 *    - Lombok 어노테이션을 통한 자동 getter/setter 생성
 *    - 빌더 패턴을 통한 편리한 객체 생성
 *    - API 요청/응답에서 사용되는 표준화된 형식 제공
 * 
 * 이 클래스는 사용자 목록 조회 및 관리에 필요한 모든 정보를
 * 포함하며, 사용자 계정의 기본 정보와 MQTT 연결 정보를
 * 제공하는 표준화된 데이터 형식을 제공합니다.
 * 
 * @author sean
 * @version 1.0
 * @date 2022/4/18
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserListDTO {

    /**
     * 사용자 ID
     * 사용자를 고유하게 식별하는 ID
     */
    private String userId;

    /**
     * 사용자명
     * 사용자의 로그인명 또는 표시명
     */
    private String username;

    /**
     * 워크스페이스 이름
     * 사용자가 속한 워크스페이스의 이름
     */
    private String workspaceName;

    /**
     * 사용자 타입
     * 사용자의 권한 레벨이나 역할을 나타내는 타입
     */
    private String userType;

    /**
     * MQTT 사용자명
     * MQTT 브로커에 연결하기 위한 사용자명
     */
    private String mqttUsername;

    /**
     * MQTT 비밀번호
     * MQTT 브로커에 연결하기 위한 비밀번호
     */
    private String mqttPassword;

    /**
     * 생성 시간
     * 사용자 계정이 생성된 시간
     */
    private LocalDateTime createTime;
}
