package com.dji.sample.manage.service;

import com.dji.sample.manage.model.dto.LogsFileDTO;
import com.dji.sample.manage.model.dto.LogsFileUploadDTO;
import com.dji.sdk.cloudapi.log.LogFileIndex;

import java.util.List;
import java.util.Optional;

/**
 * 로그 파일 인덱스 관리 서비스 인터페이스
 * 
 * DJI Cloud API의 로그 파일 인덱스 관리를 위한 서비스 인터페이스입니다.
 * 이 인터페이스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 로그 파일 인덱스 관리
 *    - 디바이스 로그 파일 인덱스 추가 및 삭제
 *    - 파일 ID 기반 로그 파일 정보 조회
 *    - 로그 파일 인덱스 데이터베이스 관리
 * 
 * 2. 로그 파일 업로드 정보 관리
 *    - 파일 ID 기반 업로드 정보 조회
 *    - 배치 로그 파일 업로드 정보 조회
 *    - 로그 파일 업로드 상태 추적
 * 
 * 3. 로그 파일 검색 및 조회
 *    - 디바이스별 로그 파일 인덱스 검색
 *    - 도메인별 로그 파일 분류
 *    - 로그 파일 메타데이터 관리
 * 
 * 4. 로그 파일 정리 및 삭제
 *    - 파일 ID 기반 로그 인덱스 삭제
 *    - 배치 로그 파일 인덱스 정리
 *    - 로그 파일 데이터 정리
 * 
 * 이 인터페이스는 DJI 디바이스의 로그 파일 인덱스를
 * 체계적으로 관리하고 검색할 수 있도록 지원합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/9/8
 */
public interface ILogsFileIndexService {

    /**
     * 디바이스 로그 파일 인덱스 추가
     * 
     * 디바이스의 로그 파일 인덱스 정보를 데이터베이스에 추가합니다.
     * 로그 파일 업로드 시 인덱스 정보를 생성할 때 사용됩니다.
     * 
     * @param file 로그 파일 인덱스 정보
     * @param deviceSn 디바이스 시리얼 번호
     * @param domain 로그 도메인 (FLIGHT_CONTROLLER, CAMERA 등)
     * @param fileId 파일 ID
     * @return 인덱스 추가 성공 여부
     */
    Boolean insertFileIndex(LogFileIndex file, String deviceSn, Integer domain, String fileId);

    /**
     * 파일 ID로 로그 파일 업로드 정보 조회
     * 
     * 특정 파일 ID에 해당하는 로그 파일의
     * 업로드 정보를 조회합니다.
     * 
     * @param fileId 파일 ID
     * @return 로그 파일 업로드 정보 (Optional)
     */
    Optional<LogsFileUploadDTO> getFileIndexByFileId(String fileId);

    /**
     * 배치 로그 파일 업로드 정보 조회
     * 
     * 여러 파일 ID에 해당하는 로그 파일들의
     * 업로드 정보를 배치로 조회합니다.
     * 
     * @param fileIds 조회할 파일 ID 목록
     * @return 로그 파일 업로드 정보 목록
     */
    List<LogsFileUploadDTO> getFileIndexByFileIds(List<LogsFileDTO> fileIds);

    /**
     * 파일 ID 기반 로그 인덱스 데이터 삭제
     * 
     * 특정 파일 ID들에 해당하는 로그 인덱스 데이터를
     * 데이터베이스에서 삭제합니다.
     * 
     * @param fileIds 삭제할 파일 ID 목록
     */
    void deleteFileIndexByFileIds(List<String> fileIds);
}
