package com.dji.sdk.cloudapi.media;

/**
 * 파일 업로드 콜백 파일 정보 클래스
 * 
 * 이 클래스는 파일 업로드 콜백 시 전달되는 파일의 상세 정보를 정의합니다.
 * 파일의 확장 정보, 이름, 경로, 객체 키, 메타데이터 등을 포함합니다.
 * 
 * 주요 구성 요소:
 * - ext: 업로드 콜백 파일 확장 정보
 * - name: 파일명
 * - path: 파일 경로
 * - objectKey: 클라우드 스토리지 객체 키
 * - metadata: 업로드 콜백 파일 메타데이터
 * 
 * 이 클래스는 파일 업로드 완료 후 서버에 보고되는 파일 정보를
 * 구조화하여 전달하는 데 사용됩니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/12/7
 */
public class FileUploadCallbackFile {

    /**
     * 업로드 콜백 파일 확장 정보
     * 
     * 파일의 추가 정보를 포함합니다.
     * 드론 모델, 페이로드 정보, 비행 ID 등의 메타데이터가 담겨있습니다.
     */
    private UploadCallbackFileExtension ext;

    /**
     * 파일명
     * 
     * 업로드된 파일의 이름입니다.
     * 드론에서 촬영한 미디어 파일의 원본 파일명을 유지합니다.
     */
    private String name;

    /**
     * 파일 경로
     * 
     * 업로드된 파일의 저장 경로입니다.
     * 웨이포인트에서 촬영된 경우 경로가 포함되고,
     * 수동 촬영의 경우 빈 값이 됩니다.
     */
    private String path;

    /**
     * 클라우드 스토리지 객체 키
     * 
     * 클라우드 스토리지 버킷 내에서 파일의 위치를 나타내는 키입니다.
     * 이 키를 사용하여 업로드된 파일에 접근할 수 있습니다.
     */
    private String objectKey;

    /**
     * 업로드 콜백 파일 메타데이터
     * 
     * 파일의 상세 메타데이터를 포함합니다.
     * 촬영 시간, 위치, 파일 크기, 해상도 등의 정보가 담겨있습니다.
     */
    private UploadCallbackFileMetadata metadata;

    public FileUploadCallbackFile() {
    }

    @Override
    public String toString() {
        return "FileUploadCallbackFile{" +
                "ext=" + ext +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", objectKey='" + objectKey + '\'' +
                ", metadata=" + metadata +
                '}';
    }

    /**
     * 업로드 콜백 파일 확장 정보를 반환합니다.
     * 
     * @return 업로드 콜백 파일 확장 정보
     */
    public UploadCallbackFileExtension getExt() {
        return ext;
    }

    /**
     * 업로드 콜백 파일 확장 정보를 설정합니다.
     * 
     * @param ext 업로드 콜백 파일 확장 정보
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FileUploadCallbackFile setExt(UploadCallbackFileExtension ext) {
        this.ext = ext;
        return this;
    }

    /**
     * 파일명을 반환합니다.
     * 
     * @return 파일명
     */
    public String getName() {
        return name;
    }

    /**
     * 파일명을 설정합니다.
     * 
     * @param name 파일명
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FileUploadCallbackFile setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * 파일 경로를 반환합니다.
     * 
     * @return 파일 경로
     */
    public String getPath() {
        return path;
    }

    /**
     * 파일 경로를 설정합니다.
     * 
     * @param path 파일 경로
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FileUploadCallbackFile setPath(String path) {
        this.path = path;
        return this;
    }

    /**
     * 클라우드 스토리지 객체 키를 반환합니다.
     * 
     * @return 클라우드 스토리지 객체 키
     */
    public String getObjectKey() {
        return objectKey;
    }

    /**
     * 클라우드 스토리지 객체 키를 설정합니다.
     * 
     * @param objectKey 클라우드 스토리지 객체 키
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FileUploadCallbackFile setObjectKey(String objectKey) {
        this.objectKey = objectKey;
        return this;
    }

    /**
     * 업로드 콜백 파일 메타데이터를 반환합니다.
     * 
     * @return 업로드 콜백 파일 메타데이터
     */
    public UploadCallbackFileMetadata getMetadata() {
        return metadata;
    }

    /**
     * 업로드 콜백 파일 메타데이터를 설정합니다.
     * 
     * @param metadata 업로드 콜백 파일 메타데이터
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FileUploadCallbackFile setMetadata(UploadCallbackFileMetadata metadata) {
        this.metadata = metadata;
        return this;
    }
}
