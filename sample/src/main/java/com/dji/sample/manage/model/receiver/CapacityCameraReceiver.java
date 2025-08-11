package com.dji.sample.manage.model.receiver;

import com.dji.sdk.cloudapi.device.PayloadIndex;
import lombok.Data;

import java.util.List;

/**
 * 카메라 용량 수신기 클래스
 * 
 * DJI Cloud API의 카메라 용량 정보를 수신하는 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 카메라 비디오 용량 관리
 *    - 사용 가능한 비디오 개수 관리
 *    - 동시 지원 가능한 최대 비디오 개수 관리
 *    - 카메라별 비디오 용량 추적
 * 
 * 2. 카메라 인덱스 관리
 *    - PayloadIndex를 활용한 카메라 인덱스 관리
 *    - 카메라별 고유 식별자 제공
 *    - 카메라 위치 및 순서 관리
 * 
 * 3. 비디오 목록 관리
 *    - 카메라별 비디오 목록 관리
 *    - CapacityVideoReceiver를 활용한 비디오 정보 관리
 *    - 카메라-비디오 관계 구조화
 * 
 * 이 클래스는 DJI 디바이스의 카메라 용량 정보를
 * 체계적으로 관리하고 추적할 수 있도록
 * 지원합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/18
 * @version 0.1
 */
@Data
public class CapacityCameraReceiver {

    /**
     * 사용 가능한 비디오 개수
     * 현재 카메라에서 사용 가능한 비디오 스트림 개수
     */
    private Integer availableVideoNumber;

    /**
     * 동시 지원 가능한 최대 비디오 개수
     * 카메라가 동시에 지원할 수 있는 최대 비디오 스트림 개수
     */
    private Integer coexistVideoNumberMax;

    /**
     * 카메라 인덱스
     * 카메라를 고유하게 식별하는 PayloadIndex
     */
    private PayloadIndex cameraIndex;

    /**
     * 비디오 목록
     * 카메라에서 지원하는 비디오 스트림들의 목록
     */
    private List<CapacityVideoReceiver> videoList;

}