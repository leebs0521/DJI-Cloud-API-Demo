package com.dji.sample.manage.service;

import com.dji.sample.manage.model.dto.CapacityVideoDTO;
import com.dji.sample.manage.model.receiver.CapacityVideoReceiver;

/**
 * 카메라 비디오 관리 서비스 인터페이스
 * 
 * DJI Cloud API의 카메라 비디오 관리를 위한 서비스 인터페이스입니다.
 * 이 인터페이스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 비디오 데이터 변환
 *    - 수신된 렌즈 용량 객체를 DTO로 변환
 *    - 비디오 정보 표준화 및 정규화
 *    - 데이터 형식 통일
 * 
 * 2. 카메라 렌즈 정보 관리
 *    - 렌즈별 비디오 용량 정보 관리
 *    - 렌즈 전환 가능한 비디오 타입 관리
 *    - 렌즈별 비디오 스트림 정보 제공
 * 
 * 3. 비디오 스트림 최적화
 *    - 렌즈별 비디오 스트림 품질 관리
 *    - 비디오 타입 전환 지원
 *    - 렌즈 리소스 관리
 * 
 * 이 인터페이스는 DJI 디바이스의 카메라 렌즈와 비디오 스트림을
 * 체계적으로 관리하고 최적화할 수 있도록 지원합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/19
 * @version 0.1
 */
public interface ICameraVideoService {

    /**
     * 렌즈 용량 수신기를 DTO로 변환
     * 
     * 수신된 렌즈 용량 객체(CapacityVideoReceiver)를
     * 데이터 전송 객체(CapacityVideoDTO)로 변환합니다.
     * 
     * @param receiver 변환할 렌즈 용량 수신기 객체
     * @return 변환된 비디오 용량 DTO
     */
    CapacityVideoDTO receiver2Dto(CapacityVideoReceiver receiver);
}
