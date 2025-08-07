package com.dji.sdk.cloudapi.firmware;

import com.dji.sdk.common.BaseModel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * OTA 생성 요청 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 OTA(Over-The-Air) 펌웨어 업데이트를 생성하기 위한 요청을 정의합니다.
 * 업데이트할 디바이스 목록을 포함하여 OTA 펌웨어 업데이트를 생성합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/28
 */
public class OtaCreateRequest extends BaseModel {

    /**
     * 디바이스 목록 (필수)
     * 업데이트할 디바이스들의 목록 (1-2개)
     */
    @Size(min = 1, max = 2)
    @NotNull
    private List<@Valid OtaCreateDevice> devices;

    /**
     * 기본 생성자
     */
    public OtaCreateRequest() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "OtaCreateRequest{" +
                "devices=" + devices +
                '}';
    }

    /**
     * 디바이스 목록을 반환합니다.
     * 
     * @return 디바이스 목록
     */
    public List<OtaCreateDevice> getDevices() {
        return devices;
    }

    /**
     * 디바이스 목록을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param devices 설정할 디바이스 목록
     * @return 현재 OtaCreateRequest 객체
     */
    public OtaCreateRequest setDevices(List<OtaCreateDevice> devices) {
        this.devices = devices;
        return this;
    }
}
