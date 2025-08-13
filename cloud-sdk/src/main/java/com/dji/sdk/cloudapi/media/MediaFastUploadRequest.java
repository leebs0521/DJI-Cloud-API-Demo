package com.dji.sdk.cloudapi.media;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
/**
 * 미디어 파일 빠른 업로드 요청 데이터 클래스
 * 
 * 이 클래스는 미디어 파일의 빠른 업로드를 위한 요청 데이터를 정의합니다.
 * 파일 지문(fingerprint)을 사용하여 중복 파일을 검사하고,
 * 이미 업로드된 파일인 경우 업로드를 건너뛰는 기능을 제공합니다.
 * 
 * 주요 구성 요소:
 * - ext: 빠른 업로드 확장 정보
 * - fingerprint: 미디어 파일 지문 (중복 검사용)
 * - name: 미디어 파일명
 * - path: 미디어 파일 경로 (웨이포인트에서 촬영되지 않은 경우 빈 값)
 * 
 * 이 클래스는 네트워크 대역폭을 절약하고 업로드 시간을 단축하는 데 사용됩니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/12/7
 */
@Schema(description = "미디어 빠른 업로드 요청 데이터")
public class MediaFastUploadRequest {
    /**
     * 빠른 업로드 확장 정보
     * 
     * 미디어 파일의 빠른 업로드를 위한 추가 정보를 포함합니다.
     * 파일 타입, 메타데이터, 업로드 설정 등의 정보가 담겨있습니다.
     */
    @NotNull
    @Valid
    private FastUploadExtension ext;
    /**
     * 미디어 파일 지문
     * 
     * 파일의 고유 식별자로 사용되는 지문(fingerprint)입니다.
     * 파일 내용을 기반으로 생성되며, 중복 파일 검사에 사용됩니다.
     * 예: "7F78C9F1999425CB61F10E1FE206009E"
     */
    @NotNull
    @Schema(description = "미디어 파일 지문", example = "7F78C9F1999425CB61F10E1FE206009E")
    private String fingerprint;
    /**
     * 미디어 파일명
     * 
     * 드론에서 촬영한 미디어 파일의 이름입니다.
     * DJI 카메라에서 자동으로 생성되는 파일명 형식을 따릅니다.
     * 예: "DJI_20220831151616_0004_W_Waypoint4.JPG"
     */
    @NotNull
    @Schema(description = "미디어 파일명", example = "DJI_20220831151616_0004_W_Waypoint4.JPG")
    private String name;
    /**
     * 미디어 파일 경로
     * 
     * 미디어 파일의 저장 경로입니다.
     * 웨이포인트에서 촬영된 경우 경로가 포함되고,
     * 수동 촬영의 경우 빈 값이 됩니다.
     * 예: "DJI_202208311455_008_Waypoint1"
     */
    @Schema(description = "미디어 파일 경로. 사진이 웨이포인트에서 촬영되지 않았다면 빈 문자열입니다.", example = "DJI_202208311455_008_Waypoint1")
    private String path;
    public MediaFastUploadRequest() {
    }
    @Override
    public String toString() {
        return "MediaFastUploadRequest{" +
                "ext=" + ext +
                ", fingerprint='" + fingerprint + '\'' +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
    public FastUploadExtension getExt() {
        return ext;
    }
    public MediaFastUploadRequest setExt(FastUploadExtension ext) {
        this.ext = ext;
        return this;
    }
    public String getFingerprint() {
        return fingerprint;
    }
    public MediaFastUploadRequest setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
        return this;
    }
    public String getName() {
        return name;
    }
    public MediaFastUploadRequest setName(String name) {
        this.name = name;
        return this;
    }
    public String getPath() {
        return path;
    }
    public MediaFastUploadRequest setPath(String path) {
        this.path = path;
        return this;
    }
}
