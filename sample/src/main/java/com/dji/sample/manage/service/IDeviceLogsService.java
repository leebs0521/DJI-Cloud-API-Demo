package com.dji.sample.manage.service;

import com.dji.sample.manage.model.dto.DeviceLogsDTO;
import com.dji.sample.manage.model.param.DeviceLogsCreateParam;
import com.dji.sample.manage.model.param.DeviceLogsQueryParam;
import com.dji.sdk.cloudapi.log.FileUploadUpdateRequest;
import com.dji.sdk.cloudapi.log.LogModuleEnum;
import com.dji.sdk.common.HttpResultResponse;
import com.dji.sdk.common.PaginationData;

import java.net.URL;
import java.util.List;

/**
 * 디바이스 로그 관리 서비스 인터페이스
 * 
 * DJI Cloud API의 디바이스 로그 관리를 위한 서비스 인터페이스입니다.
 * 이 인터페이스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 로그 업로드 관리
 *    - 디바이스 로그 파일 업로드 요청 및 처리
 *    - 실시간 로그 파일 목록 조회
 *    - 로그 업로드 진행 상황 모니터링
 * 
 * 2. 로그 데이터 관리
 *    - 업로드된 로그 목록 조회 및 관리
 *    - 로그 파일 상태 업데이트
 *    - 로그 파일 다운로드 URL 생성
 * 
 * 3. 로그 수집 및 저장
 *    - 디바이스 로그 수집 요청 생성
 *    - 로그 데이터베이스 저장 및 관리
 *    - 로그 메타데이터 관리
 * 
 * 4. 로그 상태 관리
 *    - 로그 업로드 성공/실패 상태 추적
 *    - 로그 파일 상태 변경 처리
 *    - 로그 레코드 삭제 및 정리
 * 
 * 이 인터페이스는 DJI 디바이스의 로그 데이터를
 * 체계적으로 수집하고 관리할 수 있도록 지원합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/9/7
 */
public interface IDeviceLogsService {

    /**
     * 업로드된 로그 목록 페이지네이션 조회
     * 
     * 디바이스에서 업로드된 로그 파일 목록을
     * 페이지네이션과 함께 조회합니다.
     * 
     * @param deviceSn 디바이스 시리얼 번호
     * @param param 로그 쿼리 파라미터 (날짜, 상태, 모듈 등)
     * @return 페이지네이션된 로그 목록
     */
    PaginationData<DeviceLogsDTO> getUploadedLogs(String deviceSn, DeviceLogsQueryParam param);

    /**
     * 실시간 로그 파일 목록 조회
     * 
     * 디바이스에서 실시간으로 업로드 가능한
     * 로그 파일 목록을 조회합니다.
     * 
     * @param deviceSn 디바이스 시리얼 번호
     * @param domainList 조회할 로그 모듈 목록 (예: FLIGHT_CONTROLLER, CAMERA 등)
     * @return 실시간 로그 파일 목록
     */
    HttpResultResponse getRealTimeLogs(String deviceSn, List<LogModuleEnum> domainList);

    /**
     * 디바이스 로그 추가
     * 
     * 새로운 디바이스 로그 레코드를 데이터베이스에 추가합니다.
     * 로그 업로드 요청의 메타데이터를 저장합니다.
     * 
     * @param bid 비즈니스 ID (워크스페이스 ID)
     * @param username 사용자명
     * @param deviceSn 디바이스 시리얼 번호
     * @param param 로그 생성 파라미터 (모듈, 파일명, 설명 등)
     * @return 생성된 로그 ID
     */
    String insertDeviceLogs(String bid, String username, String deviceSn, DeviceLogsCreateParam param);

    /**
     * 로그 업로드 요청 푸시
     * 
     * 게이트웨이에 로그 파일 업로드 요청을 전송합니다.
     * MQTT를 통해 디바이스에 업로드 명령을 전달합니다.
     * 
     * @param username 사용자명
     * @param deviceSn 디바이스 시리얼 번호
     * @param param 로그 업로드 파라미터
     * @return 업로드 요청 결과
     */
    HttpResultResponse pushFileUpload(String username, String deviceSn, DeviceLogsCreateParam param);

    /**
     * 로그 파일 상태 업데이트 요청 푸시
     * 
     * 로그 파일의 상태 변경 요청을 게이트웨이에 전송합니다.
     * 업로드 진행 상황이나 상태 변경을 처리합니다.
     * 
     * @param deviceSn 디바이스 시리얼 번호
     * @param param 파일 업데이트 요청 파라미터
     * @return 상태 업데이트 요청 결과
     */
    HttpResultResponse pushUpdateFile(String deviceSn, FileUploadUpdateRequest param);

    /**
     * 로그 레코드 삭제
     * 
     * 특정 로그 레코드와 관련된 모든 데이터를 삭제합니다.
     * 데이터베이스에서 로그 정보와 파일 정보를 정리합니다.
     * 
     * @param deviceSn 디바이스 시리얼 번호
     * @param logsId 삭제할 로그 ID
     */
    void deleteLogs(String deviceSn, String logsId);

    /**
     * 로그 상태 업데이트
     * 
     * 로그 업로드가 성공하거나 실패했을 때
     * 로그의 상태를 업데이트합니다.
     * 
     * @param logsId 로그 ID
     * @param value 업데이트할 상태 값 (0: 진행중, 1: 성공, 2: 실패 등)
     */
    void updateLogsStatus(String logsId, Integer value);

    /**
     * 로그 파일 다운로드 URL 조회
     * 
     * 특정 로그 파일의 다운로드 URL을 생성하여 반환합니다.
     * 클라우드 스토리지에서 파일에 접근할 수 있는 URL을 제공합니다.
     * 
     * @param logsId 로그 ID
     * @param fileId 파일 ID
     * @return 로그 파일 다운로드 URL
     */
    URL getLogsFileUrl(String logsId, String fileId);
}
