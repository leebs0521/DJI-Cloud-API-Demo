package com.dji.sample.map.model.dto;

import com.dji.sdk.cloudapi.flightarea.FlightAreaSyncReasonEnum;
import com.dji.sdk.cloudapi.flightarea.FlightAreaSyncStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 디바이스 비행 영역 전송 객체 (DTO)
 * 
 * DJI Cloud API에서 디바이스와 비행 영역 간의 관계 및 동기화 상태를 전송하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 다음과 같은 주요 정보들을 포함합니다:
 * 
 * 1. 디바이스-비행영역 관계 정보
 *    - 디바이스 시리얼 번호 (deviceSn)
 *    - 워크스페이스 ID (workspaceId)
 *    - 비행 영역 파일 ID (fileId)
 * 
 * 2. 비행 영역 동기화 상태 정보
 *    - 동기화 상태 (syncStatus)
 *    - 동기화 실패 이유 (syncCode)
 *    - 동기화 메시지 (syncMsg)
 * 
 * 3. Lombok 어노테이션 활용
 *    - @Data: getter, setter, toString, equals, hashCode 자동 생성
 *    - @Builder: 빌더 패턴 지원
 *    - @AllArgsConstructor: 모든 필드를 포함한 생성자 자동 생성
 *    - @NoArgsConstructor: 기본 생성자 자동 생성
 * 
 * 주요 용도:
 * - 디바이스별 비행 영역 할당 상태 조회
 * - 비행 영역 동기화 상태 모니터링
 * - 비행 영역 동기화 실패 원인 분석
 * - 디바이스-비행영역 관계 관리
 * 
 * 사용 예시:
 * - 디바이스 대시보드에서 비행 영역 상태 표시
 * - 비행 영역 동기화 진행 상황 추적
 * - 동기화 실패 시 오류 메시지 표시
 * 
 * @author sean
 * @version 1.9
 * @date 2023/11/23
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "디바이스 비행 영역 전송 객체")
public class DeviceFlightAreaDTO {

    /**
     * 디바이스 시리얼 번호
     * 
     * 비행 영역이 할당된 디바이스의 고유 식별자입니다.
     * 이 값은 디바이스와 비행 영역 간의 관계를 식별하는 데 사용됩니다.
     */
    @Schema(description = "디바이스 시리얼 번호")
    private String deviceSn;

    /**
     * 워크스페이스 ID
     * 
     * 비행 영역이 속한 워크스페이스의 고유 식별자입니다.
     * 워크스페이스별로 비행 영역을 관리할 수 있습니다.
     */
    @Schema(description = "워크스페이스 ID")
    private String workspaceId;

    /**
     * 비행 영역 파일 ID
     * 
     * 디바이스에 할당된 비행 영역 파일의 고유 식별자입니다.
     * 이 파일에는 비행 영역의 상세 정보가 포함되어 있습니다.
     */
    @Schema(description = "비행 영역 파일 ID")
    private String fileId;

    /**
     * 비행 영역 동기화 상태
     * 
     * 디바이스와 비행 영역 간의 동기화 진행 상태를 나타냅니다.
     * 예: SUCCESS, FAILED, SYNCING, NOT_SYNC 등
     */
    @Schema(description = "비행 영역 동기화 상태")
    private FlightAreaSyncStatusEnum syncStatus;

    /**
     * 비행 영역 동기화 실패 이유
     * 
     * 동기화가 실패한 경우 그 원인을 나타내는 코드입니다.
     * 예: FILE_NOT_FOUND, DEVICE_OFFLINE, INVALID_FORMAT 등
     */
    @Schema(description = "비행 영역 동기화 실패 이유")
    private FlightAreaSyncReasonEnum syncCode;

    /**
     * 비행 영역 동기화 메시지
     * 
     * 동기화 상태에 대한 상세한 설명 메시지입니다.
     * 사용자에게 동기화 결과를 알려주는 데 사용됩니다.
     */
    @Schema(description = "비행 영역 동기화 메시지")
    private String syncMsg;
}
