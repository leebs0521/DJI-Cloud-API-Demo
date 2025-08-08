package com.dji.sdk.cloudapi.media;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Arrays;

/**
 * 미디어 서브 파일 타입 열거형
 * 
 * 이 열거형은 미디어 파일의 서브 타입을 정의합니다.
 * 일반 사진과 파노라마 사진을 구분하는 데 사용됩니다.
 * 
 * 주요 구성 요소:
 * - NORMAL(0): 일반 사진
 * - PANORAMA(1): 파노라마 사진
 * 
 * 이 열거형은 미디어 파일의 특성을 식별하고 분류하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/16
 */
@Schema(description = "The type of image file. <br /><p>0: normal picture; <p/><p>1: panorama.</p>")
public enum MediaSubFileTypeEnum {

    /**
     * 일반 사진
     * 
     * 드론에서 촬영한 일반적인 사진 파일입니다.
     * 단일 프레임으로 촬영된 정적 이미지입니다.
     */
    NORMAL(0),

    /**
     * 파노라마 사진
     * 
     * 드론에서 촬영한 파노라마 사진 파일입니다.
     * 여러 각도에서 촬영된 이미지를 합성한 넓은 시야각의 사진입니다.
     */
    PANORAMA(1);

    /**
     * 파일 타입 값
     * 
     * 각 파일 타입을 구분하는 정수 값입니다.
     */
    private final int type;

    MediaSubFileTypeEnum(int type) {
        this.type = type;
    }

    /**
     * 파일 타입 값을 반환합니다.
     * 
     * @return 파일 타입 값 (정수)
     */
    @JsonValue
    public int getType() {
        return type;
    }

    /**
     * 타입 값으로 열거형을 찾습니다.
     * 
     * 주어진 타입 값에 해당하는 열거형을 반환합니다.
     * 해당하는 타입이 없으면 CloudSDKException을 발생시킵니다.
     * 
     * @param type 찾을 파일 타입 값
     * @return 해당하는 MediaSubFileTypeEnum 열거형
     * @throws CloudSDKException 해당하는 타입이 없을 경우
     */
    @JsonCreator
    public static MediaSubFileTypeEnum find(int type) {
        return Arrays.stream(values()).filter(subFile -> subFile.type == type).findAny()
                .orElseThrow(() -> new CloudSDKException(MediaSubFileTypeEnum.class, type));
    }
}
