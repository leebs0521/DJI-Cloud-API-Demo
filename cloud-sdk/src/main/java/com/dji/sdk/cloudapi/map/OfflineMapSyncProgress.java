package com.dji.sdk.cloudapi.map;

/**
 * 오프라인 맵 동기화 진행 상황 클래스
 * 
 * 이 클래스는 오프라인 맵 파일의 동기화 진행 상황을 추적합니다.
 * 드론이나 도킹 스테이션에서 오프라인 맵 파일을 다운로드하고
 * 적용하는 과정의 상태를 관리합니다.
 * 
 * 주요 구성 요소:
 * - status: 동기화 상태 (대기, 진행 중, 완료, 실패 등)
 * - reason: 결과 코드 (성공 또는 실패 이유)
 * - file: 동기화된 파일 정보
 * 
 * 이 클래스는 오프라인 맵 동기화 과정을 모니터링하고
 * 문제 발생 시 디버깅에 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/20
 */
public class OfflineMapSyncProgress {

    /**
     * 동기화 상태
     * 
     * 오프라인 맵 동기화의 현재 상태를 나타냅니다.
     * OfflineMapSyncStatusEnum을 사용하여 상태를 정의합니다.
     * 
     * 가능한 상태:
     * - wait_sync: 동기화 대기
     * - synchronizing: 동기화 진행 중
     * - synchronized: 동기화 완료
     * - fail: 동기화 실패
     * - switch_fail: 전환 실패
     */
    private OfflineMapSyncStatusEnum status;

    /**
     * 결과 코드
     * 
     * 동기화 결과를 나타내는 코드입니다.
     * OfflineMapSyncReasonEnum을 사용하여 결과를 정의합니다.
     * 
     * 성공 시: SUCCESS(0)
     * 실패 시: 각종 실패 이유 코드
     */
    private OfflineMapSyncReasonEnum reason;

    /**
     * 오프라인 맵 파일 정보
     * 
     * 동기화된 오프라인 맵 파일의 정보를 포함합니다.
     * 파일명, URL, 체크섬, 크기 등의 메타데이터를 담고 있습니다.
     */
    private OfflineMapSyncFile file;

    public OfflineMapSyncProgress() {
    }

    @Override
    public String toString() {
        return "OfflineMapSyncProgress{" +
                "status=" + status +
                ", reason=" + reason +
                ", file=" + file +
                '}';
    }

    public OfflineMapSyncStatusEnum getStatus() {
        return status;
    }

    public OfflineMapSyncProgress setStatus(OfflineMapSyncStatusEnum status) {
        this.status = status;
        return this;
    }

    public OfflineMapSyncReasonEnum getReason() {
        return reason;
    }

    public OfflineMapSyncProgress setReason(OfflineMapSyncReasonEnum reason) {
        this.reason = reason;
        return this;
    }

    public OfflineMapSyncFile getFile() {
        return file;
    }

    public OfflineMapSyncProgress setFile(OfflineMapSyncFile file) {
        this.file = file;
        return this;
    }
}
