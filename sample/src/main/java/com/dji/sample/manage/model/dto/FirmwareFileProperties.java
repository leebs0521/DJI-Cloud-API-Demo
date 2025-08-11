package com.dji.sample.manage.model.dto;

/**
 * 펌웨어 파일 속성 상수 클래스
 * 
 * DJI Cloud API의 펌웨어 파일 관련 상수들을 정의하는 유틸리티 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 펌웨어 파일 확장자 관리
 *    - 펌웨어 파일 확장자 (.zip) 정의
 *    - 펌웨어 서명 파일 확장자 (.sig) 정의
 *    - 펌웨어 설정 파일 확장자 (.cfg) 정의
 * 
 * 2. 펌웨어 파일명 구조 관리
 *    - 펌웨어 파일명 구분자 정의
 *    - 파일명 내 버전 인덱스 위치 정의
 *    - 파일명 내 릴리즈 날짜 인덱스 위치 정의
 * 
 * 3. 날짜 형식 관리
 *    - 릴리즈 날짜 형식 정의 (yyyyMMdd)
 *    - 파일명 내 날짜 파싱을 위한 형식 제공
 *    - 날짜 형식의 일관성 보장
 * 
 * 4. 펌웨어 파일 표준화 지원
 *    - 펌웨어 파일명의 표준화된 구조 정의
 *    - 파일명 파싱을 위한 인덱스 상수 제공
 *    - 펌웨어 파일 관리의 일관성 보장
 * 
 * 이 클래스는 펌웨어 파일의 명명 규칙과 구조를 표준화하며,
 * 펌웨어 파일 처리 시 필요한 모든 상수 정보를 제공합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/12/1
 */
public class FirmwareFileProperties {

    /**
     * 기본 생성자
     * 유틸리티 클래스이므로 외부에서 인스턴스 생성 방지
     */
    private FirmwareFileProperties() {

    }

    /**
     * 펌웨어 파일 확장자
     * 펌웨어 파일의 표준 확장자 (.zip)
     */
    public static final String FIRMWARE_FILE_SUFFIX = ".zip";

    /**
     * 펌웨어 서명 파일 확장자
     * 펌웨어 파일의 서명 파일 확장자 (.sig)
     */
    public static final String FIRMWARE_SIG_FILE_SUFFIX = ".sig";

    /**
     * 펌웨어 설정 파일 확장자
     * 펌웨어 설정 파일의 확장자 (.cfg)
     */
    public static final String FIRMWARE_CONFIG_FILE_SUFFIX = ".cfg";

    /**
     * 펌웨어 파일명 구분자
     * 펌웨어 파일명 내 필드를 구분하는 문자 (_)
     */
    public static final String FIRMWARE_FILE_DELIMITER = "_";

    /**
     * 파일명 내 버전 인덱스
     * 펌웨어 파일명에서 버전 정보가 위치하는 인덱스 (2)
     */
    public static final int FILENAME_VERSION_INDEX = 2;

    /**
     * 파일명 내 릴리즈 날짜 인덱스
     * 펌웨어 파일명에서 릴리즈 날짜가 위치하는 인덱스 (3)
     */
    public static final int FILENAME_RELEASE_DATE_INDEX = 3;

    /**
     * 파일명 릴리즈 날짜 형식
     * 펌웨어 파일명 내 릴리즈 날짜의 형식 (yyyyMMdd)
     */
    public static final String FILENAME_RELEASE_DATE_FORMAT = "yyyyMMdd";

}
