package com.dji.sample.manage.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 디바이스 펌웨어 노트 정보 전송 객체 (DTO) 클래스
 * 
 * DJI Cloud API의 디바이스 펌웨어 노트 정보를 전송하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 펌웨어 노트 정보 관리
 *    - 디바이스 이름 및 제품 버전 관리
 *    - 펌웨어 릴리즈 노트 관리
 *    - 펌웨어 릴리즈 시간 관리
 * 
 * 2. 펌웨어 버전 정보 관리
 *    - 제품 버전 정보 관리
 *    - 펌웨어 버전 호환성 정보 제공
 *    - 버전별 상세 정보 관리
 * 
 * 3. 펌웨어 릴리즈 정보 관리
 *    - 펌웨어 릴리즈 노트 관리
 *    - 펌웨어 업데이트 내용 설명
 *    - 펌웨어 릴리즈 날짜 관리
 * 
 * 4. API 통신 지원
 *    - Lombok 어노테이션을 통한 자동 getter/setter 생성
 *    - 빌더 패턴을 통한 편리한 객체 생성
 *    - API 요청/응답에서 사용되는 표준화된 형식 제공
 * 
 * 이 클래스는 펌웨어의 릴리즈 정보와 노트를 관리하며,
 * 펌웨어 업데이트 내용과 버전 정보를 제공하는
 * 표준화된 데이터 형식을 제공합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/8/16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceFirmwareNoteDTO {

    /**
     * 디바이스 이름
     * 펌웨어가 지원하는 디바이스의 모델명
     */
    private String deviceName;

    /**
     * 제품 버전
     * 펌웨어가 지원하는 제품의 버전 정보
     */
    private String productVersion;

    /**
     * 릴리즈 노트
     * 펌웨어 업데이트 내용에 대한 상세 설명
     */
    private String releaseNote;

    /**
     * 릴리즈 시간
     * 펌웨어가 공개된 날짜
     */
    private LocalDate releasedTime;
}
