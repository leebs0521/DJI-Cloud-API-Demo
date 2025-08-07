package com.dji.sdk.cloudapi.device;

/**
 * IR 측정 영역 클래스
 * 
 * 이 클래스는 적외선 카메라의 측정 영역 정보를 담습니다.
 * 위치, 크기, 온도 정보, 최고/최저 온도 포인트 등을 포함합니다.
 * 
 * @author sean
 * @version 1.9
 * @date 2023/12/12
 */
public class IrMeteringArea {

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
     * 너비
     */
    private Float width;

    /**
     * 높이
     */
    private Float height;

    /**
     * 평균 온도 (섭씨)
     */
    private Float averTemperature;

    /**
     * 최저 온도 포인트
     */
    private IrMeteringPoint minTemperaturePoint;

    /**
     * 최고 온도 포인트
     */
    private IrMeteringPoint maxTemperaturePoint;

    /**
     * 기본 생성자
     */
    public IrMeteringArea() {
    }

    @Override
    public String toString() {
        return "IrMeteringArea{" +
                "x=" + x +
                ", y=" + y +
                ", temperature=" + temperature +
                ", width=" + width +
                ", height=" + height +
                ", averTemperature=" + averTemperature +
                ", minTemperaturePoint=" + minTemperaturePoint +
                ", maxTemperaturePoint=" + maxTemperaturePoint +
                '}';
    }

    public Float getX() {
        return x;
    }

    public IrMeteringArea setX(Float x) {
        this.x = x;
        return this;
    }

    public Float getY() {
        return y;
    }

    public IrMeteringArea setY(Float y) {
        this.y = y;
        return this;
    }

    public Float getTemperature() {
        return temperature;
    }

    public IrMeteringArea setTemperature(Float temperature) {
        this.temperature = temperature;
        return this;
    }

    public Float getWidth() {
        return width;
    }

    public IrMeteringArea setWidth(Float width) {
        this.width = width;
        return this;
    }

    public Float getHeight() {
        return height;
    }

    public IrMeteringArea setHeight(Float height) {
        this.height = height;
        return this;
    }

    public Float getAverTemperature() {
        return averTemperature;
    }

    public IrMeteringArea setAverTemperature(Float averTemperature) {
        this.averTemperature = averTemperature;
        return this;
    }

    public IrMeteringPoint getMinTemperaturePoint() {
        return minTemperaturePoint;
    }

    public IrMeteringArea setMinTemperaturePoint(IrMeteringPoint minTemperaturePoint) {
        this.minTemperaturePoint = minTemperaturePoint;
        return this;
    }

    public IrMeteringPoint getMaxTemperaturePoint() {
        return maxTemperaturePoint;
    }

    public IrMeteringArea setMaxTemperaturePoint(IrMeteringPoint maxTemperaturePoint) {
        this.maxTemperaturePoint = maxTemperaturePoint;
        return this;
    }
}
