package com.dji.sdk.cloudapi.control;

import com.dji.sdk.cloudapi.device.PayloadIndex;
import com.dji.sdk.common.BaseModel;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * IR 카메라 측광 영역 설정 요청 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 적외선 카메라의 측광 영역을 설정하기 위한 요청을 정의합니다.
 * 페이로드 인덱스와 측광 영역의 좌표, 크기를 포함하여 적외선 카메라의 측광 영역을 설정합니다.
 * 
 * @author sean
 * @version 1.9
 * @date 2023/12/12
 */
public class IrMeteringAreaSetRequest extends BaseModel {

    /**
     * 페이로드 인덱스 (필수)
     * 측광 영역을 설정할 적외선 카메라의 페이로드 인덱스
     * 비공식 디바이스 모드 키로, 형식은 "타입-서브타입-짐벌인덱스"입니다.
     * 지원 제품은 [Product Supported](https://developer.dji.com/doc/cloud-api-tutorial/en/overview/product-support.html)를 참조하세요.
     */
    @NotNull
    private PayloadIndex payloadIndex;

    /**
     * 측광 영역 X 좌표 (필수)
     * 0 ~ 1 범위, 렌즈의 좌상단 모서리를 좌표 중심점으로 하며, 수평 방향이 X축
     */
    @NotNull
    @Min(0)
    @Max(1)
    private Float x;

    /**
     * 측광 영역 Y 좌표 (필수)
     * 0 ~ 1 범위, 렌즈의 좌상단 모서리를 좌표 중심점으로 하며, 수직 방향이 Y축
     */
    @NotNull
    @Min(0)
    @Max(1)
    private Float y;

    /**
     * 측광 영역 너비 (필수)
     * 0 ~ 1 범위, 측광 영역의 너비
     */
    @NotNull
    @Min(0)
    @Max(1)
    private Float width;

    /**
     * 측광 영역 높이 (필수)
     * 0 ~ 1 범위, 측광 영역의 높이
     */
    @NotNull
    @Min(0)
    @Max(1)
    private Float height;

    /**
     * 기본 생성자
     */
    public IrMeteringAreaSetRequest() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "IrMeteringAreaSetRequest{" +
                "payloadIndex=" + payloadIndex +
                ", x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                '}';
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
     * @return 현재 IrMeteringAreaSetRequest 객체
     */
    public IrMeteringAreaSetRequest setPayloadIndex(PayloadIndex payloadIndex) {
        this.payloadIndex = payloadIndex;
        return this;
    }

    /**
     * 측광 영역 X 좌표를 반환합니다.
     * 
     * @return 측광 영역 X 좌표
     */
    public Float getX() {
        return x;
    }

    /**
     * 측광 영역 X 좌표를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param x 설정할 측광 영역 X 좌표
     * @return 현재 IrMeteringAreaSetRequest 객체
     */
    public IrMeteringAreaSetRequest setX(Float x) {
        this.x = x;
        return this;
    }

    /**
     * 측광 영역 Y 좌표를 반환합니다.
     * 
     * @return 측광 영역 Y 좌표
     */
    public Float getY() {
        return y;
    }

    /**
     * 측광 영역 Y 좌표를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param y 설정할 측광 영역 Y 좌표
     * @return 현재 IrMeteringAreaSetRequest 객체
     */
    public IrMeteringAreaSetRequest setY(Float y) {
        this.y = y;
        return this;
    }

    /**
     * 측광 영역 너비를 반환합니다.
     * 
     * @return 측광 영역 너비
     */
    public Float getWidth() {
        return width;
    }

    /**
     * 측광 영역 너비를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param width 설정할 측광 영역 너비
     * @return 현재 IrMeteringAreaSetRequest 객체
     */
    public IrMeteringAreaSetRequest setWidth(Float width) {
        this.width = width;
        return this;
    }

    /**
     * 측광 영역 높이를 반환합니다.
     * 
     * @return 측광 영역 높이
     */
    public Float getHeight() {
        return height;
    }

    /**
     * 측광 영역 높이를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param height 설정할 측광 영역 높이
     * @return 현재 IrMeteringAreaSetRequest 객체
     */
    public IrMeteringAreaSetRequest setHeight(Float height) {
        this.height = height;
        return this;
    }
}
