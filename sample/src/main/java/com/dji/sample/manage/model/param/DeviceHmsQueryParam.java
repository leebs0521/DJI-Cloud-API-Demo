package com.dji.sample.manage.model.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * 디바이스 HMS 조회 파라미터 클래스
 * 
 * DJI Cloud API의 디바이스 HMS(Health Management System) 조회 요청을 위한 파라미터 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. HMS 조회 조건 관리
 *    - 디바이스 시리얼 번호로 HMS 메시지 필터링
 *    - 시간 범위로 HMS 메시지 필터링
 *    - HMS 메시지 레벨 및 언어로 필터링
 * 
 * 2. 다중 디바이스 지원
 *    - 여러 디바이스의 HMS 메시지를 동시 조회
 *    - Set을 활용한 중복 없는 디바이스 목록 관리
 *    - 대량의 디바이스 HMS 데이터 처리
 * 
 * 3. 페이징 및 검색 기능
 *    - 페이지 번호 및 페이지 크기 관리
 *    - HMS 메시지 내용 검색 기능
 *    - 대용량 HMS 데이터의 효율적인 조회
 * 
 * 4. JSON 직렬화 지원
 *    - @JsonProperty 어노테이션으로 JSON 필드명 매핑
 *    - API 요청/응답과의 호환성 보장
 *    - 표준화된 데이터 형식 제공
 * 
 * 이 클래스는 HMS 관리 시스템에서 디바이스의
 * 건강 상태 메시지를 효율적으로 조회하고 필터링할 수 있도록
 * 지원합니다.
 * 
 * @author sean
 * @version 1.1
 * @date 2022/7/8
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "디바이스 HMS 조회 파라미터")
public class DeviceHmsQueryParam {

    @Schema(description = "디바이스 시리얼 번호 집합")
    @JsonProperty("device_sn")
    private Set<String> deviceSn;

    @Schema(description = "HMS 메시지 조회 시작 시간 (밀리초)")
    @JsonProperty("begin_time")
    private Long beginTime;

    @Schema(description = "HMS 메시지 조회 종료 시간 (밀리초)")
    @JsonProperty("end_time")
    private Long endTime;

    @Schema(description = "HMS 메시지의 언어 (예: 'zh', 'en')")
    private String language;

    @Schema(description = "HMS 메시지 내용으로 검색할 키워드")
    private String message;

    @Schema(description = "조회할 페이지의 번호 (1부터 시작)")
    private Long page;

    @Schema(description = "한 페이지에 표시할 HMS 메시지 개수")
    @JsonProperty("page_size")
    private Long pageSize;

    @Schema(description = "HMS 메시지의 중요도 레벨 (예: 1-경고, 2-오류, 3-심각 등)")
    private Integer level;

    @Schema(description = "HMS 메시지의 수정 시간 기준으로 필터링 (밀리초)")
    @JsonProperty("update_time")
    private Long updateTime;
}
