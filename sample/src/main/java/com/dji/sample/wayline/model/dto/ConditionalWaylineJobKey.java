package com.dji.sample.wayline.model.dto;

import com.dji.sample.component.redis.RedisConst;
import lombok.Data;

import java.util.Objects;

/**
 * DJI Cloud API 조건부 웨이라인 작업 키 데이터 전송 객체 (DTO)
 * 
 * 이 클래스는 Redis 캐시에서 웨이라인 작업을 식별하기 위한 복합 키를 관리하는 데이터 전송 객체입니다.
 * 웨이라인 작업의 고유성을 보장하기 위해 워크스페이스 ID, Dock 시리얼 번호, 작업 ID를 조합하여 사용합니다.
 * 
 * 주요 기능:
 * - 웨이라인 작업의 복합 키 생성 및 관리
 * - Redis 캐시 키 생성 및 파싱
 * - 웨이라인 작업 고유성 보장
 * - 조건부 작업 실행 지원
 * 
 * 키 구성 요소:
 * - workspaceId: 워크스페이스 ID (조직/프로젝트 구분)
 * - dockSn: Dock 시리얼 번호 (하드웨어 구분)
 * - jobId: 웨이라인 작업 ID (작업 구분)
 * 
 * Redis 키 형식:
 * - 생성: "workspaceId:dockSn:jobId"
 * - 예시: "ws_123:dock_456:job_789"
 * 
 * 사용 시나리오:
 * - Redis 캐시에서 웨이라인 작업 정보 저장/조회
 * - 조건부 웨이라인 작업 실행 관리
 * - 웨이라인 작업 상태 캐싱
 * - 동시 작업 실행 제어
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/28
 */
@Data
public class ConditionalWaylineJobKey {

    /**
     * 워크스페이스 ID
     * 웨이라인 작업이 속한 워크스페이스의 고유 식별자
     * 
     * 특징:
     * - 조직 또는 프로젝트 단위로 구분
     * - 다중 테넌트 환경에서 격리 보장
     * - 사용자 권한 및 접근 제어에 사용
     * 
     * 사용 목적:
     * - 워크스페이스별 작업 분리
     * - 권한 기반 접근 제어
     * - 데이터 격리 및 보안
     */
    private String workspaceId;

    /**
     * Dock 시리얼 번호
     * 웨이라인 작업을 실행할 DJI Dock의 고유 시리얼 번호
     * 
     * 특징:
     * - 하드웨어 레벨의 고유 식별자
     * - 물리적 장비 구분
     * - 동일 Dock에서의 동시 작업 제어
     * 
     * 사용 목적:
     * - Dock별 작업 분리
     * - 하드웨어 리소스 관리
     * - 동시 작업 실행 제어
     */
    private String dockSn;

    /**
     * 웨이라인 작업 ID
     * 웨이라인 작업의 고유 식별자
     * 
     * 특징:
     * - 시스템에서 자동 생성되는 고유 ID
     * - 작업의 생명주기 관리
     * - 작업 상태 및 진행률 추적
     * 
     * 사용 목적:
     * - 개별 작업 식별
     * - 작업 상태 관리
     * - 작업 이력 추적
     */
    private String jobId;

    /**
     * 조건부 웨이라인 작업 키 생성자
     * 
     * @param workspaceId 워크스페이스 ID
     * @param dockSn Dock 시리얼 번호
     * @param jobId 웨이라인 작업 ID
     */
    public ConditionalWaylineJobKey(String workspaceId, String dockSn, String jobId) {
        this.workspaceId = workspaceId;
        this.dockSn = dockSn;
        this.jobId = jobId;
    }

    /**
     * Redis 키 문자열로부터 조건부 웨이라인 작업 키 객체 생성
     * 
     * Redis 키 형식: "workspaceId:dockSn:jobId"
     * 구분자로 분리하여 각 구성 요소를 추출합니다.
     * 
     * @param key Redis 키 문자열 (예: "ws_123:dock_456:job_789")
     */
    public ConditionalWaylineJobKey(String key) {
        if (Objects.isNull(key)) {
            return;
        }
        // Redis 키를 구분자로 분리하여 각 구성 요소 추출
        String[] keyArr = key.split(RedisConst.DELIMITER);
        new ConditionalWaylineJobKey(keyArr[0], keyArr[1], keyArr[2]);
    }

    /**
     * Redis 캐시 키 생성
     * 
     * 워크스페이스 ID, Dock 시리얼 번호, 작업 ID를 구분자로 연결하여
     * Redis에서 사용할 수 있는 캐시 키를 생성합니다.
     * 
     * @return Redis 캐시 키 문자열 (예: "ws_123:dock_456:job_789")
     */
    public String getKey() {
        return String.join(RedisConst.DELIMITER, workspaceId, dockSn, jobId);
    }
}
