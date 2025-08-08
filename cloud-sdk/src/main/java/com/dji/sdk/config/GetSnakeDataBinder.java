package com.dji.sdk.config;

import com.dji.sdk.common.Common;
import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import javax.servlet.ServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Snake Case 데이터 바인더
 * 
 * 이 클래스는 HTTP 요청의 snake_case 파라미터를
 * Java 객체의 camelCase 필드로 바인딩하는 기능을 제공합니다.
 * 
 * 주요 기능:
 * - snake_case 파라미터를 camelCase 필드로 자동 매핑
 * - JsonProperty 어노테이션을 통한 커스텀 필드 매핑
 * - Enum 타입의 자동 변환 및 처리
 * - Collection 타입의 파라미터 처리
 * 
 * 이 클래스는 GetSnakeArgumentProcessor에서 사용되어
 * HTTP 요청 파라미터의 자동 변환을 처리합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/9/9
 */
public class GetSnakeDataBinder extends ExtendedServletRequestDataBinder {

    /**
     * 클래스 생성자
     * 
     * @param target 바인딩할 대상 객체
     * @param objectName 객체 이름
     */
    public GetSnakeDataBinder(Object target, String objectName) {
        super(target, objectName);
    }

    /**
     * 바인딩 값을 추가합니다.
     * 
     * HTTP 요청의 파라미터를 분석하여 snake_case를 camelCase로 변환하고
     * 대상 객체의 필드에 바인딩합니다.
     * 
     * @param mpvs 변경 가능한 속성 값들
     * @param request 서블릿 요청
     */
    @Override
    protected void addBindValues(MutablePropertyValues mpvs, ServletRequest request) {
        List<PropertyValue> propertyValueList = mpvs.getPropertyValueList();
        List<PropertyValue> values = new ArrayList<>(propertyValueList);
        Field[] fields = this.getTarget().getClass().getDeclaredFields();
        Map<String, Field> fieldMap = Arrays.stream(fields).collect(Collectors.toMap(Field::getName, field -> field));
        fieldMap.putAll(Arrays.stream(fields).filter(field ->  null != field.getAnnotation(JsonProperty.class))
                .collect(Collectors.toMap(field -> field.getAnnotation(JsonProperty.class).value(), field -> field)));

        for (PropertyValue property : values) {
            if (!fieldMap.containsKey(property.getName())) {
                continue;
            }

            Field field = fieldMap.get(property.getName());
            List list = (List) Objects.requireNonNullElse(property.getConvertedValue(), new ArrayList<>());
            list.addAll((List) convertValue(field, this.getTarget(), property.getValue()));
            property.setConvertedValue(list);

            String fieldName = field.getName();
            if (mpvs.contains(fieldName)) {
                PropertyValue propertyValue = mpvs.getPropertyValue(fieldName);
                if (propertyValue != property && null != propertyValue.getConvertedValue()) {
                    ((List) propertyValue.getConvertedValue()).addAll((List) property.getConvertedValue());
                    property = propertyValue;
                }
            }
            Object data = Collection.class.isAssignableFrom(field.getType()) ? property.getConvertedValue() : ((List) property.getConvertedValue()).get(0);
            mpvs.addPropertyValue(new PropertyValue(fieldName, Objects.requireNonNullElse(data, property.getValue())));
        }

        super.addBindValues(mpvs, request);
    }

    /**
     * 값을 변환합니다.
     * 
     * 필드 타입에 따라 값을 적절한 형태로 변환합니다.
     * Enum, Collection, 기본 타입 등을 처리합니다.
     * 
     * @param field 변환할 필드
     * @param object 대상 객체
     * @param value 변환할 값
     * @return 변환된 값 리스트
     */
    private Object convertValue(Field field, Object object, Object value) {
        List convertedValue = new ArrayList();
        if (Enum.class.isAssignableFrom(field.getType())) {
            convertedValue.add(getRealEnumValue(field.getType(), object, value));
            return convertedValue;
        }
        if (field.getType() == field.getGenericType()) {
            convertedValue.add(value);
            return convertedValue;
        }
        if (Collection.class.isAssignableFrom(field.getType())) {
            if (!value.getClass().isArray()) {
                value = String.valueOf(value).split(",");
            }
            for (String v : (String[]) value) {
                if ("".equals(v)) {
                    continue;
                }
                convertedValue.add(getRealEnumValue((Class) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0], object, v));
            }
        }

        return convertedValue;
    }

    /**
     * 실제 Enum 값을 가져옵니다.
     * 
     * JsonCreator 어노테이션이 있는 메서드를 사용하여
     * Enum 값을 생성합니다.
     * 
     * @param type Enum 클래스 타입
     * @param object 대상 객체
     * @param value 변환할 값들
     * @return 생성된 Enum 값
     * @throws CloudSDKException 변환 실패 시
     */
    private Object getRealEnumValue(Class type, Object object, Object... value) {
        if (!type.isEnum()) {
            return value;
        }
        Set<Method> methods = Arrays.stream(type.getDeclaredMethods())
                .filter(m -> null != m.getAnnotation(JsonCreator.class))
                .filter(m -> m.getParameterTypes().length == value.length).collect(Collectors.toSet());
        for (Method m : methods) {
            try {
                Class<?>[] parameterTypes = m.getParameterTypes();
                for (int i = 0; i < value.length; i++) {
                    value[i] = Common.getObjectMapper().convertValue(value[i], parameterTypes[i]);
                }
                return m.invoke(object, value);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new CloudSDKException(e);
            }
        }
        return value;
    }
}
