package com.dji.sample.manage.model.receiver;

import lombok.Data;

/**
 * 무선 링크 상태 수신기 클래스
 * 
 * DJI Cloud API의 무선 링크 상태 정보를 수신하는 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 무선 링크 품질 관리
 *    - 다운로드 품질 관리
 *    - 업로드 품질 관리
 *    - 무선 링크 상태 모니터링
 * 
 * 2. 주파수 대역 관리
 *    - 무선 링크 주파수 대역 정보 관리
 *    - 주파수 대역별 품질 추적
 *    - 무선 통신 최적화 지원
 * 
 * 3. 무선 통신 상태 추적
 *    - 양방향 통신 품질 모니터링
 *    - 무선 링크 안정성 평가
 *    - 통신 품질 기반 최적화
 * 
 * 이 클래스는 DJI 디바이스의 무선 통신 상태를
 * 실시간으로 모니터링하고 관리할 수 있도록
 * 지원합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/23
 */
@Data
public class WirelessLinkStateReceiver {

    /**
     * 다운로드 품질
     * 무선 링크의 다운로드 통신 품질 (0-100)
     */
    private Integer downloadQuality;

    /**
     * 주파수 대역
     * 무선 링크가 사용하는 주파수 대역 (GHz)
     */
    private Double frequencyBand;

    /**
     * 업로드 품질
     * 무선 링크의 업로드 통신 품질 (0-100)
     */
    private Integer upwardQuality;

}