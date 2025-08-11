package com.dji.sample.manage.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 라이브스트림 정보 전송 객체 (DTO) 클래스
 * 
 * DJI Cloud API의 라이브스트림 정보를 전송하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 라이브스트림 연결 정보 관리
 *    - 라이브스트림 URL 관리
 *    - 라이브스트림 서버 연결 정보 제공
 *    - 라이브스트림 엔드포인트 관리
 * 
 * 2. 라이브스트림 인증 정보 관리
 *    - 라이브스트림 사용자명 관리
 *    - 라이브스트림 비밀번호 관리
 *    - 라이브스트림 서버 인증 정보 제공
 * 
 * 3. JSON 직렬화 지원
 *    - @JsonInclude 어노테이션을 통한 null 값 제외
 *    - API 요청/응답에서 사용되는 표준 JSON 형식 지원
 *    - Lombok 어노테이션을 통한 자동 getter/setter 생성
 * 
 * 4. 라이브스트림 설정 관리
 *    - 라이브스트림 연결 설정 정보 관리
 *    - 라이브스트림 서버 접근 정보 관리
 *    - 라이브스트림 인증 설정 관리
 * 
 * 이 클래스는 라이브스트림 연결에 필요한 모든 정보를
 * 포함하며, 라이브스트림 서버와의 연결 및 인증을 위한
 * 표준화된 데이터 형식을 제공합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/23
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LiveDTO {

    /**
     * 라이브스트림 URL
     * 라이브스트림 서버의 연결 URL
     */
    private String url;

    /**
     * 라이브스트림 사용자명
     * 라이브스트림 서버에 접근하기 위한 사용자명
     */
    private String username;

    /**
     * 라이브스트림 비밀번호
     * 라이브스트림 서버에 접근하기 위한 비밀번호
     */
    private String password;
}