package com.dji.sdk.cloudapi.control;

/**
 * 드론 제어 응답 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 드론 제어 요청에 대한 응답을 정의합니다.
 * 시퀀스 번호를 포함하여 제어 요청의 처리 결과를 확인할 수 있습니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/29
 */
public class DroneControlResponse {

    /**
     * 시퀀스 번호
     * 제어 요청의 순서를 나타내는 고유 번호
     */
    private Long seq;

    /**
     * 기본 생성자
     */
    public DroneControlResponse() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "DroneControlResponse{" +
                "seq=" + seq +
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
     * @return 현재 DroneControlResponse 객체
     */
    public DroneControlResponse setSeq(Long seq) {
        this.seq = seq;
        return this;
    }

}
