package com.dji.sample.manage.model.receiver;

import lombok.Data;

/**
 * 유지보수 상태 수신기 클래스
 * 
 * DJI Cloud API의 유지보수 상태 정보를 수신하는 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 유지보수 상태 관리
 *    - 유지보수 상태 값 관리
 *    - 유지보수 타입 정보 관리
 *    - 유지보수 시간 정보 관리
 * 
 * 2. 유지보수 이력 추적
 *    - 마지막 유지보수 시간 추적
 *    - 마지막 유지보수 타입 추적
 *    - 마지막 유지보수 작업 횟수 추적
 * 
 * 3. 유지보수 일정 관리
 *    - 유지보수 상태 모니터링
 *    - 유지보수 일정 계획 지원
 *    - 유지보수 이력 분석 지원
 * 
 * 이 클래스는 DJI 디바이스의 유지보수 상태를
 * 체계적으로 관리하고 추적할 수 있도록
 * 지원합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/3
 */
@Data
public class MaintainStatusReceiver {

    /**
     * 유지보수 상태
     * 현재 유지보수의 상태 값
     */
    private Integer state;

    /**
     * 마지막 유지보수 타입
     * 마지막으로 수행된 유지보수의 타입
     */
    private Integer lastMaintainType;

    /**
     * 마지막 유지보수 시간
     * 마지막 유지보수가 수행된 시간 (밀리초)
     */
    private Long lastMaintainTime;

    /**
     * 마지막 유지보수 작업 횟수
     * 마지막 유지보수에서 수행된 작업의 횟수
     */
    private Long lastMaintainWorkSorties;
}
