package com.dji.sdk.cloudapi.device;

import com.dji.sdk.cloudapi.livestream.VideoTypeEnum;
import com.dji.sdk.exception.CloudSDKErrorEnum;
import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Objects;

/**
 * 비디오 ID 클래스
 * 
 * 이 클래스는 비디오 스트림을 식별하기 위한 고유 ID를 관리합니다.
 * 드론 시리얼 번호, 페이로드 인덱스, 비디오 타입을 조합하여 비디오를 식별합니다.
 * 형식: "드론SN/페이로드인덱스/비디오타입-0"
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/25
 */
public class VideoId {

    /**
     * 드론 시리얼 번호
     */
    @NotNull
    private String droneSn;

    /**
     * 페이로드 인덱스
     */
    @NotNull
    private PayloadIndex payloadIndex;

    /**
     * 비디오 타입 (기본값: NORMAL)
     */
    @NotNull
    private VideoTypeEnum videoType = VideoTypeEnum.NORMAL;

    /**
     * 기본 생성자
     */
    public VideoId() {
    }

    /**
     * 비디오 ID 문자열로부터 VideoId 객체를 생성합니다.
     * 
     * @param videoId "드론SN/페이로드인덱스/비디오타입-0" 형식의 비디오 ID 문자열
     * @throws CloudSDKException 비디오 ID 형식이 올바르지 않은 경우
     */
    @JsonCreator
    public VideoId(String videoId) {
        if (!StringUtils.hasText(videoId)) {
            return;
        }
        String[] videoIdArr = Arrays.stream(videoId.split("/")).toArray(String[]::new);
        if (videoIdArr.length != 3) {
            throw new CloudSDKException(CloudSDKErrorEnum.INVALID_PARAMETER);
        }
        this.droneSn = videoIdArr[0];
        this.payloadIndex = new PayloadIndex(videoIdArr[1]);
        this.videoType = VideoTypeEnum.find(videoIdArr[2].split("-")[0]);
    }

    /**
     * 비디오 ID를 문자열로 반환합니다.
     * 
     * @return "드론SN/페이로드인덱스/비디오타입-0" 형식의 비디오 ID 문자열
     */
    @Override
    @JsonValue
    public String toString() {
        if (Objects.isNull(payloadIndex)) {
            return "";
        }
        return String.format("%s/%s/%s-0", droneSn, payloadIndex.toString(), videoType.getType());
    }

    /**
     * 드론 시리얼 번호를 반환합니다.
     * 
     * @return 드론 시리얼 번호
     */
    public String getDroneSn() {
        return droneSn;
    }

    /**
     * 드론 시리얼 번호를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param droneSn 설정할 드론 시리얼 번호
     * @return 현재 VideoId 객체
     */
    public VideoId setDroneSn(String droneSn) {
        this.droneSn = droneSn;
        return this;
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
     * @return 현재 VideoId 객체
     */
    public VideoId setPayloadIndex(PayloadIndex payloadIndex) {
        this.payloadIndex = payloadIndex;
        return this;
    }

    /**
     * 비디오 타입을 반환합니다.
     * 
     * @return 비디오 타입
     */
    public VideoTypeEnum getVideoType() {
        return videoType;
    }

    /**
     * 비디오 타입을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param videoType 설정할 비디오 타입
     * @return 현재 VideoId 객체
     */
    public VideoId setVideoType(VideoTypeEnum videoType) {
        this.videoType = videoType;
        return this;
    }
}
