package com.dji.sdk.cloudapi.tsa;

import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 파일럿에 내장된 시스템 아이콘 열거형
 * 
 * 이 열거형은 DJI Pilot 앱에 내장된 TSA(Threat and Situation Awareness) 시스템의
 * 기본 아이콘들을 정의합니다. 차량, 사람, 장비의 일반 상태와 선택 상태 아이콘을 포함합니다.
 * 
 * 주요 구성 요소:
 * - SELECT_CAR: 선택된 차량 아이콘
 * - NORMAL_CAR: 일반 차량 아이콘
 * - SELECT_PERSON: 선택된 사람 아이콘
 * - NORMAL_PERSON: 일반 사람 아이콘
 * - SELECT_EQUIPMENT: 선택된 장비 아이콘
 * - NORMAL_EQUIPMENT: 일반 장비 아이콘
 * 
 * 이 열거형은 TSA 시스템에서 지도상에 표시될 기본 아이콘들을 제공합니다.
 * 모든 아이콘은 리소스 URL 형태로 제공되어 앱 내부에서 로드됩니다.
 * 
 * @author sean
 * @version 0.3
 * @date 2022/1/5
 */
@Schema(enumAsRef = true)
public enum IconUrlEnum {

    /**
     * 선택된 차량 아이콘
     * 
     * 차량이 선택된 상태일 때 표시되는 아이콘입니다.
     * 일반 상태와 구분되는 시각적 피드백을 제공합니다.
     */
    SELECT_CAR("resource://pilot/drawable/tsa_car_select"),

    /**
     * 일반 차량 아이콘
     * 
     * 차량이 일반 상태일 때 표시되는 기본 아이콘입니다.
     * 지도상에 차량의 위치를 나타냅니다.
     */
    NORMAL_CAR("resource://pilot/drawable/tsa_car_normal"),

    /**
     * 선택된 사람 아이콘
     * 
     * 사람이 선택된 상태일 때 표시되는 아이콘입니다.
     * 일반 상태와 구분되는 시각적 피드백을 제공합니다.
     */
    SELECT_PERSON("resource://pilot/drawable/tsa_person_select"),

    /**
     * 일반 사람 아이콘
     * 
     * 사람이 일반 상태일 때 표시되는 기본 아이콘입니다.
     * 지도상에 사람의 위치를 나타냅니다.
     */
    NORMAL_PERSON("resource://pilot/drawable/tsa_person_normal"),

    /**
     * 선택된 장비 아이콘
     * 
     * 장비가 선택된 상태일 때 표시되는 아이콘입니다.
     * 일반 상태와 구분되는 시각적 피드백을 제공합니다.
     */
    SELECT_EQUIPMENT("resource://pilot/drawable/tsa_equipment_select"),

    /**
     * 일반 장비 아이콘
     * 
     * 장비가 일반 상태일 때 표시되는 기본 아이콘입니다.
     * 지도상에 장비의 위치를 나타냅니다.
     */
    NORMAL_EQUIPMENT("resource://pilot/drawable/tsa_equipment_normal");

    /**
     * 아이콘 URL
     * 
     * 각 아이콘의 리소스 URL입니다.
     * 앱 내부의 drawable 리소스를 참조합니다.
     */
    private final String url;

    /**
     * 아이콘 URL 열거형 생성자
     * 
     * @param url 아이콘 URL
     */
    IconUrlEnum(String url) {
        this.url = url;
    }

    /**
     * 아이콘 URL을 반환합니다.
     * 
     * @return 아이콘 URL
     */
    @JsonValue
    public String getUrl() {
        return url;
    }
}
