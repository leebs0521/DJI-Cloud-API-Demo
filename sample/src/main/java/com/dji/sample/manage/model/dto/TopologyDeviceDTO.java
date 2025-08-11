package com.dji.sample.manage.model.dto;

import com.dji.sdk.cloudapi.device.DeviceDomainEnum;
import com.dji.sdk.cloudapi.tsa.DeviceIconUrl;
import com.dji.sdk.cloudapi.tsa.DeviceTopology;
import com.dji.sdk.cloudapi.tsa.TopologyDeviceModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 디바이스 토폴로지 정보 전송 객체 (DTO) 클래스
 * 
 * DJI Cloud API의 디바이스 토폴로지 정보를 전송하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 디바이스 토폴로지 기본 정보 관리
 *    - 디바이스 시리얼 번호 및 호출명 관리
 *    - 디바이스 모델 및 사용자 정보 관리
 *    - 디바이스 온라인 상태 및 아이콘 URL 관리
 * 
 * 2. 디바이스 네트워크 구조 관리
 *    - 디바이스 모델 정보 관리
 *    - 디바이스 바인딩 상태 관리
 *    - 게이트웨이 시리얼 번호 관리
 *    - 디바이스 도메인 정보 관리
 * 
 * 3. 디바이스 계층 구조 확장
 *    - DeviceTopology 클래스 상속으로 기본 토폴로지 기능 확장
 *    - 추가적인 디바이스 속성 제공
 *    - 체이닝 메서드 패턴을 통한 편리한 객체 생성
 * 
 * 4. 디바이스 관계 및 연결 정보 관리
 *    - 부모-자식 디바이스 관계 표현
 *    - 게이트웨이를 통한 디바이스 연결 정보
 *    - 디바이스 도메인별 분류 정보
 * 
 * 이 클래스는 디바이스 네트워크의 토폴로지 구조를 표현하며,
 * 디바이스 간의 연결 관계와 계층 구조를 관리하는
 * 확장된 토폴로지 정보를 제공합니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/12/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TopologyDeviceDTO extends DeviceTopology {

    /**
     * 디바이스 모델
     * 디바이스의 모델명 또는 모델 식별자
     */
    private String model;

    /**
     * 바인딩 상태
     * 디바이스의 바인딩 여부 (true: 바인딩됨, false: 바인딩되지 않음)
     */
    private Boolean boundStatus;

    /**
     * 게이트웨이 시리얼 번호
     * 현재 디바이스가 연결된 게이트웨이의 시리얼 번호
     */
    private String gatewaySn;

    /**
     * 디바이스 도메인
     * 디바이스가 속한 도메인 (예: AIRCRAFT, DOCK 등)
     */
    private DeviceDomainEnum domain;

    
    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "TopologyDeviceDTO{" +
                "model='" + model + '\'' +
                ", boundStatus=" + boundStatus +
                ", gatewaySn='" + gatewaySn + '\'' +
                ", domain=" + domain +
                '}';
    }

    /**
     * 디바이스 시리얼 번호를 반환합니다.
     * 
     * @return 디바이스 시리얼 번호
     */
    @Override
    public String getSn() {
        return super.getSn();
    }

    /**
     * 디바이스 시리얼 번호를 설정하고 현재 객체를 반환합니다.
     * 
     * @param sn 설정할 디바이스 시리얼 번호
     * @return 현재 TopologyDeviceDTO 객체
     */
    @Override
    public TopologyDeviceDTO setSn(String sn) {
        super.setSn(sn);
        return this;
    }

    /**
     * 디바이스 호출명을 반환합니다.
     * 
     * @return 디바이스 호출명
     */
    @Override
    public String getDeviceCallsign() {
        return super.getDeviceCallsign();
    }

    /**
     * 디바이스 호출명을 설정하고 현재 객체를 반환합니다.
     * 
     * @param deviceCallsign 설정할 디바이스 호출명
     * @return 현재 TopologyDeviceDTO 객체
     */
    @Override
    public TopologyDeviceDTO setDeviceCallsign(String deviceCallsign) {
        super.setDeviceCallsign(deviceCallsign);
        return this;
    }

    /**
     * 디바이스 모델 정보를 반환합니다.
     * 
     * @return 디바이스 모델 정보
     */
    @Override
    public TopologyDeviceModel getDeviceModel() {
        return super.getDeviceModel();
    }

    /**
     * 디바이스 모델 정보를 설정하고 현재 객체를 반환합니다.
     * 
     * @param deviceModel 설정할 디바이스 모델 정보
     * @return 현재 TopologyDeviceDTO 객체
     */
    @Override
    public TopologyDeviceDTO setDeviceModel(TopologyDeviceModel deviceModel) {
        super.setDeviceModel(deviceModel);
        return this;
    }

    /**
     * 디바이스 온라인 상태를 반환합니다.
     * 
     * @return 디바이스 온라인 상태 (true: 온라인, false: 오프라인)
     */
    @Override
    public Boolean getOnlineStatus() {
        return super.getOnlineStatus();
    }

    /**
     * 디바이스 온라인 상태를 설정하고 현재 객체를 반환합니다.
     * 
     * @param onlineStatus 설정할 온라인 상태
     * @return 현재 TopologyDeviceDTO 객체
     */
    @Override
    public TopologyDeviceDTO setOnlineStatus(Boolean onlineStatus) {
        super.setOnlineStatus(onlineStatus);
        return this;
    }

    /**
     * 사용자 ID를 반환합니다.
     * 
     * @return 사용자 ID
     */
    @Override
    public String getUserId() {
        return super.getUserId();
    }

    /**
     * 사용자 ID를 설정하고 현재 객체를 반환합니다.
     * 
     * @param userId 설정할 사용자 ID
     * @return 현재 TopologyDeviceDTO 객체
     */
    @Override
    public TopologyDeviceDTO setUserId(String userId) {
        super.setUserId(userId);
        return this;
    }

    /**
     * 사용자 호출명을 반환합니다.
     * 
     * @return 사용자 호출명
     */
    @Override
    public String getUserCallsign() {
        return super.getUserCallsign();
    }

    /**
     * 사용자 호출명을 설정하고 현재 객체를 반환합니다.
     * 
     * @param userCallsign 설정할 사용자 호출명
     * @return 현재 TopologyDeviceDTO 객체
     */
    @Override
    public TopologyDeviceDTO setUserCallsign(String userCallsign) {
        super.setUserCallsign(userCallsign);
        return this;
    }

    /**
     * 디바이스 아이콘 URL을 반환합니다.
     * 
     * @return 디바이스 아이콘 URL
     */
    @Override
    public DeviceIconUrl getIconUrls() {
        return super.getIconUrls();
    }

    /**
     * 디바이스 아이콘 URL을 설정하고 현재 객체를 반환합니다.
     * 
     * @param iconUrls 설정할 디바이스 아이콘 URL
     * @return 현재 TopologyDeviceDTO 객체
     */
    @Override
    public TopologyDeviceDTO setIconUrls(DeviceIconUrl iconUrls) {
        super.setIconUrls(iconUrls);
        return this;
    }

    /**
     * 디바이스 모델을 반환합니다.
     * 
     * @return 디바이스 모델
     */
    public String getModel() {
        return model;
    }

    /**
     * 디바이스 모델을 설정하고 현재 객체를 반환합니다.
     * 
     * @param model 설정할 디바이스 모델
     * @return 현재 TopologyDeviceDTO 객체
     */
    public TopologyDeviceDTO setModel(String model) {
        this.model = model;
        return this;
    }

    /**
     * 바인딩 상태를 반환합니다.
     * 
     * @return 바인딩 상태
     */
    public Boolean getBoundStatus() {
        return boundStatus;
    }

    /**
     * 바인딩 상태를 설정하고 현재 객체를 반환합니다.
     * 
     * @param boundStatus 설정할 바인딩 상태
     * @return 현재 TopologyDeviceDTO 객체
     */
    public TopologyDeviceDTO setBoundStatus(Boolean boundStatus) {
        this.boundStatus = boundStatus;
        return this;
    }

    /**
     * 게이트웨이 시리얼 번호를 반환합니다.
     * 
     * @return 게이트웨이 시리얼 번호
     */
    public String getGatewaySn() {
        return gatewaySn;
    }

    /**
     * 게이트웨이 시리얼 번호를 설정하고 현재 객체를 반환합니다.
     * 
     * @param gatewaySn 설정할 게이트웨이 시리얼 번호
     * @return 현재 TopologyDeviceDTO 객체
     */
    public TopologyDeviceDTO setGatewaySn(String gatewaySn) {
        this.gatewaySn = gatewaySn;
        return this;
    }

    /**
     * 디바이스 도메인을 반환합니다.
     * 
     * @return 디바이스 도메인
     */
    public DeviceDomainEnum getDomain() {
        return domain;
    }

    /**
     * 디바이스 도메인을 설정하고 현재 객체를 반환합니다.
     * 
     * @param domain 설정할 디바이스 도메인
     * @return 현재 TopologyDeviceDTO 객체
     */
    public TopologyDeviceDTO setDomain(DeviceDomainEnum domain) {
        this.domain = domain;
        return this;
    }
}
