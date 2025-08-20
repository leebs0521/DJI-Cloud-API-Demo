package com.dji.sdk.mqtt.state;

import com.dji.sdk.cloudapi.device.*;
import com.dji.sdk.cloudapi.livestream.RcLivestreamAbilityUpdate;
import com.dji.sdk.exception.CloudSDKException;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

/**
 * RC 상태 데이터 키 열거형
 * RC 관련 상태 데이터 키와 해당하는 클래스 타입을 정의
 *
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/18
 */
public enum RcStateDataKeyEnum {

    /**
     * 펌웨어 버전
     */
    FIRMWARE_VERSION(Set.of("firmware_version"), FirmwareVersion.class),

    /**
     * 라이브스트림 능력
     */
    LIVE_CAPACITY(Set.of("live_capacity"), RcLivestreamAbilityUpdate.class),

    /**
     * 제어 소스
     */
    CONTROL_SOURCE(Set.of("control_source"), RcDroneControlSource.class),

    /**
     * 라이브 상태
     */
    LIVE_STATUS(Set.of("live_status"), RcLiveStatus.class),

    /**
     * 페이로드 펌웨어
     */
    PAYLOAD_FIRMWARE(PayloadModelConst.getAllModelWithPosition(), PayloadFirmwareVersion.class),

    /**
     * 현재 커맨더 비행 모드
     */
    CURRENT_COMMANDER_FLIGHT_MODE(Set.of("current_commander_flight_mode"), DockDroneCurrentCommanderFlightMode.class),

    /** 동글 정보 */
    DONGLE_INFOS(Set.of("dongle_infos"), DongleInfos.class),
    ;

    /**
     * 상태 데이터 키들
     */
    private final Set<String> keys;

    /**
     * 해당 상태의 클래스 타입
     */
    private final Class classType;

    /**
     * RcStateDataKeyEnum 생성자
     *
     * @param keys      상태 데이터 키들
     * @param classType 클래스 타입
     */
    RcStateDataKeyEnum(Set<String> keys, Class classType) {
        this.keys = keys;
        this.classType = classType;
    }

    /**
     * 클래스 타입을 반환합니다.
     *
     * @return 클래스 타입
     */
    public Class getClassType() {
        return classType;
    }

    /**
     * 상태 데이터 키들을 반환합니다.
     *
     * @return 상태 데이터 키들
     */
    public Set<String> getKeys() {
        return keys;
    }

    /**
     * 키들로 해당하는 RcStateDataKeyEnum을 찾습니다.
     *
     * @param keys 상태 데이터 키들
     * @return 해당하는 RcStateDataKeyEnum
     * @throws CloudSDKException 해당하는 키를 찾을 수 없는 경우
     */
    public static RcStateDataKeyEnum find(Set<String> keys) {
        return Arrays.stream(values()).filter(keyEnum -> !Collections.disjoint(keys, keyEnum.keys)).findAny()
                .orElseThrow(() -> new CloudSDKException(RcStateDataKeyEnum.class, keys));
    }

}
