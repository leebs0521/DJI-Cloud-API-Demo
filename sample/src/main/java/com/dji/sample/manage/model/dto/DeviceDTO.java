package com.dji.sample.manage.model.dto;

import com.dji.sample.manage.model.enums.DeviceFirmwareStatusEnum;
import com.dji.sdk.cloudapi.device.ControlSourceEnum;
import com.dji.sdk.cloudapi.device.DeviceDomainEnum;
import com.dji.sdk.cloudapi.device.DeviceSubTypeEnum;
import com.dji.sdk.cloudapi.device.DeviceTypeEnum;
import com.dji.sdk.cloudapi.tsa.DeviceIconUrl;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 디바이스 정보 전송 객체 (DTO) 클래스
 * 
 * DJI Cloud API의 디바이스 정보를 전송하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 디바이스 기본 정보 관리
 *    - 디바이스 시리얼 번호, 이름, 설명 관리
 *    - 디바이스 타입 및 서브타입 정보 관리
 *    - 디바이스 도메인 및 제어 소스 정보 관리
 * 
 * 2. 디바이스 상태 및 연결 정보 관리
 *    - 디바이스 온라인/오프라인 상태 관리
 *    - 디바이스 바인딩 상태 및 시간 관리
 *    - 디바이스 로그인 시간 및 사용자 정보 관리
 * 
 * 3. 디바이스 페이로드 및 펌웨어 정보 관리
 *    - 디바이스 페이로드 목록 관리
 *    - 펌웨어 버전 및 상태 정보 관리
 *    - 펌웨어 업그레이드 진행률 관리
 * 
 * 4. 디바이스 계층 구조 관리
 *    - 부모-자식 디바이스 관계 관리
 *    - 워크스페이스 정보 관리
 *    - 디바이스 아이콘 URL 관리
 * 
 * 이 클래스는 디바이스의 모든 정보를 포함하며, API 요청/응답에서
 * 디바이스 데이터를 전송하는 표준화된 형식을 제공합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/19
 * @version 0.1
 */
@Schema(description = "디바이스 정보 전송 객체")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceDTO {

    /**
     * 디바이스 시리얼 번호
     * 디바이스를 고유하게 식별하는 시리얼 번호
     */
    @Schema(description = "디바이스를 고유하게 식별하는 시리얼 번호")
    private String deviceSn;

    /**
     * 디바이스 이름
     * 사용자가 설정한 디바이스의 표시 이름
     */
    @Schema(description = "사용자가 설정한 디바이스의 표시 이름")
    private String deviceName;

    /**
     * 워크스페이스 ID
     * 디바이스가 속한 워크스페이스의 고유 식별자
     */
    @Schema(description = "디바이스가 속한 워크스페이스의 고유 식별자")
    private String workspaceId;

    /**
     * 제어 소스
     * 디바이스 제어 권한의 출처 (예: PILOT, DOCK 등)
     */
    @Schema(description = "디바이스 제어 권한의 출처 (예: PILOT, DOCK 등)", enumAsRef = true)
    private ControlSourceEnum controlSource;

    /**
     * 디바이스 설명
     * 디바이스에 대한 추가 설명 정보
     */
    @Schema(description = "디바이스에 대한 추가 설명 정보")
    private String deviceDesc;

    /**
     * 자식 디바이스 시리얼 번호
     * 현재 디바이스에 연결된 자식 디바이스의 시리얼 번호
     */
    @Schema(description = "현재 디바이스에 연결된 자식 디바이스의 시리얼 번호")
    private String childDeviceSn;

    /**
     * 디바이스 도메인
     * 디바이스가 속한 도메인 (예: AIRCRAFT, DOCK 등)
     */
    @Schema(description = "디바이스가 속한 도메인 (예: AIRCRAFT, DOCK 등)", enumAsRef = true)
    private DeviceDomainEnum domain;

    /**
     * 디바이스 타입
     * 디바이스의 주요 타입 (예: AIRCRAFT, DOCK, RC 등)
     */
    @Schema(description = "디바이스의 주요 타입 (예: AIRCRAFT, DOCK, RC 등)", enumAsRef = true)
    private DeviceTypeEnum type;

    /**
     * 디바이스 서브타입
     * 디바이스의 세부 타입 (예: M30, M300 등)
     */
    @Schema(description = "디바이스의 세부 타입 (예: M30, M300 등)", enumAsRef = true)
    private DeviceSubTypeEnum subType;

    /**
     * 페이로드 목록
     * 디바이스에 연결된 페이로드 장치들의 목록
     */
    @Schema(description = "디바이스에 연결된 페이로드 장치들의 목록")
    private List<DevicePayloadDTO> payloadsList;

    /**
     * 디바이스 아이콘 URL
     * 디바이스를 표시하기 위한 아이콘 이미지 URL
     */
    @Schema(description = "디바이스를 표시하기 위한 아이콘 이미지 URL")
    private DeviceIconUrl iconUrl;

    /**
     * 디바이스 상태
     * 디바이스의 온라인/오프라인 상태 (true: 온라인, false: 오프라인)
     */
    @Schema(description = "디바이스의 온라인/오프라인 상태 (true: 온라인, false: 오프라인)")
    private Boolean status;

    /**
     * 바인딩 상태
     * 디바이스의 바인딩 여부 (true: 바인딩됨, false: 바인딩되지 않음)
     */
    @Schema(description = "디바이스의 바인딩 여부 (true: 바인딩됨, false: 바인딩되지 않음)")
    private Boolean boundStatus;

    /**
     * 로그인 시간
     * 디바이스가 마지막으로 로그인한 시간
     */
    @Schema(description = "디바이스가 마지막으로 로그인한 시간")
    private LocalDateTime loginTime;

    /**
     * 바인딩 시간
     * 디바이스가 바인딩된 시간
     */
    @Schema(description = "디바이스가 바인딩된 시간")
    private LocalDateTime boundTime;

    /**
     * 닉네임
     * 디바이스의 별칭 또는 닉네임
     */
    @Schema(description = "디바이스의 별칭 또는 닉네임")
    private String nickname;

    /**
     * 사용자 ID
     * 디바이스를 사용하는 사용자의 고유 식별자
     */
    @Schema(description = "디바이스를 사용하는 사용자의 고유 식별자")
    private String userId;

    /**
     * 펌웨어 버전
     * 디바이스에 설치된 펌웨어의 버전 정보
     */
    @Schema(description = "디바이스에 설치된 펌웨어의 버전 정보")
    private String firmwareVersion;

    /**
     * 워크스페이스 이름
     * 디바이스가 속한 워크스페이스의 이름
     */
    @Schema(description = "디바이스가 속한 워크스페이스의 이름")
    private String workspaceName;

    /**
     * 자식 디바이스
     * 현재 디바이스의 자식 디바이스 정보
     */
    @Schema(description = "현재 디바이스의 자식 디바이스 정보")
    private DeviceDTO children;

    /**
     * 펌웨어 상태
     * 디바이스 펌웨어의 현재 상태 (업그레이드 중, 완료 등)
     */
    @Schema(description = "디바이스 펌웨어의 현재 상태 (업그레이드 중, 완료 등)")
    private DeviceFirmwareStatusEnum firmwareStatus;

    /**
     * 펌웨어 진행률
     * 펌웨어 업그레이드 진행률 (0-100%)
     */
    @Schema(description = "펌웨어 업그레이드 진행률 (0-100%)")
    private Integer firmwareProgress;

    /**
     * 부모 시리얼 번호
     * 현재 디바이스의 부모 디바이스 시리얼 번호
     */
    @Schema(description = "현재 디바이스의 부모 디바이스 시리얼 번호")
    private String parentSn;

    /**
     * Thing 버전
     * 디바이스의 Thing 모델 버전 정보
     */
    @Schema(description = "디바이스의 Thing 모델 버전 정보")
    private String thingVersion;
}
