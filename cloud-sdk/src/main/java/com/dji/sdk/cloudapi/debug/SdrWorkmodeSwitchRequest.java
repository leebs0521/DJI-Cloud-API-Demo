package com.dji.sdk.cloudapi.debug;

import com.dji.sdk.cloudapi.device.LinkWorkModeEnum;
import com.dji.sdk.common.BaseModel;

import javax.validation.constraints.NotNull;

/**
 * SDR 작업 모드 전환 요청 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 SDR(Signal Data Radio)의 작업 모드를 전환하기 위한 요청을 정의합니다.
 * 링크 작업 모드를 포함하여 SDR의 작업 모드를 전환합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/25
 */
public class SdrWorkmodeSwitchRequest extends BaseModel {

    /**
     * 링크 작업 모드 (필수)
     * 전환할 SDR의 링크 작업 모드
     */
    @NotNull
    private LinkWorkModeEnum linkWorkmode;

    /**
     * 기본 생성자
     */
    public SdrWorkmodeSwitchRequest() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "SdrWorkmodeSwitchRequest{" +
                "linkWorkmode=" + linkWorkmode +
                '}';
    }

    /**
     * 링크 작업 모드를 반환합니다.
     * 
     * @return 링크 작업 모드
     */
    public LinkWorkModeEnum getLinkWorkmode() {
        return linkWorkmode;
    }

    /**
     * 링크 작업 모드를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param linkWorkmode 설정할 링크 작업 모드
     * @return 현재 SdrWorkmodeSwitchRequest 객체
     */
    public SdrWorkmodeSwitchRequest setLinkWorkmode(LinkWorkModeEnum linkWorkmode) {
        this.linkWorkmode = linkWorkmode;
        return this;
    }
}
