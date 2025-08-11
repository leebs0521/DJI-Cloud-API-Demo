package com.dji.sample.manage.service;

import com.dji.sample.manage.model.dto.CapacityCameraDTO;
import com.dji.sample.manage.model.receiver.CapacityCameraReceiver;

import java.util.List;

/**
 * 카메라 용량 관리 서비스 인터페이스
 * 
 * DJI Cloud API의 카메라 용량 관리를 위한 서비스 인터페이스입니다.
 * 이 인터페이스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 카메라 용량 정보 관리
 *    - 디바이스별 카메라 용량 정보 조회
 *    - 카메라 용량 데이터 저장 및 업데이트
 *    - 카메라 용량 데이터 삭제
 * 
 * 2. 라이브 스트리밍 용량 관리
 *    - 라이브 스트리밍 가능한 카메라 목록 조회
 *    - 카메라별 라이브 스트리밍 용량 확인
 *    - 동시 스트리밍 지원 가능 여부 확인
 * 
 * 3. 카메라 데이터 변환
 *    - 수신된 카메라 용량 객체를 DTO로 변환
 *    - 카메라 정보 표준화 및 정규화
 *    - 데이터 형식 통일
 * 
 * 4. 카메라 용량 최적화
 *    - 카메라별 용량 분배 관리
 *    - 라이브 스트리밍 품질 최적화
 *    - 카메라 리소스 관리
 * 
 * 이 인터페이스는 DJI 디바이스의 카메라 용량을
 * 체계적으로 관리하고 최적화할 수 있도록 지원합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/19
 * @version 0.1
 */
public interface ICapacityCameraService {

    /**
     * 디바이스별 카메라 용량 정보 조회
     * 
     * 특정 디바이스에서 라이브 스트리밍이 가능한
     * 모든 카메라의 용량 정보를 조회합니다.
     * 
     * @param deviceSn 디바이스 시리얼 번호
     * @return 카메라 용량 정보 목록
     */
    List<CapacityCameraDTO> getCapacityCameraByDeviceSn(String deviceSn);

    /**
     * 디바이스별 카메라 용량 데이터 삭제
     * 
     * 특정 디바이스의 모든 라이브 스트리밍 용량 데이터를 삭제합니다.
     * 디바이스가 제거되거나 교체될 때 사용됩니다.
     * 
     * @param deviceSn 디바이스 시리얼 번호
     * @return 삭제 성공 여부
     */
    Boolean deleteCapacityCameraByDeviceSn(String deviceSn);

    /**
     * 디바이스 카메라 용량 데이터 저장
     * 
     * 디바이스의 카메라 용량 정보를 데이터베이스에 저장합니다.
     * 카메라 연결/해제 시 용량 정보를 업데이트할 때 사용됩니다.
     * 
     * @param capacityCameraReceivers 저장할 카메라 용량 수신기 목록
     * @param deviceSn 디바이스 시리얼 번호
     */
    void saveCapacityCameraReceiverList(List<CapacityCameraReceiver> capacityCameraReceivers, String deviceSn);

    /**
     * 카메라 용량 수신기를 DTO로 변환
     * 
     * 수신된 카메라 용량 객체(CapacityCameraReceiver)를
     * 데이터 전송 객체(CapacityCameraDTO)로 변환합니다.
     * 
     * @param receiver 변환할 카메라 용량 수신기 객체
     * @return 변환된 카메라 용량 DTO
     */
    CapacityCameraDTO receiver2Dto(CapacityCameraReceiver receiver);
}
