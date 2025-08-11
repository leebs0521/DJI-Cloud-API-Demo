package com.dji.sample.manage.model.receiver;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 서브 디바이스 상태 수신기 클래스
 * 
 * DJI Cloud API의 서브 디바이스 상태 정보를 수신하는 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 서브 디바이스 기본 정보 관리
 *    - 서브 디바이스 시리얼 번호 관리
 *    - 서브 디바이스 도메인 및 타입 관리
 *    - 서브 디바이스 인덱스 및 버전 관리
 * 
 * 2. 서브 디바이스 인증 정보 관리
 *    - 서브 디바이스 시크릿 관리
 *    - 서브 디바이스 논스(nonce) 관리
 *    - 서브 디바이스 보안 인증 지원
 * 
 * 3. JSON 직렬화 지원
 *    - @JsonIgnoreProperties로 알 수 없는 속성 무시
 *    - @JsonProperty로 JSON 필드명 매핑
 *    - API 응답과의 호환성 보장
 * 
 * 이 클래스는 게이트웨이 디바이스에 연결된 서브 디바이스들의
 * 상태 정보를 표준화된 방식으로 수신하고 관리할 수 있도록
 * 지원합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/12
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatusSubDeviceReceiver {

    /**
     * 서브 디바이스 시리얼 번호
     * 서브 디바이스를 고유하게 식별하는 시리얼 번호
     */
    private String sn;

    /**
     * 서브 디바이스 도메인
     * 서브 디바이스가 속한 도메인 (예: AIRCRAFT=0, DOCK=1 등)
     */
    private Integer domain;

    /**
     * 서브 디바이스 타입
     * 서브 디바이스의 주요 타입 (예: M30=0, M300=1 등)
     */
    private Integer type;

    /**
     * 서브 디바이스 서브타입
     * 서브 디바이스의 세부 타입
     */
    @JsonProperty(value = "sub_type")
    private Integer subType;

    /**
     * 서브 디바이스 인덱스
     * 서브 디바이스의 순서 또는 위치 인덱스
     */
    private String index;

    /**
     * 서브 디바이스 시크릿
     * 서브 디바이스의 보안 인증을 위한 시크릿 키
     */
    @JsonProperty(value = "device_secret")
    private String deviceSecret;

    /**
     * 서브 디바이스 논스
     * 서브 디바이스의 일회성 보안 토큰
     */
    private String nonce;

    /**
     * 서브 디바이스 버전
     * 서브 디바이스의 현재 버전 정보
     */
    private Integer version;
}