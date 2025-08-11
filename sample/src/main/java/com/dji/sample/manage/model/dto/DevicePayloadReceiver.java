package com.dji.sample.manage.model.dto;

import com.dji.sdk.cloudapi.device.ControlSourceEnum;
import com.dji.sdk.cloudapi.device.PayloadIndex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 디바이스 페이로드 수신자 정보 전송 객체 (DTO) 클래스
 * 
 * DJI Cloud API의 디바이스 페이로드 수신자 정보를 전송하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 디바이스 페이로드 수신 정보 관리
 *    - 디바이스 시리얼 번호 관리
 *    - 페이로드 수신자 시리얼 번호 관리
 *    - 페이로드 수신 관계 정보 관리
 * 
 * 2. 제어 소스 정보 관리
 *    - 페이로드 제어 권한의 출처 관리
 *    - 제어 소스별 권한 분류 및 관리
 *    - 제어 권한의 출처 추적 및 관리
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
 * 이 클래스는 디바이스 페이로드의 수신 관계를 관리하며,
 * 페이로드 장치 간의 연결 및 제어 정보를 제공하는
 * 표준화된 데이터 형식을 제공합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/18
 * @version 0.1
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DevicePayloadReceiver {

    /**
     * 디바이스 시리얼 번호
     * 페이로드를 제어하는 디바이스의 시리얼 번호
     */
    private String deviceSn;

    /**
     * 제어 소스
     * 페이로드 제어 권한의 출처 (예: PILOT, DOCK 등)
     */
    private ControlSourceEnum controlSource;

    /**
     * 페이로드 인덱스
     * 페이로드 장치의 인덱스 정보
     */
    private PayloadIndex payloadIndex;

    /**
     * 페이로드 수신자 시리얼 번호
     * 페이로드 데이터를 수신하는 장치의 시리얼 번호
     */
    private String sn;

}