package com.dji.sample.manage.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;

/**
 * 디바이스 펌웨어 상태 열거형
 * 
 * DJI Cloud API의 디바이스 펌웨어 상태를 정의하는 열거형 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 펌웨어 상태 정의
 *    - 업그레이드 불필요 (NOT_UPGRADE)
 *    - 일반 업그레이드 필요 (NORMAL_UPGRADE)
 *    - 일관성 업그레이드 필요 (CONSISTENT_UPGRADE)
 *    - 업그레이드 중 (UPGRADING)
 *    - 알 수 없는 상태 (UNKNOWN)
 * 
 * 2. 펌웨어 상태 관리
 *    - JSON 직렬화/역직렬화 지원
 *    - 정수 값으로부터 열거형 상수 검색 기능
 *    - 펌웨어 업그레이드 프로세스 추적
 * 
 * 3. 호환성 상태 관리
 *    - 내부 열거형으로 호환성 상태 정의
 *    - 일관성 있는 펌웨어 상태 관리
 *    - 호환성 검증 및 상태 추적
 * 
 * 이 열거형은 DJI 디바이스의 펌웨어 관리 시스템에서
 * 다양한 펌웨어 상태를 표준화된 방식으로
 * 관리하고 추적할 수 있도록 지원합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/8/15
 */
public enum DeviceFirmwareStatusEnum {

    /**
     * 업그레이드 불필요
     * 현재 펌웨어가 최신 버전이므로 업그레이드가 필요하지 않음
     */
    NOT_UPGRADE(1),

    /**
     * 일반 업그레이드 필요
     * 새로운 기능이나 개선사항을 위한 업그레이드가 필요함
     */
    NORMAL_UPGRADE(2),

    /**
     * 일관성 업그레이드 필요
     * 디바이스 간 펌웨어 일관성을 위한 업그레이드가 필요함
     */
    CONSISTENT_UPGRADE(3),

    /**
     * 업그레이드 중
     * 현재 펌웨어 업그레이드가 진행 중인 상태
     */
    UPGRADING(4),

    /**
     * 알 수 없는 상태
     * 정의되지 않은 펌웨어 상태에 대한 기본값
     */
    UNKNOWN(-1);

    /**
     * 펌웨어 상태 값
     * 각 펌웨어 상태에 대응하는 정수 값
     */
    int val;

    /**
     * JSON 직렬화를 위한 펌웨어 상태 값을 반환합니다.
     * @return 펌웨어 상태 값
     */
    @JsonValue
    public int getVal() {
        return val;
    }

    /**
     * 생성자
     * @param val 펌웨어 상태 값
     */
    DeviceFirmwareStatusEnum(int val) {
        this.val = val;
    }

    /**
     * 정수 값으로부터 열거형 상수를 찾습니다.
     * 
     * 주어진 정수 값에 해당하는 열거형 상수를 반환합니다.
     * 일치하는 값이 없으면 UNKNOWN을 반환합니다.
     * 
     * @param val 찾을 펌웨어 상태 값
     * @return 해당하는 열거형 상수 또는 UNKNOWN
     */
    @JsonCreator
    public static DeviceFirmwareStatusEnum find(int val) {
        return Arrays.stream(DeviceFirmwareStatusEnum.values())
                .filter(firmwareStatus -> firmwareStatus.val == val)
                .findFirst().orElse(UNKNOWN);
    }

    /**
     * 호환성 상태 열거형
     * 
     * 디바이스 펌웨어의 호환성 상태를 정의하는 내부 열거형입니다.
     * 이 열거형은 펌웨어 간의 호환성을 관리하고 추적합니다.
     */
    @Getter
    public enum CompatibleStatusEnum {
        /**
         * 일관성 없음
         * 디바이스 간 펌웨어가 일관되지 않은 상태
         */
        INCONSISTENT(1),

        /**
         * 일관성 있음
         * 디바이스 간 펌웨어가 일관된 상태
         */
        CONSISTENT(0);

        /**
         * 호환성 상태 값
         * 각 호환성 상태에 대응하는 정수 값
         */
        int val;

        /**
         * 생성자
         * @param val 호환성 상태 값
         */
        CompatibleStatusEnum(int val) {
            this.val = val;
        }
    }

}
