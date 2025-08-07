package com.dji.sdk.cloudapi.device;

/**
 * 미디어 파일 상세 정보 클래스
 * 
 * 이 클래스는 미디어 파일의 상세 정보를 담습니다.
 * 남은 업로드 개수 등을 포함합니다.
 * 
 * @author sean
 * @version 1.1
 * @date 2022/6/17
 */
public class MediaFileDetail {

    /**
     * 남은 업로드 개수
     */
    private Integer remainUpload;

    /**
     * 기본 생성자
     */
    public MediaFileDetail() {
    }

    @Override
    public String toString() {
        return "MediaFileDetail{" +
                "remainUpload=" + remainUpload +
                '}';
    }

    /**
     * 남은 업로드 개수를 반환합니다.
     * 
     * @return 남은 업로드 개수
     */
    public Integer getRemainUpload() {
        return remainUpload;
    }

    /**
     * 남은 업로드 개수를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param remainUpload 설정할 남은 업로드 개수
     * @return 현재 MediaFileDetail 객체
     */
    public MediaFileDetail setRemainUpload(Integer remainUpload) {
        this.remainUpload = remainUpload;
        return this;
    }
}
