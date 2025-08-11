package com.dji.sample.manage.model.receiver;

import com.dji.sdk.cloudapi.device.DeviceDomainEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 펌웨어 버전 수신기 클래스
 * 
 * DJI Cloud API의 펌웨어 버전 정보를 수신하는 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 펌웨어 버전 정보 관리
 *    - 펌웨어 버전 문자열 관리
 *    - 펌웨어 호환성 상태 관리
 *    - 펌웨어 업그레이드 상태 관리
 * 
 * 2. 디바이스 정보 관리
 *    - 디바이스 시리얼 번호 관리
 *    - 디바이스 도메인 정보 관리
 *    - 디바이스별 펌웨어 버전 추적
 * 
 * 3. 펌웨어 상태 모니터링
 *    - 펌웨어 호환성 상태 모니터링
 *    - 펌웨어 업그레이드 진행 상황 추적
 *    - 펌웨어 버전 관리 및 업데이트 지원
 * 
 * 이 클래스는 DJI 디바이스의 펌웨어 버전 정보를
 * 체계적으로 관리하고 추적할 수 있도록
 * 지원합니다.
 * 
 * @author sean
 * @version 1.0
 * @date 2022/4/28
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FirmwareVersionReceiver {

    /**
     * 펌웨어 버전
     * 디바이스에 설치된 펌웨어의 버전 정보
     */
    private String firmwareVersion;

    /**
     * 호환성 상태
     * 펌웨어의 호환성 상태 (0: 일관성 있음, 1: 일관성 없음)
     */
    private Integer compatibleStatus;

    /**
     * 펌웨어 업그레이드 상태
     * 펌웨어 업그레이드의 현재 상태
     */
    private Integer firmwareUpgradeStatus;

    /**
     * 디바이스 시리얼 번호
     * 펌웨어가 설치된 디바이스의 시리얼 번호
     */
    private String sn;

    /**
     * 디바이스 도메인
     * 디바이스가 속한 도메인 (DeviceDomainEnum)
     */
    private DeviceDomainEnum domain;
}
