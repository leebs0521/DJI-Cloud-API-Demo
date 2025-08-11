package com.dji.sample.map.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 디바이스 비행 영역 엔티티
 * 
 * DJI Cloud API의 디바이스와 비행 영역 간의 관계를 데이터베이스에 저장하기 위한 엔티티 클래스입니다.
 * 이 클래스는 디바이스별 비행 영역 할당과 동기화 상태를 관리합니다.
 * 
 * 1. 데이터베이스 매핑
 *    - 테이블명: device_flight_area
 *    - MyBatis-Plus 어노테이션을 통한 자동 매핑
 *    - 자동 증가 기본 키 (id)
 * 
 * 2. 디바이스-비행영역 관계 정보
 *    - 디바이스 시리얼 번호 (deviceSn): 디바이스 식별
 *    - 워크스페이스 ID (workspaceId): 워크스페이스 식별
 *    - 파일 ID (fileId): 비행 영역 파일 식별
 * 
 * 3. 동기화 상태 정보
 *    - 동기화 상태 (syncStatus): 동기화 진행 상태
 *    - 동기화 코드 (syncCode): 동기화 결과 코드
 * 
 * 4. 메타데이터 정보
 *    - 생성 시간 (createTime): 자동 생성
 *    - 수정 시간 (updateTime): 자동 업데이트
 * 
 * 5. MyBatis-Plus 어노테이션 활용
 *    - @TableName: 데이터베이스 테이블명 지정
 *    - @TableId: 기본 키 필드 지정 및 자동 증가 설정
 *    - @TableField: 데이터베이스 컬럼명 매핑 및 자동 필드 채우기
 * 
 * 6. Lombok 어노테이션 활용
 *    - @Data: getter, setter, toString, equals, hashCode 자동 생성
 *    - @Builder: 빌더 패턴 지원
 *    - @NoArgsConstructor: 기본 생성자 자동 생성
 *    - @AllArgsConstructor: 모든 필드를 포함한 생성자 자동 생성
 * 
 * 주요 용도:
 * - 디바이스-비행영역 관계 데이터베이스 저장
 * - 디바이스별 비행 영역 할당 관리
 * - 비행 영역 동기화 상태 추적
 * - 비행 안전 관리 지원
 * 
 * 사용 예시:
 * - 디바이스에 비행 영역 할당
 * - 비행 영역 동기화 상태 모니터링
 * - 디바이스별 비행 제한 관리
 * 
 * @author sean
 * @version 1.9
 * @date 2023/11/23
 */
@TableName("device_flight_area")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceFlightAreaEntity implements Serializable {

    /**
     * 기본 키 ID
     * 
     * 디바이스 비행 영역 관계 레코드의 고유 식별자입니다.
     * 데이터베이스에서 자동으로 증가하는 정수 값으로 설정됩니다.
     * 
     * MyBatis-Plus 설정:
     * - @TableId(type = IdType.AUTO): 자동 증가 기본 키
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 디바이스 시리얼 번호
     * 
     * 비행 영역이 할당된 디바이스의 고유 식별자입니다.
     * DJI 디바이스의 시리얼 번호로 디바이스를 식별합니다.
     * 
     * 데이터베이스 컬럼: device_sn
     */
    @TableField("device_sn")
    private String deviceSn;

    /**
     * 워크스페이스 ID
     * 
     * 비행 영역이 속한 워크스페이스의 고유 식별자입니다.
     * 워크스페이스별로 비행 영역을 관리할 수 있습니다.
     * 
     * 데이터베이스 컬럼: workspace_id
     */
    @TableField("workspace_id")
    private String workspaceId;

    /**
     * 파일 ID
     * 
     * 디바이스에 할당된 비행 영역 파일의 고유 식별자입니다.
     * 이 파일에는 비행 영역의 상세 정보가 포함되어 있습니다.
     * 
     * 데이터베이스 컬럼: file_id
     */
    @TableField("file_id")
    private String fileId;

    /**
     * 동기화 상태
     * 
     * 디바이스와 비행 영역 간의 동기화 진행 상태를 나타내는 문자열입니다.
     * 예: "SUCCESS", "FAILED", "SYNCING", "NOT_SYNC" 등
     * 
     * 데이터베이스 컬럼: sync_status
     */
    @TableField("sync_status")
    private String syncStatus;

    /**
     * 동기화 코드
     * 
     * 동기화 결과를 나타내는 숫자 코드입니다.
     * 0: 성공, 그 외: 실패 (오류 코드)
     * 
     * 데이터베이스 컬럼: sync_code
     */
    @TableField("sync_code")
    private Integer syncCode;

    /**
     * 생성 시간
     * 
     * 디바이스 비행 영역 관계가 생성된 시간을 Unix 타임스탬프로 나타냅니다.
     * 밀리초 단위의 정수 값으로 저장됩니다.
     * 
     * MyBatis-Plus 설정:
     * - @TableField(fill = FieldFill.INSERT): 삽입 시 자동 생성
     * 
     * 데이터베이스 컬럼: create_time
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Long createTime;

    /**
     * 수정 시간
     * 
     * 디바이스 비행 영역 관계가 마지막으로 수정된 시간을 Unix 타임스탬프로 나타냅니다.
     * 밀리초 단위의 정수 값으로 저장됩니다.
     * 
     * MyBatis-Plus 설정:
     * - @TableField(fill = FieldFill.INSERT_UPDATE): 삽입/수정 시 자동 업데이트
     * 
     * 데이터베이스 컬럼: update_time
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;
}
