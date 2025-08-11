package com.dji.sample.manage.model.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 페이로드 모델 열거형
 * 
 * DJI Cloud API의 페이로드 모델을 정의하는 열거형 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 페이로드 모델 정의
 *    - 다양한 DJI 페이로드 모델 지원 (Z30, XT2, H20, M30 등)
 *    - 각 모델별 고유 인덱스 관리
 *    - 페이로드 모델과 인덱스의 매핑 관계
 * 
 * 2. 페이로드 모델 관리
 *    - 모든 페이로드 모델 목록 조회
 *    - 모든 페이로드 인덱스 목록 조회
 *    - 스트림 API를 활용한 효율적인 데이터 처리
 * 
 * 3. 페이로드 호환성 관리
 *    - 디바이스별 지원 페이로드 모델 관리
 *    - 페이로드 모델별 특성 및 기능 분류
 *    - 페이로드 업그레이드 및 호환성 검증
 * 
 * 이 열거형은 DJI 디바이스의 다양한 페이로드를
 * 표준화된 방식으로 관리하고 식별할 수 있도록
 * 지원합니다.
 * 
 * @author sean
 * @version 1.0
 * @date 2022/4/29
 */
public enum PayloadModelEnum {

    /**
     * Z30 페이로드 모델
     * 광학 줌 카메라 페이로드
     */
    Z30("Z30", "20-0"),

    /**
     * XT2 페이로드 모델
     * 열화상 카메라 페이로드
     */
    XT2("XT2", "26-0"),

    /**
     * XTS 페이로드 모델
     * 고급 열화상 카메라 페이로드
     */
    XTS("XTS", "41-0"),

    /**
     * H20 페이로드 모델
     * 하이브리드 센서 페이로드 (광학 + 열화상)
     */
    H20("H20", "42-0"),

    /**
     * H20T 페이로드 모델
     * 하이브리드 센서 페이로드 (광학 + 열화상 + 레이저)
     */
    H20T("H20T", "43-0"),

    /**
     * P1 페이로드 모델
     * 고정밀 매핑 카메라 페이로드
     */
    P1("P1", "50-65535"),

    /**
     * M30 페이로드 모델
     * M30 시리즈 통합 페이로드
     */
    M30("M30", "52-0"),

    /**
     * M30T 페이로드 모델
     * M30 시리즈 통합 페이로드 (열화상 포함)
     */
    M30T("M30T", "53-0"),

    /**
     * H20N 페이로드 모델
     * 야간 비전 페이로드
     */
    H20N("H20N", "61-0"),

    /**
     * DOCK 페이로드 모델
     * 도킹 스테이션 페이로드
     */
    DOCK("DOCK", "165-0"),

    /**
     * L1 페이로드 모델
     * 라이다 센서 페이로드
     */
    L1("L1", "90742-0");

    /**
     * 페이로드 모델명
     * 페이로드의 표시 이름
     */
    private String model;

    /**
     * 페이로드 인덱스
     * 페이로드를 고유하게 식별하는 인덱스
     */
    private String index;

    /**
     * 생성자
     * @param model 페이로드 모델명
     * @param index 페이로드 인덱스
     */
    PayloadModelEnum(String model, String index) {
        this.model = model;
        this.index = index;
    }

    /**
     * 페이로드 모델명을 반환합니다.
     * @return 페이로드 모델명
     */
    public String getModel() {
        return model;
    }

    /**
     * 페이로드 인덱스를 반환합니다.
     * @return 페이로드 인덱스
     */
    public String getIndex() {
        return index;
    }

    /**
     * 모든 페이로드 모델명 목록을 반환합니다.
     * 
     * @return 모든 페이로드 모델명의 리스트
     */
    public static List<String> getAllModel() {
        return Arrays.stream(PayloadModelEnum.values()).map(PayloadModelEnum::getModel).collect(Collectors.toList());
    }

    /**
     * 모든 페이로드 인덱스 목록을 반환합니다.
     * 
     * @return 모든 페이로드 인덱스의 리스트
     */
    public static List<String> getAllIndex() {
        return Arrays.stream(PayloadModelEnum.values()).map(PayloadModelEnum::getIndex).collect(Collectors.toList());
    }

}
