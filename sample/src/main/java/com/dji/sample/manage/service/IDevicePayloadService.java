package com.dji.sample.manage.service;

import com.dji.sample.manage.model.dto.DeviceDTO;
import com.dji.sample.manage.model.dto.DevicePayloadDTO;
import com.dji.sample.manage.model.dto.DevicePayloadReceiver;
import com.dji.sdk.cloudapi.device.PayloadFirmwareVersion;

import java.util.Collection;
import java.util.List;

/**
 * 디바이스 페이로드 관리 서비스 인터페이스
 * 
 * DJI Cloud API의 디바이스 페이로드 관리를 위한 서비스 인터페이스입니다.
 * 이 인터페이스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 페이로드 정보 관리
 *    - 페이로드 존재 여부 확인
 *    - 페이로드 정보 저장 및 업데이트
 *    - 페이로드 데이터베이스 CRUD 작업
 * 
 * 2. 페이로드 펌웨어 관리
 *    - 페이로드 펌웨어 버전 정보 업데이트
 *    - 펌웨어 호환성 확인
 *    - 펌웨어 업그레이드 상태 추적
 * 
 * 3. 페이로드 제어 권한 관리
 *    - 페이로드 제어 권한 확인
 *    - 페이로드 제어 상태 업데이트
 *    - 다중 페이로드 디바이스 지원
 * 
 * 4. 페이로드 정리 및 삭제
 *    - 디바이스별 페이로드 데이터 삭제
 *    - 페이로드별 데이터 삭제
 *    - 페이로드 데이터 정리
 * 
 * 이 인터페이스는 DJI 디바이스의 페이로드(카메라, 센서 등)를
 * 체계적으로 관리하고 제어할 수 있도록 지원합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/19
 * @version 0.1
 */
public interface IDevicePayloadService {

    /**
     * 페이로드 존재 여부 확인
     * 
     * 페이로드의 시리얼 번호를 기반으로
     * 해당 페이로드가 데이터베이스에 저장되어 있는지 확인합니다.
     * 
     * @param payloadSn 페이로드 시리얼 번호
     * @return 페이로드 ID (존재하지 않으면 null)
     */
    Integer checkPayloadExist(String payloadSn);

    /**
     * 페이로드 데이터 일괄 저장
     * 
     * 드론 디바이스에 연결된 모든 페이로드 정보를
     * 데이터베이스에 일괄 저장합니다.
     * 
     * @param drone 드론 디바이스 정보
     * @param payloadReceiverList 저장할 페이로드 목록
     * @return 저장 성공 여부
     */
    Boolean savePayloadDTOs(DeviceDTO drone, List<DevicePayloadReceiver> payloadReceiverList);

    /**
     * 단일 페이로드 데이터 저장
     * 
     * 개별 페이로드 정보를 데이터베이스에 저장합니다.
     * 
     * @param payloadReceiver 저장할 페이로드 정보
     * @return 저장된 페이로드 ID
     */
    Integer saveOnePayloadDTO(DevicePayloadReceiver payloadReceiver);

    /**
     * 디바이스별 페이로드 목록 조회
     * 
     * 특정 디바이스에 연결된 모든 페이로드 정보를 조회합니다.
     * 
     * @param deviceSn 디바이스 시리얼 번호
     * @return 페이로드 목록
     */
    List<DevicePayloadDTO> getDevicePayloadEntitiesByDeviceSn(String deviceSn);

    /**
     * 디바이스별 페이로드 데이터 삭제
     * 
     * 특정 디바이스들에 연결된 모든 페이로드 데이터를 삭제합니다.
     * 디바이스가 제거되거나 교체될 때 사용됩니다.
     * 
     * @param deviceSns 삭제할 디바이스 시리얼 번호 목록
     */
    void deletePayloadsByDeviceSn(List<String> deviceSns);

    /**
     * 페이로드 펌웨어 버전 업데이트
     * 
     * 페이로드의 펌웨어 버전 정보를 업데이트합니다.
     * 펌웨어 업그레이드 후 버전 정보를 동기화할 때 사용됩니다.
     * 
     * @param droneSn 드론 시리얼 번호
     * @param receiver 페이로드 펌웨어 버전 정보
     * @return 업데이트 성공 여부
     */
    Boolean updateFirmwareVersion(String droneSn, PayloadFirmwareVersion receiver);

    /**
     * 페이로드별 데이터 삭제
     * 
     * 특정 페이로드들의 데이터를 삭제합니다.
     * 페이로드가 제거되거나 교체될 때 사용됩니다.
     * 
     * @param payloadSns 삭제할 페이로드 시리얼 번호 목록
     */
    void deletePayloadsByPayloadsSn(Collection<String> payloadSns);

    /**
     * 페이로드 제어 권한 확인
     * 
     * 특정 디바이스가 특정 페이로드를 제어할 수 있는
     * 권한을 가지고 있는지 확인합니다.
     * 
     * @param deviceSn 디바이스 시리얼 번호
     * @param payloadIndex 페이로드 인덱스
     * @return 페이로드 제어 권한 여부
     */
    Boolean checkAuthorityPayload(String deviceSn, String payloadIndex);

    /**
     * 페이로드 제어 상태 업데이트
     * 
     * 드론 디바이스의 페이로드 제어 상태를 업데이트합니다.
     * 페이로드 연결/해제 상태 변경 시 사용됩니다.
     * 
     * @param drone 드론 디바이스 정보
     * @param payloads 업데이트할 페이로드 목록
     */
    void updatePayloadControl(DeviceDTO drone, List<DevicePayloadReceiver> payloads);
}