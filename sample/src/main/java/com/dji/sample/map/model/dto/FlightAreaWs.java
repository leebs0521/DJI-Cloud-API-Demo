package com.dji.sample.map.model.dto;

import com.dji.sample.map.model.enums.FlightAreaOpertaionEnum;
import com.dji.sdk.cloudapi.flightarea.GeofenceTypeEnum;
import lombok.*;

/**
 * 비행 영역 WebSocket 전송 객체 (DTO)
 * 
 * DJI Cloud API에서 WebSocket을 통해 비행 영역 관련 실시간 메시지를 전송하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 비행 영역의 CRUD 작업과 실시간 상태 변경을 지원합니다.
 * 
 * 1. 작업 타입 정보
 *    - 비행 영역 작업 타입 (operation)
 * 
 * 2. 비행 영역 기본 정보
 *    - 비행 영역 ID (areaId)
 *    - 비행 영역 이름 (name)
 *    - 비행 영역 타입 (type)
 * 
 * 3. 비행 영역 내용 및 상태
 *    - 비행 영역 상세 내용 (content)
 *    - 비행 영역 활성화 상태 (status)
 * 
 * 4. 메타데이터 정보
 *    - 생성자 정보 (username)
 *    - 생성 시간 (createTime)
 *    - 수정 시간 (updateTime)
 * 
 * 5. Lombok 어노테이션 활용
 *    - @AllArgsConstructor: 모든 필드를 포함한 생성자 자동 생성
 *    - @NoArgsConstructor: 기본 생성자 자동 생성
 *    - @Data: getter, setter, toString, equals, hashCode 자동 생성
 *    - @Builder: 빌더 패턴 지원
 * 
 * 주요 용도:
 * - WebSocket을 통한 실시간 비행 영역 업데이트
 * - 비행 영역 CRUD 작업 실시간 알림
 * - 다중 사용자 간 비행 영역 동기화
 * - 실시간 비행 영역 상태 변경 전파
 * 
 * 사용 예시:
 * - 실시간 비행 영역 편집 협업
 * - 비행 영역 변경 사항 즉시 반영
 * - 다중 클라이언트 간 상태 동기화
 * 
 * @author sean
 * @version 1.9
 * @date 2023/12/1
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FlightAreaWs {

    /**
     * 비행 영역 작업 타입
     * 
     * WebSocket을 통해 수행되는 비행 영역 작업의 타입을 나타냅니다.
     * 예: CREATE (생성), UPDATE (수정), DELETE (삭제), STATUS_CHANGE (상태 변경) 등
     */
    private FlightAreaOpertaionEnum operation;

    /**
     * 비행 영역 ID
     * 
     * 작업 대상이 되는 비행 영역의 고유 식별자입니다.
     * WebSocket 메시지에서 어떤 비행 영역에 대한 작업인지 식별하는 데 사용됩니다.
     */
    private String areaId;

    /**
     * 비행 영역 이름
     * 
     * 비행 영역의 사용자 친화적인 이름입니다.
     * 실시간 업데이트 시 비행 영역을 식별하는 데 사용됩니다.
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
     * WebSocket을 통해 실시간으로 업데이트되는 비행 영역의 실제 데이터입니다.
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
     * 해당 비행 영역 작업을 수행한 사용자의 이름입니다.
     * 실시간 협업 시 작업자 추적에 사용됩니다.
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
