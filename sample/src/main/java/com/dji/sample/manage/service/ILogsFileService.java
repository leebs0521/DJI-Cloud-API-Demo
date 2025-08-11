package com.dji.sample.manage.service;

import com.dji.sample.manage.model.dto.LogsFileDTO;
import com.dji.sample.manage.model.dto.LogsFileUploadDTO;
import com.dji.sdk.cloudapi.log.FileUploadProgressFile;
import com.dji.sdk.cloudapi.log.FileUploadStartFile;

import java.net.URL;
import java.util.List;

/**
 * 로그 파일 관리 서비스 인터페이스
 * 
 * DJI Cloud API의 로그 파일 관리를 위한 서비스 인터페이스입니다.
 * 이 인터페이스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 로그 파일 정보 관리
 *    - 로그 ID 기반 로그 파일 정보 조회
 *    - 로그 파일 구조 정보 관리
 *    - 로그 파일 메타데이터 관리
 * 
 * 2. 로그 파일 업로드 관리
 *    - 로그 파일 업로드 시작 및 진행 상황 추적
 *    - 파일 업로드 상태 업데이트
 *    - 업로드 완료된 파일 정보 관리
 * 
 * 3. 로그 파일 CRUD 작업
 *    - 로그 파일 추가 및 삭제
 *    - 로그 파일 정보 업데이트
 *    - 로그 파일 다운로드 URL 생성
 * 
 * 4. 로그 파일 상태 관리
 *    - 로그 파일 업로드 상태 추적
 *    - 로그 파일 처리 상태 관리
 *    - 로그 파일 접근 권한 관리
 * 
 * 이 인터페이스는 DJI 디바이스의 로그 파일을
 * 체계적으로 관리하고 접근할 수 있도록 지원합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/9/7
 */
public interface ILogsFileService {

    /**
     * 로그 ID로 업로드된 로그 파일 정보 조회
     * 
     * 특정 로그 ID에 해당하는 업로드된 로그 파일들의
     * 상세 정보를 조회합니다.
     * 
     * @param logsId 로그 ID
     * @return 로그 파일 정보 목록
     */
    List<LogsFileDTO> getLogsFileInfoByLogsId(String logsId);

    /**
     * 로그 ID로 업로드된 로그 파일 구조 정보 조회
     * 
     * 특정 로그 ID에 해당하는 업로드된 로그 파일들의
     * 구조 정보를 조회합니다.
     * 
     * @param logsId 로그 ID
     * @return 로그 파일 업로드 정보 목록
     */
    List<LogsFileUploadDTO> getLogsFileByLogsId(String logsId);

    /**
     * 로그 파일 추가
     * 
     * 새로운 로그 파일 정보를 데이터베이스에 추가합니다.
     * 
     * @param file 업로드 시작할 파일 정보
     * @param logsId 로그 ID
     * @return 파일 추가 성공 여부
     */
    Boolean insertFile(FileUploadStartFile file, String logsId);

    /**
     * 로그 파일 삭제
     * 
     * 특정 로그 ID에 해당하는 모든 로그 파일들을 삭제합니다.
     * 
     * @param logsId 로그 ID
     */
    void deleteFileByLogsId(String logsId);

    /**
     * 파일 정보 업데이트
     * 
     * 로그 파일의 업로드 진행 상황 정보를 업데이트합니다.
     * 
     * @param logsId 로그 ID
     * @param fileReceiver 파일 업로드 진행 상황 정보
     */
    void updateFile(String logsId, FileUploadProgressFile fileReceiver);

    /**
     * 파일 업로드 상태 업데이트
     * 
     * 로그 파일의 업로드 완료 상태를 업데이트합니다.
     * 
     * @param logsId 로그 ID
     * @param isUploaded 업로드 완료 여부
     */
    void updateFileUploadStatus(String logsId, Boolean isUploaded);

    /**
     * 로그 파일 다운로드 URL 조회
     * 
     * 특정 로그 파일의 다운로드 URL을 생성하여 반환합니다.
     * 
     * @param logsId 로그 ID
     * @param fileId 파일 ID
     * @return 로그 파일 다운로드 URL
     */
    URL getLogsFileUrl(String logsId, String fileId);
}
