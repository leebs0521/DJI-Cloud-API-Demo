package com.dji.sample.control.model.dto;

import com.dji.sample.control.service.impl.RemoteDebugHandler;
import com.dji.sdk.cloudapi.device.LinkWorkModeEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 링크 작업 모드 클래스
 * 
 * 도크의 링크 작업 모드 제어를 위한 원격 디버깅 핸들러입니다.
 * 링크 작업 모드를 설정할 수 있습니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/25
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class LinkWorkMode extends RemoteDebugHandler {

    /** 링크 작업 모드 */
    private LinkWorkModeEnum linkWorkMode;

    /**
     * JSON 생성자를 통한 링크 작업 모드 설정
     * 
     * @param linkWorkMode 링크 작업 모드 값
     */
    @JsonCreator
    public LinkWorkMode(@JsonProperty("action") Integer linkWorkMode) {
        this.linkWorkMode = LinkWorkModeEnum.find(linkWorkMode);
    }

    /**
     * JSON 직렬화를 위한 맵 변환
     * 
     * @return 링크 작업 모드 맵
     */
    @JsonValue
    public Map toMap() {
        return Map.of("link_workmode", linkWorkMode.getMode());
    }
}
