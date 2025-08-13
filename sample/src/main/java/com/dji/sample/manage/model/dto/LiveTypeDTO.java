package com.dji.sample.manage.model.dto;

import com.dji.sdk.cloudapi.device.VideoId;
import com.dji.sdk.cloudapi.livestream.LensChangeVideoTypeEnum;
import com.dji.sdk.cloudapi.livestream.UrlTypeEnum;
import com.dji.sdk.cloudapi.livestream.VideoQualityEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "라이브스트림 타입 정보 전송 객체")
public class LiveTypeDTO {

    @Schema(description = "라이브스트림 URL의 타입 (Agora, RTMP, RTSP, GB28181, WHIP 등)", enumAsRef = true)
    @JsonProperty("url_type")
    private UrlTypeEnum urlType;

    @Schema(description = "비디오 스트림을 식별하는 고유 ID")
    @JsonProperty("video_id")
    private VideoId videoId;

    @Schema(description = "비디오 스트림의 품질 설정 (HD, SD 등)", enumAsRef = true)
    @JsonProperty("video_quality")
    private VideoQualityEnum videoQuality;

    @Schema(description = "렌즈 변경 비디오 타입 (광각, 줌 등)", enumAsRef = true)
    private LensChangeVideoTypeEnum videoType;
}
