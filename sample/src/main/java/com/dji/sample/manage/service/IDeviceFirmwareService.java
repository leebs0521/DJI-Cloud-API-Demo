package com.dji.sample.manage.service;

import com.dji.sample.manage.model.dto.DeviceFirmwareDTO;
import com.dji.sample.manage.model.dto.DeviceFirmwareNoteDTO;
import com.dji.sample.manage.model.dto.DeviceFirmwareUpgradeDTO;
import com.dji.sample.manage.model.param.DeviceFirmwareQueryParam;
import com.dji.sample.manage.model.param.DeviceFirmwareUploadParam;
import com.dji.sdk.cloudapi.firmware.OtaCreateDevice;
import com.dji.sdk.common.PaginationData;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

/**
 * 디바이스 펌웨어 관리 서비스 인터페이스
 * 
 * DJI Cloud API의 디바이스 펌웨어 관리를 위한 서비스 인터페이스입니다.
 * 이 인터페이스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 펌웨어 정보 관리
 *    - 펌웨어 버전 정보 조회 및 관리
 *    - 펌웨어 릴리즈 노트 관리
 *    - 펌웨어 호환성 상태 확인
 * 
 * 2. 펌웨어 업그레이드 관리
 *    - 펌웨어 업그레이드 작업 생성
 *    - 업그레이드 대상 펌웨어 정보 조회
 *    - 펌웨어 업그레이드 상태 추적
 * 
 * 3. 펌웨어 파일 관리
 *    - 펌웨어 파일 업로드 및 저장
 *    - 파일 중복 검사 (MD5 기반)
 *    - 펌웨어 파일 정보 업데이트
 * 
 * 4. 펌웨어 조회 및 검색
 *    - 페이지네이션을 통한 펌웨어 목록 조회
 *    - 디바이스 모델별 펌웨어 검색
 *    - 특정 버전의 펌웨어 정보 조회
 * 
 * 이 인터페이스는 DJI 디바이스의 펌웨어 생명주기를
 * 안전하고 효율적으로 관리할 수 있도록 지원합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/8/16
 */
public interface IDeviceFirmwareService {

    /**
     * 특정 펌웨어 정보 조회
     * 
     * 디바이스 모델과 펌웨어 버전을 기반으로
     * 특정 펌웨어의 상세 정보를 조회합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param deviceName 디바이스 모델명 (예: M30, M300 등)
     * @param version 펌웨어 버전 (예: "01.00.0000")
     * @return 펌웨어 정보 (Optional)
     */
    Optional<DeviceFirmwareDTO> getFirmware(String workspaceId, String deviceName, String version);

    /**
     * 최신 펌웨어 릴리즈 노트 조회
     * 
     * 특정 디바이스 모델의 최신 펌웨어에 대한
     * 릴리즈 노트 정보를 조회합니다.
     * 
     * @param deviceName 디바이스 모델명
     * @return 최신 펌웨어 릴리즈 노트 (Optional)
     */
    Optional<DeviceFirmwareNoteDTO> getLatestFirmwareReleaseNote(String deviceName);

    /**
     * 디바이스 업그레이드용 펌웨어 정보 조회
     * 
     * 디바이스 업그레이드 작업에 필요한
     * 펌웨어 정보들을 조회합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param upgradeDTOS 업그레이드할 디바이스 목록
     * @return OTA 생성용 디바이스 정보 목록
     */
    List<OtaCreateDevice> getDeviceOtaFirmware(String workspaceId, List<DeviceFirmwareUpgradeDTO> upgradeDTOS);

    /**
     * 펌웨어 버전 정보 페이지네이션 조회
     * 
     * 다양한 쿼리 조건에 따라 펌웨어 버전 정보를
     * 페이지네이션과 함께 조회합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param param 펌웨어 쿼리 파라미터 (모델명, 버전, 상태 등)
     * @return 페이지네이션된 펌웨어 목록
     */
    PaginationData<DeviceFirmwareDTO> getAllFirmwarePagination(String workspaceId, DeviceFirmwareQueryParam param);

    /**
     * 파일 존재 여부 확인 (MD5 기반)
     * 
     * MD5 해시값을 기반으로 펌웨어 파일이
     * 이미 업로드되어 있는지 확인합니다.
     * 중복 업로드를 방지하는 데 사용됩니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param fileMd5 파일의 MD5 해시값
     * @return 파일 존재 여부
     */
    Boolean checkFileExist(String workspaceId, String fileMd5);

    /**
     * 펌웨어 파일 업로드
     * 
     * 디바이스 업그레이드를 위한 펌웨어 파일을
     * 업로드하고 시스템에 등록합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param creator 파일 업로드자 정보
     * @param param 펌웨어 업로드 파라미터 (모델명, 버전, 설명 등)
     * @param file 업로드할 펌웨어 파일
     */
    void importFirmwareFile(String workspaceId, String creator, DeviceFirmwareUploadParam param, MultipartFile file);

    /**
     * 펌웨어 정보 저장
     * 
     * 펌웨어의 기본 정보를 데이터베이스에 저장하고,
     * 지원하는 디바이스 모델들과 연결합니다.
     * 
     * @param firmware 저장할 펌웨어 정보
     * @param deviceNames 지원하는 디바이스 모델명 목록
     */
    void saveFirmwareInfo(DeviceFirmwareDTO firmware, List<String> deviceNames);

    /**
     * 펌웨어 정보 업데이트
     * 
     * 기존 펌웨어의 정보를 업데이트합니다.
     * 버전 정보, 설명, 지원 디바이스 등을 수정할 수 있습니다.
     * 
     * @param firmware 업데이트할 펌웨어 정보
     */
    void updateFirmwareInfo(DeviceFirmwareDTO firmware);
}
