package com.dji.sample.manage.model.receiver;

import lombok.Data;

/**
 * 도킹 스테이션 SDR 수신기 클래스
 * 
 * DJI Cloud API의 도킹 스테이션 SDR(Software Defined Radio) 상태 정보를 수신하는 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. SDR 통신 품질 관리
 *    - 다운로드 품질 관리
 *    - 업로드 품질 관리
 *    - SDR 통신 상태 모니터링
 * 
 * 2. 주파수 대역 관리
 *    - SDR 주파수 대역 정보 관리
 *    - 주파수 대역별 품질 추적
 *    - SDR 통신 최적화 지원
 * 
 * 3. 도킹 스테이션 통신 상태 추적
 *    - 도킹 스테이션의 무선 통신 품질 모니터링
 *    - SDR 통신 안정성 평가
 *    - 통신 품질 기반 최적화
 * 
 * 이 클래스는 도킹 스테이션의 SDR 통신 상태를
 * 실시간으로 모니터링하고 관리할 수 있도록
 * 지원합니다.
 * 
 * @author sean
 * @version 1.1
 * @date 2022/6/17
 */
@Data
public class DockSdrReceiver {

    /**
     * 다운로드 품질
     * SDR의 다운로드 통신 품질 (0-100)
     */
    private Integer downQuality;

    /**
     * 주파수 대역
     * SDR가 사용하는 주파수 대역 (GHz)
     */
    private Double frequencyBand;

    /**
     * 업로드 품질
     * SDR의 업로드 통신 품질 (0-100)
     */
    private Integer upQuality;
}
