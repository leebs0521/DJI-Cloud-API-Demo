package com.dji.sample.manage.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 로그 파일 정보 전송 객체 (DTO) 클래스
 * 
 * DJI Cloud API의 로그 파일 정보를 전송하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 로그 파일 기본 정보 관리
 *    - 로그 파일 ID 및 이름 관리
 *    - 로그 파일 크기 및 상태 관리
 *    - 로그 파일 메타데이터 관리
 * 
 * 2. 로그 파일 식별 정보 관리
 *    - 로그 ID 및 디바이스 시리얼 번호 관리
 *    - 로그 파일 핑거프린트 관리
 *    - 로그 파일 객체 키 관리
 * 
 * 3. 로그 파일 무결성 관리
 *    - 로그 파일 핑거프린트를 통한 무결성 검증
 *    - 로그 파일 상태를 통한 유효성 관리
 *    - 로그 파일 접근 권한 관리
 * 
 * 4. API 통신 지원
 *    - Lombok 어노테이션을 통한 자동 getter/setter 생성
 *    - 빌더 패턴을 통한 편리한 객체 생성
 *    - API 요청/응답에서 사용되는 표준화된 형식 제공
 * 
 * 이 클래스는 로그 파일의 모든 메타데이터 정보를 포함하며,
 * 로그 파일의 저장, 검색, 다운로드 등을 위한
 * 표준화된 데이터 형식을 제공합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/9/9
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogsFileDTO {

    /**
     * 파일 ID
     * 로그 파일을 고유하게 식별하는 ID
     */
    private String fileId;

    /**
     * 파일 이름
     * 로그 파일의 원본 파일명
     */
    private String name;

    /**
     * 파일 크기
     * 로그 파일의 크기 (바이트 단위)
     */
    private Long size;

    /**
     * 로그 ID
     * 로그 파일이 속한 로그의 고유 식별자
     */
    private String logsId;

    /**
     * 디바이스 시리얼 번호
     * 로그 파일을 생성한 디바이스의 시리얼 번호
     */
    private String deviceSn;

    /**
     * 파일 핑거프린트
     * 로그 파일의 무결성을 검증하기 위한 해시값
     */
    private String fingerprint;

    /**
     * 객체 키
     * 클라우드 스토리지에서 로그 파일을 식별하는 키
     */
    private String objectKey;

    /**
     * 파일 상태
     * 로그 파일의 현재 상태 (true: 활성, false: 비활성)
     */
    private Boolean status;

}
