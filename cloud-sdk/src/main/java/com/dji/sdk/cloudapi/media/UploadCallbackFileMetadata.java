package com.dji.sdk.cloudapi.media;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * 업로드 콜백 파일 메타데이터 클래스
 * 
 * 이 클래스는 파일 업로드 콜백 시 사용되는 파일 메타데이터를 정의합니다.
 * 촬영 시점의 드론 위치, 고도, 짐벌 각도, 촬영 시간 등의 정보를 포함합니다.
 * 
 * 주요 구성 요소:
 * - absoluteAltitude: 절대 고도 (해수면 기준)
 * - createdTime: 미디어 파일 생성 시간
 * - gimbalYawDegree: 짐벌 요 각도
 * - shootPosition: 촬영 위치 (위도/경도)
 * - relativeAltitude: 상대 고도 (이륙점 기준)
 * 
 * 이 클래스는 업로드된 파일의 촬영 환경과 조건을 추적하는 데 사용됩니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/12/7
 */
@Schema(description = "media file metadata")
public class UploadCallbackFileMetadata {

    /**
     * 절대 고도
     * 
     * 해수면을 기준으로 한 드론의 절대 고도입니다.
     * 음수 값은 지하 또는 해수면 아래를 나타냅니다.
     * 단위: 미터 (m)
     */
    private Double absoluteAltitude;

    /**
     * 미디어 파일 생성 시간
     * 
     * 미디어 파일이 촬영된 정확한 시간입니다.
     * ISO 8601 형식으로 표현되며, 타임존 정보를 포함합니다.
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssVV")
    private LocalDateTime createdTime;

    /**
     * 짐벌 요 각도
     * 
     * 카메라 짐벌의 요(yaw) 회전 각도입니다.
     * 드론의 기수 방향을 기준으로 한 카메라의 좌우 회전 각도입니다.
     * 단위: 도 (degree)
     */
    private Double gimbalYawDegree;

    /**
     * 촬영 위치
     * 
     * 미디어 파일이 촬영된 지리적 위치 정보입니다.
     * 위도(latitude)와 경도(longitude) 정보를 포함합니다.
     */
    private Position shootPosition;

    /**
     * 상대 고도
     * 
     * 이륙점을 기준으로 한 드론의 상대 고도입니다.
     * 이륙점에서 얼마나 높이 올라갔는지를 나타냅니다.
     * 단위: 미터 (m)
     */
    private Double relativeAltitude;

    public UploadCallbackFileMetadata() {
    }

    @Override
    public String toString() {
        return "MediaFileMetadata{" +
                "absoluteAltitude=" + absoluteAltitude +
                ", createdTime=" + createdTime +
                ", gimbalYawDegree=" + gimbalYawDegree +
                ", shootPosition=" + shootPosition +
                ", relativeAltitude=" + relativeAltitude +
                '}';
    }

    /**
     * 절대 고도를 반환합니다.
     * 
     * @return 절대 고도 (미터)
     */
    public Double getAbsoluteAltitude() {
        return absoluteAltitude;
    }

    /**
     * 절대 고도를 설정합니다.
     * 
     * @param absoluteAltitude 절대 고도 (미터)
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public UploadCallbackFileMetadata setAbsoluteAltitude(Double absoluteAltitude) {
        this.absoluteAltitude = absoluteAltitude;
        return this;
    }

    /**
     * 미디어 파일 생성 시간을 반환합니다.
     * 
     * @return 미디어 파일 생성 시간
     */
    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    /**
     * 미디어 파일 생성 시간을 설정합니다.
     * 
     * @param createdTime 미디어 파일 생성 시간
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public UploadCallbackFileMetadata setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    /**
     * 짐벌 요 각도를 반환합니다.
     * 
     * @return 짐벌 요 각도 (도)
     */
    public Double getGimbalYawDegree() {
        return gimbalYawDegree;
    }

    /**
     * 짐벌 요 각도를 설정합니다.
     * 
     * @param gimbalYawDegree 짐벌 요 각도 (도)
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public UploadCallbackFileMetadata setGimbalYawDegree(Double gimbalYawDegree) {
        this.gimbalYawDegree = gimbalYawDegree;
        return this;
    }

    /**
     * 촬영 위치를 반환합니다.
     * 
     * @return 촬영 위치 (Position 객체)
     */
    public Position getShootPosition() {
        return shootPosition;
    }

    /**
     * 촬영 위치를 설정합니다.
     * 
     * @param shootPosition 촬영 위치 (Position 객체)
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public UploadCallbackFileMetadata setShootPosition(Position shootPosition) {
        this.shootPosition = shootPosition;
        return this;
    }

    /**
     * 상대 고도를 반환합니다.
     * 
     * @return 상대 고도 (미터)
     */
    public Double getRelativeAltitude() {
        return relativeAltitude;
    }

    /**
     * 상대 고도를 설정합니다.
     * 
     * @param relativeAltitude 상대 고도 (미터)
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public UploadCallbackFileMetadata setRelativeAltitude(Double relativeAltitude) {
        this.relativeAltitude = relativeAltitude;
        return this;
    }
}
