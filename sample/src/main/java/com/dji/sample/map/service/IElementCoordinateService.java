package com.dji.sample.map.service;

import com.dji.sdk.cloudapi.map.ElementCoordinate;

import java.util.List;

/**
 * 요소 좌표 서비스 인터페이스
 * 
 * DJI Cloud API의 맵 요소 좌표 관리를 위한 서비스 인터페이스입니다.
 * 이 인터페이스는 맵 요소의 지리적 좌표 정보를 관리하는 기능을 제공합니다.
 * 
 * 1. 요소 좌표 조회 기능
 *    - 요소 ID 기반 좌표 목록 조회 (getCoordinateByElementId)
 * 
 * 2. 요소 좌표 관리 기능
 *    - 요소 좌표 데이터 저장 (saveCoordinate)
 *    - 요소 ID 기반 좌표 삭제 (deleteCoordinateByElementId)
 * 
 * 3. 지리적 데이터 관리
 *    - 위도/경도 좌표 관리
 *    - 고도 정보 관리
 *    - 좌표계 변환 지원
 * 
 * 주요 용도:
 * - 맵 요소의 지리적 위치 관리
 * - 좌표 데이터 CRUD 작업 처리
 * - 지리적 계산 및 분석 지원
 * - 좌표계 변환 및 투영 지원
 * 
 * 사용 예시:
 * - 맵 요소 좌표 저장 및 조회
 * - 지리적 범위 검색
 * - 좌표 기반 요소 필터링
 * 
 * @author sean
 * @version 0.2
 * @date 2021/11/29
 */
public interface IElementCoordinateService {

    /**
     * 요소 ID를 기반으로 해당 요소의 모든 좌표를 조회합니다.
     * 
     * @param elementId 조회할 요소의 고유 식별자
     * @return 요소의 좌표 목록
     */
    List<ElementCoordinate> getCoordinateByElementId(String elementId);

    /**
     * 해당 요소의 모든 좌표 데이터를 저장합니다.
     * 
     * @param coordinate 저장할 좌표 데이터 목록
     * @param elementId 좌표를 저장할 요소의 고유 식별자
     * @return 저장 성공 여부
     */
    Boolean saveCoordinate(List<ElementCoordinate> coordinate, String elementId);

    /**
     * 요소 ID를 기반으로 해당 요소의 모든 좌표를 삭제합니다.
     * 
     * @param elementId 삭제할 좌표가 속한 요소의 고유 식별자
     * @return 삭제 성공 여부
     */
    Boolean deleteCoordinateByElementId(String elementId);
}
