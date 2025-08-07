package com.dji.sdk.cloudapi.device;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 페이로드 모델 상수 클래스
 * 
 * 이 클래스는 페이로드 관련 상수와 유틸리티 메서드를 제공합니다.
 * 페이로드 키, 모델과 위치 조합, 인덱스와 위치 조합 등을 관리합니다.
 * 
 * @author sean
 * @version 1.0
 * @date 2022/4/29
 */
public class PayloadModelConst {

    /**
     * 기본 생성자 (인스턴스화 방지)
     */
    private PayloadModelConst() {
    }

    /**
     * 페이로드 키 상수
     */
    public static final String PAYLOAD_KEY = "payload";

    /**
     * 모든 페이로드 모델과 위치의 조합을 반환합니다.
     * 
     * @return 페이로드 모델과 위치 조합 집합
     */
    public static Set<String> getAllModelWithPosition() {
        Set<String> position = Arrays.stream(PayloadPositionEnum.values()).map(PayloadPositionEnum::getPosition)
                .map(String::valueOf).collect(Collectors.toSet());
        return Arrays.stream(DeviceEnum.values()).filter(device -> DeviceDomainEnum.PAYLOAD == device.getDomain())
                .map(Enum::name).map(name -> name.replace("_CAMERA", ""))
                .flatMap(m -> position.stream().map(p -> m.concat("-").concat(p))).collect(Collectors.toSet());
    }

    /**
     * 모든 페이로드 인덱스와 위치의 조합을 반환합니다.
     * 
     * @return 페이로드 인덱스와 위치 조합 집합
     */
    public static Set<String> getAllIndexWithPosition() {
        Set<String> position = Arrays.stream(PayloadPositionEnum.values()).map(PayloadPositionEnum::getPosition)
                .map(String::valueOf).collect(Collectors.toSet());
        return Arrays.stream(DeviceEnum.values()).filter(device -> DeviceDomainEnum.PAYLOAD == device.getDomain())
                .map(device -> String.format("%d-%d", device.getType().getType(), device.getSubType().getSubType()))
                .flatMap(m -> position.stream().map(p -> m.concat("-").concat(p))).collect(Collectors.toSet());
    }

}
