package com.dji.sdk.cloudapi.hms;

import java.util.List;

/**
 * HMS 메인 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 HMS(Health Management System) 정보를 담는 메인 클래스입니다.
 * 디바이스의 HMS 정보 목록을 포함하여 전체 HMS 데이터를 관리합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/27
 */
public class Hms {

    /**
     * HMS 목록
     * 디바이스 HMS 정보들의 목록
     */
    private List<DeviceHms> list;

    /**
     * 기본 생성자
     */
    public Hms() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "Hms{" +
                "list=" + list +
                '}';
    }

    /**
     * HMS 목록을 반환합니다.
     * 
     * @return HMS 목록
     */
    public List<DeviceHms> getList() {
        return list;
    }

    /**
     * HMS 목록을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param list 설정할 HMS 목록
     * @return 현재 Hms 객체
     */
    public Hms setList(List<DeviceHms> list) {
        this.list = list;
        return this;
    }
}
