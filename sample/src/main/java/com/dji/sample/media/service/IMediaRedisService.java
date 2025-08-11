package com.dji.sample.media.service;

import com.dji.sample.media.model.MediaFileCountDTO;

/**
 * 미디어 Redis 서비스 인터페이스
 * 미디어 파일 업로드 진행 상황과 우선순위 정보를 Redis에 저장하고 관리하는 서비스 인터페이스입니다.
 * 파일 업로드 카운트, 우선순위 업로드 상태 등을 캐시로 관리하여 성능을 향상시킵니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/12/9
 */
public interface IMediaRedisService {

    /**
     * 미디어 파일 카운트 정보를 Redis에 저장합니다.
     * @param gatewaySn 게이트웨이 시리얼 번호
     * @param jobId 작업 ID
     * @param mediaFile 미디어 파일 카운트 DTO
     */
    void setMediaCount(String gatewaySn, String jobId, MediaFileCountDTO mediaFile);

    /**
     * Redis에서 미디어 파일 카운트 정보를 조회합니다.
     * @param gatewaySn 게이트웨이 시리얼 번호
     * @param jobId 작업 ID
     * @return 미디어 파일 카운트 DTO
     */
    MediaFileCountDTO getMediaCount(String gatewaySn, String jobId);

    /**
     * 특정 작업의 미디어 파일 카운트 정보를 Redis에서 삭제합니다.
     * @param gatewaySn 게이트웨이 시리얼 번호
     * @param jobId 작업 ID
     * @return 삭제 성공 여부
     */
    boolean delMediaCount(String gatewaySn, String jobId);

    /**
     * 특정 디바이스의 모든 미디어 파일 카운트 정보를 Redis에서 삭제합니다.
     * @param gatewaySn 게이트웨이 시리얼 번호
     * @return 삭제 성공 여부
     */
    boolean detMediaCountByDeviceSn(String gatewaySn);

    /**
     * 최고 우선순위 미디어 파일 정보를 Redis에 저장합니다.
     * @param gatewaySn 게이트웨이 시리얼 번호
     * @param mediaFile 미디어 파일 카운트 DTO
     */
    void setMediaHighestPriority(String gatewaySn, MediaFileCountDTO mediaFile);

    /**
     * Redis에서 최고 우선순위 미디어 파일 정보를 조회합니다.
     * @param gatewaySn 게이트웨이 시리얼 번호
     * @return 최고 우선순위 미디어 파일 카운트 DTO
     */
    MediaFileCountDTO getMediaHighestPriority(String gatewaySn);

    /**
     * 최고 우선순위 미디어 파일 정보를 Redis에서 삭제합니다.
     * @param gatewaySn 게이트웨이 시리얼 번호
     * @return 삭제 성공 여부
     */
    boolean delMediaHighestPriority(String gatewaySn);

}
