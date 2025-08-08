package com.dji.sdk.cloudapi.media;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 스토리지 설정 조회 모듈 열거형
 * 
 * 이 열거형은 스토리지 설정을 조회할 때 사용하는 모듈 타입을 정의합니다.
 * 현재는 미디어 파일 관련 설정만 지원합니다.
 * 
 * 주요 구성 요소:
 * - MEDIA(0): 미디어 파일 스토리지 설정
 * 
 * 이 열거형은 클라우드 스토리지의 설정 정보를 조회할 때
 * 어떤 모듈의 설정을 가져올지 지정하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/27
 */
public enum StorageConfigGetModuleEnum {

    /**
     * 미디어 파일 스토리지 설정
     * 
     * 드론에서 촬영한 미디어 파일(사진, 비디오)의
     * 클라우드 스토리지 설정 정보를 조회합니다.
     */
    MEDIA(0);

    /**
     * 모듈 값
     * 
     * 각 모듈을 구분하는 정수 값입니다.
     */
    private final int module;

    StorageConfigGetModuleEnum(int module) {
        this.module = module;
    }

    /**
     * 모듈 값을 반환합니다.
     * 
     * @return 모듈 값 (정수)
     */
    @JsonValue
    public int getModule() {
        return module;
    }

    /**
     * 모듈 값으로 열거형을 찾습니다.
     * 
     * 주어진 모듈 값에 해당하는 열거형을 반환합니다.
     * 해당하는 모듈이 없으면 CloudSDKException을 발생시킵니다.
     * 
     * @param module 찾을 모듈 값
     * @return 해당하는 StorageConfigGetModuleEnum 열거형
     * @throws CloudSDKException 해당하는 모듈이 없을 경우
     */
    @JsonCreator
    public StorageConfigGetModuleEnum find(int module) {
        return Arrays.stream(values()).filter(moduleEnum -> moduleEnum.module == module).findAny()
                .orElseThrow(() -> new CloudSDKException(StorageConfigGetModuleEnum.class, module));
    }
}
