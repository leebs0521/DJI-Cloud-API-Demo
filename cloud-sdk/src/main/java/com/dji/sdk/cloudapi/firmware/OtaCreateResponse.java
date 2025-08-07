package com.dji.sdk.cloudapi.firmware;

/**
 * OTA 생성 응답 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 OTA(Over-The-Air) 펌웨어 업데이트 생성 요청에 대한 응답을 정의합니다.
 * OTA 진행 상태 정보를 포함하여 펌웨어 업데이트 생성 요청의 처리 결과를 확인할 수 있습니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/23
 */
public class OtaCreateResponse {

    /**
     * 미션 상태
     * OTA 펌웨어 업데이트의 진행 상태
     */
    private OtaProgressStatusEnum status;

    /**
     * 기본 생성자
     */
    public OtaCreateResponse() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "OtaCreateResponse{" +
                "status=" + status +
                '}';
    }

    /**
     * OTA 진행 상태를 반환합니다.
     * 
     * @return OTA 진행 상태
     */
    public OtaProgressStatusEnum getStatus() {
        return status;
    }

    /**
     * OTA 진행 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param status 설정할 OTA 진행 상태
     * @return 현재 OtaCreateResponse 객체
     */
    public OtaCreateResponse setStatus(OtaProgressStatusEnum status) {
        this.status = status;
        return this;
    }
}
