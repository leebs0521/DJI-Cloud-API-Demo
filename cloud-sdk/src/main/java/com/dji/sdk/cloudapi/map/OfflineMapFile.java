package com.dji.sdk.cloudapi.map;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 오프라인 맵 파일 정보 클래스
 * 
 * 이 클래스는 오프라인 맵 파일의 메타데이터를 관리합니다.
 * 드론이 인터넷 연결 없이도 지도를 사용할 수 있도록 하는
 * 오프라인 맵 파일의 정보를 담고 있습니다.
 * 
 * 주요 구성 요소:
 * - name: 파일명 (버전 정보 포함)
 * - url: 파일 다운로드 URL
 * - checksum: 파일 무결성 검증용 체크섬
 * - size: 파일 크기 (바이트 단위)
 * 
 * 오프라인 맵 파일은 드론의 안정적인 비행을 위해 중요한 역할을 합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/20
 */
public class OfflineMapFile {

    /**
     * 오프라인 맵 파일명
     * 
     * 버전을 결정하는 방법으로 사용되며, 형식은 다음과 같습니다:
     * offline_map_{sync_method}_{version}.rocksdb.zip
     * 
     * 구성 요소:
     * - offline_map: 고정 접두사
     * - sync_method: 데이터 동기화 방법 (full: 전체 동기화)
     * - version: 버전 번호 (문자열)
     * - rocksdb.zip: 파일 확장자
     * 
     * 예시: "offline_map_full_v1.2.3.rocksdb.zip"
     */
    @NotNull
    @Pattern(regexp = "^offline_map_full_\\w+\\.rocksdb\\.zip$")
    private String name;

    /**
     * 파일 다운로드 URL
     * 
     * 오프라인 맵 파일을 다운로드할 수 있는 URL입니다.
     * HTTPS 프로토콜을 사용하여 안전한 다운로드를 보장합니다.
     */
    @NotNull
    private String url;

    /**
     * SHA256 체크섬
     * 
     * SHA256 알고리즘으로 계산된 체크섬입니다.
     * 이 값은 파일이 완전한지 확인하는 데 사용할 수 있습니다.
     * 파일 다운로드 후 체크섬을 검증하여 파일 무결성을 보장합니다.
     */
    @NotNull
    private String checksum;

    /**
     * 파일 크기
     * 
     * 파일의 크기를 바이트 단위로 나타냅니다.
     * 다운로드 진행률 계산이나 저장 공간 확인에 사용됩니다.
     */
    @NotNull
    private Long size;

    public OfflineMapFile() {
    }

    @Override
    public String toString() {
        return "OfflineMapFile{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", checksum='" + checksum + '\'' +
                ", size=" + size +
                '}';
    }

    public String getName() {
        return name;
    }

    public OfflineMapFile setName(String name) {
        this.name = name;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public OfflineMapFile setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getChecksum() {
        return checksum;
    }

    public OfflineMapFile setChecksum(String checksum) {
        this.checksum = checksum;
        return this;
    }

    public Long getSize() {
        return size;
    }

    public OfflineMapFile setSize(Long size) {
        this.size = size;
        return this;
    }
}
