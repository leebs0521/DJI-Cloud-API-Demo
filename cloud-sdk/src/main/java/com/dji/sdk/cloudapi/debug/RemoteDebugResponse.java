package com.dji.sdk.cloudapi.debug;

/**
 * 원격 디버그 응답 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 원격 디버그 요청에 대한 응답을 정의합니다.
 * 원격 디버그의 상태 정보를 포함하여 디버그 요청의 처리 결과를 확인할 수 있습니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/29
 */
public class RemoteDebugResponse {

    /**
     * 원격 디버그 상태
     * 원격 디버그의 현재 상태 정보
     */
    private RemoteDebugStatusEnum status;

    /**
     * 기본 생성자
     */
    public RemoteDebugResponse() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "RemoteDebugResponse{" +
                "status=" + status +
                '}';
    }

    /**
     * 원격 디버그 상태를 반환합니다.
     * 
     * @return 원격 디버그 상태
     */
    public RemoteDebugStatusEnum getStatus() {
        return status;
    }

    /**
     * 원격 디버그 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param status 설정할 원격 디버그 상태
     * @return 현재 RemoteDebugResponse 객체
     */
    public RemoteDebugResponse setStatus(RemoteDebugStatusEnum status) {
        this.status = status;
        return this;
    }
}
