package com.dji.sdk.cloudapi.tsa;

import com.dji.sdk.common.BaseModel;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 토폴로지 응답 데이터 클래스
 * 
 * 이 클래스는 TSA(Threat and Situation Awareness) 시스템에서 토폴로지 정보의
 * 응답 데이터를 정의합니다. BaseModel을 상속받아 유효성 검사를 지원하며,
 * 여러 토폴로지 리스트를 포함하는 응답 구조를 관리합니다.
 * 
 * 주요 구성 요소:
 * - list: 토폴로지 리스트 컬렉션
 * 
 * 이 클래스는 TSA 시스템에서 디바이스 토폴로지 정보를 API 응답으로
 * 제공할 때 사용되는 래퍼 클래스입니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/16
 */
@Schema(description = "토폴로지 응답 데이터")
public class TopologyResponse extends BaseModel {

    /**
     * 토폴로지 리스트 컬렉션
     * 
     * 여러 토폴로지 리스트를 담고 있는 컬렉션입니다.
     * 각 토폴로지 리스트는 드론과 게이트웨이의 토폴로지 정보를 포함합니다.
     */
    @NotNull
    private List<@Valid TopologyList> list;

    public TopologyResponse() {
    }

    @Override
    public String toString() {
        return "TopologyResponse{" +
                "list=" + list +
                '}';
    }

    /**
     * 토폴로지 리스트 컬렉션을 반환합니다.
     * 
     * @return 토폴로지 리스트 컬렉션
     */
    public List<TopologyList> getList() {
        return list;
    }

    /**
     * 토폴로지 리스트 컬렉션을 설정합니다.
     * 
     * @param list 토폴로지 리스트 컬렉션
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public TopologyResponse setList(List<TopologyList> list) {
        this.list = list;
        return this;
    }
}
