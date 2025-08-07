package com.dji.sdk.cloudapi.hms;

import com.dji.sdk.exception.CloudSDKException;

import java.util.Arrays;

/**
 * HMS 충전 로드 인덱스 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 HMS(Health Management System) 충전 로드 관련 인덱스를 정의합니다.
 * 전면, 후면, 좌측, 우측 네 방향의 충전 로드를 구분하며, 영어와 중국어로 각각의 위치를 표현합니다.
 * 
 * @author sean
 * @version 1.1
 * @date 2022/7/7
 */
public enum HmsChargingRodIndexEnum {

    /**
     * 전면
     * 전면 충전 로드 (0: front, 前)
     */
    FRONT(0, "front", "前"),

    /**
     * 후면
     * 후면 충전 로드 (1: back, 后)
     */
    BACK(1, "back", "后"),

    /**
     * 좌측
     * 좌측 충전 로드 (2: left, 左)
     */
    LEFT(2, "left", "左"),

    /**
     * 우측
     * 우측 충전 로드 (3: right, 右)
     */
    RIGHT(3, "right", "右");

    /**
     * 충전 로드 인덱스 값
     * 각 충전 로드 위치에 대한 고유한 숫자 값
     */
    private final int val;

    /**
     * 영어 표현
     * 충전 로드 위치의 영어 표현
     */
    private final String en;

    /**
     * 중국어 표현
     * 충전 로드 위치의 중국어 표현
     */
    private final String zh;

    /**
     * HMS 충전 로드 인덱스 열거형 생성자
     * 
     * @param val 충전 로드 인덱스 값
     * @param en 영어 표현
     * @param zh 중국어 표현
     */
    HmsChargingRodIndexEnum(int val, String en, String zh) {
        this.val = val;
        this.en = en;
        this.zh = zh;
    }

    /**
     * 충전 로드 인덱스 값을 반환합니다.
     * 
     * @return 충전 로드 인덱스 값
     */
    public int getVal() {
        return val;
    }

    /**
     * 영어 표현을 반환합니다.
     * 
     * @return 영어 표현
     */
    public String getEn() {
        return en;
    }

    /**
     * 중국어 표현을 반환합니다.
     * 
     * @return 중국어 표현
     */
    public String getZh() {
        return zh;
    }

    /**
     * 숫자로 HMS 충전 로드 인덱스를 찾습니다.
     * 
     * @param val 찾을 충전 로드 인덱스 값
     * @return 찾은 HMS 충전 로드 인덱스 열거형
     * @throws CloudSDKException 해당하는 값을 찾을 수 없는 경우
     */
    public static HmsChargingRodIndexEnum find(int val) {
        return Arrays.stream(HmsChargingRodIndexEnum.values()).filter(rod -> rod.val == val).findAny()
                .orElseThrow(() -> new CloudSDKException(HmsChargingRodIndexEnum.class, val));
    }
}
