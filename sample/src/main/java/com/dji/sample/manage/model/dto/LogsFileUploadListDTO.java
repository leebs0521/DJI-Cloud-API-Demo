package com.dji.sample.manage.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 로그 파일 업로드 목록 정보 전송 객체 (DTO) 클래스
 * 
 * DJI Cloud API의 로그 파일 업로드 목록 정보를 전송하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 로그 파일 업로드 목록 관리
 *    - 로그 파일 업로드 정보 목록 관리
 *    - 다중 파일 업로드 상태 관리
 *    - 업로드 파일 그룹 관리
 * 
 * 2. 업로드 결과 관리
 *    - 전체 업로드 결과 코드 관리
 *    - 업로드 성공/실패 상태 관리
 *    - 배치 업로드 결과 추적
 * 
 * 3. 파일 그룹 처리 지원
 *    - 여러 로그 파일의 일괄 업로드 지원
 *    - 파일 그룹별 업로드 상태 관리
 *    - 업로드 진행 상황 통합 관리
 * 
 * 4. API 통신 지원
 *    - Lombok 어노테이션을 통한 자동 getter/setter 생성
 *    - 빌더 패턴을 통한 편리한 객체 생성
 *    - API 요청/응답에서 사용되는 표준화된 형식 제공
 * 
 * 이 클래스는 여러 로그 파일의 업로드를 일괄적으로 관리하며,
 * 배치 업로드의 결과와 상태를 추적하는 표준화된
 * 데이터 형식을 제공합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/9/7
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogsFileUploadListDTO {

    /**
     * 로그 파일 업로드 정보 목록
     * 업로드할 로그 파일들의 정보 목록
     */
    private List<LogsFileUploadDTO> files;

    /**
     * 업로드 결과
     * 전체 로그 파일 업로드의 결과 코드 (예: 0-성공, 1-실패)
     */
    private Integer result;
}
