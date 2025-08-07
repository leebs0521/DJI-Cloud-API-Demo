package com.dji.sdk.cloudapi.control;

import com.dji.sdk.common.BaseModel;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 드론 제어 요청 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 드론의 직접 제어를 요청할 때 사용됩니다.
 * 조이스틱 입력을 시뮬레이션하여 드론의 비행을 제어합니다.
 * X, Y축 이동, 고도(H), 회전(W) 등의 제어를 지원합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/29
 */
public class DroneControlRequest extends BaseModel {

    /**
     * 시퀀스 번호 (필수)
     * 제어 명령의 순서를 나타내는 고유 번호
     */
    @NotNull
    private Long seq;

    /**
     * X축 제어 값 (전진/후진)
     * -17 ~ 17 범위, 음수는 후진, 양수는 전진
     */
    @Min(-17)
    @Max(17)
    private Float x;

    /**
     * Y축 제어 값 (좌우 이동)
     * -17 ~ 17 범위, 음수는 좌측, 양수는 우측
     */
    @Min(-17)
    @Max(17)
    private Float y;

    /**
     * 고도 제어 값 (상승/하강)
     * -4 ~ 5 범위, 음수는 하강, 양수는 상승
     */
    @Min(-4)
    @Max(5)
    private Float h;

    /**
     * 회전 제어 값 (좌회전/우회전)
     * -90 ~ 90 범위, 음수는 좌회전, 양수는 우회전
     */
    @Min(-90)
    @Max(90)
    private Float w;

    /**
     * 제어 주파수
     * 2 ~ 10 범위, 제어 명령의 전송 주기
     */
    @Min(2)
    @Max(10)
    private Integer freq;

    /**
     * 지연 시간 (밀리초)
     * 100 ~ 1000 범위, 제어 명령의 지연 시간
     */
    @Min(100)
    @Max(1000)
    private Integer delayTime;

    /**
     * 기본 생성자
     */
    public DroneControlRequest() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "DroneControlRequest{" +
                "seq=" + seq +
                ", x=" + x +
                ", y=" + y +
                ", h=" + h +
                ", w=" + w +
                ", freq=" + freq +
                ", delayTime=" + delayTime +
                '}';
    }

    /**
     * 시퀀스 번호를 반환합니다.
     * 
     * @return 시퀀스 번호
     */
    public Long getSeq() {
        return seq;
    }

    /**
     * 시퀀스 번호를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param seq 설정할 시퀀스 번호
     * @return 현재 DroneControlRequest 객체
     */
    public DroneControlRequest setSeq(Long seq) {
        this.seq = seq;
        return this;
    }

    /**
     * X축 제어 값을 반환합니다.
     * 
     * @return X축 제어 값
     */
    public Float getX() {
        return x;
    }

    /**
     * X축 제어 값을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param x 설정할 X축 제어 값
     * @return 현재 DroneControlRequest 객체
     */
    public DroneControlRequest setX(Float x) {
        this.x = x;
        return this;
    }

    /**
     * Y축 제어 값을 반환합니다.
     * 
     * @return Y축 제어 값
     */
    public Float getY() {
        return y;
    }

    /**
     * Y축 제어 값을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param y 설정할 Y축 제어 값
     * @return 현재 DroneControlRequest 객체
     */
    public DroneControlRequest setY(Float y) {
        this.y = y;
        return this;
    }

    /**
     * 고도 제어 값을 반환합니다.
     * 
     * @return 고도 제어 값
     */
    public Float getH() {
        return h;
    }

    /**
     * 고도 제어 값을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param h 설정할 고도 제어 값
     * @return 현재 DroneControlRequest 객체
     */
    public DroneControlRequest setH(Float h) {
        this.h = h;
        return this;
    }

    /**
     * 회전 제어 값을 반환합니다.
     * 
     * @return 회전 제어 값
     */
    public Float getW() {
        return w;
    }

    /**
     * 회전 제어 값을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param w 설정할 회전 제어 값
     * @return 현재 DroneControlRequest 객체
     */
    public DroneControlRequest setW(Float w) {
        this.w = w;
        return this;
    }

    /**
     * 제어 주파수를 반환합니다.
     * 
     * @return 제어 주파수
     */
    public Integer getFreq() {
        return freq;
    }

    /**
     * 제어 주파수를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param freq 설정할 제어 주파수
     * @return 현재 DroneControlRequest 객체
     */
    public DroneControlRequest setFreq(Integer freq) {
        this.freq = freq;
        return this;
    }

    /**
     * 지연 시간을 반환합니다.
     * 
     * @return 지연 시간 (밀리초)
     */
    public Integer getDelayTime() {
        return delayTime;
    }

    /**
     * 지연 시간을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param delayTime 설정할 지연 시간 (밀리초)
     * @return 현재 DroneControlRequest 객체
     */
    public DroneControlRequest setDelayTime(Integer delayTime) {
        this.delayTime = delayTime;
        return this;
    }
}
