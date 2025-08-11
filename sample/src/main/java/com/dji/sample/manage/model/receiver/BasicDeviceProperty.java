package com.dji.sample.manage.model.receiver;

import com.dji.sdk.cloudapi.device.OsdDockDrone;

/**
 * 기본 디바이스 속성 추상 클래스
 * 
 * DJI Cloud API의 디바이스 속성들을 관리하기 위한 기본 추상 클래스입니다.
 * 이 클래스는 모든 디바이스 속성 수신기 클래스들이 공통으로 구현해야 하는
 * 기본 기능들을 정의합니다.
 * 
 * 주요 기능:
 * 
 * 1. 속성 유효성 검증
 *    - valid() 메서드를 통해 속성 값의 유효성 검증
 *    - 각 하위 클래스에서 구체적인 검증 로직 구현
 *    - 데이터 무결성 보장
 * 
 * 2. 상태 변경 감지
 *    - canPublish() 메서드를 통해 상태 변경 필요성 판단
 *    - 현재 OSD 상태와 새로운 상태 비교
 *    - 불필요한 상태 업데이트 방지
 * 
 * 3. 표준화된 인터페이스 제공
 *    - 모든 디바이스 속성 클래스의 공통 인터페이스 정의
 *    - 일관된 데이터 처리 방식 보장
 *    - 코드 재사용성 향상
 * 
 * 사용 예시:
 * - RthAltitudeReceiver, ObstacleAvoidanceReceiver 등이 이 클래스를 상속
 * - 디바이스 속성 변경 시 유효성 검증 및 상태 변경 감지
 * - MQTT 메시지 발행 전 데이터 검증
 * 
 * @author sean
 * @version 1.3
 * @date 2022/10/27
 */
public abstract class BasicDeviceProperty {

    /**
     * 속성 값의 유효성을 검증합니다.
     * 
     * 각 하위 클래스에서 구체적인 검증 로직을 구현해야 합니다.
     * 기본 구현은 false를 반환하며, 하위 클래스에서 오버라이드하여
     * 실제 검증 로직을 구현해야 합니다.
     * 
     * 검증 항목 예시:
     * - 값의 범위 검증 (예: RTH 고도 20m-500m)
     * - 필수 값 존재 여부 확인
     * - 데이터 타입 및 형식 검증
     * - 비즈니스 규칙 검증
     * 
     * @return 속성 값의 유효성 여부 (true: 유효, false: 무효)
     */
    public boolean valid() {
        return false;
    }

    /**
     * 상태 변경이 필요한지 확인합니다.
     * 
     * 현재 OSD 상태와 새로운 속성 값을 비교하여
     * 실제로 변경이 필요한 경우에만 true를 반환합니다.
     * 이를 통해 불필요한 MQTT 메시지 발행을 방지할 수 있습니다.
     * 
     * 기본 구현은 valid() 메서드의 결과를 반환하며,
     * 하위 클래스에서 오버라이드하여 구체적인 비교 로직을 구현할 수 있습니다.
     * 
     * 비교 로직 예시:
     * - 현재 RTH 고도와 새로운 RTH 고도 비교
     * - 현재 장애물 회피 설정과 새로운 설정 비교
     * - 현재 통신 끊김 동작과 새로운 동작 비교
     * 
     * @param osd 현재 OSD 상태 정보 (드론의 현재 상태)
     * @return 상태 변경 필요 여부 (true: 변경 필요, false: 변경 불필요)
     */
    public boolean canPublish(OsdDockDrone osd) {
        return valid();
    }
}
