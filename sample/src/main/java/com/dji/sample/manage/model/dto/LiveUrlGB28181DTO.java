package com.dji.sample.manage.model.dto;

import lombok.Data;

/**
 * GB28181 라이브스트림 URL 정보 전송 객체 (DTO) 클래스
 * 
 * DJI Cloud API의 GB28181 라이브스트림 URL 정보를 전송하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. GB28181 서버 정보 관리
 *    - GB28181 서버 IP 주소 관리
 *    - GB28181 서버 포트 관리
 *    - GB28181 서버 ID 관리
 * 
 * 2. GB28181 에이전트 정보 관리
 *    - GB28181 에이전트 ID 관리
 *    - GB28181 에이전트 비밀번호 관리
 *    - GB28181 에이전트 인증 정보 제공
 * 
 * 3. GB28181 채널 및 포트 관리
 *    - GB28181 채널 정보 관리
 *    - 로컬 포트 관리
 *    - GB28181 스트림 연결 정보 관리
 * 
 * 4. API 통신 지원
 *    - Lombok 어노테이션을 통한 자동 getter/setter 생성
 *    - API 요청/응답에서 사용되는 표준화된 형식 제공
 *    - GB28181 라이브스트림 연결의 표준화된 데이터 구조 제공
 * 
 * 이 클래스는 GB28181 라이브스트림 연결에 필요한 모든 정보를
 * 포함하며, GB28181 서버와의 연결 및 인증을 위한
 * 표준화된 데이터 형식을 제공합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/23
 */
@Data
public class LiveUrlGB28181DTO {

    /**
     * GB28181 서버 IP 주소
     * GB28181 서버의 IP 주소
     */
    private String serverIP;

    /**
     * GB28181 서버 포트
     * GB28181 서버의 포트 번호
     */
    private Integer serverPort;

    /**
     * GB28181 서버 ID
     * GB28181 서버의 고유 식별자
     */
    private String serverID;

    /**
     * GB28181 에이전트 ID
     * GB28181 에이전트의 고유 식별자
     */
    private String agentID;

    /**
     * GB28181 에이전트 비밀번호
     * GB28181 에이전트 인증을 위한 비밀번호
     */
    private String agentPassword;

    /**
     * 로컬 포트
     * GB28181 스트림을 수신할 로컬 포트 번호
     */
    private Integer localPort;

    /**
     * GB28181 채널
     * GB28181 스트림의 채널 정보
     */
    private String channel;

}