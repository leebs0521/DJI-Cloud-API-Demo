package com.dji.sdk.cloudapi.tsa;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 디바이스 토폴로지 리스트 클래스
 * 
 * 이 클래스는 TSA(Threat and Situation Awareness) 시스템에서 디바이스 토폴로지 정보의
 * 리스트를 관리합니다. 드론 디바이스와 게이트웨이 디바이스의 토폴로지 정보를
 * 분리하여 저장하고 관리합니다.
 * 
 * 주요 구성 요소:
 * - hosts: 드론 디바이스 토폴로지 컬렉션
 * - parents: 게이트웨이 디바이스 토폴로지 컬렉션
 * 
 * 이 클래스는 TSA 시스템에서 드론과 게이트웨이의 토폴로지 정보를
 * 체계적으로 관리하고 제공하는 데 사용됩니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/12/8
 */
@Schema(description = "디바이스 토폴로지 리스트")
public class TopologyList {

    /**
     * 드론 디바이스 토폴로지 컬렉션
     * 
     * 드론 디바이스들의 토폴로지 정보를 담고 있는 리스트입니다.
     * 각 드론의 위치, 상태, 사용자 정보 등을 포함합니다.
     */
    @Schema(description = "드론 디바이스 토폴로지 컬렉션")
    @NotNull
    private List<@Valid DeviceTopology> hosts;

    /**
     * 게이트웨이 디바이스 토폴로지 컬렉션
     * 
     * 게이트웨이 디바이스들의 토폴로지 정보를 담고 있는 리스트입니다.
     * 각 게이트웨이의 위치, 상태, 사용자 정보 등을 포함합니다.
     */
    @Schema(description = "게이트웨이 디바이스 토폴로지 컬렉션")
    @NotNull
    private List<@Valid DeviceTopology> parents;

    public TopologyList() {
    }

    @Override
    public String toString() {
        return "TopologyList{" +
                "hosts=" + hosts +
                ", parents=" + parents +
                '}';
    }

    /**
     * 드론 디바이스 토폴로지 컬렉션을 반환합니다.
     * 
     * @return 드론 디바이스 토폴로지 컬렉션
     */
    public List<DeviceTopology> getHosts() {
        return hosts;
    }

    /**
     * 드론 디바이스 토폴로지 컬렉션을 설정합니다.
     * 
     * @param hosts 드론 디바이스 토폴로지 컬렉션
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public TopologyList setHosts(List<DeviceTopology> hosts) {
        this.hosts = hosts;
        return this;
    }

    /**
     * 게이트웨이 디바이스 토폴로지 컬렉션을 반환합니다.
     * 
     * @return 게이트웨이 디바이스 토폴로지 컬렉션
     */
    public List<DeviceTopology> getParents() {
        return parents;
    }

    /**
     * 게이트웨이 디바이스 토폴로지 컬렉션을 설정합니다.
     * 
     * @param parents 게이트웨이 디바이스 토폴로지 컬렉션
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public TopologyList setParents(List<DeviceTopology> parents) {
        this.parents = parents;
        return this;
    }
}
