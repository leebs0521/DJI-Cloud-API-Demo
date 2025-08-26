package com.dji.sdk.cloudapi.livestream;

import com.dji.sdk.cloudapi.device.VideoId;
import javax.validation.constraints.NotNull;

/**
 * 라이브스트림 카메라 변경 요청을 나타내는 클래스
 * 라이브스트림 중 카메라를 전환하기 위한 요청 정보를 관리합니다.
 */
public class LiveCameraChangeRequest {

    /**
     * 카메라의 물리적 위치
     * INSIDE: 장비 내부에 설치된 카메라
     * OUTSIDE: 장비 외부에 설치된 카메라
     */
    @NotNull
    private CameraPositionEnum cameraPosition;

    /**
     * 비디오 ID
     * 형식: #{uav_sn}/#{camera_id}/#{video_index}
     * - uav_sn: 드론 시리얼 번호
     * - camera_id: 페이로드 및 장착 위치 열거형 값
     * - video_index: 페이로드 렌즈 번호
     */
    @NotNull
    private VideoId videoId;

    /**
     * 기본 생성자
     */
    public LiveCameraChangeRequest() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return "LiveCameraChangeRequest{cameraPosition=위치, videoId=ID}" 형식의 문자열
     */
    @Override
    public String toString() {
        return "LiveCameraChangeRequest{" +
                "cameraPosition=" + cameraPosition +
                ", videoId=" + videoId +
                '}';
    }

    /**
     * 카메라 위치를 반환합니다.
     * 
     * @return 설정된 카메라 위치 열거형 값
     */
    public CameraPositionEnum getCameraPosition() {
        return cameraPosition;
    }

    /**
     * 카메라 위치를 설정합니다.
     * 
     * @param cameraPosition 설정할 카메라 위치 열거형 값
     */
    public LiveCameraChangeRequest setCameraPosition(CameraPositionEnum cameraPosition) {
        this.cameraPosition = cameraPosition;
        return this;
    }

    public VideoId getVideoId() {
        return videoId;
    }

    public LiveCameraChangeRequest setVideoId(VideoId videoId) {
        this.videoId = videoId;
        return this;
    }
}
