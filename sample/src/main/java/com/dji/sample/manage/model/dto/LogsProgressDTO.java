package com.dji.sample.manage.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 로그 진행 상황 정보 전송 객체 (DTO) 클래스
 * 
 * DJI Cloud API의 로그 업로드 진행 상황 정보를 전송하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 로그 업로드 진행률 관리
 *    - 로그 업로드 진행률 정보 관리
 *    - 업로드 완료율 및 상태 추적
 *    - 실시간 업로드 진행 상황 모니터링
 * 
 * 2. 디바이스 정보 관리
 *    - 디바이스 시리얼 번호 관리
 *    - 디바이스 모델 도메인 정보 관리
 *    - 디바이스별 로그 업로드 상태 관리
 * 
 * 3. 업로드 결과 및 성능 관리
 *    - 업로드 결과 코드 관리
 *    - 업로드 속도 정보 관리
 *    - 업로드 성능 지표 추적
 * 
 * 4. 상태 정보 관리
 *    - 업로드 상태 문자열 관리
 *    - 상태별 상세 정보 제공
 *    - 상태 변경 이력 추적
 * 
 * 이 클래스는 로그 파일 업로드 과정에서 발생하는 진행 상황을
 * 실시간으로 추적하며, 업로드 성능과 결과를 모니터링하는
 * 표준화된 데이터 형식을 제공합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/9/7
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogsProgressDTO {

    /**
     * 디바이스 시리얼 번호
     * 로그를 업로드하는 디바이스의 시리얼 번호
     */
    private String deviceSn;

    /**
     * 디바이스 모델 도메인
     * 디바이스의 모델 및 도메인 정보
     */
    private String deviceModelDomain;

    /**
     * 업로드 진행률
     * 로그 업로드의 진행률 (0-100%)
     */
    private Integer progress;

    /**
     * 업로드 결과
     * 로그 업로드의 결과 코드 (예: 0-성공, 1-실패, 2-진행중)
     */
    private Integer result;

    /**
     * 업로드 속도
     * 로그 업로드의 속도 (MB/s 또는 KB/s)
     */
    private Float uploadRate;

    /**
     * 업로드 상태
     * 로그 업로드의 현재 상태 문자열
     */
    private String status;

}
