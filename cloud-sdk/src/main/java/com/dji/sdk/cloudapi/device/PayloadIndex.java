package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKErrorEnum;
import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Objects;

/**
 * 페이로드 인덱스 클래스
 * 
 * 이 클래스는 드론에 장착된 페이로드(카메라, 센서 등)를 식별하기 위한 인덱스를 관리합니다.
 * 페이로드의 타입, 서브타입, 위치 정보를 조합하여 고유한 식별자를 생성합니다.
 * 형식: "타입-서브타입-위치" (예: "0-0-0")
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/29
 */
@Schema(name = "PayloadIndex", description = "페이로드 인덱스 정보")
public class PayloadIndex {

    /**
     * 페이로드 타입 (카메라, 센서 등)
     */
    @NotNull
    @Schema(name = "type", description = "페이로드 타입 (카메라, 센서 등)")
    private DeviceTypeEnum type;

    /**
     * 페이로드 서브타입 (모델별 세부 구분)
     */
    @NotNull
    @Schema(name = "subType", description = "페이로드 서브타입 (모델별 세부 구분)")
    private DeviceSubTypeEnum subType;

    /**
     * 페이로드 위치 (드론 내 장착 위치)
     */
    @NotNull
    @Schema(name = "position", description = "페이로드 위치 (드론 내 장착 위치)")
    private PayloadPositionEnum position;

    /**
     * 기본 생성자
     */
    public PayloadIndex() {
    }

    /**
     * 페이로드 인덱스 문자열로부터 PayloadIndex 객체를 생성합니다.
     * 
     * @param payloadIndex "타입-서브타입-위치" 형식의 페이로드 인덱스 문자열
     * @throws CloudSDKException 페이로드 인덱스 형식이 올바르지 않은 경우
     */
    @JsonCreator
    public PayloadIndex(String payloadIndex) {
        Objects.requireNonNull(payloadIndex);
        int[] payloadIndexArr = Arrays.stream(payloadIndex.split("-")).mapToInt(Integer::parseInt).toArray();
        if (payloadIndexArr.length != 3) {
            throw new CloudSDKException(CloudSDKErrorEnum.INVALID_PARAMETER);
        }
        this.type = DeviceTypeEnum.find(payloadIndexArr[0]);
        this.subType = DeviceSubTypeEnum.find(payloadIndexArr[1]);
        this.position = PayloadPositionEnum.find(payloadIndexArr[2]);
    }

    /**
     * 페이로드 인덱스를 문자열로 반환합니다.
     * 
     * @return "타입-서브타입-위치" 형식의 페이로드 인덱스 문자열
     */
    @Override
    @JsonValue
    public String toString() {
        return String.format("%s-%s-%s", type.getType(), subType.getSubType(), position.getPosition());
    }

    /**
     * 페이로드 타입을 반환합니다.
     * 
     * @return 페이로드 타입
     */
    public DeviceTypeEnum getType() {
        return type;
    }

    /**
     * 페이로드 타입을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param type 설정할 페이로드 타입
     * @return 현재 PayloadIndex 객체
     */
    public PayloadIndex setType(DeviceTypeEnum type) {
        this.type = type;
        return this;
    }

    /**
     * 페이로드 서브타입을 반환합니다.
     * 
     * @return 페이로드 서브타입
     */
    public DeviceSubTypeEnum getSubType() {
        return subType;
    }

    /**
     * 페이로드 서브타입을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param subType 설정할 페이로드 서브타입
     * @return 현재 PayloadIndex 객체
     */
    public PayloadIndex setSubType(DeviceSubTypeEnum subType) {
        this.subType = subType;
        return this;
    }

    /**
     * 페이로드 위치를 반환합니다.
     * 
     * @return 페이로드 위치
     */
    public PayloadPositionEnum getPosition() {
        return position;
    }

    /**
     * 페이로드 위치를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param position 설정할 페이로드 위치
     * @return 현재 PayloadIndex 객체
     */
    public PayloadIndex setPosition(PayloadPositionEnum position) {
        this.position = position;
        return this;
    }
}
