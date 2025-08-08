package com.dji.sdk.config.version;

/**
 * Cloud SDK 버전 열거형
 * 
 * 이 열거형은 Cloud SDK의 다양한 버전을 정의합니다.
 * 각 버전은 문자열 형태의 버전 번호를 가집니다.
 * 
 * 주요 구성 요소:
 * - V0_0_1 ~ V1_0_3: 특정 버전들
 * - DEFAULT: 기본 버전 (1.0.3)
 * - V99: 최신 버전을 나타내는 특별한 값
 * 
 * 이 열거형은 SDK의 버전 호환성을 관리하고
 * API의 지원 여부를 판단하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/22
 */
public enum CloudSDKVersionEnum {

    /**
     * 버전 0.0.1
     * 
     * 초기 버전입니다.
     */
    V0_0_1("0.0.1"),

    /**
     * 버전 1.0.0
     * 
     * 첫 번째 메이저 버전입니다.
     */
    V1_0_0("1.0.0"),

    /**
     * 버전 1.0.1
     * 
     * 첫 번째 패치 버전입니다.
     */
    V1_0_1("1.0.1"),

    /**
     * 버전 1.0.2
     * 
     * 두 번째 패치 버전입니다.
     */
    V1_0_2("1.0.2"),

    /**
     * 버전 1.0.3
     * 
     * 세 번째 패치 버전입니다.
     */
    V1_0_3("1.0.3"),

    /**
     * 기본 버전
     * 
     * 현재 기본으로 사용되는 버전입니다.
     */
    DEFAULT("1.0.3"),

    /**
     * 버전 99
     * 
     * 최신 버전을 나타내는 특별한 값입니다.
     */
    V99("99");

    /**
     * 버전 문자열
     * 
     * 각 버전을 나타내는 문자열 값입니다.
     */
    private final String version;

    /**
     * Cloud SDK 버전 열거형 생성자
     * 
     * @param version 버전 문자열
     */
    CloudSDKVersionEnum(String version) {
        this.version = version;
    }

    /**
     * 버전 문자열을 반환합니다.
     * 
     * @return 버전 문자열
     */
    public String getVersion() {
        return version;
    }

    /**
     * 지정된 버전이 지원되는지 확인합니다.
     * 
     * 현재 버전이 지정된 버전보다 크거나 같은지 확인합니다.
     * 
     * @param version 확인할 버전
     * @return 지원 여부
     */
    public boolean isSupported(CloudSDKVersionEnum version) {
        return this.version.compareTo(version.getVersion()) >= 0;
    }

    /**
     * 지정된 버전이 더 이상 사용되지 않는지 확인합니다.
     * 
     * 현재 버전이 지정된 버전보다 크거나 같은지 확인합니다.
     * 
     * @param version 확인할 버전
     * @return 더 이상 사용되지 않는지 여부
     */
    public boolean isDeprecated(CloudSDKVersionEnum version) {
        return this.version.compareTo(version.getVersion()) >= 0;
    }
}
