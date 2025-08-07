package com.dji.sdk.cloudapi.device;

import java.util.List;

/**
 * 동글 정보 목록 클래스
 * 
 * 이 클래스는 여러 동글의 정보를 담습니다.
 * 동글 정보 목록을 관리합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/20
 */
public class DongleInfos {

    /**
     * 동글 정보 목록
     */
    private List<DongleInfo> dongleInfos;

    /**
     * 기본 생성자
     */
    public DongleInfos() {
    }

    @Override
    public String toString() {
        return "DongleInfos{" +
                "dongleInfos=" + dongleInfos +
                '}';
    }

    /**
     * 동글 정보 목록을 반환합니다.
     * 
     * @return 동글 정보 목록
     */
    public List<DongleInfo> getDongleInfos() {
        return dongleInfos;
    }

    /**
     * 동글 정보 목록을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param dongleInfos 설정할 동글 정보 목록
     * @return 현재 DongleInfos 객체
     */
    public DongleInfos setDongleInfos(List<DongleInfo> dongleInfos) {
        this.dongleInfos = dongleInfos;
        return this;
    }
}
