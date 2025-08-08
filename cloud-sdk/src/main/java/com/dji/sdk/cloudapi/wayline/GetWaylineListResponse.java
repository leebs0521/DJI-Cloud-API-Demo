package com.dji.sdk.cloudapi.wayline;

import com.dji.sdk.cloudapi.device.DeviceEnum;
import com.dji.sdk.common.BaseModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 웨이라인 목록 조회 응답 클래스
 * 
 * 이 클래스는 웨이라인 파일 목록 조회 결과를 나타내는 응답 데이터를 정의합니다.
 * 웨이라인 파일의 기본 정보, 메타데이터, 사용자 정보 등을 포함하여
 * 클라이언트에게 완전한 웨이라인 파일 정보를 제공합니다.
 * 
 * 주요 구성 요소:
 * - name: 웨이라인 파일명
 * - id: 웨이라인 파일 ID
 * - droneModelKey: 드론 모델 키
 * - sign: 파일 서명
 * - payloadModelKeys: 페이로드 모델 키들
 * - favorited: 즐겨찾기 여부
 * - templateTypes: 템플릿 타입들
 * - objectKey: 객체 키
 * - username: 업로더 사용자명
 * - updateTime: 업데이트 시간
 * - createTime: 생성 시간
 * - actionType: 액션 타입
 * 
 * 이 클래스는 웨이라인 파일 목록 조회 시 각 파일의
 * 상세 정보를 제공하는 데 사용됩니다.
 * 
 * @author sean
 * @version 0.3
 * @date 2021/12/22
 */
@Schema(description = "웨이라인 파일 데이터")
public class GetWaylineListResponse extends BaseModel {

    /**
     * 웨이라인 파일명
     * 
     * 웨이라인 파일의 이름입니다.
     * 특수 문자 제한이 있으며, 파일 시스템에서 사용할 수 없는
     * 문자들은 포함할 수 없습니다.
     * 
     * 제약 조건: 특수 문자 제한 (<>:"/|?*._\\ 제외)
     */
    @NotNull
    @Schema(description = "웨이라인 파일명", example = "waylineFile")
    @Pattern(regexp = "^[^<>:\"/|?*._\\\\]+$")
    private String name;

    /**
     * 웨이라인 파일 ID
     * 
     * 웨이라인 파일을 고유하게 식별하는 UUID입니다.
     * 데이터베이스에서 웨이라인 파일을 조회하고 관리하는 데 사용됩니다.
     * 
     * 제약 조건: UUID 형식 (8-4-4-4-12 자리)
     */
    @NotNull
    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$")
    @Schema(description = "웨이라인 파일 ID", format = "uuid")
    private String id;

    /**
     * 드론 디바이스 제품 열거형
     * 
     * 웨이라인 파일이 지원하는 드론의 모델 정보입니다.
     * 드론의 하드웨어 사양과 호환성을 확인하는 데 사용됩니다.
     * 
     * 예시: "0-67-0"
     */
    @NotNull
    @JsonProperty("drone_model_key")
    @Schema(description = "드론 디바이스 제품 열거형", example = "0-67-0")
    private DeviceEnum droneModelKey;

    /**
     * 파일 서명
     * 
     * 웨이라인 파일의 무결성을 확인하기 위한 서명값입니다.
     * 파일이 변경되지 않았음을 보장하는 데 사용됩니다.
     */
    private String sign;

    /**
     * 페이로드 디바이스 제품 열거형 리스트
     * 
     * 웨이라인 파일이 지원하는 페이로드 디바이스들의 모델 정보입니다.
     * 최소 1개 이상의 페이로드가 포함되어야 합니다.
     * 
     * 제약 조건:
     * - 최소 1개 이상의 페이로드 포함
     * 
     * 예시: ["1-53-0"]
     */
    @NotNull
    @Size(min = 1)
    @JsonProperty("payload_model_keys")
    @Schema(description = "페이로드 디바이스 제품 열거형", example = "[\"1-53-0\"]")
    private List<DeviceEnum> payloadModelKeys;

    /**
     * 웨이라인 파일 즐겨찾기 여부
     * 
     * 웨이라인 파일이 사용자의 즐겨찾기로 설정되어 있는지 여부입니다.
     * true: 즐겨찾기됨
     * false: 즐겨찾기되지 않음
     */
    @NotNull
    @Schema(description = "웨이라인 파일 즐겨찾기 여부")
    private Boolean favorited;

    /**
     * 웨이라인 템플릿 컬렉션
     * 
     * 웨이라인 파일이 지원하는 템플릿 타입들의 리스트입니다.
     * 최소 1개 이상의 템플릿 타입이 포함되어야 합니다.
     * 
     * 제약 조건:
     * - 최소 1개 이상의 템플릿 타입 포함
     * 
     * 예시: [0] (웨이포인트 비행)
     */
    @NotNull
    @Size(min = 1)
    @Schema(description = "웨이라인 템플릿 컬렉션", example = "[0]")
    @JsonProperty("template_types")
    private List<WaylineTypeEnum> templateTypes;

    /**
     * 버킷 내 객체 키
     * 
     * 클라우드 스토리지 버킷에서 웨이라인 파일의 위치를 나타내는 키입니다.
     * 파일의 경로와 파일명을 포함한 전체 경로입니다.
     * 
     * 예시: "wayline/waylineFile.kmz"
     */
    @NotNull
    @Schema(description = "버킷 내 객체의 키", example = "wayline/waylineFile.kmz")
    @JsonProperty("object_key")
    private String objectKey;

    /**
     * 업로더 사용자명
     * 
     * 웨이라인 파일을 업로드한 사용자의 이름입니다.
     * 파일의 소유권과 관리 권한을 확인하는 데 사용됩니다.
     * 
     * 예시: "admin"
     */
    @NotNull
    @JsonProperty("user_name")
    @Schema(description = "업로더의 사용자명", example = "admin")
    private String username;

    /**
     * 업데이트 시간 (밀리초)
     * 
     * 웨이라인 파일이 마지막으로 업데이트된 시간입니다.
     * 밀리초 단위의 타임스탬프로 저장됩니다.
     * 
     * 제약 조건:
     * - 최소값: 123456789012L
     * - 데이터베이스 테이블에 'update_time' 필드가 반드시 존재해야 함
     * 
     * 예시: 123456789012
     */
    @NotNull
    @Min(123456789012L)
    @Schema(description = "업데이트 시간 (밀리초). 테이블에 'update_time' 필드가 반드시 존재해야 합니다.", example = "123456789012")
    @JsonProperty("update_time")
    private Long updateTime;

    /**
     * 생성 시간 (밀리초)
     * 
     * 웨이라인 파일이 생성된 시간입니다.
     * 밀리초 단위의 타임스탬프로 저장됩니다.
     * 
     * 제약 조건:
     * - 최소값: 123456789012L
     * - 데이터베이스 테이블에 'create_time' 필드가 반드시 존재해야 함
     * 
     * 예시: 123456789012
     */
    @NotNull
    @Min(123456789012L)
    @Schema(description = "생성 시간 (밀리초). 테이블에 'create_time' 필드가 반드시 존재해야 합니다.", example = "123456789012")
    @JsonProperty("create_time")
    private Long createTime;

    /**
     * 액션 타입
     * 
     * 웨이라인 액션 타입을 정의합니다.
     * AI 스팟 체크 등의 특별한 기능을 활성화하는 데 사용됩니다.
     * 
     * 예시: 1 (AI 스팟 체크 활성화)
     */
    @JsonProperty("action_type")
    @Parameter(name = "action_type", description = "웨이라인 액션 타입", example = "1")
    private ActionTypeEnum actionType;

    public GetWaylineListResponse() {
    }

    @Override
    public String toString() {
        return "GetWaylineListResponse{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", droneModelKey=" + droneModelKey +
                ", sign='" + sign + '\'' +
                ", payloadModelKeys=" + payloadModelKeys +
                ", favorited=" + favorited +
                ", templateTypes=" + templateTypes +
                ", objectKey='" + objectKey + '\'' +
                ", username='" + username + '\'' +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                ", actionType=" + actionType +
                '}';
    }

    /**
     * 웨이라인 파일명을 반환합니다.
     * 
     * @return 웨이라인 파일명
     */
    public String getName() {
        return name;
    }

    /**
     * 웨이라인 파일명을 설정합니다.
     * 
     * @param name 웨이라인 파일명
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public GetWaylineListResponse setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * 웨이라인 파일 ID를 반환합니다.
     * 
     * @return 웨이라인 파일 ID
     */
    public String getId() {
        return id;
    }

    /**
     * 웨이라인 파일 ID를 설정합니다.
     * 
     * @param id 웨이라인 파일 ID
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public GetWaylineListResponse setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * 드론 디바이스 제품 열거형을 반환합니다.
     * 
     * @return 드론 디바이스 제품 열거형
     */
    public DeviceEnum getDroneModelKey() {
        return droneModelKey;
    }

    /**
     * 드론 디바이스 제품 열거형을 설정합니다.
     * 
     * @param droneModelKey 드론 디바이스 제품 열거형
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public GetWaylineListResponse setDroneModelKey(DeviceEnum droneModelKey) {
        this.droneModelKey = droneModelKey;
        return this;
    }

    /**
     * 파일 서명을 반환합니다.
     * 
     * @return 파일 서명
     */
    public String getSign() {
        return sign;
    }

    /**
     * 파일 서명을 설정합니다.
     * 
     * @param sign 파일 서명
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public GetWaylineListResponse setSign(String sign) {
        this.sign = sign;
        return this;
    }

    /**
     * 페이로드 디바이스 제품 열거형 리스트를 반환합니다.
     * 
     * @return 페이로드 디바이스 제품 열거형 리스트
     */
    public List<DeviceEnum> getPayloadModelKeys() {
        return payloadModelKeys;
    }

    /**
     * 페이로드 디바이스 제품 열거형 리스트를 설정합니다.
     * 
     * @param payloadModelKeys 페이로드 디바이스 제품 열거형 리스트
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public GetWaylineListResponse setPayloadModelKeys(List<DeviceEnum> payloadModelKeys) {
        this.payloadModelKeys = payloadModelKeys;
        return this;
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
    public GetWaylineListResponse setFavorited(Boolean favorited) {
        this.favorited = favorited;
        return this;
    }

    /**
     * 웨이라인 템플릿 컬렉션을 반환합니다.
     * 
     * @return 웨이라인 템플릿 컬렉션
     */
    public List<WaylineTypeEnum> getTemplateTypes() {
        return templateTypes;
    }

    /**
     * 웨이라인 템플릿 컬렉션을 설정합니다.
     * 
     * @param templateTypes 웨이라인 템플릿 컬렉션
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public GetWaylineListResponse setTemplateTypes(List<WaylineTypeEnum> templateTypes) {
        this.templateTypes = templateTypes;
        return this;
    }

    /**
     * 버킷 내 객체 키를 반환합니다.
     * 
     * @return 버킷 내 객체 키
     */
    public String getObjectKey() {
        return objectKey;
    }

    /**
     * 버킷 내 객체 키를 설정합니다.
     * 
     * @param objectKey 버킷 내 객체 키
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public GetWaylineListResponse setObjectKey(String objectKey) {
        this.objectKey = objectKey;
        return this;
    }

    /**
     * 업로더 사용자명을 반환합니다.
     * 
     * @return 업로더 사용자명
     */
    public String getUsername() {
        return username;
    }

    /**
     * 업로더 사용자명을 설정합니다.
     * 
     * @param username 업로더 사용자명
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public GetWaylineListResponse setUsername(String username) {
        this.username = username;
        return this;
    }

    /**
     * 업데이트 시간을 반환합니다.
     * 
     * @return 업데이트 시간 (밀리초)
     */
    public Long getUpdateTime() {
        return updateTime;
    }

    /**
     * 업데이트 시간을 설정합니다.
     * 
     * @param updateTime 업데이트 시간 (밀리초)
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public GetWaylineListResponse setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    /**
     * 생성 시간을 반환합니다.
     * 
     * @return 생성 시간 (밀리초)
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * 생성 시간을 설정합니다.
     * 
     * @param createTime 생성 시간 (밀리초)
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public GetWaylineListResponse setCreateTime(Long createTime) {
        this.createTime = createTime;
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
    public GetWaylineListResponse setActionType(ActionTypeEnum actionType) {
        this.actionType = actionType;
        return this;
    }
}
