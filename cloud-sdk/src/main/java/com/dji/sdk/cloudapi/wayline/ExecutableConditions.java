package com.dji.sdk.cloudapi.wayline;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 실행 가능 조건 클래스
 * 
 * 이 클래스는 웨이라인 비행 작업이 실행되기 위해 필요한
 * 조건들을 정의합니다.
 * 
 * 주요 구성 요소:
 * - storageCapacity: 저장 용량
 * 
 * 이 클래스는 비행 작업 실행 전에 필요한 조건들을
 * 확인하여 안전한 실행을 보장하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/6
 */
public class ExecutableConditions {

    /**
     * 저장 용량
     * 
     * 작업을 실행할 수 있는 DJI 도크 또는 항공기의 최소 저장 용량입니다.
     * 단위는 MB이며, 저장 용량이 storageCapacity를 만족하지 않으면
     * 작업 실행이 실패합니다.
     * 
     * 제약 조건: 최소값 0
     */
    @NotNull
    @Min(0)
    private Integer storageCapacity;

    public ExecutableConditions() {}

    @Override
    public String toString() {
        return "ExecutableConditions{" +
                "storageCapacity=" + storageCapacity +
                '}';
    }

    /**
     * 저장 용량을 반환합니다.
     * 
     * @return 저장 용량 (MB)
     */
    public Integer getStorageCapacity() {
        return storageCapacity;
    }

    /**
     * 저장 용량을 설정합니다.
     * 
     * @param storageCapacity 저장 용량 (MB)
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public ExecutableConditions setStorageCapacity(Integer storageCapacity) {
        this.storageCapacity = storageCapacity;
        return this;
    }
}