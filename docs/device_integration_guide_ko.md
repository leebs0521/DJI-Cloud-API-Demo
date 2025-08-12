# DJI Cloud API - 디바이스 통합 가이드

## 목차
1. [소개](#소개)
2. [디바이스 타입 시스템 아키텍처](#디바이스-타입-시스템-아키텍처)
3. [새로운 디바이스 추가 방법](#새로운-디바이스-추가-방법)
   - [새로운 드론 모델 추가](#새로운-드론-모델-추가)
   - [새로운 RC(리모트 컨트롤) 모델 추가](#새로운-rc리모트-컨트롤-모델-추가)
   - [새로운 페이로드 모델 추가](#새로운-페이로드-모델-추가)
   - [새로운 도크 모델 추가](#새로운-도크-모델-추가)
   - [완전히 새로운 디바이스 도메인 추가](#완전히-새로운-디바이스-도메인-추가)
4. [예제](#예제)
5. [주의사항](#주의사항)

## 소개

이 가이드는 DJI Cloud API 시스템에 새로운 디바이스를 추가하는 방법을 설명합니다. DJI Cloud API는 다양한 디바이스 타입을 지원하며, 새로운 디바이스가 출시될 때마다 코드베이스에 통합해야 합니다. 이 문서는 개발자가 새로운 디바이스를 시스템에 효과적으로 통합할 수 있도록 단계별 지침을 제공합니다.

## 디바이스 타입 시스템 아키텍처

DJI Cloud API의 디바이스 타입 시스템은 다음과 같은 주요 컴포넌트로 구성됩니다:

1. **DeviceDomainEnum**: 디바이스의 기본 도메인을 정의합니다 (드론, 페이로드, 리모트 컨트롤, 도크).
2. **DeviceTypeEnum**: 각 도메인 내의 구체적인 디바이스 타입을 정의합니다 (M300, M350, Z30 등).
3. **DeviceSubTypeEnum**: 디바이스의 서브타입을 정의합니다 (ZERO, ONE, TWO 등).
4. **DeviceEnum**: 도메인, 타입, 서브타입을 조합하여 완전한 디바이스 모델을 정의합니다.
5. **DeviceModelEnum**: 디바이스의 기본 모델을 정의합니다 (RC, DOCK, DRONE).
6. **GatewayTypeEnum**: 게이트웨이 타입을 정의하고 각 게이트웨이에 해당하는 디바이스를 매핑합니다.

이 시스템은 "domain-type-subType" 형식의 문자열로 각 디바이스를 고유하게 식별합니다.

## 새로운 디바이스 추가 방법

### 새로운 드론 모델 추가

새로운 드론 모델을 추가하려면 다음 단계를 따르세요:

1. **DeviceTypeEnum.java** 파일에 새로운 드론 타입 추가:
   ```
   /** 새로운 드론 모델 (타입값: X) */
   NEW_DRONE(X),
   ```
   여기서 `X`는 이 드론 모델에 할당된 고유한 정수값입니다.

2. **DeviceEnum.java** 파일에 새로운 드론 모델 추가:
   ```
   /** 새로운 드론 모델 */
   NEW_DRONE(DeviceDomainEnum.DRONE, DeviceTypeEnum.NEW_DRONE, DeviceSubTypeEnum.ZERO),
   ```

### 새로운 RC(리모트 컨트롤) 모델 추가

새로운 RC 모델을 추가하려면 다음 단계를 따르세요:

1. **DeviceTypeEnum.java** 파일에 새로운 RC 타입 추가:
   ```
   /** 새로운 RC 모델 (타입값: Y) */
   NEW_RC(Y),
   ```
   여기서 `Y`는 이 RC 모델에 할당된 고유한 정수값입니다.

2. **DeviceEnum.java** 파일에 새로운 RC 모델 추가:
   ```
   /** 새로운 RC 모델 */
   NEW_RC(DeviceDomainEnum.REMOTER_CONTROL, DeviceTypeEnum.NEW_RC, DeviceSubTypeEnum.ZERO),
   ```

3. **GatewayTypeEnum.java** 파일에서 RC 게이트웨이 타입 업데이트:
   ```
   RC(DeviceEnum.RC, DeviceEnum.RC_PLUS, DeviceEnum.RC_PRO, DeviceEnum.NEW_RC),
   ```

### 새로운 페이로드 모델 추가

새로운 페이로드 모델을 추가하려면 다음 단계를 따르세요:

1. **DeviceTypeEnum.java** 파일에 새로운 페이로드 타입 추가:
   ```
   /** 새로운 페이로드 모델 (타입값: Z) */
   NEW_PAYLOAD(Z),
   ```
   여기서 `Z`는 이 페이로드 모델에 할당된 고유한 정수값입니다.

2. **DeviceEnum.java** 파일에 새로운 페이로드 모델 추가:
   ```
   /** 새로운 페이로드 모델 */
   NEW_PAYLOAD(DeviceDomainEnum.PAYLOAD, DeviceTypeEnum.NEW_PAYLOAD, DeviceSubTypeEnum.ZERO),
   ```

### 새로운 도크 모델 추가

새로운 도크 모델을 추가하려면 다음 단계를 따르세요:

1. **DeviceTypeEnum.java** 파일에 새로운 도크 타입 추가:
   ```
   /** 새로운 도크 모델 (타입값: W) */
   NEW_DOCK(W),
   ```
   여기서 `W`는 이 도크 모델에 할당된 고유한 정수값입니다.

2. **DeviceEnum.java** 파일에 새로운 도크 모델 추가:
   ```
   /** 새로운 도크 모델 */
   NEW_DOCK(DeviceDomainEnum.DOCK, DeviceTypeEnum.NEW_DOCK, DeviceSubTypeEnum.ZERO),
   ```

3. 새로운 도크가 기존 도크와 근본적으로 다른 경우, **GatewayTypeEnum.java** 파일에 새로운 게이트웨이 타입 추가:
   ```
   /**
    * 새로운 도크 게이트웨이
    * 
    * NEW_DOCK 디바이스를 포함합니다.
    */
   NEW_DOCK(DeviceEnum.NEW_DOCK),
   ```

### 완전히 새로운 디바이스 도메인 추가

완전히 새로운 디바이스 도메인을 추가하려면 다음 단계를 따르세요:

1. **DeviceDomainEnum.java** 파일에 새로운 도메인 추가:
   ```
   /**
    * 새로운 도메인 (정수값: N)
    */
   NEW_DOMAIN(N),
   ```
   여기서 `N`은 이 도메인에 할당된 고유한 정수값입니다.

2. **DeviceTypeEnum.java** 파일에 새로운 도메인에 속하는 디바이스 타입 추가:
   ```
   /** 새로운 도메인의 디바이스 타입 (타입값: M) */
   NEW_DOMAIN_DEVICE(M),
   ```

3. **DeviceEnum.java** 파일에 새로운 디바이스 추가:
   ```
   /** 새로운 도메인의 디바이스 */
   NEW_DOMAIN_DEVICE(DeviceDomainEnum.NEW_DOMAIN, DeviceTypeEnum.NEW_DOMAIN_DEVICE, DeviceSubTypeEnum.ZERO),
   ```

4. 필요한 경우 **DeviceModelEnum.java** 파일에 새로운 모델 추가:
   ```
   /**
    * 새로운 디바이스 모델
    */
   NEW_MODEL,
   ```

5. 새로운 디바이스가 게이트웨이 역할을 하는 경우, **GatewayTypeEnum.java** 파일에 새로운 게이트웨이 타입 추가:
   ```
   /**
    * 새로운 게이트웨이 타입
    * 
    * NEW_DOMAIN_DEVICE 디바이스를 포함합니다.
    */
   NEW_GATEWAY(DeviceEnum.NEW_DOMAIN_DEVICE),
   ```

## 예제

### 예제 1: 새로운 드론 모델 'M400' 추가

1. **DeviceTypeEnum.java** 파일 수정:
   ```
   /** M400 드론 (타입값: 95) */
   M400(95),
   ```

2. **DeviceEnum.java** 파일 수정:
   ```
   /** M400 드론 */
   M400(DeviceDomainEnum.DRONE, DeviceTypeEnum.M400, DeviceSubTypeEnum.ZERO),
   ```

### 예제 2: 새로운 RC 모델 'RC_MAX' 추가

1. **DeviceTypeEnum.java** 파일 수정:
   ```
   /** RC Max 리모트 컨트롤 (타입값: 150) */
   RC_MAX(150),
   ```

2. **DeviceEnum.java** 파일 수정:
   ```
   /** RC Max 리모트 컨트롤 */
   RC_MAX(DeviceDomainEnum.REMOTER_CONTROL, DeviceTypeEnum.RC_MAX, DeviceSubTypeEnum.ZERO),
   ```

3. **GatewayTypeEnum.java** 파일 수정:
   ```
   RC(DeviceEnum.RC, DeviceEnum.RC_PLUS, DeviceEnum.RC_PRO, DeviceEnum.RC_MAX),
   ```

### 예제 3: 새로운 페이로드 'H30' 추가

1. **DeviceTypeEnum.java** 파일 수정:
   ```
   /** H30 카메라 (타입값: 70) */
   H30(70),
   ```

2. **DeviceEnum.java** 파일 수정:
   ```
   /** H30 카메라 */
   H30(DeviceDomainEnum.PAYLOAD, DeviceTypeEnum.H30, DeviceSubTypeEnum.ZERO),
   ```

## 주의사항

1. 각 디바이스 타입에 할당하는 정수값은 고유해야 합니다. 기존 값과 충돌하지 않도록 주의하세요.
2. 새로운 디바이스를 추가한 후에는 관련 테스트를 실행하여 시스템이 올바르게 작동하는지 확인하세요.
3. 디바이스 열거형을 수정할 때는 기존 코드의 동작에 영향을 미치지 않도록 주의하세요.
4. 새로운 디바이스를 추가한 후에는 문서를 업데이트하여 변경 사항을 기록하세요.
5. 디바이스 타입 시스템은 여러 파일에 걸쳐 있으므로, 모든 관련 파일을 일관되게 수정해야 합니다.
