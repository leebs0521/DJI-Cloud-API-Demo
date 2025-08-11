package com.dji.sample.manage.model.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 디바이스 펌웨어 조회 파라미터 클래스
 * 
 * DJI Cloud API의 디바이스 펌웨어 조회 요청을 위한 파라미터 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 펌웨어 조회 조건 관리
 *    - 디바이스 이름으로 펌웨어 필터링
 *    - 제품 버전으로 펌웨어 필터링
 *    - 펌웨어 상태로 필터링 (활성/비활성)
 * 
 * 2. 페이징 처리 지원
 *    - 페이지 번호 및 페이지 크기 관리
 *    - 대용량 펌웨어 목록의 효율적인 조회
 *    - 페이징 파라미터의 필수 입력 검증
 * 
 * 3. JSON 직렬화 지원
 *    - @JsonProperty 어노테이션으로 JSON 필드명 매핑
 *    - API 요청/응답과의 호환성 보장
 *    - 표준화된 데이터 형식 제공
 * 
 * 이 클래스는 펌웨어 관리 시스템에서 펌웨어 목록을
 * 효율적으로 조회하고 필터링할 수 있도록
 * 지원합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/12/1
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceFirmwareQueryParam {

    /**
     * 디바이스 이름
     * 조회할 펌웨어가 지원하는 디바이스의 이름
     */
    @JsonProperty("device_name")
    private String deviceName;

    /**
     * 제품 버전
     * 조회할 펌웨어의 제품 버전 정보
     */
    @JsonProperty("product_version")
    private String productVersion;

    /**
     * 펌웨어 상태
     * 펌웨어의 활성/비활성 상태 (true: 활성, false: 비활성)
     */
    private Boolean status;

    /**
     * 페이지 번호
     * 조회할 페이지의 번호 (1부터 시작)
     */
    @NotNull
    private Long page;

    /**
     * 페이지 크기
     * 한 페이지에 표시할 펌웨어 개수
     */
    @NotNull
    @JsonProperty("page_size")
    private Long pageSize;
}
