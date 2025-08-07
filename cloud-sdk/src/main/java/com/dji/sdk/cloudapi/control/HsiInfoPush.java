package com.dji.sdk.cloudapi.control;

import java.util.List;

/**
 * HSI 정보 푸시 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 HSI(Horizontal Situation Indicator) 정보를 푸시하기 위한 클래스입니다.
 * 드론의 거리 정보, 센서 활성화 상태, 작업 상태 등을 포함하여 HSI 정보를 전송합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/29
 */
public class HsiInfoPush {

    /**
     * 상방 거리
     * 드론 상방의 거리 (센티미터)
     */
    private Integer upDistance;

    /**
     * 하방 거리
     * 드론 하방의 거리 (센티미터)
     */
    private Integer downDistance;

    /**
     * 주변 거리 목록
     * 드론 주변의 거리 정보 목록 (센티미터)
     */
    private List<Integer> aroundDistance;

    /**
     * 상방 센서 활성화
     * 상방 센서의 활성화 여부
     */
    private Boolean upEnable;

    /**
     * 상방 센서 작업 상태
     * 상방 센서의 작업 상태
     */
    private Boolean upWork;

    /**
     * 하방 센서 활성화
     * 하방 센서의 활성화 여부
     */
    private Boolean downEnable;

    /**
     * 하방 센서 작업 상태
     * 하방 센서의 작업 상태
     */
    private Boolean downWork;

    /**
     * 좌측 센서 활성화
     * 좌측 센서의 활성화 여부
     */
    private Boolean leftEnable;

    /**
     * 좌측 센서 작업 상태
     * 좌측 센서의 작업 상태
     */
    private Boolean leftWork;

    /**
     * 우측 센서 활성화
     * 우측 센서의 활성화 여부
     */
    private Boolean rightEnable;

    /**
     * 우측 센서 작업 상태
     * 우측 센서의 작업 상태
     */
    private Boolean rightWork;

    /**
     * 전방 센서 활성화
     * 전방 센서의 활성화 여부
     */
    private Boolean frontEnable;

    /**
     * 전방 센서 작업 상태
     * 전방 센서의 작업 상태
     */
    private Boolean frontWork;

    /**
     * 후방 센서 활성화
     * 후방 센서의 활성화 여부
     */
    private Boolean backEnable;

    /**
     * 후방 센서 작업 상태
     * 후방 센서의 작업 상태
     */
    private Boolean backWork;

    /**
     * 수직 센서 활성화
     * 수직 센서의 활성화 여부
     */
    private Boolean verticalEnable;

    /**
     * 수직 센서 작업 상태
     * 수직 센서의 작업 상태
     */
    private Boolean verticalWork;

    /**
     * 수평 센서 활성화
     * 수평 센서의 활성화 여부
     */
    private Boolean horizontalEnable;

    /**
     * 수평 센서 작업 상태
     * 수평 센서의 작업 상태
     */
    private Boolean horizontalWork;

    /**
     * 기본 생성자
     */
    public HsiInfoPush() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "HsiInfoPush{" +
                "upDistance=" + upDistance +
                ", downDistance=" + downDistance +
                ", aroundDistance=" + aroundDistance +
                ", upEnable=" + upEnable +
                ", upWork=" + upWork +
                ", downEnable=" + downEnable +
                ", downWork=" + downWork +
                ", leftEnable=" + leftEnable +
                ", leftWork=" + leftWork +
                ", rightEnable=" + rightEnable +
                ", rightWork=" + rightWork +
                ", frontEnable=" + frontEnable +
                ", frontWork=" + frontWork +
                ", backEnable=" + backEnable +
                ", backWork=" + backWork +
                ", verticalEnable=" + verticalEnable +
                ", verticalWork=" + verticalWork +
                ", horizontalEnable=" + horizontalEnable +
                ", horizontalWork=" + horizontalWork +
                '}';
    }

    /**
     * 상방 거리를 반환합니다.
     * 
     * @return 상방 거리 (센티미터)
     */
    public Integer getUpDistance() {
        return upDistance;
    }

    /**
     * 상방 거리를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param upDistance 설정할 상방 거리 (센티미터)
     * @return 현재 HsiInfoPush 객체
     */
    public HsiInfoPush setUpDistance(Integer upDistance) {
        this.upDistance = upDistance;
        return this;
    }

    /**
     * 하방 거리를 반환합니다.
     * 
     * @return 하방 거리 (센티미터)
     */
    public Integer getDownDistance() {
        return downDistance;
    }

    /**
     * 하방 거리를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param downDistance 설정할 하방 거리 (센티미터)
     * @return 현재 HsiInfoPush 객체
     */
    public HsiInfoPush setDownDistance(Integer downDistance) {
        this.downDistance = downDistance;
        return this;
    }

    /**
     * 주변 거리 목록을 반환합니다.
     * 
     * @return 주변 거리 목록 (센티미터)
     */
    public List<Integer> getAroundDistance() {
        return aroundDistance;
    }

    /**
     * 주변 거리 목록을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param aroundDistance 설정할 주변 거리 목록 (센티미터)
     * @return 현재 HsiInfoPush 객체
     */
    public HsiInfoPush setAroundDistance(List<Integer> aroundDistance) {
        this.aroundDistance = aroundDistance;
        return this;
    }

    /**
     * 상방 센서 활성화 여부를 반환합니다.
     * 
     * @return 상방 센서 활성화 여부
     */
    public Boolean getUpEnable() {
        return upEnable;
    }

    /**
     * 상방 센서 활성화 여부를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param upEnable 설정할 상방 센서 활성화 여부
     * @return 현재 HsiInfoPush 객체
     */
    public HsiInfoPush setUpEnable(Boolean upEnable) {
        this.upEnable = upEnable;
        return this;
    }

    /**
     * 상방 센서 작업 상태를 반환합니다.
     * 
     * @return 상방 센서 작업 상태
     */
    public Boolean getUpWork() {
        return upWork;
    }

    /**
     * 상방 센서 작업 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param upWork 설정할 상방 센서 작업 상태
     * @return 현재 HsiInfoPush 객체
     */
    public HsiInfoPush setUpWork(Boolean upWork) {
        this.upWork = upWork;
        return this;
    }

    /**
     * 하방 센서 활성화 여부를 반환합니다.
     * 
     * @return 하방 센서 활성화 여부
     */
    public Boolean getDownEnable() {
        return downEnable;
    }

    /**
     * 하방 센서 활성화 여부를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param downEnable 설정할 하방 센서 활성화 여부
     * @return 현재 HsiInfoPush 객체
     */
    public HsiInfoPush setDownEnable(Boolean downEnable) {
        this.downEnable = downEnable;
        return this;
    }

    /**
     * 하방 센서 작업 상태를 반환합니다.
     * 
     * @return 하방 센서 작업 상태
     */
    public Boolean getDownWork() {
        return downWork;
    }

    /**
     * 하방 센서 작업 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param downWork 설정할 하방 센서 작업 상태
     * @return 현재 HsiInfoPush 객체
     */
    public HsiInfoPush setDownWork(Boolean downWork) {
        this.downWork = downWork;
        return this;
    }

    /**
     * 좌측 센서 활성화 여부를 반환합니다.
     * 
     * @return 좌측 센서 활성화 여부
     */
    public Boolean getLeftEnable() {
        return leftEnable;
    }

    /**
     * 좌측 센서 활성화 여부를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param leftEnable 설정할 좌측 센서 활성화 여부
     * @return 현재 HsiInfoPush 객체
     */
    public HsiInfoPush setLeftEnable(Boolean leftEnable) {
        this.leftEnable = leftEnable;
        return this;
    }

    /**
     * 좌측 센서 작업 상태를 반환합니다.
     * 
     * @return 좌측 센서 작업 상태
     */
    public Boolean getLeftWork() {
        return leftWork;
    }

    /**
     * 좌측 센서 작업 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param leftWork 설정할 좌측 센서 작업 상태
     * @return 현재 HsiInfoPush 객체
     */
    public HsiInfoPush setLeftWork(Boolean leftWork) {
        this.leftWork = leftWork;
        return this;
    }

    /**
     * 우측 센서 활성화 여부를 반환합니다.
     * 
     * @return 우측 센서 활성화 여부
     */
    public Boolean getRightEnable() {
        return rightEnable;
    }

    /**
     * 우측 센서 활성화 여부를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param rightEnable 설정할 우측 센서 활성화 여부
     * @return 현재 HsiInfoPush 객체
     */
    public HsiInfoPush setRightEnable(Boolean rightEnable) {
        this.rightEnable = rightEnable;
        return this;
    }

    /**
     * 우측 센서 작업 상태를 반환합니다.
     * 
     * @return 우측 센서 작업 상태
     */
    public Boolean getRightWork() {
        return rightWork;
    }

    /**
     * 우측 센서 작업 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param rightWork 설정할 우측 센서 작업 상태
     * @return 현재 HsiInfoPush 객체
     */
    public HsiInfoPush setRightWork(Boolean rightWork) {
        this.rightWork = rightWork;
        return this;
    }

    /**
     * 전방 센서 활성화 여부를 반환합니다.
     * 
     * @return 전방 센서 활성화 여부
     */
    public Boolean getFrontEnable() {
        return frontEnable;
    }

    /**
     * 전방 센서 활성화 여부를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param frontEnable 설정할 전방 센서 활성화 여부
     * @return 현재 HsiInfoPush 객체
     */
    public HsiInfoPush setFrontEnable(Boolean frontEnable) {
        this.frontEnable = frontEnable;
        return this;
    }

    /**
     * 전방 센서 작업 상태를 반환합니다.
     * 
     * @return 전방 센서 작업 상태
     */
    public Boolean getFrontWork() {
        return frontWork;
    }

    /**
     * 전방 센서 작업 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param frontWork 설정할 전방 센서 작업 상태
     * @return 현재 HsiInfoPush 객체
     */
    public HsiInfoPush setFrontWork(Boolean frontWork) {
        this.frontWork = frontWork;
        return this;
    }

    /**
     * 후방 센서 활성화 여부를 반환합니다.
     * 
     * @return 후방 센서 활성화 여부
     */
    public Boolean getBackEnable() {
        return backEnable;
    }

    /**
     * 후방 센서 활성화 여부를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param backEnable 설정할 후방 센서 활성화 여부
     * @return 현재 HsiInfoPush 객체
     */
    public HsiInfoPush setBackEnable(Boolean backEnable) {
        this.backEnable = backEnable;
        return this;
    }

    /**
     * 후방 센서 작업 상태를 반환합니다.
     * 
     * @return 후방 센서 작업 상태
     */
    public Boolean getBackWork() {
        return backWork;
    }

    /**
     * 후방 센서 작업 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param backWork 설정할 후방 센서 작업 상태
     * @return 현재 HsiInfoPush 객체
     */
    public HsiInfoPush setBackWork(Boolean backWork) {
        this.backWork = backWork;
        return this;
    }

    /**
     * 수직 센서 활성화 여부를 반환합니다.
     * 
     * @return 수직 센서 활성화 여부
     */
    public Boolean getVerticalEnable() {
        return verticalEnable;
    }

    /**
     * 수직 센서 활성화 여부를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param verticalEnable 설정할 수직 센서 활성화 여부
     * @return 현재 HsiInfoPush 객체
     */
    public HsiInfoPush setVerticalEnable(Boolean verticalEnable) {
        this.verticalEnable = verticalEnable;
        return this;
    }

    /**
     * 수직 센서 작업 상태를 반환합니다.
     * 
     * @return 수직 센서 작업 상태
     */
    public Boolean getVerticalWork() {
        return verticalWork;
    }

    /**
     * 수직 센서 작업 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param verticalWork 설정할 수직 센서 작업 상태
     * @return 현재 HsiInfoPush 객체
     */
    public HsiInfoPush setVerticalWork(Boolean verticalWork) {
        this.verticalWork = verticalWork;
        return this;
    }

    /**
     * 수평 센서 활성화 여부를 반환합니다.
     * 
     * @return 수평 센서 활성화 여부
     */
    public Boolean getHorizontalEnable() {
        return horizontalEnable;
    }

    /**
     * 수평 센서 활성화 여부를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param horizontalEnable 설정할 수평 센서 활성화 여부
     * @return 현재 HsiInfoPush 객체
     */
    public HsiInfoPush setHorizontalEnable(Boolean horizontalEnable) {
        this.horizontalEnable = horizontalEnable;
        return this;
    }

    /**
     * 수평 센서 작업 상태를 반환합니다.
     * 
     * @return 수평 센서 작업 상태
     */
    public Boolean getHorizontalWork() {
        return horizontalWork;
    }

    /**
     * 수평 센서 작업 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param horizontalWork 설정할 수평 센서 작업 상태
     * @return 현재 HsiInfoPush 객체
     */
    public HsiInfoPush setHorizontalWork(Boolean horizontalWork) {
        this.horizontalWork = horizontalWork;
        return this;
    }
}
