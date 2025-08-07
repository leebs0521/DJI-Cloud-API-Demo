package com.dji.sdk.cloudapi.firmware;

/**
 * OTA 진행 확장 정보 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 OTA(Over-The-Air) 펌웨어 업데이트의 진행 확장 정보를 담는 클래스입니다.
 * 전송 속도 등의 추가 정보를 포함하여 OTA 펌웨어 업데이트의 상세 진행 상황을 관리합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/30
 */
public class OtaProgressExt {

    /**
     * 전송 속도
     * OTA 펌웨어 업데이트의 전송 속도 (바이트/초)
     */
    private Long rate;

    /**
     * 기본 생성자
     */
    public OtaProgressExt() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "OtaProgressExt{" +
                "rate=" + rate +
                '}';
    }

    /**
     * 전송 속도를 반환합니다.
     * 
     * @return 전송 속도 (바이트/초)
     */
    public Long getRate() {
        return rate;
    }

    /**
     * 전송 속도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param rate 설정할 전송 속도 (바이트/초)
     * @return 현재 OtaProgressExt 객체
     */
    public OtaProgressExt setRate(Long rate) {
        this.rate = rate;
        return this;
    }
}
