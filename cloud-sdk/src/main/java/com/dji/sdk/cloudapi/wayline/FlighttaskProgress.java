package com.dji.sdk.cloudapi.wayline;

/**
 * 비행 작업 진행 상황 클래스
 * 
 * 이 클래스는 웨이라인 비행 작업의 진행 상황을 나타내는 데이터를 정의합니다.
 * 작업의 현재 상태, 진행률, 확장 정보 등을 포함하여
 * 실시간으로 비행 작업의 진행 상황을 모니터링할 수 있습니다.
 * 
 * 주요 구성 요소:
 * - ext: 확장 정보
 * - progress: 진행률 데이터
 * - status: 작업 상태
 * 
 * 이 클래스는 웨이라인 비행 작업의 진행 상황을
 * 실시간으로 추적하고 보고하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.1
 * @date 2022/6/9
 */
public class FlighttaskProgress {

    /**
     * 확장 정보
     * 
     * 비행 작업 진행 상황의 추가 정보를 포함합니다.
     * 미디어 파일 수, 중단점 정보 등의 상세한 정보를 제공합니다.
     */
    private FlighttaskProgressExt ext;

    /**
     * 진행률 데이터
     * 
     * 비행 작업의 현재 진행률 정보를 포함합니다.
     * 완료된 웨이포인트 수, 전체 웨이포인트 수 등의 정보를 제공합니다.
     */
    private FlighttaskProgressData progress;

    /**
     * 작업 상태
     * 
     * 비행 작업의 현재 상태를 나타냅니다.
     * 준비 중, 실행 중, 완료, 실패 등의 상태를 구분합니다.
     */
    private FlighttaskStatusEnum status;

    public FlighttaskProgress() {
    }

    @Override
    public String toString() {
        return "FlighttaskProgress{" +
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
    public FlighttaskProgressExt getExt() {
        return ext;
    }

    /**
     * 확장 정보를 설정합니다.
     * 
     * @param ext 확장 정보
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FlighttaskProgress setExt(FlighttaskProgressExt ext) {
        this.ext = ext;
        return this;
    }

    /**
     * 진행률 데이터를 반환합니다.
     * 
     * @return 진행률 데이터
     */
    public FlighttaskProgressData getProgress() {
        return progress;
    }

    /**
     * 진행률 데이터를 설정합니다.
     * 
     * @param progress 진행률 데이터
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FlighttaskProgress setProgress(FlighttaskProgressData progress) {
        this.progress = progress;
        return this;
    }

    /**
     * 작업 상태를 반환합니다.
     * 
     * @return 작업 상태
     */
    public FlighttaskStatusEnum getStatus() {
        return status;
    }

    /**
     * 작업 상태를 설정합니다.
     * 
     * @param status 작업 상태
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FlighttaskProgress setStatus(FlighttaskStatusEnum status) {
        this.status = status;
        return this;
    }
}
