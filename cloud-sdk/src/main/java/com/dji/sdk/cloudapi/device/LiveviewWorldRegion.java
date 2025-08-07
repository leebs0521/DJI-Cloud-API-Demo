package com.dji.sdk.cloudapi.device;

/**
 * 라이브뷰 월드 영역 클래스
 * 
 * 이 클래스는 라이브뷰의 월드 좌표 영역을 담습니다.
 * 하단, 좌측, 우측, 상단 좌표를 포함합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/8
 */
public class LiveviewWorldRegion {

    /**
     * 하단 좌표
     */
    private Float bottom;

    /**
     * 좌측 좌표
     */
    private Float left;

    /**
     * 우측 좌표
     */
    private Float right;

    /**
     * 상단 좌표
     */
    private Float top;

    /**
     * 기본 생성자
     */
    public LiveviewWorldRegion() {
    }

    @Override
    public String toString() {
        return "LiveviewWorldRegion{" +
                "bottom=" + bottom +
                ", left=" + left +
                ", right=" + right +
                ", top=" + top +
                '}';
    }

    /**
     * 하단 좌표를 반환합니다.
     * 
     * @return 하단 좌표
     */
    public Float getBottom() {
        return bottom;
    }

    /**
     * 하단 좌표를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param bottom 설정할 하단 좌표
     * @return 현재 LiveviewWorldRegion 객체
     */
    public LiveviewWorldRegion setBottom(Float bottom) {
        this.bottom = bottom;
        return this;
    }

    /**
     * 좌측 좌표를 반환합니다.
     * 
     * @return 좌측 좌표
     */
    public Float getLeft() {
        return left;
    }

    /**
     * 좌측 좌표를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param left 설정할 좌측 좌표
     * @return 현재 LiveviewWorldRegion 객체
     */
    public LiveviewWorldRegion setLeft(Float left) {
        this.left = left;
        return this;
    }

    /**
     * 우측 좌표를 반환합니다.
     * 
     * @return 우측 좌표
     */
    public Float getRight() {
        return right;
    }

    /**
     * 우측 좌표를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param right 설정할 우측 좌표
     * @return 현재 LiveviewWorldRegion 객체
     */
    public LiveviewWorldRegion setRight(Float right) {
        this.right = right;
        return this;
    }

    /**
     * 상단 좌표를 반환합니다.
     * 
     * @return 상단 좌표
     */
    public Float getTop() {
        return top;
    }

    /**
     * 상단 좌표를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param top 설정할 상단 좌표
     * @return 현재 LiveviewWorldRegion 객체
     */
    public LiveviewWorldRegion setTop(Float top) {
        this.top = top;
        return this;
    }
}
