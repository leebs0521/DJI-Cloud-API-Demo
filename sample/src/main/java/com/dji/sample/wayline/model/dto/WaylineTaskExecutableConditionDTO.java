package com.dji.sample.wayline.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DJI Cloud API 웨이라인 작업 실행 조건 데이터 전송 객체 (DTO)
 * 
 * 이 클래스는 웨이라인 작업이 실행 중에 계속 만족되어야 하는 실행 조건들을 정의하는 데이터 전송 객체입니다.
 * 웨이라인 작업이 안전하고 정상적으로 계속 실행될 수 있도록 필요한 모든 런타임 조건들을 포함합니다.
 * 
 * 주요 기능:
 * - 저장공간 용량 조건 설정 및 모니터링
 * - 실행 중 조건 검증 및 관리
 * - 안전 조건 실시간 확인
 * - 조건 불만족 시 작업 중단 처리
 * 
 * 조건 검증 과정:
 * 1. 작업 실행 중 지속적으로 조건 만족 여부 확인
 * 2. 조건이 만족되지 않으면 작업 일시 정지 또는 중단
 * 3. 조건이 다시 만족되면 작업 재개
 * 4. 조건이 계속 불만족 시 작업 완전 중단
 * 
 * 사용 시나리오:
 * - 웨이라인 작업 생성 시 실행 조건 설정
 * - 웨이라인 작업 실행 중 조건 모니터링
 * - 자동 작업 중단 및 재개 처리
 * 
 * @author sean
 * @version 1.3
 * @date 2023/2/16
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WaylineTaskExecutableConditionDTO {

    /**
     * 최소 저장공간 용량 요구사항
     * 웨이라인 작업 실행 중 미디어 파일 저장을 위해 필요한 최소 저장공간 (MB 단위)
     * 
     * 값 의미:
     * - 0: 저장공간 조건 없음
     * - 양수 값: 최소 필요한 저장공간 (MB)
     * 
     * 고려사항:
     * - 사진/비디오 파일 크기: 고해상도 미디어 파일은 큰 용량 필요
     * - 작업 시간: 긴 작업일수록 더 많은 저장공간 필요
     * - 안전 마진: 예상 용량보다 여유 공간 확보 필요
     * 
     * 권장 설정:
     * - 짧은 작업 (30분 이하): 1-2GB
     * - 중간 작업 (1-2시간): 5-10GB
     * - 긴 작업 (2시간 이상): 10-20GB 이상
     * 
     * 단위: MB (메가바이트)
     */
    private Integer storageCapacity;
}
