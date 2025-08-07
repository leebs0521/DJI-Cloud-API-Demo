package com.dji.sdk.cloudapi.hms;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * HMS 도크 커버 인덱스 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 HMS(Health Management System) 도크 커버 관련 인덱스를 정의합니다.
 * 좌측과 우측 도크 커버를 구분하며, 영어와 중국어로 각각의 위치를 표현합니다.
 * 
 * @author sean
 * @version 1.1
 * @date 2022/7/7
 */
public enum HmsDockCoverIndexEnum {

    /**
     * 좌측
     * 좌측 도크 커버 (0: left, 左)
     */
    LEFT(0, "left", "左"),

    /**
     * 우측
     * 우측 도크 커버 (1: right, 右)
     */
    RIGHT(1, "right", "右");

    /**
     * 도크 커버 인덱스 값
     * 각 도크 커버 위치에 대한 고유한 숫자 값
     */
    private final int val;

    /**
     * 영어 표현
     * 도크 커버 위치의 영어 표현
     */
    private final String en;

    /**
     * 중국어 표현
     * 도크 커버 위치의 중국어 표현
     */
    private final String zh;

    /**
     * HMS 도크 커버 인덱스 열거형 생성자
     * 
     * @param val 도크 커버 인덱스 값
     * @param en 영어 표현
     * @param zh 중국어 표현
     */
    HmsDockCoverIndexEnum(int val, String en, String zh) {
        this.val = val;
        this.en = en;
        this.zh = zh;
    }

    /**
     * 도크 커버 인덱스 값을 반환합니다.
     * JSON 직렬화 시 사용됩니다.
     * 
     * @return 도크 커버 인덱스 값
     */
    @JsonValue
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
     * 숫자로 HMS 도크 커버 인덱스를 찾습니다.
     * 
     * @param val 찾을 도크 커버 인덱스 값
     * @return 찾은 HMS 도크 커버 인덱스 열거형
     * @throws CloudSDKException 해당하는 값을 찾을 수 없는 경우
     */
    public static HmsDockCoverIndexEnum find(int val) {
        return Arrays.stream(HmsDockCoverIndexEnum.values()).filter(dockCover -> dockCover.val == val).findAny()
                .orElseThrow(() -> new CloudSDKException(HmsDockCoverIndexEnum.class, val));
    }
}
