package com.dji.sample.manage.model.dto;
import com.dji.sdk.cloudapi.device.ControlSourceEnum;
import com.dji.sdk.cloudapi.device.PayloadIndex;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 디바이스 페이로드 정보 전송 객체 (DTO) 클래스
 * 
 * DJI Cloud API의 디바이스 페이로드 정보를 전송하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 페이로드 기본 정보 관리
 *    - 페이로드 시리얼 번호 및 이름 관리
 *    - 페이로드 설명 및 인덱스 정보 관리
 *    - 페이로드 식별자 및 메타데이터 관리
 * 
 * 2. 페이로드 제어 정보 관리
 *    - 페이로드 제어 소스 정보 관리
 *    - 페이로드 제어 권한 및 접근 정보 관리
 *    - 페이로드 제어 상태 추적
 * 
 * 3. 페이로드 인덱스 관리
 *    - 페이로드 인덱스 정보 관리
 *    - 페이로드 위치 및 연결 정보 관리
 *    - 페이로드 장치별 인덱싱 정보
 * 
 * 4. API 통신 지원
 *    - Lombok 어노테이션을 통한 자동 getter/setter 생성
 *    - 빌더 패턴을 통한 편리한 객체 생성
 *    - API 요청/응답에서 사용되는 표준화된 형식 제공
 * 
 * 이 클래스는 디바이스에 연결된 페이로드 장치(카메라, 짐벌 등)의
 * 모든 정보를 포함하며, 페이로드 장치의 제어 및 관리를 위한
 * 표준화된 데이터 형식을 제공합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/19
 * @version 0.1
 */
@Schema(description = "디바이스 페이로드 정보 전송 객체")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DevicePayloadDTO {
    /**
     * 페이로드 시리얼 번호
     * 페이로드 장치를 고유하게 식별하는 시리얼 번호
     */
    @Schema(description = "페이로드 장치를 고유하게 식별하는 시리얼 번호")
    private String payloadSn;

    /**
     * 페이로드 이름
     * 페이로드 장치의 표시 이름
     */
    @Schema(description = "페이로드 장치의 표시 이름")
    private String payloadName;

    /**
     * 페이로드 인덱스
     * 페이로드 장치의 순서 또는 위치 인덱스
     */
    @Schema(description = "페이로드 장치의 순서 또는 위치 인덱스")
    private Integer index;

    /**
     * 페이로드 설명
     * 페이로드 장치에 대한 추가 설명 정보
     */
    @Schema(description = "페이로드 장치에 대한 추가 설명 정보")
    private String payloadDesc;

    /**
     * 제어 소스
     * 페이로드 제어 권한의 출처 (예: PILOT, DOCK 등)
     */
    @Schema(description = "페이로드 제어 권한의 출처 (예: PILOT, DOCK 등)", enumAsRef = true)
    private ControlSourceEnum controlSource;

    /**
     * 페이로드 인덱스 정보
     * 페이로드 장치의 상세 인덱스 정보
     */
    @Schema(description = "페이로드 장치의 상세 인덱스 정보")
    private PayloadIndex payloadIndex;
}
