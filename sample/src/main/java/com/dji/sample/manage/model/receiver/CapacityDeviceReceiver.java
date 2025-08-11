package com.dji.sample.manage.model.receiver;

import lombok.Data;

import java.util.List;

/**
 * 디바이스 용량 수신기 클래스
 * 
 * DJI Cloud API의 디바이스 용량 정보를 수신하는 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 디바이스 비디오 용량 관리
 *    - 디바이스 시리얼 번호 관리
 *    - 사용 가능한 비디오 개수 관리
 *    - 동시 지원 가능한 최대 비디오 개수 관리
 * 
 * 2. 카메라 목록 관리
 *    - 디바이스에 연결된 카메라 목록 관리
 *    - CapacityCameraReceiver를 활용한 카메라 정보 관리
 *    - 디바이스-카메라 관계 구조화
 * 
 * 3. 디바이스 용량 추적
 *    - 디바이스별 비디오 용량 모니터링
 *    - 카메라별 용량 분배 관리
 *    - 디바이스 용량 최적화 지원
 * 
 * 이 클래스는 DJI 디바이스의 전체 용량 정보를
 * 체계적으로 관리하고 추적할 수 있도록
 * 지원합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/18
 * @version 0.1
 */
@Data
public class CapacityDeviceReceiver {

    /**
     * 디바이스 시리얼 번호
     * 디바이스를 고유하게 식별하는 시리얼 번호
     */
    private String sn;

    /**
     * 사용 가능한 비디오 개수
     * 현재 디바이스에서 사용 가능한 비디오 스트림 개수
     */
    private Integer availableVideoNumber;

    /**
     * 동시 지원 가능한 최대 비디오 개수
     * 디바이스가 동시에 지원할 수 있는 최대 비디오 스트림 개수
     */
    private Integer coexistVideoNumberMax;

    /**
     * 카메라 목록
     * 디바이스에 연결된 카메라들의 목록
     */
    private List<CapacityCameraReceiver> cameraList;

}