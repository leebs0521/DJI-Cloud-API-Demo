package com.dji.sdk.cloudapi.control;

import com.dji.sdk.common.BaseModel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 하트비트 요청 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 연결 상태를 확인하기 위한 하트비트 요청을 정의합니다.
 * 시퀀스 번호와 타임스탬프를 포함하여 연결의 활성 상태를 모니터링합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/29
 */
public class HeartBeatRequest extends BaseModel {

    /**
     * 시퀀스 번호 (필수)
     * 하트비트 요청의 순서를 나타내는 고유 번호
     */
    @NotNull
    private Long seq;

    /**
     * 타임스탬프 (필수)
     * 123456789012 이상, 하트비트 요청의 시간 정보 (밀리초)
     */
    @NotNull
    @Min(123456789012L)
    private Long timestamp;

    /**
     * 기본 생성자
     */
    public HeartBeatRequest() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "HeartBeatRequest{" +
                "seq=" + seq +
                ", timestamp=" + timestamp +
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
     * @return 현재 HeartBeatRequest 객체
     */
    public HeartBeatRequest setSeq(Long seq) {
        this.seq = seq;
        return this;
    }

    /**
     * 타임스탬프를 반환합니다.
     * 
     * @return 타임스탬프 (밀리초)
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * 타임스탬프를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param timestamp 설정할 타임스탬프 (밀리초)
     * @return 현재 HeartBeatRequest 객체
     */
    public HeartBeatRequest setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}
