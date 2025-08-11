package com.dji.sample.map.service;

import com.dji.sample.map.model.dto.FlightAreaDTO;
import com.dji.sample.map.model.dto.FlightAreaFileDTO;
import com.dji.sample.map.model.param.PostFlightAreaParam;
import com.dji.sample.map.model.param.PutFlightAreaParam;

import java.util.List;
import java.util.Optional;

/**
f * 비행 영역 서비스 인터페이스
 * 
 * DJI Cloud API의 비행 영역 관리를 위한 서비스 인터페이스입니다.
 * 이 인터페이스는 비행 영역의 CRUD 작업과 동기화 기능을 제공합니다.
 * 
 * 1. 비행 영역 조회 기능
 *    - 개별 비행 영역 조회 (getFlightAreaByAreaId)
 *    - 워크스페이스별 비행 영역 목록 조회 (getFlightAreaList)
 * 
 * 2. 비행 영역 관리 기능
 *    - 비행 영역 생성 (createFlightArea)
 *    - 비행 영역 수정 (updateFlightArea)
 *    - 비행 영역 삭제 (deleteFlightArea)
 * 
 * 3. 비행 영역 동기화 기능
 *    - 디바이스와 비행 영역 동기화 (syncFlightArea)
 *    - 비행 영역 파일 패키징 (packageFlightArea)
 * 
 * 주요 용도:
 * - 비행 영역 CRUD 작업 처리
 * - 비행 영역 동기화 관리
 * - 비행 안전 규칙 적용
 * - 디바이스별 비행 제한 관리
 * 
 * 사용 예시:
 * - 비행 영역 생성 및 관리
 * - 디바이스에 비행 영역 동기화
 * - 비행 영역 파일 생성 및 배포
 * 
 * @author sean
 * @version 1.9
 * @date 2023/11/22
 */
public interface IFlightAreaService {

    /**
     * 비행 영역 ID로 개별 비행 영역을 조회합니다.
     * 
     * @param areaId 조회할 비행 영역의 고유 식별자
     * @return 비행 영역 정보를 포함하는 Optional 객체
     */
    Optional<FlightAreaDTO> getFlightAreaByAreaId(String areaId);

    /**
     * 워크스페이스 ID로 해당 워크스페이스의 모든 비행 영역 목록을 조회합니다.
     * 
     * @param workspaceId 조회할 워크스페이스의 고유 식별자
     * @return 비행 영역 목록
     */
    List<FlightAreaDTO> getFlightAreaList(String workspaceId);

    /**
     * 새로운 비행 영역을 생성합니다.
     * 
     * @param workspaceId 비행 영역을 생성할 워크스페이스의 고유 식별자
     * @param username 비행 영역을 생성하는 사용자의 이름
     * @param param 비행 영역 생성에 필요한 파라미터
     */
    void createFlightArea(String workspaceId, String username, PostFlightAreaParam param);

    /**
     * 지정된 디바이스들과 비행 영역을 동기화합니다.
     * 
     * @param workspaceId 동기화할 워크스페이스의 고유 식별자
     * @param deviceSns 동기화할 디바이스들의 시리얼 번호 목록
     */
    void syncFlightArea(String workspaceId, List<String> deviceSns);

    /**
     * 워크스페이스의 비행 영역들을 파일로 패키징합니다.
     * 
     * @param workspaceId 패키징할 워크스페이스의 고유 식별자
     * @return 패키징된 비행 영역 파일 정보
     */
    FlightAreaFileDTO packageFlightArea(String workspaceId);

    /**
     * 지정된 비행 영역을 삭제합니다.
     * 
     * @param workspaceId 비행 영역이 속한 워크스페이스의 고유 식별자
     * @param areaId 삭제할 비행 영역의 고유 식별자
     */
    void deleteFlightArea(String workspaceId, String areaId);

    /**
     * 지정된 비행 영역을 수정합니다.
     * 
     * @param workspaceId 비행 영역이 속한 워크스페이스의 고유 식별자
     * @param areaId 수정할 비행 영역의 고유 식별자
     * @param param 비행 영역 수정에 필요한 파라미터
     */
    void updateFlightArea(String workspaceId, String areaId, PutFlightAreaParam param);

}
