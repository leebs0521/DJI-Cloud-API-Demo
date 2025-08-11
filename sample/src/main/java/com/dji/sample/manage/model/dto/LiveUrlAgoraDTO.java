package com.dji.sample.manage.model.dto;

import lombok.Data;

/**
 * Agora 라이브스트림 URL 정보 전송 객체 (DTO) 클래스
 * 
 * DJI Cloud API의 Agora 라이브스트림 URL 정보를 전송하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. Agora 채널 정보 관리
 *    - Agora 채널명 관리
 *    - Agora 채널 연결 정보 제공
 *    - Agora 채널 식별 정보 관리
 * 
 * 2. Agora 인증 정보 관리
 *    - Agora 토큰 관리
 *    - Agora 사용자 ID 관리
 *    - Agora 서버 인증 정보 제공
 * 
 * 3. 디바이스 연결 정보 관리
 *    - 디바이스 시리얼 번호 관리
 *    - 디바이스와 Agora 채널 연결 정보
 *    - 디바이스별 Agora 스트림 관리
 * 
 * 4. API 통신 지원
 *    - Lombok 어노테이션을 통한 자동 getter/setter 생성
 *    - API 요청/응답에서 사용되는 표준화된 형식 제공
 *    - Agora 라이브스트림 연결의 표준화된 데이터 구조 제공
 * 
 * 이 클래스는 Agora 라이브스트림 연결에 필요한 모든 정보를
 * 포함하며, Agora 서버와의 연결 및 인증을 위한
 * 표준화된 데이터 형식을 제공합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/23
 * @version 0.1
 */
@Data
public class LiveUrlAgoraDTO {

    /**
     * Agora 채널명
     * Agora 서버에서 사용할 채널명
     */
    private String channel;

    /**
     * 디바이스 시리얼 번호
     * 라이브스트림을 제공하는 디바이스의 시리얼 번호
     */
    private String sn;

    /**
     * Agora 토큰
     * Agora 서버에 접근하기 위한 인증 토큰
     */
    private String token;

    /**
     * Agora 사용자 ID
     * Agora 서버에서 사용할 사용자 ID
     */
    private Integer uid;
}