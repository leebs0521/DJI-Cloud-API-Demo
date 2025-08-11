package com.dji.sample.manage.model.dto;

import com.dji.sdk.cloudapi.device.VideoId;
import com.dji.sdk.cloudapi.livestream.LensChangeVideoTypeEnum;
import com.dji.sdk.cloudapi.livestream.UrlTypeEnum;
import com.dji.sdk.cloudapi.livestream.VideoQualityEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 라이브스트림 타입 정보 전송 객체 (DTO) 클래스
 * 
 * DJI Cloud API의 라이브스트림 타입 정보를 전송하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 라이브스트림 URL 타입 관리
 *    - 라이브스트림 URL 타입 열거형 관리
 *    - 다양한 프로토콜 지원 (Agora, RTMP, RTSP, GB28181, WHIP)
 *    - URL 타입별 설정 정보 관리
 * 
 * 2. 비디오 식별 정보 관리
 *    - 비디오 ID 관리
 *    - 비디오 스트림 식별 정보 제공
 *    - 비디오 스트림별 설정 관리
 * 
 * 3. 비디오 품질 및 타입 관리
 *    - 비디오 품질 열거형 관리
 *    - 렌즈 변경 비디오 타입 관리
 *    - 비디오 스트림 품질 설정 관리
 * 
 * 4. JSON 직렬화 지원
 *    - @JsonProperty 어노테이션을 통한 JSON 필드명 매핑
 *    - API 요청/응답에서 사용되는 표준 JSON 형식 지원
 *    - Lombok 어노테이션을 통한 자동 getter/setter 생성
 * 
 * 이 클래스는 라이브스트림의 타입과 품질 설정을 관리하며,
 * 다양한 프로토콜과 비디오 스트림 설정을 지원하는
 * 표준화된 데이터 형식을 제공합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/22
 */
@Data
public class LiveTypeDTO {

    /**
     * URL 타입
     * 라이브스트림 URL의 타입 (Agora, RTMP, RTSP, GB28181, WHIP 등)
     */
    @JsonProperty("url_type")
    private UrlTypeEnum urlType;

    /**
     * 비디오 ID
     * 비디오 스트림을 식별하는 고유 ID
     */
    @JsonProperty("video_id")
    private VideoId videoId;

    /**
     * 비디오 품질
     * 비디오 스트림의 품질 설정 (HD, SD 등)
     */
    @JsonProperty("video_quality")
    private VideoQualityEnum videoQuality;

    /**
     * 비디오 타입
     * 렌즈 변경 비디오 타입 (광각, 줌 등)
     */
    private LensChangeVideoTypeEnum videoType;

}