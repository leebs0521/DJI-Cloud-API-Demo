package com.dji.sdk.cloudapi.organization;

import com.dji.sdk.common.BaseModel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 공항 바인딩 상태 응답 클래스
 * 
 * 이 클래스는 공항(도크)의 디바이스 바인딩 상태 조회 결과를 정의합니다.
 * BaseModel을 상속받아 유효성 검사를 지원하며, 공항에 연결된 디바이스들의
 * 바인딩 상태 정보를 포함합니다.
 * 
 * 주요 구성 요소:
 * - bindStatus: 바인딩 상태 요청 디바이스 목록 (1-2개)
 * 
 * 이 클래스는 공항에 연결된 디바이스들의 바인딩 상태를
 * 응답으로 반환하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/30
 */
public class AirportBindStatusResponse extends BaseModel {

    /**
     * 바인딩 상태 요청 디바이스 목록
     * 
     * 공항에 연결된 디바이스들의 바인딩 상태 정보를 담은 목록입니다.
     * 최소 1개, 최대 2개의 디바이스 정보를 포함할 수 있습니다.
     * 각 디바이스의 시리얼 번호, 바인딩 상태, 조직 정보 등을 포함합니다.
     */
    @NotNull
    @Size(min = 1, max = 2)
    private List<@Valid BindStatusRequestDevice> bindStatus;

    public AirportBindStatusResponse() {
    }

    @Override
    public String toString() {
        return "AirportBindStatusResponse{" +
                "bindStatus=" + bindStatus +
                '}';
    }

    /**
     * 바인딩 상태 요청 디바이스 목록을 반환합니다.
     * 
     * @return 바인딩 상태 요청 디바이스 목록
     */
    public List<BindStatusRequestDevice> getBindStatus() {
        return bindStatus;
    }

    /**
     * 바인딩 상태 요청 디바이스 목록을 설정합니다.
     * 
     * @param bindStatus 바인딩 상태 요청 디바이스 목록
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public AirportBindStatusResponse setBindStatus(List<BindStatusRequestDevice> bindStatus) {
        this.bindStatus = bindStatus;
        return this;
    }
}
