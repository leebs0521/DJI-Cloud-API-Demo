package com.dji.sdk.cloudapi.device;

/**
 * IR 측정점 클래스
 * 
 * 이 클래스는 적외선 카메라의 측정점 정보를 담습니다.
 * X, Y 좌표와 온도 정보를 포함합니다.
 * 
 * @author sean
 * @version 1.9
 * @date 2023/12/12
 */
public class IrMeteringPoint {

    /**
     * X 좌표
     */
    private Float x;

    /**
     * Y 좌표
     */
    private Float y;

    /**
     * 온도 (섭씨)
     */
    private Float temperature;

    /**
     * 기본 생성자
     */
    public IrMeteringPoint() {
    }

    @Override
    public String toString() {
        return "IrMeteringPoint{" +
                "x=" + x +
                ", y=" + y +
                ", temperature=" + temperature +
                '}';
    }

    /**
     * X 좌표를 반환합니다.
     * 
     * @return X 좌표
     */
    public Float getX() {
        return x;
    }

    /**
     * X 좌표를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param x 설정할 X 좌표
     * @return 현재 IrMeteringPoint 객체
     */
    public IrMeteringPoint setX(Float x) {
        this.x = x;
        return this;
    }

    /**
     * Y 좌표를 반환합니다.
     * 
     * @return Y 좌표
     */
    public Float getY() {
        return y;
    }

    /**
     * Y 좌표를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param y 설정할 Y 좌표
     * @return 현재 IrMeteringPoint 객체
     */
    public IrMeteringPoint setY(Float y) {
        this.y = y;
        return this;
    }

    /**
     * 온도를 반환합니다.
     * 
     * @return 온도 (섭씨)
     */
    public Float getTemperature() {
        return temperature;
    }

    /**
     * 온도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param temperature 설정할 온도 (섭씨)
     * @return 현재 IrMeteringPoint 객체
     */
    public IrMeteringPoint setTemperature(Float temperature) {
        this.temperature = temperature;
        return this;
    }
}
