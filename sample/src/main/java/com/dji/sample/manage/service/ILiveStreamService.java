package com.dji.sample.manage.service;

import com.dji.sample.manage.model.dto.CapacityDeviceDTO;
import com.dji.sample.manage.model.dto.LiveTypeDTO;
import com.dji.sdk.cloudapi.device.VideoId;
import com.dji.sdk.common.HttpResultResponse;

import java.util.List;

/**
 * 라이브 스트리밍 관리 서비스 인터페이스
 * 
 * DJI Cloud API의 라이브 스트리밍 관리를 위한 서비스 인터페이스입니다.
 * 이 인터페이스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 라이브 스트리밍 용량 관리
 *    - 워크스페이스 내 라이브 스트리밍 가능한 디바이스 조회
 *    - 디바이스별 라이브 스트리밍 용량 확인
 *    - 동시 스트리밍 지원 가능 여부 확인
 * 
 * 2. 라이브 스트리밍 제어
 *    - 라이브 스트리밍 시작/중지 관리
 *    - MQTT를 통한 실시간 스트리밍 제어
 *    - 스트리밍 상태 모니터링
 * 
 * 3. 스트리밍 품질 관리
 *    - 라이브 스트리밍 화질 조정
 *    - 실시간 화질 변경 지원
 *    - 다양한 해상도 및 비트레이트 지원
 * 
 * 4. 카메라 렌즈 제어
 *    - 라이브 스트리밍 중 렌즈 전환
 *    - 다중 카메라 디바이스 지원
 *    - 실시간 카메라 제어
 * 
 * 이 인터페이스는 DJI 디바이스의 라이브 스트리밍 기능을
 * 실시간으로 제어하고 관리할 수 있도록 지원합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/19
 * @version 0.1
 */
public interface ILiveStreamService {

    /**
     * 라이브 스트리밍 용량 조회
     * 
     * 워크스페이스 내에서 라이브 스트리밍이 가능한
     * 모든 드론 디바이스의 용량 정보를 조회합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @return 라이브 스트리밍 가능한 디바이스 목록
     */
    List<CapacityDeviceDTO> getLiveCapacity(String workspaceId);

    /**
     * 라이브 스트리밍 시작
     * 
     * MQTT 메시지를 발행하여 라이브 스트리밍을 시작합니다.
     * 디바이스에 스트리밍 시작 명령을 전송합니다.
     * 
     * @param liveParam 라이브 스트리밍 시작에 필요한 파라미터
     *                 (디바이스 SN, 카메라 인덱스, 화질 등)
     * @return 라이브 스트리밍 시작 결과
     */
    HttpResultResponse liveStart(LiveTypeDTO liveParam);

    /**
     * 라이브 스트리밍 중지
     * 
     * MQTT 메시지를 발행하여 라이브 스트리밍을 중지합니다.
     * 디바이스에 스트리밍 중지 명령을 전송합니다.
     * 
     * @param videoId 중지할 비디오 스트림의 ID
     * @return 라이브 스트리밍 중지 결과
     */
    HttpResultResponse liveStop(VideoId videoId);

    /**
     * 라이브 스트리밍 화질 조정
     * 
     * MQTT 메시지를 발행하여 라이브 스트리밍의
     * 화질을 실시간으로 조정합니다.
     * 
     * @param liveParam 화질 조정에 필요한 파라미터
     *                 (비디오 ID, 새로운 화질 설정 등)
     * @return 화질 조정 결과
     */
    HttpResultResponse liveSetQuality(LiveTypeDTO liveParam);

    /**
     * 라이브 스트리밍 중 렌즈 전환
     * 
     * 라이브 스트리밍 중에 디바이스의 렌즈를 전환합니다.
     * 다중 카메라 디바이스에서 다른 카메라로 전환할 때 사용됩니다.
     * 
     * @param liveParam 렌즈 전환에 필요한 파라미터
     *                 (비디오 ID, 새로운 카메라 인덱스 등)
     * @return 렌즈 전환 결과
     */
    HttpResultResponse liveLensChange(LiveTypeDTO liveParam);
}
