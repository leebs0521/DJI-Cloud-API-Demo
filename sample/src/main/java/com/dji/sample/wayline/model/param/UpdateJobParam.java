package com.dji.sample.wayline.model.param;

import com.dji.sample.wayline.model.enums.WaylineTaskStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DJI Cloud API 웨이라인 작업 업데이트 파라미터 클래스
 * 
 * 이 클래스는 기존 웨이라인 작업의 상태를 업데이트할 때 사용되는 파라미터들을 정의합니다.
 * 주로 웨이라인 작업의 제어 명령(일시 정지, 재개)을 전달하는 용도로 사용되며,
 * Lombok 어노테이션을 통해 빌더 패턴과 생성자를 자동 생성합니다.
 * 
 * 주요 기능:
 * - 웨이라인 작업 상태 업데이트 파라미터 정의
 * - 작업 제어 명령 전달 (PAUSE, RESUME)
 * - 빌더 패턴을 통한 객체 생성 지원
 * - 유연한 생성자 제공 (기본 생성자, 전체 인자 생성자)
 * 
 * 사용 시나리오:
 * - 웨이라인 작업 일시 정지 API 호출 시
 * - 웨이라인 작업 재개 API 호출 시
 * - DJI Pilot에서 작업 제어 명령 전송 시
 * 
 * Lombok 어노테이션:
 * - @Data: getter, setter, toString, equals, hashCode 자동 생성
 * - @Builder: 빌더 패턴 지원
 * - @AllArgsConstructor: 모든 필드를 인자로 받는 생성자 생성
 * - @NoArgsConstructor: 기본 생성자 생성
 * 
 * @author sean
 * @version 1.3
 * @date 2023/2/1
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateJobParam {

    /**
     * 웨이라인 작업 상태
     * 업데이트할 웨이라인 작업의 새로운 상태
     * 
     * 데이터 타입: WaylineTaskStatusEnum
     * 
     * 가능한 값:
     * - PAUSE: 작업 일시 정지
     * - RESUME: 작업 재개
     * 
     * 사용 목적:
     * - 실행 중인 웨이라인 작업 제어
     * - 작업 상태 변경 명령 전달
     * - 안전한 작업 중단 및 재개
     * 
     * 제어 흐름:
     * IN_PROGRESS → PAUSE → RESUME → IN_PROGRESS
     * 
     * 예시:
     * - status = PAUSE: 현재 실행 중인 작업을 일시 정지
     * - status = RESUME: 일시 정지된 작업을 재개
     */
    private WaylineTaskStatusEnum status;
}
