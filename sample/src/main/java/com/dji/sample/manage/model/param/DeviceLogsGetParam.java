package com.dji.sample.manage.model.param;

import com.dji.sdk.cloudapi.log.LogModuleEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

/**
 * 디바이스 로그 조회 파라미터 클래스
 * 
 * DJI Cloud API의 디바이스 로그 조회 요청을 위한 파라미터 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 로그 모듈 필터링
 *    - LogModuleEnum을 활용한 로그 모듈별 필터링
 *    - 다중 모듈 로그 조회 지원
 *    - 모듈별 로그 데이터 분류 및 관리
 * 
 * 2. 로그 도메인 관리
 *    - 디바이스 도메인별 로그 조회
 *    - 도메인별 로그 데이터 구조화
 *    - 도메인별 로그 처리 로직 분리
 * 
 * 3. JSON 직렬화 지원
 *    - @JsonProperty 어노테이션으로 JSON 필드명 매핑
 *    - API 요청/응답과의 호환성 보장
 *    - 표준화된 데이터 형식 제공
 * 
 * 이 클래스는 로그 관리 시스템에서 특정 모듈의
 * 로그를 효율적으로 조회할 수 있도록
 * 지원합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/9/7
 */
@Data
@Schema(description = "디바이스 로그 조회 파라미터")
public class DeviceLogsGetParam {

    /**
     * 도메인 목록
     * 조회할 로그의 도메인 목록 (LogModuleEnum 값들의 리스트)
     */
    @JsonProperty("domain_list")
    @Schema(description = "조회할 로그의 도메인 목록")
    List<LogModuleEnum> domainList;
}
