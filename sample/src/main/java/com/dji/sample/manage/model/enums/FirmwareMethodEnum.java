package com.dji.sample.manage.model.enums;

import lombok.Getter;

/**
 * 펌웨어 방법 열거형
 * 
 * DJI Cloud API의 펌웨어 관련 방법을 정의하는 열거형 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 펌웨어 방법 정의
 *    - OTA 생성 (OTA_CREATE)
 *    - 펌웨어 업데이트 방법 관리
 *    - 펌웨어 관련 작업 방법 표준화
 * 
 * 2. 펌웨어 업데이트 관리
 *    - Over-The-Air 펌웨어 업데이트 지원
 *    - 펌웨어 업데이트 방법별 처리 로직
 *    - 펌웨어 업데이트 프로세스 제어
 * 
 * 3. 펌웨어 작업 표준화
 *    - 펌웨어 관련 작업의 일관된 처리
 *    - 펌웨어 업데이트 방법의 확장성
 *    - 펌웨어 관리 시스템의 모듈화
 * 
 * 이 열거형은 DJI 디바이스의 펌웨어 관리 시스템에서
 * 다양한 펌웨어 관련 방법을 표준화된 방식으로
 * 관리하고 처리할 수 있도록 지원합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/14
 */
@Getter
public enum FirmwareMethodEnum {

    /**
     * OTA 생성
     * Over-The-Air 펌웨어 업데이트 생성 방법
     */
    OTA_CREATE("ota_create");

    /**
     * 펌웨어 방법 문자열
     * 각 펌웨어 방법에 대응하는 문자열 값
     */
    private String method;

    /**
     * 생성자
     * @param method 펌웨어 방법 문자열
     */
    FirmwareMethodEnum(String method) {
        this.method = method;
    }

}
