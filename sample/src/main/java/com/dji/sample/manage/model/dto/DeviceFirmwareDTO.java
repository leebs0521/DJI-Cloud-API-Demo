package com.dji.sample.manage.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * 디바이스 펌웨어 정보 전송 객체 (DTO) 클래스
 * 
 * DJI Cloud API의 디바이스 펌웨어 정보를 전송하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 펌웨어 기본 정보 관리
 *    - 펌웨어 ID 및 파일명 관리
 *    - 제품 버전 및 릴리즈 노트 관리
 *    - 펌웨어 상태 및 릴리즈 시간 관리
 * 
 * 2. 펌웨어 파일 정보 관리
 *    - 펌웨어 파일 크기 및 MD5 해시 관리
 *    - 펌웨어 파일 객체 키 관리
 *    - 펌웨어 파일 무결성 검증 정보 제공
 * 
 * 3. 디바이스 호환성 관리
 *    - 지원 디바이스 모델명 목록 관리
 *    - 펌웨어와 디바이스 간의 호환성 정보
 *    - 다중 디바이스 지원 정보 제공
 * 
 * 4. 워크스페이스 및 사용자 정보 관리
 *    - 워크스페이스 ID 관리
 *    - 펌웨어 업로드 사용자 정보 관리
 *    - 펌웨어 소유권 및 접근 권한 정보
 * 
 * 이 클래스는 펌웨어의 모든 정보를 포함하며, 펌웨어 업로드부터
 * 배포까지의 전체 라이프사이클을 관리하는 표준화된
 * 형식을 제공합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/8/16
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceFirmwareDTO {

    /**
     * 펌웨어 ID
     * 펌웨어를 고유하게 식별하는 ID
     */
    private String firmwareId;

    /**
     * 펌웨어 파일명
     * 펌웨어 파일의 원본 파일명
     */
    private String fileName;

    /**
     * 제품 버전
     * 펌웨어가 지원하는 제품의 버전 정보
     */
    private String productVersion;

    /**
     * 객체 키
     * 클라우드 스토리지에서 펌웨어 파일을 식별하는 키
     */
    private String objectKey;

    /**
     * 파일 크기
     * 펌웨어 파일의 크기 (바이트 단위)
     */
    private Long fileSize;

    /**
     * 파일 MD5 해시
     * 펌웨어 파일의 무결성을 검증하기 위한 MD5 해시값
     */
    private String fileMd5;

    /**
     * 지원 디바이스 모델명 목록
     * 이 펌웨어가 지원하는 디바이스 모델명들의 목록
     */
    private List<String> deviceName;

    /**
     * 릴리즈 노트
     * 펌웨어 업데이트 내용에 대한 설명
     */
    private String releaseNote;

    /**
     * 릴리즈 시간
     * 펌웨어가 공개된 날짜
     */
    private LocalDate releasedTime;

    /**
     * 펌웨어 상태
     * 펌웨어의 현재 상태 (true: 활성, false: 비활성)
     */
    private Boolean firmwareStatus;

    /**
     * 워크스페이스 ID
     * 펌웨어가 속한 워크스페이스의 고유 식별자
     */
    private String workspaceId;

    /**
     * 사용자명
     * 펌웨어를 업로드한 사용자의 이름
     */
    private String username;
}
