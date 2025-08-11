package com.dji.sample.manage.model.dto;

import com.dji.sdk.cloudapi.tsa.TopologyList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 디바이스 로그 정보 전송 객체 (DTO) 클래스
 * 
 * DJI Cloud API의 디바이스 로그 정보를 전송하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 로그 기본 정보 관리
 *    - 로그 ID 및 발생 시간 관리
 *    - 사용자명 및 로그 정보 관리
 *    - 로그 생성 시간 및 상태 관리
 * 
 * 2. 로그 진행 상황 관리
 *    - 로그 업로드 진행률 정보 관리
 *    - 로그 처리 결과 및 상태 관리
 *    - 로그 업로드 속도 및 진행 상황 추적
 * 
 * 3. 디바이스 토폴로지 정보 관리
 *    - 디바이스 토폴로지 리스트 관리
 *    - 디바이스 네트워크 구조 정보 관리
 *    - 디바이스 연결 관계 정보 관리
 * 
 * 4. 로그 파일 업로드 정보 관리
 *    - 로그 파일 업로드 목록 관리
 *    - 로그 파일 업로드 상태 및 결과 관리
 *    - 로그 파일 메타데이터 관리
 * 
 * 이 클래스는 디바이스 로그의 모든 정보를 포함하며, 로그 수집부터
 * 업로드까지의 전체 프로세스를 관리하는 표준화된
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
public class DeviceLogsDTO {

    /**
     * 로그 ID
     * 로그를 고유하게 식별하는 ID
     */
    private String logsId;

    /**
     * 발생 시간
     * 로그가 발생한 시간
     */
    private LocalDateTime happenTime;

    /**
     * 사용자명
     * 로그를 생성한 사용자의 이름
     */
    private String userName;

    /**
     * 로그 정보
     * 로그의 상세 내용 또는 설명
     */
    private String logsInformation;

    /**
     * 생성 시간
     * 로그 레코드가 생성된 시간
     */
    private LocalDateTime createTime;

    /**
     * 로그 상태
     * 로그의 현재 처리 상태 (예: 0-대기, 1-처리중, 2-완료, 3-실패)
     */
    private Integer status;

    /**
     * 디바이스 토폴로지
     * 로그와 관련된 디바이스의 토폴로지 정보
     */
    private TopologyList deviceTopo;

    /**
     * 로그 진행 상황 목록
     * 로그 업로드 진행률 및 상태 정보 목록
     */
    private List<LogsProgressDTO> logsProgress;

    /**
     * 디바이스 로그 파일 업로드 정보
     * 디바이스 로그 파일의 업로드 관련 정보
     */
    private LogsFileUploadListDTO deviceLogs;

}
