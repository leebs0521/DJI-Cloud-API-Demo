package com.dji.sample.manage.model.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 디바이스 로그 조회 파라미터 클래스
 * 
 * DJI Cloud API의 디바이스 로그 조회 요청을 위한 파라미터 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 로그 조회 조건 관리
 *    - 로그 상태로 필터링 (업로드 중, 완료, 실패 등)
 *    - 시간 범위로 로그 필터링
 *    - 로그 정보 내용으로 검색
 * 
 * 2. 페이징 처리 지원
 *    - 페이지 번호 및 페이지 크기 관리
 *    - 대용량 로그 목록의 효율적인 조회
 *    - 로그 데이터의 구조화된 접근
 * 
 * 3. 로그 검색 및 필터링
 *    - 로그 정보 내용 기반 검색 기능
 *    - 시간 기반 로그 필터링
 *    - 상태별 로그 분류 및 관리
 * 
 * 4. JSON 직렬화 지원
 *    - @JsonProperty 어노테이션으로 JSON 필드명 매핑
 *    - API 요청/응답과의 호환성 보장
 *    - 표준화된 데이터 형식 제공
 * 
 * 이 클래스는 로그 관리 시스템에서 디바이스 로그를
 * 효율적으로 조회하고 필터링할 수 있도록
 * 지원합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/9/7
 */
@Data
public class DeviceLogsQueryParam {

    /**
     * 페이지 번호
     * 조회할 페이지의 번호 (1부터 시작)
     */
    private Long page;

    /**
     * 페이지 크기
     * 한 페이지에 표시할 로그 개수
     */
    @JsonProperty("page_size")
    private Long pageSize;

    /**
     * 로그 상태
     * 로그의 현재 상태 (예: 0-대기, 1-처리 중, 2-완료, 3-실패 등)
     */
    private Integer status;

    /**
     * 시작 시간
     * 로그 조회 시작 시간 (밀리초)
     */
    @JsonProperty("begin_time")
    private Long beginTime;

    /**
     * 종료 시간
     * 로그 조회 종료 시간 (밀리초)
     */
    @JsonProperty("end_time")
    private Long endTime;

    /**
     * 로그 정보
     * 로그 정보 내용으로 검색할 키워드
     */
    @JsonProperty("logs_information")
    private String logsInformation;
}
