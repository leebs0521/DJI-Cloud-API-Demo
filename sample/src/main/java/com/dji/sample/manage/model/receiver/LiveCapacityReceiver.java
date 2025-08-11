package com.dji.sample.manage.model.receiver;

import lombok.Data;

import java.util.List;

/**
 * 라이브 용량 수신기 클래스
 * 
 * DJI Cloud API의 라이브 스트리밍 용량 정보를 수신하는 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 라이브 비디오 용량 관리
 *    - 사용 가능한 비디오 개수 관리
 *    - 동시 지원 가능한 최대 비디오 개수 관리
 *    - 라이브 스트리밍 용량 추적
 * 
 * 2. 디바이스 목록 관리
 *    - 라이브 스트리밍을 지원하는 디바이스 목록 관리
 *    - CapacityDeviceReceiver를 활용한 디바이스 정보 관리
 *    - 디바이스별 라이브 스트리밍 용량 관리
 * 
 * 3. 라이브 스트리밍 최적화
 *    - 라이브 스트리밍 용량 모니터링
 *    - 디바이스별 용량 분배 관리
 *    - 라이브 스트리밍 품질 최적화 지원
 * 
 * 이 클래스는 DJI 디바이스의 라이브 스트리밍 용량을
 * 체계적으로 관리하고 추적할 수 있도록
 * 지원합니다.
 * 
 * @author sean
 * @version 1.0
 * @date 2022/5/24
 */
@Data
public class LiveCapacityReceiver {

    /**
     * 사용 가능한 비디오 개수
     * 현재 라이브 스트리밍에서 사용 가능한 비디오 스트림 개수
     */
    private Integer availableVideoNumber;

    /**
     * 동시 지원 가능한 최대 비디오 개수
     * 라이브 스트리밍이 동시에 지원할 수 있는 최대 비디오 스트림 개수
     */
    private Integer coexistVideoNumberMax;

    /**
     * 디바이스 목록
     * 라이브 스트리밍을 지원하는 디바이스들의 목록
     */
    private List<CapacityDeviceReceiver> deviceList;
}
