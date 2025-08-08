package com.dji.sdk.cloudapi.organization;

import com.dji.sdk.mqtt.MqttReply;

import javax.validation.constraints.NotNull;

/**
 * 조직 바인딩 정보 클래스
 * 
 * 이 클래스는 디바이스와 조직 간의 바인딩 상태 정보를 정의합니다.
 * 디바이스의 시리얼 번호와 바인딩 결과 코드를 포함합니다.
 * 
 * 주요 구성 요소:
 * - sn: 디바이스 시리얼 번호
 * - errCode: 바인딩 결과 코드 (성공/실패)
 * 
 * 이 클래스는 디바이스가 조직에 성공적으로 바인딩되었는지
 * 확인하고 결과를 추적하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.1
 * @date 2022/6/14
 */
public class OrganizationBindInfo {

    /**
     * 디바이스 시리얼 번호
     * 
     * 바인딩 대상 디바이스의 고유 시리얼 번호입니다.
     * 드론, 도크, RC 등의 디바이스를 식별하는 데 사용됩니다.
     */
    @NotNull
    private String sn;

    /**
     * 바인딩 결과 코드
     * 
     * 디바이스와 조직 간의 바인딩 작업 결과를 나타내는 코드입니다.
     * 0: 성공, 기타 값: 실패 (에러 코드)
     */
    @NotNull
    private Integer errCode;

    public OrganizationBindInfo() {
    }

    /**
     * 조직 바인딩 정보를 생성합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @param errCode 바인딩 결과 코드
     */
    public OrganizationBindInfo(String sn, Integer errCode) {
        this.sn = sn;
        this.errCode = errCode;
    }

    /**
     * 성공적인 바인딩 정보를 생성합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @return 성공 상태의 OrganizationBindInfo 객체
     */
    public static OrganizationBindInfo success(String sn) {
        return new OrganizationBindInfo(sn, MqttReply.CODE_SUCCESS);
    }

    @Override
    public String toString() {
        return "OrganizationBindInfo{" +
                "sn='" + sn + '\'' +
                ", errCode=" + errCode +
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
    public OrganizationBindInfo setSn(String sn) {
        this.sn = sn;
        return this;
    }

    /**
     * 바인딩 결과 코드를 반환합니다.
     * 
     * @return 바인딩 결과 코드
     */
    public Integer getErrCode() {
        return errCode;
    }

    /**
     * 바인딩 결과 코드를 설정합니다.
     * 
     * @param errCode 바인딩 결과 코드
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public OrganizationBindInfo setErrCode(Integer errCode) {
        this.errCode = errCode;
        return this;
    }
}
