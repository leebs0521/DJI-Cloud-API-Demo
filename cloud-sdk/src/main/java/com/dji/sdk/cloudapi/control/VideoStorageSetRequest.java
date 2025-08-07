package com.dji.sdk.cloudapi.control;

import com.dji.sdk.cloudapi.device.PayloadIndex;
import com.dji.sdk.common.BaseModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 비디오 저장 설정 요청 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 카메라의 비디오 저장 방식을 설정하기 위한 요청을 정의합니다.
 * 특정 페이로드 인덱스와 비디오 저장 설정을 지정하여 카메라의 저장 방식을 설정합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/12
 */
public class VideoStorageSetRequest extends BaseModel {

    /**
     * 페이로드 인덱스 (필수)
     * 저장 설정을 변경할 카메라의 페이로드 인덱스
     * 비공식 디바이스 모드 키로, 형식은 "타입-서브타입-짐벌인덱스"입니다.
     * 지원 제품은 [Product Supported](https://developer.dji.com/doc/cloud-api-tutorial/en/overview/product-support.html)를 참조하세요.
     */
    @NotNull
    private PayloadIndex payloadIndex;

    /**
     * 비디오 저장 설정 목록 (필수)
     * 비디오 저장 방식을 다중 선택으로 설정
     * 최소 1개 이상의 설정이 필요합니다.
     */
    @NotNull
    @Size(min = 1)
    private List<LensStorageSettingsEnum> videoStorageSettings;

    /**
     * 기본 생성자
     */
    public VideoStorageSetRequest() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "VideoStorageSetRequest{" +
                "payloadIndex=" + payloadIndex +
                ", videoStorageSettings=" + videoStorageSettings +
                '}';
    }

    /**
     * 페이로드 인덱스를 반환합니다.
     * 
     * @return 페이로드 인덱스
     */
    public PayloadIndex getPayloadIndex() {
        return payloadIndex;
    }

    /**
     * 페이로드 인덱스를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param payloadIndex 설정할 페이로드 인덱스
     * @return 현재 VideoStorageSetRequest 객체
     */
    public VideoStorageSetRequest setPayloadIndex(PayloadIndex payloadIndex) {
        this.payloadIndex = payloadIndex;
        return this;
    }

    /**
     * 비디오 저장 설정 목록을 반환합니다.
     * 
     * @return 비디오 저장 설정 목록
     */
    public List<LensStorageSettingsEnum> getVideoStorageSettings() {
        return videoStorageSettings;
    }

    /**
     * 비디오 저장 설정 목록을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param videoStorageSettings 설정할 비디오 저장 설정 목록
     * @return 현재 VideoStorageSetRequest 객체
     */
    public VideoStorageSetRequest setVideoStorageSettings(List<LensStorageSettingsEnum> videoStorageSettings) {
        this.videoStorageSettings = videoStorageSettings;
        return this;
    }
}
