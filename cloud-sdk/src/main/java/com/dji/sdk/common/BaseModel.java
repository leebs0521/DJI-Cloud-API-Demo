package com.dji.sdk.common;

import com.dji.sdk.annotations.CloudSDKVersion;
import com.dji.sdk.config.version.GatewayManager;
import com.dji.sdk.exception.CloudSDKErrorEnum;
import com.dji.sdk.exception.CloudSDKException;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 기본 모델 클래스
 * 
 * 이 클래스는 SDK의 모든 모델 클래스들이 상속받는
 * 기본 클래스로, 공통적인 유효성 검사 기능을 제공합니다.
 * 
 * 주요 기능:
 * - Bean Validation을 통한 모델 유효성 검사
 * - CloudSDKVersion 어노테이션을 통한 버전 호환성 검사
 * - 필드별 지원 여부 확인
 * 
 * 이 클래스는 SDK의 모든 모델이 일관된
 * 유효성 검사 방식을 사용하도록 보장합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/23
 */
public class BaseModel {

    /**
     * Bean Validation 검증기
     * 
     * 모델의 유효성 검사를 위한 Validator 인스턴스입니다.
     */
    private final static Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 기본 유효성 검사를 수행합니다.
     * 
     * GatewayManager 없이 기본적인 유효성 검사를 실행합니다.
     */
    public void valid() {
        this.valid(null);
    }

    /**
     * 특정 필드의 속성을 확인합니다.
     * 
     * 지정된 필드가 현재 Gateway에서 지원되는지 확인합니다.
     * 
     * @param fieldName 확인할 필드 이름
     * @param gateway Gateway 관리자
     * @throws CloudSDKException 필드가 지원되지 않을 경우
     */
    public void checkProperty(String fieldName, GatewayManager gateway) {
        try {
            Field field = this.getClass().getDeclaredField(fieldName);
            CloudSDKVersion annotation = field.getDeclaredAnnotation(CloudSDKVersion.class);
            if (!gateway.isTypeSupport(annotation) || !gateway.isVersionSupport(annotation)) {
                throw new CloudSDKException(CloudSDKErrorEnum.DEVICE_PROPERTY_NOT_SUPPORT, fieldName);
            }
        } catch (NoSuchFieldException e) {
            throw new CloudSDKException(e);
        }
    }

    /**
     * Gateway를 고려한 유효성 검사를 수행합니다.
     * 
     * Bean Validation과 Gateway 지원 여부를 모두 확인합니다.
     * 
     * @param gateway Gateway 관리자 (null일 수 있음)
     * @throws CloudSDKException 유효성 검사 실패 시
     */
    public void valid(GatewayManager gateway) {
        Set<ConstraintViolation<BaseModel>> violations = VALIDATOR.validate(this);
        if (null != gateway) {
            Set<String> names = new HashSet<>();
            violations = violations.stream().filter(violation ->
                    filterProperty(gateway, violation.getRootBeanClass(),
                        violation.getPropertyPath().toString().split("\\."), 0, true, names))
                    .collect(Collectors.toSet());
        }

        if (CollectionUtils.isEmpty(violations)) {
            return;
        }
        throw new CloudSDKException(CloudSDKErrorEnum.INVALID_PARAMETER, violations.stream()
                .map(violation -> violation.getPropertyPath().toString() + violation.getMessage() +
                        ", Current value is: " + violation.getInvalidValue())
                .collect(Collectors.joining("; ")));

    }

    /**
     * 속성 필터링을 수행합니다.
     * 
     * 재귀적으로 필드의 지원 여부를 확인하고
     * 지원되지 않는 필드를 필터링합니다.
     * 
     * @param gateway Gateway 관리자
     * @param clazz 확인할 클래스
     * @param fields 필드 경로 배열
     * @param index 현재 확인 중인 필드 인덱스
     * @param isValid 현재까지의 유효성 상태
     * @param names 이미 확인된 필드 이름들
     * @return 필터링 결과 (true: 유효, false: 무효)
     * @throws CloudSDKException 필드를 찾을 수 없을 경우
     */
    private boolean filterProperty(GatewayManager gateway, Class clazz, String[] fields, int index, boolean isValid, Set<String> names) {
        if (!isValid || index == fields.length) {
            return isValid;
        }
        String name = String.join(".", Arrays.copyOf(fields, index + 1));
        if (names.contains(name)) {
            return false;
        }
        try {
            Field field = clazz.getDeclaredField(fields[index]);
            isValid = gateway.isPropertyValid(field.getAnnotation(CloudSDKVersion.class));
            if (!isValid) {
                names.add(name);
            }
            return filterProperty(gateway, field.getType(), fields, index + 1, isValid, names);
        } catch (NoSuchFieldException e) {
            throw new CloudSDKException(e);
        }
    }
}