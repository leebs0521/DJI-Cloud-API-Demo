package com.dji.sample.manage.model.enums;

/**
 * 라이브 URL 타입 열거형
 * 
 * DJI Cloud API의 라이브 스트리밍 URL 타입을 정의하는 열거형 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 라이브 URL 타입 정의
 *    - Agora 스트리밍 (AGORA)
 *    - RTMP 스트리밍 (RTMP)
 *    - RTSP 스트리밍 (RTSP)
 *    - GB28181 스트리밍 (GB28181)
 *    - 알 수 없는 타입 (UNKNOWN)
 * 
 * 2. 스트리밍 프로토콜 관리
 *    - 다양한 스트리밍 프로토콜 지원
 *    - 프로토콜별 특성 및 기능 분류
 *    - 스트리밍 프로토콜 호환성 관리
 * 
 * 3. 라이브 스트리밍 최적화
 *    - 프로토콜별 최적화된 스트리밍 설정
 *    - 네트워크 환경에 따른 프로토콜 선택
 *    - 스트리밍 품질 및 안정성 향상
 * 
 * 이 열거형은 DJI 디바이스의 라이브 스트리밍 시스템에서
 * 다양한 스트리밍 프로토콜을 표준화된 방식으로
 * 관리하고 지원할 수 있도록 지원합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/22
 */
public enum LiveUrlTypeEnum {

    /**
     * Agora 스트리밍
     * Agora 플랫폼을 통한 실시간 스트리밍
     */
    AGORA(0),

    /**
     * RTMP 스트리밍
     * Real-Time Messaging Protocol을 통한 스트리밍
     */
    RTMP(1),

    /**
     * RTSP 스트리밍
     * Real-Time Streaming Protocol을 통한 스트리밍
     */
    RTSP(2),

    /**
     * GB28181 스트리밍
     * GB28181 표준을 통한 스트리밍 (중국 표준)
     */
    GB28181(3),

    /**
     * 알 수 없는 타입
     * 정의되지 않은 스트리밍 타입에 대한 기본값
     */
    UNKNOWN(-1);

    /**
     * 라이브 URL 타입 값
     * 각 라이브 URL 타입에 대응하는 정수 값
     */
    private int val;

    /**
     * 생성자
     * @param val 라이브 URL 타입 값
     */
    LiveUrlTypeEnum(int val) {
        this.val = val;
    }

    /**
     * 정수 값으로부터 열거형 상수를 찾습니다.
     * 
     * 주어진 정수 값에 해당하는 열거형 상수를 반환합니다.
     * 일치하는 값이 없으면 UNKNOWN을 반환합니다.
     * 
     * @param val 찾을 라이브 URL 타입 값
     * @return 해당하는 열거형 상수 또는 UNKNOWN
     */
    public static LiveUrlTypeEnum find(Integer val) {
        if (AGORA.val == val) {
            return AGORA;
        }
        if (RTMP.val == val) {
            return RTMP;
        }
        if (RTSP.val == val) {
            return RTSP;
        }
        if (GB28181.val == val) {
            return GB28181;
        }
        return UNKNOWN;
    }
}
