package com.dji.sample.map.model.param;

import com.dji.sample.map.model.dto.FlightAreaContent;
import lombok.Data;

/**
 * 비행 영역 수정 요청 파라미터
 * 
 * DJI Cloud API에서 비행 영역을 수정하기 위한 HTTP PUT 요청의 파라미터 클래스입니다.
 * 이 클래스는 비행 영역 수정에 필요한 선택적 정보를 포함합니다.
 * 
 * 1. 비행 영역 수정 정보
 *    - 비행 영역 이름 (name): 수정할 이름 (선택사항)
 *    - 비행 영역 내용 (content): 수정할 상세 내용 (선택사항)
 *    - 활성화 상태 (status): 수정할 활성화 상태 (선택사항)
 * 
 * 2. 부분 업데이트 지원
 *    - 모든 필드가 선택사항으로 설정됨
 *    - null 값은 수정하지 않음을 의미
 *    - 필요한 필드만 업데이트 가능
 * 
 * 3. Lombok 어노테이션 활용
 *    - @Data: getter, setter, toString, equals, hashCode 자동 생성
 * 
 * 주요 용도:
 * - 비행 영역 수정 API 요청 파라미터
 * - 부분 업데이트 지원
 * - REST API 요청 본문 구조화
 * 
 * 사용 예시:
 * - 비행 영역 수정 API 호출
 * - 선택적 필드 업데이트
 * - 클라이언트 요청 데이터 매핑
 * 
 * @author sean
 * @version 1.9
 * @date 2023/11/22
 */
@Data
public class PutFlightAreaParam {

    /**
     * 비행 영역 이름
     * 
     * 수정할 비행 영역의 사용자 친화적인 이름입니다.
     * null인 경우 기존 이름을 유지합니다.
     * 
     * 선택사항:
     * - null: 이름 수정하지 않음
     * - 문자열: 새로운 이름으로 수정
     */
    private String name;

    /**
     * 비행 영역 상세 내용
     * 
     * 수정할 비행 영역의 속성과 기하학적 정보를 포함하는 상세 내용입니다.
     * null인 경우 기존 내용을 유지합니다.
     * 
     * 선택사항:
     * - null: 내용 수정하지 않음
     * - FlightAreaContent 객체: 새로운 내용으로 수정
     */
    private FlightAreaContent content;

    /**
     * 활성화 상태
     * 
     * 수정할 비행 영역의 활성화 상태입니다.
     * null인 경우 기존 상태를 유지합니다.
     * 
     * 선택사항:
     * - null: 상태 수정하지 않음
     * - true: 활성화됨 (비행 제한 적용)
     * - false: 비활성화됨 (비행 제한 미적용)
     */
    private Boolean status;

}
