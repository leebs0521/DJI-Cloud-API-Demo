package com.dji.sample.manage.model.receiver;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 게이트웨이 상태 수신기 클래스
 * 
 * DJI Cloud API의 게이트웨이 디바이스 상태 정보를 수신하는 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 게이트웨이 기본 정보 관리
 *    - 게이트웨이 시리얼 번호 관리
 *    - 게이트웨이 도메인 및 타입 관리
 *    - 게이트웨이 인덱스 및 버전 관리
 * 
 * 2. 게이트웨이 인증 정보 관리
 *    - 게이트웨이 시크릿 관리
 *    - 게이트웨이 논스(nonce) 관리
 *    - 게이트웨이 보안 인증 지원
 * 
 * 3. 서브 디바이스 목록 관리
 *    - 게이트웨이에 연결된 서브 디바이스 목록 관리
 *    - StatusSubDeviceReceiver를 활용한 서브 디바이스 정보 관리
 *    - 게이트웨이-서브 디바이스 관계 구조화
 * 
 * 4. JSON 직렬화 지원
 *    - @JsonIgnoreProperties로 알 수 없는 속성 무시
 *    - @JsonProperty로 JSON 필드명 매핑
 *    - API 응답과의 호환성 보장
 * 
 * 이 클래스는 게이트웨이 디바이스의 상태 정보를
 * 표준화된 방식으로 수신하고 관리할 수 있도록
 * 지원합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/12
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatusGatewayReceiver {

    /**
     * 게이트웨이 시리얼 번호
     * 게이트웨이를 고유하게 식별하는 시리얼 번호
     */
    private String sn;

    /**
     * 게이트웨이 도메인
     * 게이트웨이가 속한 도메인 (예: AIRCRAFT=0, DOCK=1 등)
     */
    private Integer domain;

    /**
     * 게이트웨이 타입
     * 게이트웨이의 주요 타입 (예: M30=0, M300=1 등)
     */
    private Integer type;

    /**
     * 게이트웨이 서브타입
     * 게이트웨이의 세부 타입
     */
    @JsonProperty(value = "sub_type")
    private Integer subType;

    /**
     * 게이트웨이 시크릿
     * 게이트웨이의 보안 인증을 위한 시크릿 키
     */
    @JsonProperty(value = "device_secret")
    private String deviceSecret;

    /**
     * 게이트웨이 논스
     * 게이트웨이의 일회성 보안 토큰
     */
    private String nonce;

    /**
     * 게이트웨이 버전
     * 게이트웨이의 현재 버전 정보
     */
    private Integer version;

    /**
     * 서브 디바이스 목록
     * 게이트웨이에 연결된 서브 디바이스들의 목록
     */
    @JsonProperty(value = "sub_devices")
    private List<StatusSubDeviceReceiver> subDevices;
}