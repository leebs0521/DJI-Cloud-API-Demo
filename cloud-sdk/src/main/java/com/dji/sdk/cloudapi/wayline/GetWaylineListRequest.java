package com.dji.sdk.cloudapi.wayline;

import com.dji.sdk.cloudapi.device.DeviceEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 웨이라인 목록 조회 요청 클래스
 * 
 * 이 클래스는 웨이라인 파일 목록을 조회하기 위한 요청 매개변수를 정의합니다.
 * 페이지네이션, 필터링, 정렬 등의 다양한 조회 조건을 지원하여
 * 효율적인 웨이라인 파일 관리가 가능합니다.
 * 
 * 주요 구성 요소:
 * - favorited: 즐겨찾기 여부
 * - orderBy: 정렬 조건
 * - page: 현재 페이지
 * - pageSize: 페이지 크기
 * - templateType: 웨이라인 템플릿 타입
 * - actionType: 액션 타입
 * - droneModelKeys: 드론 모델 키
 * - payloadModelKey: 페이로드 모델 키
 * - key: 검색 키워드
 * 
 * 이 클래스는 웨이라인 파일 목록 조회 시 필요한
 * 모든 매개변수를 포함하고 있습니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/8
 */
@Schema(description = "웨이라인 파일 목록 조회 매개변수")
public class GetWaylineListRequest {

    /**
     * 웨이라인 파일 즐겨찾기 여부
     * 
     * 웨이라인 파일이 즐겨찾기로 설정되어 있는지 여부를 나타냅니다.
     * true: 즐겨찾기된 파일만 조회
     * false: 즐겨찾기되지 않은 파일만 조회
     * null: 모든 파일 조회
     */
    @Parameter(name = "favorited", description = "웨이라인 파일 즐겨찾기 여부")
    private Boolean favorited;

    /**
     * 정렬 조건
     * 
     * 웨이라인 파일 목록의 정렬 기준을 정의합니다.
     * 파일명, 업데이트 시간, 생성 시간 등을 기준으로 정렬할 수 있습니다.
     * 
     * Pilot2 지원 값: name, update_time, create_time
     * 정렬 방향: asc(오름차순), desc(내림차순)
     */
    @NotNull
    @JsonProperty("order_by")
    @Parameter(name = "order_by", description = "정렬 필드명", example = "update_time desc",
            schema = @Schema(allowableValues = {"name desc", "name asc", "update_time desc", "update_time asc", "create_time desc", "create_time asc"}))
    @Valid
    private GetWaylineListOrderBy orderBy;

    /**
     * 현재 페이지
     * 
     * 조회할 페이지 번호입니다.
     * 최소값은 1이며, 기본값은 1입니다.
     */
    @Min(1)
    @Parameter(name = "page", description = "현재 페이지", schema = @Schema(defaultValue = "1", type = "int"))
    private int page = 1;

    /**
     * 페이지 크기
     * 
     * 한 페이지에 표시할 항목의 개수입니다.
     * 최소값은 1이며, 기본값은 10입니다.
     */
    @Min(1)
    @JsonProperty("page_size")
    @Parameter(name = "page_size", description = "페이지 크기", schema = @Schema(defaultValue = "10", type = "int"))
    private int pageSize = 10;

    /**
     * 웨이라인 템플릿 타입 컬렉션
     * 
     * 조회할 웨이라인 템플릿 타입들의 리스트입니다.
     * 최소 1개 이상의 템플릿 타입이 포함되어야 합니다.
     * 
     * 예시: [0] (웨이포인트 비행)
     */
    @Size(min = 1)
    @JsonProperty("template_type")
    @Parameter(name = "template_type", description = "웨이라인 템플릿 타입 컬렉션", example = "[0]")
    private List<WaylineTypeEnum> templateType;

    /**
     * 액션 타입
     * 
     * 웨이라인 액션 타입을 정의합니다.
     * 1: AI 스팟 체크 웨이라인 활성화
     * 이 필드가 없으면 모든 웨이라인을 의미합니다.
     */
    @JsonProperty("action_type")
    @Parameter(name = "action_type", description = "웨이라인 액션 타입", example = "1")
    private ActionTypeEnum actionType;

    /**
     * 선택된 드론 모델들
     * 
     * 조회할 드론 디바이스 제품 열거형들의 리스트입니다.
     * 특정 드론 모델과 호환되는 웨이라인 파일만 조회할 수 있습니다.
     * 
     * 예시: ["0-67-0"]
     */
    @JsonProperty("drone_model_keys")
    @Schema(name = "drone_model_keys", description = "드론 디바이스 제품 열거형", example = "[\"0-67-0\"]")
    private List<DeviceEnum> droneModelKeys;

    /**
     * 선택된 페이로드 모델들
     * 
     * 조회할 페이로드 디바이스 제품 열거형들의 리스트입니다.
     * 특정 페이로드 모델과 호환되는 웨이라인 파일만 조회할 수 있습니다.
     * 
     * 예시: ["1-53-0"]
     */
    @JsonProperty("payload_model_key")
    @Schema(name = "payload_model_key", description = "페이로드 디바이스 제품 열거형", example = "[\"1-53-0\"]")
    private List<DeviceEnum> payloadModelKey;

    /**
     * 웨이라인 이름 필터
     * 
     * 웨이라인 파일명으로 필터링하기 위한 검색 키워드입니다.
     * 파일명에 이 키워드가 포함된 웨이라인 파일만 조회됩니다.
     * 
     * 예시: "waypoint"
     */
    @JsonProperty("key")
    @Schema(name = "key", description = "웨이라인 파일명", example = "waypoint")
    private String key;

    public GetWaylineListRequest() {
    }

    @Override
    public String toString() {
        return "GetWaylineListRequest{" +
                "favorited=" + favorited +
                ", orderBy='" + orderBy + '\'' +
                ", page=" + page +
                ", pageSize=" + pageSize +
                ", templateType=" + templateType +
                ", actionType=" + actionType +
                ", droneModelKeys=" + droneModelKeys +
                ", payloadModelKey=" + payloadModelKey +
                ", key='" + key + '\'' +
                '}';
    }

    /**
     * 웨이라인 파일 즐겨찾기 여부를 반환합니다.
     * 
     * @return 웨이라인 파일 즐겨찾기 여부
     */
    public Boolean getFavorited() {
        return favorited;
    }

    /**
     * 웨이라인 파일 즐겨찾기 여부를 설정합니다.
     * 
     * @param favorited 웨이라인 파일 즐겨찾기 여부
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public GetWaylineListRequest setFavorited(Boolean favorited) {
        this.favorited = favorited;
        return this;
    }

    /**
     * 정렬 조건을 반환합니다.
     * 
     * @return 정렬 조건
     */
    public GetWaylineListOrderBy getOrderBy() {
        return orderBy;
    }

    /**
     * 정렬 조건을 설정합니다.
     * 
     * @param orderBy 정렬 조건
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public GetWaylineListRequest setOrderBy(GetWaylineListOrderBy orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    /**
     * 현재 페이지를 반환합니다.
     * 
     * @return 현재 페이지
     */
    public int getPage() {
        return page;
    }

    /**
     * 현재 페이지를 설정합니다.
     * 
     * @param page 현재 페이지
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public GetWaylineListRequest setPage(int page) {
        this.page = page;
        return this;
    }

    /**
     * 페이지 크기를 반환합니다.
     * 
     * @return 페이지 크기
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 페이지 크기를 설정합니다.
     * 
     * @param pageSize 페이지 크기
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public GetWaylineListRequest setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * 웨이라인 템플릿 타입 컬렉션을 반환합니다.
     * 
     * @return 웨이라인 템플릿 타입 컬렉션
     */
    public List<WaylineTypeEnum> getTemplateType() {
        return templateType;
    }

    /**
     * 웨이라인 템플릿 타입 컬렉션을 설정합니다.
     * 
     * @param templateType 웨이라인 템플릿 타입 컬렉션
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public GetWaylineListRequest setTemplateType(List<WaylineTypeEnum> templateType) {
        this.templateType = templateType;
        return this;
    }

    /**
     * 액션 타입을 반환합니다.
     * 
     * @return 액션 타입
     */
    public ActionTypeEnum getActionType() {
        return actionType;
    }

    /**
     * 액션 타입을 설정합니다.
     * 
     * @param actionType 액션 타입
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public GetWaylineListRequest setActionType(ActionTypeEnum actionType) {
        this.actionType = actionType;
        return this;
    }

    /**
     * 선택된 드론 모델들을 반환합니다.
     * 
     * @return 선택된 드론 모델들
     */
    public List<DeviceEnum> getDroneModelKeys() {
        return droneModelKeys;
    }

    /**
     * 선택된 드론 모델들을 설정합니다.
     * 
     * @param droneModelKeys 선택된 드론 모델들
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public GetWaylineListRequest setDroneModelKeys(List<DeviceEnum> droneModelKeys) {
        this.droneModelKeys = droneModelKeys;
        return this;
    }

    /**
     * 선택된 페이로드 모델들을 반환합니다.
     * 
     * @return 선택된 페이로드 모델들
     */
    public List<DeviceEnum> getPayloadModelKey() {
        return payloadModelKey;
    }

    /**
     * 선택된 페이로드 모델들을 설정합니다.
     * 
     * @param payloadModelKey 선택된 페이로드 모델들
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public GetWaylineListRequest setPayloadModelKey(List<DeviceEnum> payloadModelKey) {
        this.payloadModelKey = payloadModelKey;
        return this;
    }

    /**
     * 웨이라인 이름 필터를 반환합니다.
     * 
     * @return 웨이라인 이름 필터
     */
    public String getKey() {
        return key;
    }

    /**
     * 웨이라인 이름 필터를 설정합니다.
     * 
     * @param key 웨이라인 이름 필터
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public GetWaylineListRequest setKey(String key) {
        this.key = key;
        return this;
    }
}
