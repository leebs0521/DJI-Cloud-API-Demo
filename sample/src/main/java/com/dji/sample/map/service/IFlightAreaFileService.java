package com.dji.sample.map.service;

import com.dji.sample.map.model.dto.FlightAreaDTO;
import com.dji.sample.map.model.dto.FlightAreaFileDTO;

import java.util.List;
import java.util.Optional;

/**
 * 비행 영역 파일 서비스 인터페이스
 * 
 * DJI Cloud API의 비행 영역 파일 관리를 위한 서비스 인터페이스입니다.
 * 이 인터페이스는 비행 영역 파일의 CRUD 작업과 패키징 기능을 제공합니다.
 * 
 * 1. 비행 영역 파일 조회 기능
 *    - 파일 ID 기반 파일 정보 조회 (getFlightAreaFileByFileId)
 *    - 워크스페이스별 최신 파일 조회 (getLatestByWorkspaceId)
 * 
 * 2. 비행 영역 파일 관리 기능
 *    - 비행 영역 파일 저장 (saveFlightAreaFile)
 *    - 워크스페이스별 최신 상태 설정 (setNonLatestByWorkspaceId)
 * 
 * 3. 비행 영역 파일 패키징 기능
 *    - 비행 영역들을 파일로 패키징 (packageFlightAreaFile)
 * 
 * 주요 용도:
 * - 비행 영역 파일 메타데이터 관리
 * - 비행 영역 파일 버전 관리
 * - 비행 영역 파일 패키징 및 배포
 * - 파일 시스템 연동 관리
 * 
 * 사용 예시:
 * - 비행 영역 파일 생성 및 저장
 * - 파일 버전 관리
 * - 비행 영역 파일 패키징
 * 
 * @author sean
 * @version 1.9
 * @date 2023/11/22
 */
public interface IFlightAreaFileService {

    /**
     * 파일 ID를 기반으로 비행 영역 파일 정보를 조회합니다.
     * 
     * @param fileId 조회할 파일의 고유 식별자
     * @return 비행 영역 파일 정보를 포함하는 Optional 객체
     */
    Optional<FlightAreaFileDTO> getFlightAreaFileByFileId(String fileId);

    /**
     * 비행 영역 파일 정보를 저장합니다.
     * 
     * @param file 저장할 비행 영역 파일 정보
     * @return 저장된 파일의 ID
     */
    Integer saveFlightAreaFile(FlightAreaFileDTO file);

    /**
     * 워크스페이스 ID를 기반으로 해당 워크스페이스의 모든 파일을 최신이 아닌 상태로 설정합니다.
     * 
     * @param workspaceId 설정할 워크스페이스의 고유 식별자
     * @return 설정된 파일의 개수
     */
    Integer setNonLatestByWorkspaceId(String workspaceId);

    /**
     * 워크스페이스 ID를 기반으로 해당 워크스페이스의 최신 파일을 조회합니다.
     * 
     * @param workspaceId 조회할 워크스페이스의 고유 식별자
     * @return 최신 비행 영역 파일 정보를 포함하는 Optional 객체
     */
    Optional<FlightAreaFileDTO> getLatestByWorkspaceId(String workspaceId);

    /**
     * 워크스페이스의 비행 영역들을 파일로 패키징합니다.
     * 
     * @param workspaceId 패키징할 워크스페이스의 고유 식별자
     * @param flightAreas 패키징할 비행 영역 목록
     * @return 패키징된 비행 영역 파일 정보
     */
    FlightAreaFileDTO packageFlightAreaFile(String workspaceId, List<FlightAreaDTO> flightAreas);
}
