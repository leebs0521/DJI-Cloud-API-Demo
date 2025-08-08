package com.dji.sdk.cloudapi.map;

/**
 * 오프라인 맵 동기화 파일 클래스
 * 
 * 이 클래스는 오프라인 맵 동기화 과정에서 사용되는 파일 정보를 정의합니다.
 * 오프라인 맵 동기화 진행 상황을 추적할 때 사용되는 파일 메타데이터를 담고 있습니다.
 * 
 * 주요 구성 요소:
 * - name: 오프라인 맵 파일명 (버전 정보 포함)
 * - checksum: SHA256 체크섬 (파일 무결성 검증용)
 * 
 * 이 클래스는 OfflineMapSyncProgress에서 동기화된 파일의 정보를
 * 추적하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/20
 */
public class OfflineMapSyncFile {

    /**
     * 오프라인 맵 파일명
     * 
     * 버전을 결정하는 방법으로 사용되며, 형식은 다음과 같습니다:
     * offline_map_{sync_method}_{version}
     * 
     * 구성 요소:
     * - offline_map: 고정 접두사
     * - sync_method: 데이터 동기화 방법 (full: 전체 동기화)
     * - version: 버전 번호 (문자열)
     * 
     * 예시: "offline_map_full_v1.2.3"
     */
    private String name;

    /**
     * SHA256 체크섬
     * 
     * SHA256 알고리즘으로 계산된 체크섬입니다.
     * 이 값은 파일이 완전한지 확인하는 데 사용할 수 있습니다.
     * 파일 다운로드 후 체크섬을 검증하여 파일 무결성을 보장합니다.
     */
    private String checksum;

    public OfflineMapSyncFile() {
    }

    @Override
    public String toString() {
        return "OfflineMapSyncFile{" +
                "name='" + name + '\'' +
                ", checksum='" + checksum + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public OfflineMapSyncFile setName(String name) {
        this.name = name;
        return this;
    }

    public String getChecksum() {
        return checksum;
    }

    public OfflineMapSyncFile setChecksum(String checksum) {
        this.checksum = checksum;
        return this;
    }
}
