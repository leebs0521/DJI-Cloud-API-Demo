package com.dji.sdk.cloudapi.debug;

/**
 * 원격 디버그 진행 상황 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 원격 디버그의 진행 상황을 담는 클래스입니다.
 * 디버그 상태와 진행 데이터를 포함하여 원격 디버그의 진행 상황을 관리합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/7/29
 */
public class RemoteDebugProgress {

    /**
     * 원격 디버그 상태
     * 현재 원격 디버그의 상태 정보
     */
    private RemoteDebugStatusEnum status;

    /**
     * 원격 디버그 진행 데이터
     * 원격 디버그의 진행 상황 데이터
     */
    private RemoteDebugProgressData progress;

    /**
     * 기본 생성자
     */
    public RemoteDebugProgress() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "RemoteDebugProgress{" +
                "status=" + status +
                ", progress=" + progress +
                '}';
    }

    /**
     * 원격 디버그 상태를 반환합니다.
     * 
     * @return 원격 디버그 상태
     */
    public RemoteDebugStatusEnum getStatus() {
        return status;
    }

    /**
     * 원격 디버그 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param status 설정할 원격 디버그 상태
     * @return 현재 RemoteDebugProgress 객체
     */
    public RemoteDebugProgress setStatus(RemoteDebugStatusEnum status) {
        this.status = status;
        return this;
    }

    /**
     * 원격 디버그 진행 데이터를 반환합니다.
     * 
     * @return 원격 디버그 진행 데이터
     */
    public RemoteDebugProgressData getProgress() {
        return progress;
    }

    /**
     * 원격 디버그 진행 데이터를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param progress 설정할 원격 디버그 진행 데이터
     * @return 현재 RemoteDebugProgress 객체
     */
    public RemoteDebugProgress setProgress(RemoteDebugProgressData progress) {
        this.progress = progress;
        return this;
    }
}
