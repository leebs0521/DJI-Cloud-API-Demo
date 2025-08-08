package com.dji.sdk.cloudapi.organization;

/**
 * 바인딩 상태 응답 디바이스 클래스
 * 
 * 이 클래스는 디바이스의 바인딩 상태 조회 결과를 정의합니다.
 * 디바이스의 시리얼 번호 정보를 포함합니다.
 * 
 * 주요 구성 요소:
 * - sn: 디바이스 시리얼 번호
 * 
 * 이 클래스는 디바이스의 바인딩 상태 조회 결과를
 * 응답으로 반환하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/30
 */
public class BindStatusResponseDevice {

    /**
     * 디바이스 시리얼 번호
     * 
     * 바인딩 상태를 조회한 디바이스의 고유 시리얼 번호입니다.
     * 드론, 도크, RC 등의 디바이스를 식별하는 데 사용됩니다.
     */
    private String sn;

    public BindStatusResponseDevice() {
    }

    @Override
    public String toString() {
        return "BindStatusResponseDevice{" +
                "sn='" + sn + '\'' +
                '}';
    }

    /**
     * 디바이스 시리얼 번호를 반환합니다.
     * 
     * @return 디바이스 시리얼 번호
     */
    public String getSn() {
        return sn;
    }

    /**
     * 디바이스 시리얼 번호를 설정합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public BindStatusResponseDevice setSn(String sn) {
        this.sn = sn;
        return this;
    }
}
