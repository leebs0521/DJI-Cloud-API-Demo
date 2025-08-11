package com.dji.sample.map.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 비행 영역 속성 업데이트 전송 객체 (DTO)
 * 
 * DJI Cloud API에서 비행 영역 속성의 부분 업데이트를 위한 데이터 전송 객체입니다.
 * 이 클래스는 업데이트 가능한 속성들만을 포함하여 효율적인 부분 업데이트를 지원합니다.
 * 
 * 1. 요소 식별 정보
 *    - 요소 ID (elementId)
 * 
 * 2. 업데이트 가능한 속성
 *    - 활성화 상태 (status)
 *    - 반지름 (radius)
 * 
 * 3. Lombok 어노테이션 활용
 *    - @Data: getter, setter, toString, equals, hashCode 자동 생성
 *    - @Builder: 빌더 패턴 지원
 *    - @AllArgsConstructor: 모든 필드를 포함한 생성자 자동 생성
 *    - @NoArgsConstructor: 기본 생성자 자동 생성
 * 
 * 주요 용도:
 * - 비행 영역 속성 부분 업데이트
 * - 비행 영역 활성화/비활성화 토글
 * - 비행 영역 크기 조정
 * - 효율적인 속성 변경
 * 
 * 사용 예시:
 * - 비행 영역 활성화 상태 변경
 * - 원형 비행 영역 반지름 조정
 * - 실시간 비행 영역 설정 수정
 * 
 * @author sean
 * @version 1.9
 * @date 2023/11/22
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightAreaPropertyUpdate {

    /**
     * 요소 ID
     * 
     * 업데이트할 비행 영역 요소의 고유 식별자입니다.
     * 어떤 비행 영역 요소를 수정할지 식별하는 데 사용됩니다.
     */
    private String elementId;

    /**
     * 활성화 상태
     * 
     * 비행 영역 요소의 새로운 활성화 상태입니다.
     * true: 활성화됨 (비행 제한 적용)
     * false: 비활성화됨 (비행 제한 미적용)
     * null: 상태 변경 없음
     */
    private Boolean status;

    /**
     * 반지름
     * 
     * 원형 비행 영역의 새로운 반지름을 미터 단위로 나타냅니다.
     * 원형 또는 구형 비행 영역의 크기를 조정할 때 사용됩니다.
     * null: 반지름 변경 없음
     */
    private Float radius;

}
