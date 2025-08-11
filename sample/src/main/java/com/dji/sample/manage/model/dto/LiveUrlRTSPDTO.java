package com.dji.sample.manage.model.dto;

import lombok.Data;

/**
 * RTSP 라이브스트림 URL 정보 전송 객체 (DTO) 클래스
 * 
 * DJI Cloud API의 RTSP 라이브스트림 URL 정보를 전송하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. RTSP 인증 정보 관리
 *    - RTSP 사용자명 관리
 *    - RTSP 비밀번호 관리
 *    - RTSP 서버 인증 정보 제공
 * 
 * 2. RTSP 포트 관리
 *    - RTSP 서버 포트 관리
 *    - RTSP 연결 포트 설정
 *    - RTSP 스트림 포트 정보 관리
 * 
 * 3. RTSP 연결 설정 관리
 *    - RTSP 서버 연결 설정 정보 관리
 *    - RTSP 스트림 접근 정보 관리
 *    - RTSP 인증 설정 관리
 * 
 * 4. API 통신 지원
 *    - Lombok 어노테이션을 통한 자동 getter/setter 생성
 *    - API 요청/응답에서 사용되는 표준화된 형식 제공
 *    - RTSP 라이브스트림 연결의 표준화된 데이터 구조 제공
 * 
 * 이 클래스는 RTSP 라이브스트림 연결에 필요한 모든 정보를
 * 포함하며, RTSP 서버와의 연결 및 인증을 위한
 * 표준화된 데이터 형식을 제공합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/23
 */
@Data
public class LiveUrlRTSPDTO {

    /**
     * RTSP 사용자명
     * RTSP 서버에 접근하기 위한 사용자명
     */
    private String userName;

    /**
     * RTSP 비밀번호
     * RTSP 서버에 접근하기 위한 비밀번호
     */
    private String password;

    /**
     * RTSP 포트
     * RTSP 서버의 포트 번호
     */
    private Integer port;
}