package com.dji.sdk.cloudapi.tsa;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 디바이스 토폴로지 데이터 클래스
 * 
 * 이 클래스는 TSA(Threat and Situation Awareness) 시스템에서 디바이스의 토폴로지 정보를 정의합니다.
 * 디바이스의 기본 정보, 사용자 정보, 온라인 상태, 아이콘 URL 등을 포함하여
 * 지도상에 디바이스를 표시하기 위한 모든 정보를 관리합니다.
 * 
 * 주요 구성 요소:
 * - sn: 디바이스 시리얼 번호
 * - deviceCallsign: 디바이스 닉네임
 * - deviceModel: 디바이스 모델 정보
 * - onlineStatus: 온라인 상태
 * - userId: 사용자 ID
 * - userCallsign: 사용자 닉네임
 * - iconUrls: 디바이스 아이콘 URL
 * 
 * 이 클래스는 TSA 시스템에서 디바이스의 상태와 위치를 시각적으로 표현하는 데 사용됩니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/12/8
 */
@Schema(description = "디바이스 토폴로지 데이터")
public class DeviceTopology {

    /**
     * 디바이스 시리얼 번호
     * 
     * 디바이스를 고유하게 식별하는 시리얼 번호입니다.
     * 각 디바이스마다 고유한 시리얼 번호를 가집니다.
     * 
     * 예시: "1AEC32CK4AD23R"
     */
    @NotNull
    @Schema(description = "디바이스 시리얼 번호", example = "1AEC32CK4AD23R")
    private String sn;

    /**
     * 디바이스 닉네임
     * 
     * 디바이스의 사용자 정의 이름 또는 별칭입니다.
     * 사용자가 디바이스를 쉽게 식별할 수 있도록 하는 이름입니다.
     * 
     * 예시: "my M350"
     */
    @NotNull
    @JsonProperty("device_callsign")
    @Schema(description = "디바이스 닉네임", example = "my M350")
    private String deviceCallsign;

    /**
     * 디바이스 모델 정보
     * 
     * 디바이스의 모델 정보를 담고 있는 객체입니다.
     * 디바이스의 제조사, 모델명, 타입 등의 정보를 포함합니다.
     */
    @NotNull
    @JsonProperty("device_model")
    @Valid
    private TopologyDeviceModel deviceModel;

    /**
     * 온라인 상태
     * 
     * 디바이스의 현재 온라인 연결 상태를 나타냅니다.
     * true는 온라인, false는 오프라인 상태를 의미합니다.
     */
    @NotNull
    @Schema(description = "온라인 상태")
    @JsonProperty("online_status")
    private Boolean onlineStatus;

    /**
     * 사용자 ID
     * 
     * 디바이스를 사용하는 사용자의 고유 식별자입니다.
     * UUID 형식으로 제공됩니다.
     */
    @Schema(description = "디바이스를 사용하는 사용자의 ID", format = "uuid")
    @JsonProperty("user_id")
    private String userId;

    /**
     * 사용자 닉네임
     * 
     * 디바이스를 사용하는 사용자의 닉네임 또는 별칭입니다.
     * 사용자를 쉽게 식별할 수 있도록 하는 이름입니다.
     * 
     * 예시: "admin"
     */
    @NotNull
    @Schema(description = "디바이스를 사용하는 사용자의 닉네임", example = "admin")
    @JsonProperty("user_callsign")
    private String userCallsign;

    /**
     * 디바이스 아이콘 URL
     * 
     * 지도상에 표시될 디바이스 아이콘의 URL 정보입니다.
     * 일반 상태와 선택 상태의 아이콘 URL을 포함합니다.
     */
    @NotNull
    @JsonProperty("icon_urls")
    @Valid
    private DeviceIconUrl iconUrls;

    public DeviceTopology() {
    }

    @Override
    public String toString() {
        return "DeviceTopology{" +
                "sn='" + sn + '\'' +
                ", deviceCallsign='" + deviceCallsign + '\'' +
                ", deviceModel=" + deviceModel +
                ", onlineStatus=" + onlineStatus +
                ", userId='" + userId + '\'' +
                ", userCallsign='" + userCallsign + '\'' +
                ", iconUrls=" + iconUrls +
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
    public DeviceTopology setSn(String sn) {
        this.sn = sn;
        return this;
    }

    /**
     * 디바이스 닉네임을 반환합니다.
     * 
     * @return 디바이스 닉네임
     */
    public String getDeviceCallsign() {
        return deviceCallsign;
    }

    /**
     * 디바이스 닉네임을 설정합니다.
     * 
     * @param deviceCallsign 디바이스 닉네임
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public DeviceTopology setDeviceCallsign(String deviceCallsign) {
        this.deviceCallsign = deviceCallsign;
        return this;
    }

    /**
     * 디바이스 모델 정보를 반환합니다.
     * 
     * @return 디바이스 모델 정보
     */
    public TopologyDeviceModel getDeviceModel() {
        return deviceModel;
    }

    /**
     * 디바이스 모델 정보를 설정합니다.
     * 
     * @param deviceModel 디바이스 모델 정보
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public DeviceTopology setDeviceModel(TopologyDeviceModel deviceModel) {
        this.deviceModel = deviceModel;
        return this;
    }

    /**
     * 온라인 상태를 반환합니다.
     * 
     * @return 온라인 상태
     */
    public Boolean getOnlineStatus() {
        return onlineStatus;
    }

    /**
     * 온라인 상태를 설정합니다.
     * 
     * @param onlineStatus 온라인 상태
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public DeviceTopology setOnlineStatus(Boolean onlineStatus) {
        this.onlineStatus = onlineStatus;
        return this;
    }

    /**
     * 사용자 ID를 반환합니다.
     * 
     * @return 사용자 ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 사용자 ID를 설정합니다.
     * 
     * @param userId 사용자 ID
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public DeviceTopology setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    /**
     * 사용자 닉네임을 반환합니다.
     * 
     * @return 사용자 닉네임
     */
    public String getUserCallsign() {
        return userCallsign;
    }

    /**
     * 사용자 닉네임을 설정합니다.
     * 
     * @param userCallsign 사용자 닉네임
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public DeviceTopology setUserCallsign(String userCallsign) {
        this.userCallsign = userCallsign;
        return this;
    }

    /**
     * 디바이스 아이콘 URL을 반환합니다.
     * 
     * @return 디바이스 아이콘 URL
     */
    public DeviceIconUrl getIconUrls() {
        return iconUrls;
    }

    /**
     * 디바이스 아이콘 URL을 설정합니다.
     * 
     * @param iconUrls 디바이스 아이콘 URL
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public DeviceTopology setIconUrls(DeviceIconUrl iconUrls) {
        this.iconUrls = iconUrls;
        return this;
    }
}
