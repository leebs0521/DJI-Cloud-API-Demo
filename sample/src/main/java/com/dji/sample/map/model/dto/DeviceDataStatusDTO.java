package com.dji.sample.map.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 디바이스 데이터 상태 전송 객체 (DTO)
 * 
 * DJI Cloud API에서 디바이스의 기본 정보와 상태를 전송하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 다음과 같은 주요 정보들을 포함합니다:
 * 
 * 1. 디바이스 식별 정보
 *    - 디바이스 시리얼 번호 (deviceSn)
 *    - 디바이스 닉네임 (nickname)
 *    - 디바이스 이름 (deviceName)
 * 
 * 2. 디바이스 상태 정보
 *    - 온라인/오프라인 상태 (online)
 *    - 비행 영역 상태 정보 (flightAreaStatus)
 * 
 * 3. Lombok 어노테이션 활용
 *    - @Data: getter, setter, toString, equals, hashCode 자동 생성
 *    - @Builder: 빌더 패턴 지원
 *    - @AllArgsConstructor: 모든 필드를 포함한 생성자 자동 생성
 *    - @NoArgsConstructor: 기본 생성자 자동 생성
 * 
 * 주요 용도:
 * - 디바이스 목록 조회 시 상태 정보 전송
 * - 디바이스 상태 변경 알림
 * - 디바이스 정보 업데이트
 * - 비행 영역 상태와 함께 디바이스 정보 제공
 * 
 * 사용 예시:
 * - 디바이스 대시보드에서 디바이스 상태 표시
 * - 실시간 디바이스 상태 모니터링
 * - 디바이스별 비행 영역 상태 확인
 * 
 * @author sean
 * @version 1.9
 * @date 2023/11/24
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDataStatusDTO {

    /**
     * 디바이스 시리얼 번호
     * 
     * DJI 디바이스의 고유 식별자로 사용되는 시리얼 번호입니다.
     * 이 값은 디바이스 식별 및 API 호출 시 필수적으로 사용됩니다.
     */
    private String deviceSn;

    /**
     * 디바이스 닉네임
     * 
     * 사용자가 설정한 디바이스의 별칭입니다.
     * 디바이스 식별을 위한 사용자 친화적인 이름으로 사용됩니다.
     */
    private String nickname;

    /**
     * 디바이스 이름
     * 
     * 디바이스의 공식 모델명 또는 제품명입니다.
     * 예: M30, M300 RTK, Dock 등
     */
    private String deviceName;

    /**
     * 디바이스 온라인 상태
     * 
     * 디바이스의 현재 연결 상태를 나타냅니다.
     * true: 온라인 (연결됨)
     * false: 오프라인 (연결되지 않음)
     */
    private Boolean online;

    /**
     * 디바이스 비행 영역 상태 정보
     * 
     * 해당 디바이스와 관련된 비행 영역의 상태 정보를 포함합니다.
     * 비행 영역 할당, 동기화 상태, 권한 정보 등을 포함할 수 있습니다.
     */
    private DeviceFlightAreaDTO flightAreaStatus;
}
