package com.dji.sample.manage.model.dto;

import com.dji.sdk.cloudapi.log.LogFileIndex;
import com.dji.sdk.cloudapi.log.LogModuleEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 로그 파일 업로드 정보 전송 객체 (DTO) 클래스
 * 
 * DJI Cloud API의 로그 파일 업로드 정보를 전송하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 로그 파일 업로드 기본 정보 관리
 *    - 디바이스 시리얼 번호 관리
 *    - 로그 파일 ID 및 객체 키 관리
 *    - 업로드 결과 및 상태 관리
 * 
 * 2. 로그 파일 인덱스 관리
 *    - 로그 파일 인덱스 목록 관리
 *    - 로그 파일 메타데이터 정보 관리
 *    - 로그 파일 구조 및 내용 정보 관리
 * 
 * 3. 디바이스 모델 도메인 관리
 *    - 디바이스 모델별 로그 모듈 관리
 *    - 로그 모듈별 분류 및 필터링
 *    - 디바이스 도메인별 로그 관리
 * 
 * 4. JSON 직렬화 지원
 *    - @JsonProperty 어노테이션을 통한 JSON 필드명 매핑
 *    - API 요청/응답에서 사용되는 표준 JSON 형식 지원
 *    - Lombok 어노테이션을 통한 자동 getter/setter 생성
 * 
 * 이 클래스는 로그 파일의 업로드 과정에서 필요한 모든 정보를
 * 포함하며, 로그 파일의 메타데이터와 업로드 상태를 관리하는
 * 표준화된 데이터 형식을 제공합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/9/7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogsFileUploadDTO {

    /**
     * 디바이스 시리얼 번호
     * 로그 파일을 업로드하는 디바이스의 시리얼 번호
     */
    private String deviceSn;

    /**
     * 로그 파일 인덱스 목록
     * 업로드할 로그 파일들의 인덱스 정보 목록
     */
    private List<LogFileIndex> list;

    /**
     * 디바이스 모델 도메인
     * 로그 파일이 속한 디바이스 모델의 도메인 정보
     */
    @JsonProperty("module")
    private LogModuleEnum deviceModelDomain;

    /**
     * 객체 키
     * 클라우드 스토리지에서 로그 파일을 식별하는 키
     */
    private String objectKey;

    /**
     * 업로드 결과
     * 로그 파일 업로드의 결과 코드 (예: 0-성공, 1-실패)
     */
    private Integer result;

    /**
     * 파일 ID
     * 로그 파일을 고유하게 식별하는 ID
     */
    private String fileId;
}
