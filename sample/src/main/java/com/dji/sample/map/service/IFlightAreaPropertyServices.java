package com.dji.sample.map.service;

import com.dji.sample.map.model.dto.FlightAreaPropertyDTO;
import com.dji.sample.map.model.dto.FlightAreaPropertyUpdate;

import java.util.List;

/**
 * 비행 영역 속성 서비스 인터페이스
 * 
 * DJI Cloud API의 비행 영역 속성 관리를 위한 서비스 인터페이스입니다.
 * 이 인터페이스는 비행 영역의 상세 속성 정보를 관리하는 기능을 제공합니다.
 * 
 * 1. 비행 영역 속성 조회 기능
 *    - 요소 ID 목록 기반 속성 조회 (getPropertyByElementIds)
 * 
 * 2. 비행 영역 속성 관리 기능
 *    - 속성 정보 저장 (saveProperty)
 *    - 속성 정보 삭제 (deleteProperty)
 *    - 속성 정보 업데이트 (updateProperty)
 * 
 * 3. 비행 영역 속성 설정 관리
 *    - 비행 영역 타입별 속성 정의
 *    - 기하학적 속성 관리
 *    - 활성화 상태 관리
 * 
 * 주요 용도:
 * - 비행 영역 속성 정보 관리
 * - 비행 영역 설정 관리
 * - 비행 영역 타입별 속성 정의
 * - 비행 영역 기하학적 속성 관리
 * 
 * 사용 예시:
 * - 비행 영역 속성 생성 및 수정
 * - 비행 영역 설정 변경
 * - 비행 영역 타입별 동작 정의
 * 
 * @author sean
 * @version 1.9
 * @date 2023/11/22
 */
public interface IFlightAreaPropertyServices {

    /**
     * 요소 ID 목록을 기반으로 해당 요소들의 비행 영역 속성을 조회합니다.
     * 
     * @param elementIds 조회할 요소들의 고유 식별자 목록
     * @return 비행 영역 속성 정보 목록
     */
    List<FlightAreaPropertyDTO> getPropertyByElementIds(List<String> elementIds);

    /**
     * 비행 영역 속성 정보를 저장합니다.
     * 
     * @param property 저장할 비행 영역 속성 정보
     * @return 저장된 속성의 ID
     */
    Integer saveProperty(FlightAreaPropertyDTO property);

    /**
     * 요소 ID를 기반으로 해당 요소의 비행 영역 속성을 삭제합니다.
     * 
     * @param elementId 삭제할 속성이 속한 요소의 고유 식별자
     * @return 삭제된 속성의 개수
     */
    Integer deleteProperty(String elementId);

    /**
     * 비행 영역 속성 정보를 업데이트합니다.
     * 
     * @param property 업데이트할 비행 영역 속성 정보
     * @return 업데이트된 속성의 개수
     */
    Integer updateProperty(FlightAreaPropertyUpdate property);
}
