package com.dji.sample.map.model.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 비행 영역 동기화 요청 파라미터
 * 
 * DJI Cloud API에서 비행 영역을 디바이스와 동기화하기 위한 HTTP 요청의 파라미터 클래스입니다.
 * 이 클래스는 동기화 대상 디바이스들의 목록을 포함합니다.
 * 
 * 1. 동기화 대상 정보
 *    - 디바이스 시리얼 번호 목록 (deviceSns): 동기화할 디바이스들
 * 
 * 2. 데이터 검증
 *    - @NotNull: 디바이스 목록이 필수 입력 사항
 * 
 * 3. JSON 직렬화 설정
 *    - @JsonProperty: JSON 필드명을 "device_sn"으로 매핑
 * 
 * 4. Lombok 어노테이션 활용
 *    - @Data: getter, setter, toString, equals, hashCode 자동 생성
 * 
 * 주요 용도:
 * - 비행 영역 동기화 API 요청 파라미터
 * - 다중 디바이스 동기화 지원
 * - REST API 요청 본문 구조화
 * 
 * 사용 예시:
 * - 비행 영역 동기화 API 호출
 * - 다중 디바이스에 비행 영역 전파
 * - 클라이언트 요청 데이터 매핑
 * 
 * @author sean
 * @version 1.9
 * @date 2023/11/22
 */
@Data
public class SyncFlightAreaParam {

    /**
     * 디바이스 시리얼 번호 목록
     * 
     * 비행 영역을 동기화할 디바이스들의 시리얼 번호 목록입니다.
     * 여러 디바이스에 동시에 비행 영역을 동기화할 수 있습니다.
     * 
     * 검증 규칙:
     * - @NotNull: null 값이 허용되지 않음
     * 
     * JSON 매핑:
     * - @JsonProperty("device_sn"): JSON 필드명을 "device_sn"으로 매핑
     * 
     * 사용 예시:
     * - 단일 디바이스: ["device_sn_001"]
     * - 다중 디바이스: ["device_sn_001", "device_sn_002", "device_sn_003"]
     */
    @NotNull
    @JsonProperty("device_sn")
    private List<String> deviceSns;

}
