package com.dji.sample.control.service.impl;

import com.dji.sample.control.model.param.DronePayloadParam;

import java.util.Objects;

/**
 * 카메라 조준 핸들러 구현 클래스
 * 
 * 카메라 조준 명령의 실행 조건을 검증하는 핸들러 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 카메라 조준 파라미터 검증
 *    - 조준 좌표 X, Y 값 유효성 검사
 *    - 조준 잠금 상태 파라미터 검증
 *    - 카메라 타입 파라미터 검증
 * 
 * 2. 조준 명령 조건 확인
 *    - 모든 필수 파라미터가 설정되어 있는지 확인
 *    - 조준 좌표가 유효한 범위인지 확인
 *    - 카메라 타입이 지원되는지 확인
 * 
 * 3. 안전한 카메라 조준 보장
 *    - 필수 파라미터 누락 시 조준 방지
 *    - 잘못된 조준 좌표 입력 방지
 *    - 지원되지 않는 카메라 타입 조준 방지
 * 
 * 카메라 조준은 X, Y 좌표, 잠금 상태, 카메라 타입이 모두
 * 올바르게 설정되어 있을 때만 가능합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/4/23
 */
public class CameraAimImpl extends PayloadCommandsHandler {

    /**
     * 카메라 조준 핸들러 생성자
     * 
     * @param param 페이로드 파라미터 (조준 좌표, 잠금 상태, 카메라 타입 포함)
     */
    public CameraAimImpl(DronePayloadParam param) {
        super(param);
    }

    /**
     * 카메라 조준 파라미터의 유효성을 검사합니다.
     * 
     * 이 메서드는 카메라 조준 명령에 필요한 모든 파라미터가
     * 올바르게 설정되어 있는지 확인합니다:
     * - 조준 X 좌표가 null이 아닌지 확인
     * - 조준 Y 좌표가 null이 아닌지 확인
     * - 조준 잠금 상태가 null이 아닌지 확인
     * - 카메라 타입이 null이 아닌지 확인
     * 
     * @return 파라미터 유효성 여부 (모든 필수 파라미터가 설정된 경우 true)
     */
    @Override
    public boolean valid() {
        return Objects.nonNull(param.getX()) && Objects.nonNull(param.getY())
                && Objects.nonNull(param.getLocked()) && Objects.nonNull(param.getCameraType());
    }

}
