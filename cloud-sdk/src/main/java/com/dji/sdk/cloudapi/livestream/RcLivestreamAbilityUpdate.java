package com.dji.sdk.cloudapi.livestream;

/**
 * RC 라이브스트림 기능 업데이트 정보를 나타내는 클래스
 * RC의 라이브스트림 기능 변경사항을 관리합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/23
 */
public class RcLivestreamAbilityUpdate {

    /** 라이브스트림 용량 정보 */
    private RcLiveCapacity liveCapacity;

    /**
     * 기본 생성자
     */
    public RcLivestreamAbilityUpdate() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "RcLivestreamAbilityUpdate{" +
                "liveCapacity=" + liveCapacity +
                '}';
    }

    /**
     * 라이브스트림 용량 정보를 반환합니다.
     * 
     * @return 라이브스트림 용량 정보
     */
    public RcLiveCapacity getLiveCapacity() {
        return liveCapacity;
    }

    /**
     * 라이브스트림 용량 정보를 설정합니다.
     * 
     * @param liveCapacity 설정할 라이브스트림 용량 정보
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public RcLivestreamAbilityUpdate setLiveCapacity(RcLiveCapacity liveCapacity) {
        this.liveCapacity = liveCapacity;
        return this;
    }
}
