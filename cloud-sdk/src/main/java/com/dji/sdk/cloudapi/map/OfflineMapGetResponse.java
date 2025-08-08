package com.dji.sdk.cloudapi.map;

import com.dji.sdk.common.BaseModel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 오프라인 맵 정보 조회 응답 클래스
 * 
 * 이 클래스는 오프라인 맵 정보 조회 API의 응답 데이터를 정의합니다.
 * 도킹 스테이션이 클라우드에서 최신 오프라인 맵 파일 정보를 요청했을 때
 * 반환되는 응답 데이터를 담고 있습니다.
 * 
 * 주요 구성 요소:
 * - offlineMapEnable: 오프라인 맵 활성화 여부
 * - files: 오프라인 맵 파일 객체 리스트
 * 
 * 이 클래스는 도킹 스테이션이 오프라인 맵 동기화를 결정하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/20
 */
public class OfflineMapGetResponse extends BaseModel {

    /**
     * 오프라인 맵 활성화 여부
     * 
     * 이 매개변수는 도킹 스테이션이 항공기의 오프라인 맵 기능을
     * 비활성화할 수 있도록 합니다.
     * 
     * true: 오프라인 맵 기능 활성화
     * false: 오프라인 맵 기능 비활성화
     */
    @NotNull
    private Boolean offlineMapEnable;

    /**
     * 오프라인 맵 파일 객체 리스트
     * 
     * 사용 가능한 오프라인 맵 파일들의 정보를 포함합니다.
     * 각 파일은 OfflineMapFile 객체로 표현되며,
     * 파일명, URL, 체크섬, 크기 등의 메타데이터를 담고 있습니다.
     */
    @NotNull
    private List<@Valid OfflineMapFile> files;

    public OfflineMapGetResponse() {
    }

    @Override
    public String toString() {
        return "OfflineMapGetResponse{" +
                "offlineMapEnable=" + offlineMapEnable +
                ", files=" + files +
                '}';
    }

    public Boolean getOfflineMapEnable() {
        return offlineMapEnable;
    }

    public OfflineMapGetResponse setOfflineMapEnable(Boolean offlineMapEnable) {
        this.offlineMapEnable = offlineMapEnable;
        return this;
    }

    public List<OfflineMapFile> getFiles() {
        return files;
    }

    public OfflineMapGetResponse setFiles(List<OfflineMapFile> files) {
        this.files = files;
        return this;
    }
}
