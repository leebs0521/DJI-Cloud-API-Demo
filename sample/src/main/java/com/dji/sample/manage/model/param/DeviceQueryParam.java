package com.dji.sample.manage.model.param;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 디바이스 조회 파라미터 클래스
 * 
 * DJI Cloud API의 디바이스 조회 요청을 위한 파라미터 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 디바이스 조회 조건 관리
 *    - 디바이스 시리얼 번호로 필터링
 *    - 워크스페이스 ID로 필터링
 *    - 디바이스 타입 및 서브타입으로 필터링
 * 
 * 2. 디바이스 계층 구조 관리
 *    - 디바이스 도메인별 필터링
 *    - 자식 디바이스 시리얼 번호로 필터링
 *    - 디바이스 간 관계 관리
 * 
 * 3. 디바이스 상태 관리
 *    - 바인딩 상태로 필터링
 *    - 디바이스 연결 상태 관리
 *    - 디바이스 활성화 상태 추적
 * 
 * 4. 정렬 및 정렬 옵션
 *    - 정렬 기능 활성화/비활성화
 *    - 오름차순/내림차순 정렬 옵션
 *    - 조회 결과의 구조화된 정렬
 * 
 * 이 클래스는 디바이스 관리 시스템에서 디바이스를
 * 효율적으로 조회하고 필터링할 수 있도록
 * 지원합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/16
 * @version 0.1
 */
@Data
@Builder
public class DeviceQueryParam {

    /**
     * 디바이스 시리얼 번호
     * 조회할 디바이스의 시리얼 번호
     */
    private String deviceSn;

    /**
     * 워크스페이스 ID
     * 디바이스가 속한 워크스페이스의 고유 식별자
     */
    private String workspaceId;

    /**
     * 디바이스 타입
     * 디바이스의 주요 타입 (예: AIRCRAFT=0, DOCK=1, RC=2 등)
     */
    private Integer deviceType;

    /**
     * 디바이스 서브타입
     * 디바이스의 세부 타입 (예: M30=0, M300=1 등)
     */
    private Integer subType;

    /**
     * 디바이스 도메인 목록
     * 디바이스가 속한 도메인들의 목록 (예: AIRCRAFT=0, DOCK=1 등)
     */
    private List<Integer> domains;

    /**
     * 자식 디바이스 시리얼 번호
     * 현재 디바이스에 연결된 자식 디바이스의 시리얼 번호
     */
    private String childSn;

    /**
     * 바인딩 상태
     * 디바이스의 바인딩 여부 (true: 바인딩됨, false: 바인딩되지 않음)
     */
    private Boolean boundStatus;

    /**
     * 정렬 기능 활성화 여부
     * 조회 결과에 정렬을 적용할지 여부
     */
    private boolean orderBy;

    /**
     * 오름차순 정렬 여부
     * true: 오름차순, false: 내림차순
     */
    private boolean isAsc;
}