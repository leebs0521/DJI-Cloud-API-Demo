package com.dji.sdk.cloudapi.media;

/**
 * 파일 업로드 콜백 데이터 클래스
 * 
 * 이 클래스는 파일 업로드 완료 후 호출되는 콜백의 데이터를 정의합니다.
 * 업로드 결과, 진행률, 파일 정보 등을 포함합니다.
 * 
 * 주요 구성 요소:
 * - result: 업로드 결과 코드
 * - progress: 업로드 진행률 (0-100)
 * - file: 업로드된 파일 정보
 * 
 * 이 클래스는 MQTT를 통해 파일 업로드 상태를 실시간으로 보고하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.1
 * @date 2022/6/9
 */
public class FileUploadCallback {

    /**
     * 업로드 결과 코드
     * 
     * 파일 업로드의 성공/실패 여부를 나타내는 코드입니다.
     * 0: 성공, 기타 값: 실패 (에러 코드)
     */
    private Integer result;

    /**
     * 업로드 진행률
     * 
     * 파일 업로드의 진행률을 백분율로 나타냅니다.
     * 0-100 사이의 값으로, 100은 완료를 의미합니다.
     */
    private Integer progress;

    /**
     * 업로드된 파일 정보
     * 
     * 업로드된 파일의 상세 정보를 포함합니다.
     * 파일명, 크기, 경로 등의 메타데이터가 담겨있습니다.
     */
    private FileUploadCallbackFile file;

    public FileUploadCallback() {
    }

    @Override
    public String toString() {
        return "FileUploadCallback{" +
                "result=" + result +
                ", progress=" + progress +
                ", file=" + file +
                '}';
    }

    /**
     * 업로드 결과 코드를 반환합니다.
     * 
     * @return 업로드 결과 코드
     */
    public Integer getResult() {
        return result;
    }

    /**
     * 업로드 결과 코드를 설정합니다.
     * 
     * @param result 업로드 결과 코드
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FileUploadCallback setResult(Integer result) {
        this.result = result;
        return this;
    }

    /**
     * 업로드 진행률을 반환합니다.
     * 
     * @return 업로드 진행률 (0-100)
     */
    public Integer getProgress() {
        return progress;
    }

    /**
     * 업로드 진행률을 설정합니다.
     * 
     * @param progress 업로드 진행률 (0-100)
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FileUploadCallback setProgress(Integer progress) {
        this.progress = progress;
        return this;
    }

    /**
     * 업로드된 파일 정보를 반환합니다.
     * 
     * @return 업로드된 파일 정보
     */
    public FileUploadCallbackFile getFile() {
        return file;
    }

    /**
     * 업로드된 파일 정보를 설정합니다.
     * 
     * @param file 업로드된 파일 정보
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FileUploadCallback setFile(FileUploadCallbackFile file) {
        this.file = file;
        return this;
    }
}
