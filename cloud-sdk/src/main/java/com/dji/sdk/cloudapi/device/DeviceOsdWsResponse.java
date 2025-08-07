package com.dji.sdk.cloudapi.device;

import com.dji.sdk.common.BaseModel;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 디바이스 OSD 웹소켓 응답 클래스
 * 
 * 이 클래스는 디바이스 토폴로지 변경 시 웹소켓으로 전송되는 응답 데이터를 담습니다.
 * 디바이스 시리얼 번호와 호스트 정보를 포함합니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/12/8
 */
@Schema(name = "DeviceOsdWsResponse", description = "BizCode: device_osd.<p>Websocket response data when device topology changes.</p>")
public class DeviceOsdWsResponse extends BaseModel {

    /**
     * 디바이스 시리얼 번호
     */
    @NotNull
    @Schema(description = "drone sn", example = "1AD3CA2VL3LAD6")
    private String sn;

    /**
     * 디바이스 OSD 호스트 정보
     */
    @NotNull
    @Valid
    private DeviceOsdHost host;

    /**
     * 기본 생성자
     */
    public DeviceOsdWsResponse() {
    }

    @Override
    public String toString() {
        return "DeviceOsdWsResponse{" +
                "sn='" + sn + '\'' +
                ", host=" + host +
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
     * 디바이스 시리얼 번호를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param sn 설정할 디바이스 시리얼 번호
     * @return 현재 DeviceOsdWsResponse 객체
     */
    public DeviceOsdWsResponse setSn(String sn) {
        this.sn = sn;
        return this;
    }

    /**
     * 디바이스 OSD 호스트 정보를 반환합니다.
     * 
     * @return 디바이스 OSD 호스트 정보
     */
    public DeviceOsdHost getHost() {
        return host;
    }

    /**
     * 디바이스 OSD 호스트 정보를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param host 설정할 디바이스 OSD 호스트 정보
     * @return 현재 DeviceOsdWsResponse 객체
     */
    public DeviceOsdWsResponse setHost(DeviceOsdHost host) {
        this.host = host;
        return this;
    }
}
