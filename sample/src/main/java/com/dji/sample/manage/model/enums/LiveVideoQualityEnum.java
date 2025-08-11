package com.dji.sample.manage.model.enums;

/**
 * 라이브 비디오 품질 열거형
 * 
 * DJI Cloud API의 라이브 스트리밍 비디오 품질을 정의하는 열거형 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 비디오 품질 레벨 정의
 *    - 자동 품질 조정 (AUTO)
 *    - 부드러운 스트리밍 (SMOOTH)
 *    - 표준 화질 (STANDARD_DEFINITION)
 *    - 고화질 (HIGH_DEFINITION)
 *    - 초고화질 (ULTRA_HD)
 * 
 * 2. 품질 값 매핑
 *    - 각 품질 레벨에 대한 정수 값 매핑
 *    - 품질 값으로부터 열거형 상수 검색 기능
 *    - 알 수 없는 품질에 대한 처리
 * 
 * 3. 라이브 스트리밍 최적화
 *    - 네트워크 환경에 따른 품질 자동 조정
 *    - 대역폭에 따른 적응형 스트리밍 지원
 *    - 사용자 경험 최적화를 위한 품질 선택
 * 
 * 이 열거형은 라이브 스트리밍 서비스에서 비디오 품질을
 * 표준화된 방식으로 관리하고 설정할 수 있도록
 * 지원합니다.
 * 
 * @author sean
 * @version 0.1
 * @date 2021/11/26
 */
public enum LiveVideoQualityEnum {

    /**
     * 자동 품질 조정
     * 네트워크 환경에 따라 자동으로 품질을 조정
     */
    AUTO (0),

    /**
     * 부드러운 스트리밍
     * 낮은 대역폭에서도 부드러운 재생을 위한 품질
     */
    SMOOTH(1),

    /**
     * 표준 화질
     * 일반적인 스트리밍에 적합한 표준 품질
     */
    STANDARD_DEFINITION(2),

    /**
     * 고화질
     * 선명한 화질을 제공하는 고품질 스트리밍
     */
    HIGH_DEFINITION(3),

    /**
     * 초고화질
     * 최고 품질의 스트리밍 (4K 등)
     */
    ULTRA_HD(4),

    /**
     * 알 수 없는 품질
     * 정의되지 않은 품질 값에 대한 기본값
     */
    UNKNOWN(-1);

    /**
     * 품질 값
     * 각 품질 레벨에 대응하는 정수 값
     */
    private int val;

    /**
     * 생성자
     * @param val 품질 값
     */
    LiveVideoQualityEnum(int val) {
        this.val = val;
    }

    /**
     * 품질 값으로부터 열거형 상수를 찾습니다.
     * 
     * 주어진 품질 값에 해당하는 열거형 상수를 반환합니다.
     * 일치하는 값이 없으면 UNKNOWN을 반환합니다.
     * 
     * @param val 찾을 품질 값
     * @return 해당하는 열거형 상수 또는 UNKNOWN
     */
    public static LiveVideoQualityEnum find(int val) {
        if (AUTO.val == val) {
            return AUTO;
        }

        if (SMOOTH.val == val) {
            return SMOOTH;
        }

        if (STANDARD_DEFINITION.val == val) {
            return STANDARD_DEFINITION;
        }

        if (HIGH_DEFINITION.val == val) {
            return HIGH_DEFINITION;
        }

        if (ULTRA_HD.val == val) {
            return ULTRA_HD;
        }

        return UNKNOWN;
    }
}
