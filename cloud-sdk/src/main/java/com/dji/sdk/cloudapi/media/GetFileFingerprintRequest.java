package com.dji.sdk.cloudapi.media;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 파일 지문 조회 요청 데이터 클래스
 * 
 * 이 클래스는 워크스페이스에 존재하는 파일들의 지문을 조회하기 위한
 * 요청 데이터를 정의합니다. 작은 지문(tiny fingerprint) 컬렉션을 사용하여
 * 중복 파일을 검사합니다.
 * 
 * 주요 구성 요소:
 * - tinyFingerprints: 작은 지문 컬렉션 (파일명과 촬영 시간 기반)
 * 
 * 이 클래스는 대용량 파일 업로드 전에 중복 파일을 확인하여
 * 네트워크 대역폭을 절약하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/16
 */
@Schema(description = "get request data for tiny fingerprints of existing files")
public class GetFileFingerprintRequest {

    /**
     * 작은 지문 컬렉션
     * 
     * 조회할 파일들의 작은 지문(tiny fingerprint) 목록입니다.
     * 파일명과 촬영 시간을 기반으로 생성되는 고유 식별자입니다.
     * 예: "297f490b0252690d3f93841818567cc6_2022_8_31_15_16_16"
     */
    @NotNull
    @Schema(description = "tiny fingerprints collection", example = "[\"297f490b0252690d3f93841818567cc6_2022_8_31_15_16_16\"]")
    @JsonProperty("tiny_fingerprints")
    private List<String> tinyFingerprints;

    public GetFileFingerprintRequest() {
    }

    @Override
    public String toString() {
        return "GetFileFingerprintRequest{" +
                "tinyFingerprints=" + tinyFingerprints +
                '}';
    }

    /**
     * 작은 지문 컬렉션을 반환합니다.
     * 
     * @return 작은 지문 컬렉션
     */
    public List<String> getTinyFingerprints() {
        return tinyFingerprints;
    }

    /**
     * 작은 지문 컬렉션을 설정합니다.
     * 
     * @param tinyFingerprints 작은 지문 컬렉션
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public GetFileFingerprintRequest setTinyFingerprints(List<String> tinyFingerprints) {
        this.tinyFingerprints = tinyFingerprints;
        return this;
    }
}
