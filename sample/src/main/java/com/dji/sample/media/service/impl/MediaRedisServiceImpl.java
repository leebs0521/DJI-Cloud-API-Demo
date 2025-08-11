package com.dji.sample.media.service.impl;

import com.dji.sample.component.redis.RedisConst;
import com.dji.sample.component.redis.RedisOpsUtils;
import com.dji.sample.media.model.MediaFileCountDTO;
import com.dji.sample.media.service.IMediaRedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 미디어 Redis 서비스 구현체
 * 미디어 파일 업로드 진행 상황과 우선순위 정보를 Redis에 저장하고 관리하는 서비스 구현 클래스입니다.
 * 파일 업로드 카운트, 우선순위 업로드 상태 등을 캐시로 관리하여 성능을 향상시키며,
 * Redis의 Hash 구조와 String 구조를 활용하여 데이터를 효율적으로 저장합니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/12/9
 */
@Service
@Slf4j
public class MediaRedisServiceImpl implements IMediaRedisService {

    /**
     * 미디어 파일 카운트 정보를 Redis에 저장합니다.
     * Hash 구조를 사용하여 게이트웨이별로 작업 ID와 미디어 파일 카운트 정보를 저장합니다.
     * @param gatewaySn 게이트웨이 시리얼 번호
     * @param jobId 작업 ID
     * @param mediaFile 미디어 파일 카운트 DTO
     */
    @Override
    public void setMediaCount(String gatewaySn, String jobId, MediaFileCountDTO mediaFile) {
        RedisOpsUtils.hashSet(RedisConst.MEDIA_FILE_PREFIX + gatewaySn, jobId, mediaFile);
    }

    /**
     * Redis에서 미디어 파일 카운트 정보를 조회합니다.
     * Hash 구조에서 특정 작업 ID에 해당하는 미디어 파일 카운트 정보를 가져옵니다.
     * @param gatewaySn 게이트웨이 시리얼 번호
     * @param jobId 작업 ID
     * @return 미디어 파일 카운트 DTO
     */
    @Override
    public MediaFileCountDTO getMediaCount(String gatewaySn, String jobId) {
        return (MediaFileCountDTO) RedisOpsUtils.hashGet(RedisConst.MEDIA_FILE_PREFIX + gatewaySn, jobId);
    }

    /**
     * 특정 작업의 미디어 파일 카운트 정보를 Redis에서 삭제합니다.
     * Hash 구조에서 특정 작업 ID에 해당하는 필드를 삭제합니다.
     * @param gatewaySn 게이트웨이 시리얼 번호
     * @param jobId 작업 ID
     * @return 삭제 성공 여부
     */
    @Override
    public boolean delMediaCount(String gatewaySn, String jobId) {
        return RedisOpsUtils.hashDel(RedisConst.MEDIA_FILE_PREFIX + gatewaySn, new String[]{jobId});
    }

    /**
     * 특정 디바이스의 모든 미디어 파일 카운트 정보를 Redis에서 삭제합니다.
     * 해당 게이트웨이의 전체 Hash 키를 삭제합니다.
     * @param gatewaySn 게이트웨이 시리얼 번호
     * @return 삭제 성공 여부
     */
    @Override
    public boolean detMediaCountByDeviceSn(String gatewaySn) {
        return RedisOpsUtils.del(RedisConst.MEDIA_FILE_PREFIX + gatewaySn);
    }

    /**
     * 최고 우선순위 미디어 파일 정보를 Redis에 저장합니다.
     * String 구조를 사용하여 게이트웨이별로 최고 우선순위 미디어 파일 정보를 저장하며,
     * 만료 시간을 설정하여 자동 정리되도록 합니다.
     * @param gatewaySn 게이트웨이 시리얼 번호
     * @param mediaFile 미디어 파일 카운트 DTO
     */
    @Override
    public void setMediaHighestPriority(String gatewaySn, MediaFileCountDTO mediaFile) {
        RedisOpsUtils.setWithExpire(RedisConst.MEDIA_HIGHEST_PRIORITY_PREFIX + gatewaySn, mediaFile, RedisConst.DEVICE_ALIVE_SECOND * 5);
    }

    /**
     * Redis에서 최고 우선순위 미디어 파일 정보를 조회합니다.
     * String 구조에서 최고 우선순위 미디어 파일 정보를 가져옵니다.
     * @param gatewaySn 게이트웨이 시리얼 번호
     * @return 최고 우선순위 미디어 파일 카운트 DTO
     */
    @Override
    public MediaFileCountDTO getMediaHighestPriority(String gatewaySn) {
        return (MediaFileCountDTO) RedisOpsUtils.get(RedisConst.MEDIA_HIGHEST_PRIORITY_PREFIX + gatewaySn);
    }

    /**
     * 최고 우선순위 미디어 파일 정보를 Redis에서 삭제합니다.
     * String 구조에서 최고 우선순위 미디어 파일 정보를 삭제합니다.
     * @param gatewaySn 게이트웨이 시리얼 번호
     * @return 삭제 성공 여부
     */
    @Override
    public boolean delMediaHighestPriority(String gatewaySn) {
        return RedisOpsUtils.del(RedisConst.MEDIA_HIGHEST_PRIORITY_PREFIX + gatewaySn);
    }
}
