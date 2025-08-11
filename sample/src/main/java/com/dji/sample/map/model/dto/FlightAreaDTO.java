package com.dji.sample.map.model.dto;

import com.dji.sdk.cloudapi.flightarea.GeofenceTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 비행 영역 전송 객체 (DTO)
 * 
 * DJI Cloud API에서 비행 영역의 전체 정보를 전송하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 비행 영역의 기본 정보, 내용, 상태, 메타데이터를 포함합니다.
 * 
 * 1. 비행 영역 기본 정보
 *    - 비행 영역 ID (areaId)
 *    - 비행 영역 이름 (name)
 *    - 비행 영역 타입 (type)
 * 
 * 2. 비행 영역 내용 및 상태
 *    - 비행 영역 상세 내용 (content)
 *    - 비행 영역 활성화 상태 (status)
 * 
 * 3. 메타데이터 정보
 *    - 생성자 정보 (username)
 *    - 생성 시간 (createTime)
 *    - 수정 시간 (updateTime)
 * 
 * 4. Lombok 어노테이션 활용
 *    - @Data: getter, setter, toString, equals, hashCode 자동 생성
 *    - @Builder: 빌더 패턴 지원
 *    - @AllArgsConstructor: 모든 필드를 포함한 생성자 자동 생성
 *    - @NoArgsConstructor: 기본 생성자 자동 생성
 * 
 * 주요 용도:
 * - 비행 영역 목록 조회 시 데이터 전송
 * - 비행 영역 생성 및 수정 시 데이터 전송
 * - 비행 영역 상세 정보 조회
 * - 비행 영역 상태 관리
 * 
 * 사용 예시:
 * - 비행 영역 관리 대시보드
 * - 비행 영역 CRUD 작업
 * - 비행 영역 상태 모니터링
 * 
 * @author sean
 * @version 1.9
 * @date 2023/11/22
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightAreaDTO {

    /**
     * 비행 영역 ID
     * 
     * 비행 영역의 고유 식별자입니다.
     * 시스템에서 비행 영역을 구분하는 데 사용되는 기본 키입니다.
     */
    private String areaId;

    /**
     * 비행 영역 이름
     * 
     * 사용자가 설정한 비행 영역의 이름입니다.
     * 비행 영역 식별을 위한 사용자 친화적인 이름으로 사용됩니다.
     */
    private String name;

    /**
     * 비행 영역 타입
     * 
     * 비행 영역의 분류 타입을 나타냅니다.
     * 예: RESTRICTED_AREA (제한 구역), WARNING_AREA (경고 구역) 등
     */
    private GeofenceTypeEnum type;

    /**
     * 비행 영역 상세 내용
     * 
     * 비행 영역의 속성과 기하학적 정보를 포함하는 상세 내용입니다.
     * 비행 영역의 실제 형태와 설정 정보가 포함됩니다.
     */
    private FlightAreaContent content;

    /**
     * 비행 영역 활성화 상태
     * 
     * 비행 영역의 현재 활성화 상태를 나타냅니다.
     * true: 활성화됨 (비행 제한 적용)
     * false: 비활성화됨 (비행 제한 미적용)
     */
    private Boolean status;

    /**
     * 생성자 사용자명
     * 
     * 해당 비행 영역을 생성한 사용자의 이름입니다.
     * 비행 영역의 소유권 및 관리 권한을 추적하는 데 사용됩니다.
     */
    private String username;

    /**
     * 생성 시간
     * 
     * 비행 영역이 생성된 시간을 Unix 타임스탬프로 나타냅니다.
     * 밀리초 단위의 정수 값으로 저장됩니다.
     */
    private Long createTime;

    /**
     * 수정 시간
     * 
     * 비행 영역이 마지막으로 수정된 시간을 Unix 타임스탬프로 나타냅니다.
     * 밀리초 단위의 정수 값으로 저장됩니다.
     */
    private Long updateTime;
}
