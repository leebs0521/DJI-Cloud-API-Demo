package com.dji.sample.map.model.dto;

import com.dji.sdk.cloudapi.map.ElementResource;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 그룹 요소 전송 객체 (DTO)
 * 
 * DJI Cloud API에서 그룹에 속한 맵 요소의 정보를 전송하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 그룹-요소 관계와 요소의 상세 정보를 포함합니다.
 * 
 * 1. 요소 기본 정보
 *    - 요소 ID (elementId)
 *    - 요소 이름 (name)
 * 
 * 2. 시간 정보
 *    - 생성 시간 (createTime)
 *    - 수정 시간 (updateTime)
 * 
 * 3. 그룹 관계 및 리소스
 *    - 그룹 ID (groupId)
 *    - 요소 리소스 정보 (resource)
 * 
 * 4. JSON 직렬화 설정
 *    - @JsonInclude(JsonInclude.Include.NON_NULL): null 값 제외
 *    - @JsonProperty: JSON 필드명 매핑
 * 
 * 5. Lombok 어노테이션 활용
 *    - @Data: getter, setter, toString, equals, hashCode 자동 생성
 *    - @AllArgsConstructor: 모든 필드를 포함한 생성자 자동 생성
 *    - @NoArgsConstructor: 기본 생성자 자동 생성
 *    - @Builder: 빌더 패턴 지원
 * 
 * 주요 용도:
 * - 그룹별 맵 요소 목록 조회
 * - 그룹-요소 관계 관리
 * - 맵 요소 메타데이터 전송
 * - JSON API 응답 데이터 구조화
 * 
 * 사용 예시:
 * - 그룹별 맵 요소 대시보드
 * - 맵 요소 그룹화 관리
 * - REST API 응답 데이터
 * 
 * @author sean
 * @version 0.2
 * @date 2021/11/29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupElementDTO {

    /**
     * 요소 ID
     * 
     * 맵 요소의 고유 식별자입니다.
     * JSON 직렬화 시 "id" 필드명으로 매핑됩니다.
     */
    @JsonProperty("id")
    private String elementId;

    /**
     * 요소 이름
     * 
     * 맵 요소의 사용자 친화적인 이름입니다.
     * 맵 요소 식별을 위한 표시명으로 사용됩니다.
     */
    private String name;

    /**
     * 생성 시간
     * 
     * 맵 요소가 생성된 시간을 Unix 타임스탬프로 나타냅니다.
     * JSON 직렬화 시 "create_time" 필드명으로 매핑됩니다.
     * 밀리초 단위의 정수 값으로 저장됩니다.
     */
    @JsonProperty(value = "create_time")
    private Long createTime;

    /**
     * 수정 시간
     * 
     * 맵 요소가 마지막으로 수정된 시간을 Unix 타임스탬프로 나타냅니다.
     * JSON 직렬화 시 "update_time" 필드명으로 매핑됩니다.
     * 밀리초 단위의 정수 값으로 저장됩니다.
     */
    @JsonProperty(value = "update_time")
    private Long updateTime;

    /**
     * 요소 리소스 정보
     * 
     * 맵 요소의 상세 리소스 정보를 포함합니다.
     * 요소의 타입, 속성, 기하학적 정보 등이 포함될 수 있습니다.
     */
    private ElementResource resource;

    /**
     * 그룹 ID
     * 
     * 해당 요소가 속한 그룹의 고유 식별자입니다.
     * JSON 직렬화 시 "group_id" 필드명으로 매핑됩니다.
     * 그룹-요소 관계를 식별하는 데 사용됩니다.
     */
    @JsonProperty("group_id")
    private String groupId;
}
