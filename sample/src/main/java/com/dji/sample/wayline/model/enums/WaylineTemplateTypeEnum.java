package com.dji.sample.wayline.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Optional;

/**
 * DJI Cloud API 웨이라인 템플릿 타입 열거형
 * 
 * 이 열거형은 웨이라인 파일의 템플릿 타입을 정의하는 상수들을 제공합니다.
 * 웨이라인 파일이 어떤 용도로 사용되는지를 나타내며, 각 템플릿 타입에 따라
 * 다른 비행 패턴과 데이터 수집 방식이 적용됩니다.
 * 
 * 주요 기능:
 * - 웨이라인 템플릿 타입 정의 및 관리
 * - 정수값과 문자열 타입 간의 매핑
 * - JSON 직렬화/역직렬화 지원
 * - 템플릿 타입 검색 기능
 * 
 * 사용 시나리오:
 * - 웨이라인 파일 업로드 시 템플릿 타입 분류
 * - 웨이라인 작업 생성 시 템플릿 타입 검증
 * - 웨이라인 파일 목록 필터링
 * 
 * @author sean
 * @version 1.3
 * @date 2022/9/26
 */
public enum WaylineTemplateTypeEnum {

    /**
     * 웨이포인트 (Waypoint)
     * 일반적인 웨이포인트 기반 비행 경로
     * 
     * 값: 0
     * 타입: "waypoint"
     * 
     * 특징:
     * - 사용자가 직접 정의한 웨이포인트 경로
     * - 자유로운 비행 경로 설정 가능
     * - 정찰, 순찰, 측량 등 다양한 용도
     * 
     * 사용 예시:
     * - 건설 현장 순찰
     * - 농작물 모니터링
     * - 보안 순찰
     */
    WAYPOINT(0, "waypoint"),

    /**
     * 2D 매핑 (2D Mapping)
     * 2차원 지도 생성을 위한 격자 패턴 비행
     * 
     * 값: 1
     * 타입: "mapping2d"
     * 
     * 특징:
     * - 격자 패턴으로 체계적인 영역 촬영
     * - 정사영상(Orthomosaic) 생성
     * - 정밀한 2D 지도 제작
     * 
     * 사용 예시:
     * - 토지 측량
     * - 건설 현장 문서화
     * - 농업용 정밀 지도
     */
    MAPPING_2D(1, "mapping2d"),

    /**
     * 3D 매핑 (3D Mapping)
     * 3차원 모델 생성을 위한 격자 패턴 비행
     * 
     * 값: 2
     * 타입: "mapping3d"
     * 
     * 특징:
     * - 격자 패턴 + 고도 변화로 3D 데이터 수집
     * - 3D 포인트 클라우드 생성
     * - 디지털 표면 모델(DSM) 생성
     * 
     * 사용 예시:
     * - 건물 3D 모델링
     * - 지형 분석
     * - 볼륨 계산
     */
    MAPPING_3D(2, "mapping3d"),

    /**
     * 스트립 매핑 (Strip Mapping)
     * 선형 구조물을 따라 스트립 패턴으로 촬영
     * 
     * 값: 4
     * 타입: "mappingStrip"
     * 
     * 특징:
     * - 선형 경로를 따라 스트립 패턴 비행
     * - 긴 구조물의 효율적인 촬영
     * - 도로, 파이프라인, 전선 등에 적합
     * 
     * 사용 예시:
     * - 도로 상태 점검
     * - 송전선 점검
     * - 파이프라인 모니터링
     */
    MAPPING_STRIP(4, "mappingStrip");

    /**
     * 템플릿 타입 값
     * 데이터베이스에 저장되는 정수 형태의 템플릿 타입값
     */
    int val;

    /**
     * 템플릿 타입 문자열
     * API 통신에서 사용되는 문자열 형태의 템플릿 타입
     */
    String type;

    /**
     * 웨이라인 템플릿 타입 열거형 생성자
     * 
     * @param val 템플릿 타입 값 (정수)
     * @param type 템플릿 타입 문자열
     */
    WaylineTemplateTypeEnum(int val, String type) {
        this.val = val;
        this.type = type;
    }

    /**
     * JSON 직렬화를 위한 값 반환
     * 
     * @JsonValue 어노테이션을 사용하여 JSON 직렬화 시 val 값을 사용
     * 
     * @return 템플릿 타입 값 (정수)
     */
    @JsonValue
    public int getVal() {
        return val;
    }

    /**
     * 정수값으로부터 웨이라인 템플릿 타입 열거형 객체를 찾습니다.
     * 
     * @JsonCreator 어노테이션을 사용하여 JSON 역직렬화 시 자동 호출
     * 
     * @param val 찾을 템플릿 타입 값
     * @return 해당 값에 대응하는 열거형 객체
     * @throws IllegalArgumentException 해당 값이 존재하지 않는 경우
     */
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static WaylineTemplateTypeEnum find(Integer val) {
        return Arrays.stream(values())
                .filter(templateTypeEnum -> templateTypeEnum.val == val)
                .findAny()
                .get();
    }

    /**
     * 문자열로부터 웨이라인 템플릿 타입 열거형 객체를 찾습니다.
     * 
     * @param type 찾을 템플릿 타입 문자열
     * @return 해당 문자열에 대응하는 열거형 객체를 포함한 Optional, 없으면 빈 Optional
     */
    public static Optional<WaylineTemplateTypeEnum> find(String type) {
        return Arrays.stream(values())
                .filter(templateTypeEnum -> templateTypeEnum.type.equals(type))
                .findAny();
    }
}
