package com.dji.sdk.cloudapi.property;

import com.dji.sdk.common.BaseModel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 거리 제한 상태 설정 클래스
 * 
 * 이 클래스는 드론의 거리 제한 상태를 설정하기 위한 데이터를 정의합니다.
 * BaseModel을 상속받아 유효성 검사를 지원하며, 안전한 비행을 위한
 * 거리 제한 시스템을 관리합니다.
 * 
 * 주요 구성 요소:
 * - distanceLimitStatus: 거리 제한 상태 데이터
 * 
 * 이 클래스는 드론의 비행 거리를 제한하여 안전한 비행 영역을 설정하는 데 사용됩니다.
 * 규정 준수 및 안전 관리를 위해 최대 비행 거리를 제한할 수 있습니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/10/27
 */
public class DistanceLimitStatusSet extends BaseModel {

    /**
     * 거리 제한 상태 데이터
     * 
     * 드론의 거리 제한 기능을 설정하는 데이터 객체입니다.
     * DistanceLimitStatusData 클래스를 사용하여 거리 제한의 활성화/비활성화,
     * 최대 거리 설정 등을 관리할 수 있습니다.
     * 
     * 설정 가능한 옵션:
     * - 거리 제한 활성화/비활성화
     * - 최대 비행 거리 설정
     * - 제한 영역 설정
     * - 경고 거리 설정
     */
    @Valid
    @NotNull
    private DistanceLimitStatusData distanceLimitStatus;

    public DistanceLimitStatusSet() {
    }

    @Override
    public String toString() {
        return "DistanceLimitStatusSet{" +
                "distanceLimitStatus=" + distanceLimitStatus +
                '}';
    }

    /**
     * 거리 제한 상태 데이터를 반환합니다.
     * 
     * @return 거리 제한 상태 데이터
     */
    public DistanceLimitStatusData getDistanceLimitStatus() {
        return distanceLimitStatus;
    }

    /**
     * 거리 제한 상태 데이터를 설정합니다.
     * 
     * @param distanceLimitStatus 거리 제한 상태 데이터
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public DistanceLimitStatusSet setDistanceLimitStatus(DistanceLimitStatusData distanceLimitStatus) {
        this.distanceLimitStatus = distanceLimitStatus;
        return this;
    }
}
