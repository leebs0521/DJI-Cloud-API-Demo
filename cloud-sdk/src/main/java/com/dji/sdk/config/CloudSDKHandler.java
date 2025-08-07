package com.dji.sdk.config;

import com.dji.sdk.annotations.CloudSDKVersion;
import com.dji.sdk.common.*;
import com.dji.sdk.config.version.GatewayManager;
import com.dji.sdk.exception.CloudSDKErrorEnum;
import com.dji.sdk.exception.CloudSDKException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.*;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Cloud SDK AOP 핸들러 클래스
 * 
 * 이 클래스는 AOP(Aspect-Oriented Programming)를 사용하여 Cloud SDK API 호출을 가로채고
 * 검증 및 처리를 수행합니다. 디바이스 타입 지원 여부, 버전 호환성, 요청/응답 데이터 검증을 담당합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/22
 */
@Aspect
@Component
public class CloudSDKHandler {

    /**
     * Cloud SDK API 호출 전 디바이스 타입 및 버전 지원 여부를 검증합니다.
     * 
     * 이 메서드는 cloudapi 패키지의 모든 API 메서드 호출을 가로채어
     * GatewayManager를 첫 번째 매개변수로 받는 메서드에 대해 검증을 수행합니다.
     * 
     * @param point AOP JoinPoint
     */
    @Before("execution(public * com.dji.sdk.cloudapi.*.api.*.*(com.dji.sdk.config.version.GatewayManager, ..))")
    public void checkCloudSDK(JoinPoint point) {
        GatewayManager deviceSDK = (GatewayManager) point.getArgs()[0];
        CloudSDKVersion since = ((MethodSignature) point.getSignature()).getMethod().getDeclaredAnnotation(CloudSDKVersion.class);
        if (Objects.isNull(since)) {
            return;
        }
        if (!deviceSDK.isTypeSupport(since)) {
            throw new CloudSDKException(CloudSDKErrorEnum.DEVICE_TYPE_NOT_SUPPORT);
        }
        if (!deviceSDK.isVersionSupport(since)) {
            throw new CloudSDKException(CloudSDKErrorEnum.DEVICE_VERSION_NOT_SUPPORT);
        }
    }

    /**
     * API 요청 데이터를 검증합니다.
     * 
     * 이 메서드는 BaseModel을 두 번째 매개변수로 받는 API 메서드에 대해
     * 요청 데이터의 유효성을 검증합니다.
     * 
     * @param point AOP JoinPoint
     */
    @Before("execution(public * com.dji.sdk.cloudapi.*.api.*.*(com.dji.sdk.config.version.GatewayManager, com.dji.sdk.common.BaseModel+))")
    public void checkRequest(JoinPoint point) {
        Common.validateModel((BaseModel) point.getArgs()[1], (GatewayManager) point.getArgs()[0]);
    }

    /**
     * API 응답 데이터를 검증하고 처리합니다.
     * 
     * 이 메서드는 HttpResultResponse를 반환하는 API 메서드의 응답을 가로채어
     * 응답 데이터의 유효성을 검증하고 필요한 경우 기본값을 설정합니다.
     * 
     * @param point AOP JoinPoint
     * @param response API 응답 객체
     * @throws NoSuchMethodException 메서드를 찾을 수 없는 경우
     * @throws IllegalAccessException 접근 권한이 없는 경우
     * @throws InstantiationException 인스턴스 생성 실패 시
     * @throws InvocationTargetException 메서드 호출 실패 시
     */
    @AfterReturning(value = "execution(public com.dji.sdk.common.HttpResultResponse+ com.dji.sdk.cloudapi.*.api.*.*(..))", returning = "response")
    public void checkResponse(JoinPoint point, HttpResultResponse response) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        if (null == response) {
            throw new CloudSDKException(CloudSDKErrorEnum.INVALID_PARAMETER, "The return value cannot be null.");
        }
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        if (method.getGenericReturnType() instanceof Class) {
            if (null == response.getData()) {
                response.setData("");
            }
            return;
        }
        checkClassType((ParameterizedType) method.getGenericReturnType(), response);
        validData(response.getData(), point.getArgs()[0]);
    }

    /**
     * 제네릭 타입의 클래스 타입을 검증하고 기본값을 설정합니다.
     * 
     * @param type 제네릭 타입
     * @param response 응답 객체
     * @throws NoSuchMethodException 메서드를 찾을 수 없는 경우
     * @throws IllegalAccessException 접근 권한이 없는 경우
     * @throws InstantiationException 인스턴스 생성 실패 시
     * @throws InvocationTargetException 메서드 호출 실패 시
     */
    private void checkClassType(ParameterizedType type, HttpResultResponse response) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Type actualType = type.getActualTypeArguments()[0];
        Class typeClass = actualType instanceof Class ? (Class) actualType : (Class) ((ParameterizedType) actualType).getRawType();
        if (null == response.getData()) {
            if (List.class.isAssignableFrom(typeClass)) {
                response.setData(Collections.emptyList());
                return;
            }
            response.setData(typeClass.getDeclaredConstructor().newInstance());
            return;
        }
        boolean isAssignableFrom = typeClass.isAssignableFrom(response.getData().getClass());
        if (!isAssignableFrom) {
            throw new CloudSDKException(CloudSDKErrorEnum.INVALID_PARAMETER);
        }
    }

    /**
     * 응답 데이터의 유효성을 검증하고 필요한 경우 기본값을 설정합니다.
     * 
     * @param data 검증할 데이터
     * @param arg 원본 요청 인자
     */
    private void validData(Object data, Object arg) {
        if (data instanceof BaseModel) {
            Common.validateModel((BaseModel) data);
            return;
        }
        if (data instanceof PaginationData) {
            List<BaseModel> list = ((PaginationData) data).getList();
            if (null == list) {
                ((PaginationData) data).setList(Collections.EMPTY_LIST);
                try {
                    Field page = arg.getClass().getDeclaredField("page");
                    Field pageSize = arg.getClass().getDeclaredField("pageSize");
                    page.setAccessible(true);
                    pageSize.setAccessible(true);
                    ((PaginationData) data).setPagination(
                            new Pagination().setPage((int) page.get(arg)).setPageSize((int) pageSize.get(arg)));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new CloudSDKException(CloudSDKErrorEnum.INVALID_PARAMETER, e.getMessage());
                }
                return;
            }
            for (BaseModel model : list) {
                Common.validateModel(model);
            }
        }
    }
}
