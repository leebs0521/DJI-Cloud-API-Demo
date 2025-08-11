package com.dji.sample.manage.model.receiver;

import com.dji.sample.manage.model.dto.DevicePayloadReceiver;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 디바이스 기본 정보 수신기 클래스
 * 
 * DJI Cloud API의 디바이스 기본 정보를 수신하는 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 디바이스 기본 정보 관리
 *    - 디바이스 시리얼 번호 관리
 *    - 디바이스 제어 소스 관리
 *    - 디바이스 홈 위치 정보 관리
 * 
 * 2. 배터리 경고 임계값 관리
 *    - 배터리 부족 경고 임계값 관리
 *    - 배터리 심각 부족 경고 임계값 관리
 *    - 배터리 상태 모니터링 지원
 * 
 * 3. 페이로드 목록 관리
 *    - 디바이스에 연결된 페이로드 목록 관리
 *    - DevicePayloadReceiver를 활용한 페이로드 정보 관리
 *    - 디바이스-페이로드 관계 구조화
 * 
 * 4. JSON 직렬화 지원
 *    - @JsonIgnoreProperties로 알 수 없는 속성 무시
 *    - API 응답과의 호환성 보장
 *    - 표준화된 데이터 형식 제공
 * 
 * 이 클래스는 DJI 디바이스의 기본 정보를
 * 체계적으로 관리하고 추적할 수 있도록
 * 지원합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/18
 * @version 0.1
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceBasicReceiver {

    /**
     * 디바이스 시리얼 번호
     * 디바이스를 고유하게 식별하는 시리얼 번호
     */
    private String deviceSn;

    /**
     * 제어 소스
     * 디바이스를 제어하는 소스 정보
     */
    private String controlSource;

    /**
     * 홈 위도
     * 디바이스의 홈 위치 위도 좌표
     */
    private Float homeLatitude;

    /**
     * 홈 경도
     * 디바이스의 홈 위치 경도 좌표
     */
    private Float homeLongitude;

    /**
     * 배터리 부족 경고 임계값
     * 배터리 부족 경고를 발생시키는 배터리 잔량 임계값 (%)
     */
    private Integer lowBatteryWarningThreshold;

    /**
     * 배터리 심각 부족 경고 임계값
     * 배터리 심각 부족 경고를 발생시키는 배터리 잔량 임계값 (%)
     */
    private Integer seriousLowBatteryWarningThreshold;

    /**
     * 페이로드 목록
     * 디바이스에 연결된 페이로드들의 목록
     */
    private List<DevicePayloadReceiver> payloads;
}