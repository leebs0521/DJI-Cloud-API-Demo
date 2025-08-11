package com.dji.sample.manage.service.impl;

import com.dji.sample.manage.model.dto.CapacityVideoDTO;
import com.dji.sample.manage.model.receiver.CapacityVideoReceiver;
import com.dji.sample.manage.service.ICameraVideoService;
import com.dji.sdk.cloudapi.livestream.VideoTypeEnum;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 카메라 비디오 관리 서비스 구현체
 * 
 * DJI Cloud API의 카메라 비디오 관리를 위한 서비스 구현체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 구현합니다:
 * 
 * 1. 비디오 데이터 변환
 *    - CapacityVideoReceiver를 CapacityVideoDTO로 변환
 *    - 비디오 인덱스 및 타입 정보 매핑
 *    - 전환 가능한 비디오 타입 리스트 처리
 *    - 고유 ID 생성 및 할당
 * 
 * 2. 비디오 타입 관리
 *    - 비디오 타입 열거형 처리
 *    - 전환 가능한 비디오 타입 리스트 관리
 *    - 비디오 타입 유효성 검증
 *    - 타입별 비디오 스트림 처리
 * 
 * 3. 데이터 매핑 및 변환
 *    - 수신자 객체를 DTO로 변환
 *    - 비디오 메타데이터 처리
 *    - 스트림 타입 정보 변환
 *    - 빌더 패턴을 통한 객체 생성
 * 
 * 4. 비디오 스트림 처리
 *    - 비디오 인덱스 관리
 *    - 스트림 타입별 처리
 *    - 전환 가능한 스트림 타입 관리
 *    - 비디오 스트림 설정 관리
 * 
 * 주요 의존성:
 * - ICameraVideoService: 카메라 비디오 서비스 인터페이스
 * - CapacityVideoDTO: 비디오 데이터 전송 객체
 * - CapacityVideoReceiver: 비디오 수신자 객체
 * - VideoTypeEnum: 비디오 타입 열거형
 * 
 * 이 클래스는 DJI 디바이스의 카메라 비디오 스트림을
 * 효율적으로 관리하는 서비스입니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/19
 */
@Service
public class CameraVideoServiceImpl implements ICameraVideoService {

    /**
     * 비디오 수신자 객체를 DTO로 변환합니다.
     * 
     * CapacityVideoReceiver 객체를 CapacityVideoDTO로 변환하여
     * 비디오 스트림 정보를 처리할 수 있는 형태로 만듭니다.
     * 비디오 인덱스, 타입, 전환 가능한 타입 리스트 등을 포함하여
     * 완전한 비디오 정보를 구성합니다.
     * 
     * @param receiver 변환할 비디오 수신자 객체
     * @return 변환된 CapacityVideoDTO 객체 (null이 아닌 경우)
     */
    @Override
    public CapacityVideoDTO receiver2Dto(CapacityVideoReceiver receiver) {
        // 수신자 객체가 null이면 null 반환
        if (null == receiver) {
            return null;
        }
        
        // CapacityVideoDTO 빌더 생성 및 기본 정보 설정
        CapacityVideoDTO.CapacityVideoDTOBuilder builder = CapacityVideoDTO.builder()
                .id(UUID.randomUUID().toString())        // 고유 ID 생성
                .index(receiver.getVideoIndex())         // 비디오 인덱스 설정
                .type(receiver.getVideoType().getType()); // 비디오 타입 설정
        
        // 전환 가능한 비디오 타입이 있으면 리스트로 변환하여 설정
        if (null != receiver.getSwitchableVideoTypes()) {
            builder.switchVideoTypes(receiver.getSwitchableVideoTypes().stream()
                    .map(VideoTypeEnum::getType)         // VideoTypeEnum을 타입 문자열로 변환
                    .collect(Collectors.toList()));      // 리스트로 수집
        }
        
        // 완성된 CapacityVideoDTO 객체 반환
        return builder.build();
    }
}