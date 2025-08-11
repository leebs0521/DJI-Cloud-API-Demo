package com.dji.sample.manage.model.receiver;

import lombok.Data;

import java.util.List;

/**
 * 디바이스 유지보수 상태 수신기 클래스
 * 
 * DJI Cloud API의 디바이스 유지보수 상태 정보를 수신하는 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 유지보수 상태 배열 관리
 *    - 디바이스의 유지보수 상태 배열 관리
 *    - MaintainStatusReceiver를 활용한 개별 유지보수 상태 관리
 *    - 다중 유지보수 상태 추적
 * 
 * 2. 유지보수 상태 모니터링
 *    - 디바이스별 유지보수 상태 모니터링
 *    - 유지보수 이력 추적
 *    - 유지보수 일정 관리 지원
 * 
 * 3. 유지보수 데이터 구조화
 *    - 유지보수 상태 정보의 체계적 관리
 *    - 유지보수 관련 데이터의 일관성 보장
 *    - 유지보수 상태 변경 추적
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
public class DeviceMaintainStatusReceiver {

    /**
     * 유지보수 상태 배열
     * 디바이스의 유지보수 상태 정보들을 담은 배열
     */
    private List<MaintainStatusReceiver> maintainStatusArray;
}
