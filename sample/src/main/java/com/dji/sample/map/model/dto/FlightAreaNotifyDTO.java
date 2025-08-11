package com.dji.sample.map.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 비행 영역 알림 전송 객체 (DTO)
 * 
 * DJI Cloud API에서 비행 영역 관련 작업의 결과를 알림하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 비행 영역 작업의 성공/실패 상태와 관련 메시지를 포함합니다.
 * 
 * 1. 디바이스 식별 정보
 *    - 디바이스 시리얼 번호 (sn)
 * 
 * 2. 작업 결과 정보
 *    - 작업 결과 코드 (result)
 *    - 작업 상태 (status)
 *    - 결과 메시지 (message)
 * 
 * 3. Lombok 어노테이션 활용
 *    - @Data: getter, setter, toString, equals, hashCode 자동 생성
 *    - @Builder: 빌더 패턴 지원
 *    - @NoArgsConstructor: 기본 생성자 자동 생성
 *    - @AllArgsConstructor: 모든 필드를 포함한 생성자 자동 생성
 * 
 * 주요 용도:
 * - 비행 영역 동기화 결과 알림
 * - 비행 영역 업데이트 결과 알림
 * - 비행 영역 작업 상태 모니터링
 * - 실시간 작업 결과 전송
 * 
 * 사용 예시:
 * - WebSocket을 통한 실시간 알림
 * - 비행 영역 작업 진행 상황 추적
 * - 오류 발생 시 사용자 알림
 * 
 * @author sean
 * @version 1.9
 * @date 2023/12/5
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightAreaNotifyDTO {

    /**
     * 디바이스 시리얼 번호
     * 
     * 비행 영역 작업이 수행된 디바이스의 고유 식별자입니다.
     * 알림을 받을 디바이스를 식별하는 데 사용됩니다.
     */
    private String sn;

    /**
     * 작업 결과 코드
     * 
     * 비행 영역 작업의 결과를 나타내는 숫자 코드입니다.
     * 0: 성공, 그 외: 실패 (오류 코드)
     */
    private Integer result;

    /**
     * 작업 상태
     * 
     * 비행 영역 작업의 현재 상태를 나타내는 문자열입니다.
     * 예: "SUCCESS", "FAILED", "PROCESSING" 등
     */
    private String status;

    /**
     * 결과 메시지
     * 
     * 비행 영역 작업 결과에 대한 상세한 설명 메시지입니다.
     * 성공 시에는 확인 메시지, 실패 시에는 오류 원인을 포함합니다.
     */
    private String message;
}
