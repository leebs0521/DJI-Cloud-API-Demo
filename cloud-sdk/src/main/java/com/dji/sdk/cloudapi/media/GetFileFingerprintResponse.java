package com.dji.sdk.cloudapi.media;


import com.dji.sdk.common.BaseModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 파일 지문 조회 응답 데이터 클래스
 * 
 * 이 클래스는 워크스페이스에 존재하는 파일들의 지문 조회 결과를 정의합니다.
 * BaseModel을 상속받아 유효성 검사를 지원하며, 중복 파일 검사 결과를 반환합니다.
 * 
 * 주요 구성 요소:
 * - tinyFingerprints: 존재하는 파일들의 작은 지문 컬렉션
 * 
 * 이 클래스는 파일 업로드 전 중복 검사 결과를 반환하여
 * 불필요한 업로드를 방지하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/16
 */
@Schema(description = "response data for tiny fingerprints of existing files")
public class GetFileFingerprintResponse extends BaseModel {

    /**
     * 존재하는 파일들의 작은 지문 컬렉션
     * 
     * 워크스페이스에 이미 존재하는 파일들의 작은 지문(tiny fingerprint) 목록입니다.
     * 이 목록에 포함된 지문을 가진 파일들은 중복 파일로 간주되어
     * 업로드를 건너뛸 수 있습니다.
     * 예: "297f490b0252690d3f93841818567cc6_2022_8_31_15_16_16"
     */
    @NotNull
    @Schema(description = "tiny fingerprints collection", example = "[\"297f490b0252690d3f93841818567cc6_2022_8_31_15_16_16\"]")
    @JsonProperty("tiny_fingerprints")
    private List<String> tinyFingerprints;

    public GetFileFingerprintResponse() {
    }

    @Override
    public String toString() {
        return "GetFileFingerprintResponse{" +
                "tinyFingerprints=" + tinyFingerprints +
                '}';
    }

    /**
     * 존재하는 파일들의 작은 지문 컬렉션을 반환합니다.
     * 
     * @return 존재하는 파일들의 작은 지문 컬렉션
     */
    public List<String> getTinyFingerprints() {
        return tinyFingerprints;
    }

    /**
     * 존재하는 파일들의 작은 지문 컬렉션을 설정합니다.
     * 
     * @param tinyFingerprints 존재하는 파일들의 작은 지문 컬렉션
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public GetFileFingerprintResponse setTinyFingerprints(List<String> tinyFingerprints) {
        this.tinyFingerprints = tinyFingerprints;
        return this;
    }
}
