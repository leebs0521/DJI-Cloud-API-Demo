package com.dji.sample.manage.model.dto;

import com.dji.sdk.cloudapi.log.FileUploadStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 로그 출력 진행 상황 정보 전송 객체 (DTO) 클래스
 * 
 * DJI Cloud API의 로그 출력 진행 상황 정보를 전송하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 로그 출력 진행 상황 관리
 *    - 로그 ID 기반 출력 진행 상황 관리
 *    - 로그 출력 상태 및 진행률 추적
 *    - 실시간 로그 출력 모니터링
 * 
 * 2. 파일 업로드 상태 관리
 *    - 파일 업로드 상태 열거형 관리
 *    - 업로드 상태별 상세 정보 제공
 *    - 상태 변경 이력 추적
 * 
 * 3. 로그 파일 진행 상황 관리
 *    - 로그 파일별 진행 상황 목록 관리
 *    - 개별 파일 업로드 상태 추적
 *    - 파일별 진행률 및 결과 관리
 * 
 * 4. API 통신 지원
 *    - Lombok 어노테이션을 통한 자동 getter/setter 생성
 *    - 빌더 패턴을 통한 편리한 객체 생성
 *    - API 요청/응답에서 사용되는 표준화된 형식 제공
 * 
 * 이 클래스는 로그 출력 과정에서 발생하는 진행 상황을
 * 실시간으로 추적하며, 로그 파일의 출력 상태와 진행률을
 * 모니터링하는 표준화된 데이터 형식을 제공합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/9/9
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogsOutputProgressDTO {

    /**
     * 로그 ID
     * 출력 진행 상황을 추적할 로그의 고유 식별자
     */
    private String logsId;

    /**
     * 파일 업로드 상태
     * 로그 파일 업로드의 현재 상태 (대기, 진행중, 완료, 실패 등)
     */
    private FileUploadStatusEnum status;

    /**
     * 로그 파일 진행 상황 목록
     * 개별 로그 파일들의 업로드 진행 상황 정보 목록
     */
    private List<LogsProgressDTO> files;
}
