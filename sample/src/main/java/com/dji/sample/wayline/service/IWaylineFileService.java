package com.dji.sample.wayline.service;

import com.dji.sample.wayline.model.dto.WaylineFileDTO;
import com.dji.sdk.cloudapi.wayline.GetWaylineListRequest;
import com.dji.sdk.cloudapi.wayline.GetWaylineListResponse;
import com.dji.sdk.common.PaginationData;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * DJI Cloud API 웨이라인 파일 서비스 인터페이스
 * 
 * 이 인터페이스는 웨이라인 파일의 전체 라이프사이클을 관리하는 서비스 계층의 계약을 정의합니다.
 * 웨이라인 파일의 업로드, 조회, 다운로드, 즐겨찾기, 삭제 등의 기능을 제공하며,
 * DJI Cloud API와의 연동을 통해 웨이라인 파일을 효율적으로 관리합니다.
 * 
 * 주요 기능:
 * - 웨이라인 파일 목록 조회 및 페이징 처리
 * - 웨이라인 파일 상세 정보 조회
 * - 웨이라인 파일 다운로드 URL 생성
 * - 웨이라인 파일 메타데이터 저장
 * - 웨이라인 파일 즐겨찾기 관리
 * - 중복 파일명 검사
 * - 웨이라인 파일 삭제
 * - KMZ 파일 업로드 및 임포트
 * 
 * 사용 시나리오:
 * - DJI Pilot에서 웨이라인 파일 관리
 * - 웨이라인 작업 생성 시 파일 참조
 * - 웨이라인 파일 라이브러리 관리
 * 
 * @author sean
 * @version 0.3
 * @date 2021/12/22
 */
public interface IWaylineFileService {

    /**
     * 쿼리 파라미터를 기반으로 웨이라인 파일 목록을 페이징 조회합니다.
     * 
     * @param workspaceId 워크스페이스 ID (조직/프로젝트 구분)
     * @param param 웨이라인 파일 조회 파라미터 (필터링, 정렬, 페이징 정보 포함)
     * @return 페이징 처리된 웨이라인 파일 목록 응답
     */
    PaginationData<GetWaylineListResponse> getWaylinesByParam(String workspaceId, GetWaylineListRequest param);

    /**
     * 웨이라인 파일 ID에 따라 해당 웨이라인 파일의 정보를 조회합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param waylineId 조회할 웨이라인 파일의 고유 ID
     * @return 웨이라인 파일 정보 (Optional로 래핑되어 있음)
     */
    Optional<GetWaylineListResponse> getWaylineByWaylineId(String workspaceId, String waylineId);

    /**
     * 웨이라인 파일의 다운로드 URL을 생성합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param waylineId 웨이라인 파일 ID
     * @return 웨이라인 파일 다운로드 URL
     * @throws SQLException 데이터베이스 오류 발생 시
     */
    URL getObjectUrl(String workspaceId, String waylineId) throws SQLException;

    /**
     * 웨이라인 파일의 기본 정보를 데이터베이스에 저장합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param metadata 저장할 웨이라인 파일 메타데이터
     * @return 저장된 웨이라인 파일의 ID
     */
    Integer saveWaylineFile(String workspaceId, WaylineFileDTO metadata);

    /**
     * 전달된 파라미터에 따라 웨이라인 파일의 즐겨찾기 상태를 업데이트합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param ids 즐겨찾기 상태를 변경할 웨이라인 파일 ID 목록
     * @param isFavorite 웨이라인 파일을 즐겨찾기로 설정할지 여부
     * @return 업데이트 성공 여부
     */
    Boolean markFavorite(String workspaceId, List<String> ids, Boolean isFavorite);

    /**
     * 워크스페이스 내에서 중복된 파일명을 일괄 조회합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param names 중복 검사할 파일명 목록
     * @return 중복된 파일명 목록
     */
    List<String> getDuplicateNames(String workspaceId, List<String> names);

    /**
     * 웨이라인 ID를 기반으로 웨이라인 파일을 삭제합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param waylineId 삭제할 웨이라인 파일 ID
     * @return 삭제 성공 여부
     */
    Boolean deleteByWaylineId(String workspaceId, String waylineId);

    /**
     * KMZ 웨이라인 파일을 업로드하고 임포트합니다.
     * 
     * @param file 업로드할 KMZ 파일
     * @param workspaceId 워크스페이스 ID
     * @param creator 파일 업로드자 정보
     */
    void importKmzFile(MultipartFile file, String workspaceId, String creator);
}
