package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 열화상 팔레트 스타일 열거형 클래스
 * 
 * 이 클래스는 열화상 카메라의 색상 팔레트 스타일을 정의합니다.
 * 다양한 색상 조합을 통해 열화상 이미지의 시각적 표현을 다양화합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/30
 */
public enum ThermalPaletteStyleEnum {

    /**
     * 화이트 핫 (정수값: 0)
     */
    WHITE_HOT(0),

    /**
     * 블랙 핫 (정수값: 1)
     */
    BLACK_HOT(1),

    /**
     * 레드 핫 (정수값: 2)
     */
    RED_HOT(2),

    /**
     * 그린 핫 (정수값: 3)
     */
    GREEN_HOT(3),

    /**
     * 퓨전 (정수값: 4)
     */
    FUSION(4),

    /**
     * 레인보우 (정수값: 5)
     */
    RAINBOW(5),

    /**
     * 아이언보우1 (정수값: 6)
     */
    IRONBOW1(6),

    /**
     * 아이언보우2 (정수값: 7)
     */
    IRONBOW2(7),

    /**
     * 아이스 파이어 (정수값: 8)
     */
    ICE_FIRE(8),

    /**
     * 세피아 (정수값: 9)
     */
    SEPIA(9),

    /**
     * 글로우보우 (정수값: 10)
     */
    GLOWBOW(10),

    /**
     * 컬러1 (정수값: 11)
     */
    COLOR1(11),

    /**
     * 컬러2 (정수값: 12)
     */
    COLOR2(12),

    /**
     * 레인 (정수값: 13)
     */
    RAIN(13),

    /**
     * 핫 스팟 (정수값: 14)
     */
    HOT_SPOT(14),

    /**
     * 레인보우2 (정수값: 15)
     */
    RAINBOW2(15),

    /**
     * 그레이 (정수값: 16)
     */
    GRAY(16),

    /**
     * 메탈 (정수값: 17)
     */
    METAL(17),

    /**
     * 콜드 스팟 (정수값: 18)
     */
    COLD_SPOT(18),
    ;

    /**
     * 팔레트 스타일 정수값
     */
    private final int style;

    /**
     * 열화상 팔레트 스타일 열거형 생성자
     * 
     * @param style 팔레트 스타일 정수값
     */
    ThermalPaletteStyleEnum(int style) {
        this.style = style;
    }

    /**
     * 팔레트 스타일 정수값을 반환합니다.
     * 
     * @return 팔레트 스타일 정수값
     */
    @JsonValue
    public int getStyle() {
        return style;
    }

    /**
     * 정수값으로 열화상 팔레트 스타일을 찾습니다.
     * 
     * @param style 찾을 팔레트 스타일 정수값
     * @return 찾은 열화상 팔레트 스타일 열거형
     * @throws CloudSDKException 해당하는 팔레트 스타일을 찾을 수 없는 경우
     */
    @JsonCreator
    public static ThermalPaletteStyleEnum find(int style) {
        return Arrays.stream(values()).filter(styleEnum -> styleEnum.style == style).findAny()
            .orElseThrow(() -> new CloudSDKException(ThermalPaletteStyleEnum.class, style));
    }

}
