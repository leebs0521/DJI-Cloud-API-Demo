package com.dji.sample.manage.model.receiver;

import com.dji.sdk.cloudapi.livestream.VideoTypeEnum;
import lombok.Data;

import java.util.List;

/**
 * 비디오 용량 수신기 클래스
 * 
 * DJI Cloud API의 비디오 용량 정보를 수신하는 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 비디오 인덱스 관리
 *    - 비디오 스트림 인덱스 관리
 *    - 비디오 스트림 고유 식별자 제공
 *    - 비디오 스트림 순서 및 위치 관리
 * 
 * 2. 비디오 타입 관리
 *    - VideoTypeEnum을 활용한 비디오 타입 관리
 *    - 비디오 스트림 타입 분류
 *    - 비디오 타입별 특성 관리
 * 
 * 3. 전환 가능한 비디오 타입 관리
 *    - 현재 비디오에서 전환 가능한 타입 목록 관리
 *    - 비디오 타입 전환 옵션 제공
 *    - 동적 비디오 타입 변경 지원
 * 
 * 이 클래스는 DJI 디바이스의 비디오 스트림 용량 정보를
 * 체계적으로 관리하고 추적할 수 있도록
 * 지원합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/18
 * @version 0.1
 */
@Data
public class CapacityVideoReceiver {

    /**
     * 비디오 인덱스
     * 비디오 스트림을 고유하게 식별하는 인덱스
     */
    private String videoIndex;

    /**
     * 비디오 타입
     * 현재 비디오 스트림의 타입 (VideoTypeEnum)
     */
    private VideoTypeEnum videoType;

    /**
     * 전환 가능한 비디오 타입 목록
     * 현재 비디오에서 전환 가능한 비디오 타입들의 목록
     */
    private List<VideoTypeEnum> switchableVideoTypes;
}