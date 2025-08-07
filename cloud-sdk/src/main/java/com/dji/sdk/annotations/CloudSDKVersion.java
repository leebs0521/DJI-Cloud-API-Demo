package com.dji.sdk.annotations;

import com.dji.sdk.config.version.CloudSDKVersionEnum;
import com.dji.sdk.config.version.GatewayTypeEnum;

import java.lang.annotation.*;

/**
 * Cloud SDK 버전 관리 어노테이션
 * 
 * 이 어노테이션은 Cloud SDK의 버전 호환성을 관리하기 위해 사용됩니다.
 * 필드나 메서드에 적용하여 해당 기능이 지원되는 버전과 deprecated된 버전을 명시할 수 있습니다.
 * 또한 특정 게이트웨이 타입에 대한 포함/제외 설정도 지원합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/22
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface CloudSDKVersion {

    /**
     * 해당 기능이 도입된 버전
     * 
     * @return 도입 버전 (기본값: V0_0_1)
     */
    CloudSDKVersionEnum since() default CloudSDKVersionEnum.V0_0_1;

    /**
     * 해당 기능이 deprecated된 버전
     * 
     * @return deprecated 버전 (기본값: V99 - 사용되지 않음)
     */
    CloudSDKVersionEnum deprecated() default CloudSDKVersionEnum.V99;

    /**
     * 해당 기능을 포함할 게이트웨이 타입 목록
     * 
     * @return 포함할 게이트웨이 타입 배열 (기본값: 빈 배열)
     */
    GatewayTypeEnum[] include() default {};

    /**
     * 해당 기능을 제외할 게이트웨이 타입 목록
     * 
     * @return 제외할 게이트웨이 타입 배열 (기본값: 빈 배열)
     */
    GatewayTypeEnum[] exclude() default {};

}
