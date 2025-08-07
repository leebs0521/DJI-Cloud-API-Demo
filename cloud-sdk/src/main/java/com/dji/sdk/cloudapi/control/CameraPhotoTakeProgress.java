package com.dji.sdk.cloudapi.control;

import com.dji.sdk.cloudapi.wayline.FlighttaskStatusEnum;

/**
 * 카메라 사진 촬영 진행 상황 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 카메라 사진 촬영의 진행 상황을 담는 클래스입니다.
 * 확장 정보, 진행 데이터, 상태 등을 포함합니다.
 * 
 * @author sean
 * @version 1.9
 * @date 2023/12/12
 */
public class CameraPhotoTakeProgress {

    /**
     * 확장 정보
     * 사진 촬영 진행 상황의 확장 정보
     */
    private PhotoTakeProgressExt ext;

    /**
     * 진행 데이터
     * 사진 촬영 진행 상황의 데이터
     */
    private PhotoTakeProgressData progress;

    /**
     * 상태
     * 사진 촬영의 현재 상태
     */
    private FlighttaskStatusEnum status;

    /**
     * 기본 생성자
     */
    public CameraPhotoTakeProgress() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "CameraPhotoTakeProgress{" +
                "ext=" + ext +
                ", progress=" + progress +
                ", status=" + status +
                '}';
    }

    /**
     * 확장 정보를 반환합니다.
     * 
     * @return 확장 정보
     */
    public PhotoTakeProgressExt getExt() {
        return ext;
    }

    /**
     * 확장 정보를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param ext 설정할 확장 정보
     * @return 현재 CameraPhotoTakeProgress 객체
     */
    public CameraPhotoTakeProgress setExt(PhotoTakeProgressExt ext) {
        this.ext = ext;
        return this;
    }

    /**
     * 진행 데이터를 반환합니다.
     * 
     * @return 진행 데이터
     */
    public PhotoTakeProgressData getProgress() {
        return progress;
    }

    /**
     * 진행 데이터를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param progress 설정할 진행 데이터
     * @return 현재 CameraPhotoTakeProgress 객체
     */
    public CameraPhotoTakeProgress setProgress(PhotoTakeProgressData progress) {
        this.progress = progress;
        return this;
    }

    /**
     * 상태를 반환합니다.
     * 
     * @return 상태
     */
    public FlighttaskStatusEnum getStatus() {
        return status;
    }

    /**
     * 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param status 설정할 상태
     * @return 현재 CameraPhotoTakeProgress 객체
     */
    public CameraPhotoTakeProgress setStatus(FlighttaskStatusEnum status) {
        this.status = status;
        return this;
    }
}
