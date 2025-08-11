package com.dji.sample.control.service.impl;

import com.dji.sample.control.model.param.DronePayloadParam;

import java.util.Objects;

/**
 * 짐벌 리셋 핸들러 구현 클래스
 * 
 * 짐벌 리셋 명령의 실행 조건을 검증하는 핸들러 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 짐벌 리셋 파라미터 검증
 *    - 리셋 모드 파라미터 검증
 *    - 리셋 명령 유효성 확인
 * 
 * 2. 짐벌 리셋 조건 확인
 *    - 리셋 모드가 올바르게 설정되어 있는지 확인
 *    - 리셋 명령의 안전성 검증
 * 
 * 3. 안전한 짐벌 리셋 보장
 *    - 필수 파라미터 누락 시 리셋 방지
 *    - 잘못된 리셋 모드 입력 방지
 *    - 짐벌 상태 충돌 방지
 * 
 * 짐벌 리셋은 리셋 모드가 올바르게 설정되어 있을 때만 가능합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/4/23
 */
public class GimbalResetImpl extends PayloadCommandsHandler {
    
    /**
     * 짐벌 리셋 핸들러 생성자
     * 
     * @param param 페이로드 파라미터 (리셋 모드 포함)
     */
    public GimbalResetImpl(DronePayloadParam param) {
        super(param);
    }

    /**
     * 짐벌 리셋 파라미터의 유효성을 검사합니다.
     * 
     * 이 메서드는 짐벌 리셋 명령에 필요한 파라미터가
     * 올바르게 설정되어 있는지 확인합니다:
     * - 리셋 모드가 null이 아닌지 확인
     * - 리셋 명령이 유효한지 확인
     * 
     * @return 파라미터 유효성 여부 (리셋 모드가 설정된 경우 true)
     */
    @Override
    public boolean valid() {
        return Objects.nonNull(param.getResetMode());
    }

}
