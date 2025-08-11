package com.dji.sample.manage.model.common;

import lombok.Data;

/**
 * HMS(Health Management System) 메시지 모델 클래스
 * 
 * DJI Cloud API의 HMS 메시지 정보를 담는 데이터 모델 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 다국어 메시지 지원
 *    - 중국어(zh) 메시지 관리
 *    - 영어(en) 메시지 관리
 *    - 언어별 메시지 분리 및 관리
 * 
 * 2. HMS 메시지 구조화
 *    - HMS 메시지의 표준화된 구조 정의
 *    - JSON 직렬화/역직렬화 지원
 *    - Lombok @Data를 통한 자동 getter/setter 생성
 * 
 * 3. 메시지 데이터 관리
 *    - HMS 메시지 내용 저장
 *    - 메시지 언어별 접근 제공
 *    - 빈 메시지 객체 생성 지원
 * 
 * 4. JSON 통합 지원
 *    - Jackson 라이브러리와의 호환성
 *    - JSON 형태의 HMS 메시지 파싱
 *    - HmsJsonUtil과의 연동 지원
 * 
 * 이 클래스는 hms.json 파일의 메시지 구조와 일치하며,
 * 디바이스의 건강 상태 메시지를 다국어로 제공하는
 * 데이터 전송 객체(DTO) 역할을 담당합니다.
 * 
 * @author sean
 * @version 1.1
 * @date 2022/7/7
 */
@Data
public class HmsMessage {

    /**
     * 중국어 메시지
     * HMS 메시지의 중국어 버전
     */
    private String zh;

    /**
     * 영어 메시지
     * HMS 메시지의 영어 버전
     */
    private String en;
}
