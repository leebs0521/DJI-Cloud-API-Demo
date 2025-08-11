package com.dji.sample.manage.service;

import com.dji.sample.manage.model.dto.DeviceHmsDTO;
import com.dji.sample.manage.model.param.DeviceHmsQueryParam;
import com.dji.sdk.common.PaginationData;

/**
 * 디바이스 HMS 관리 서비스 인터페이스
 * 
 * DJI Cloud API의 디바이스 HMS(Health Management System) 관리를 위한 서비스 인터페이스입니다.
 * 이 인터페이스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. HMS 데이터 조회 및 관리
 *    - 디바이스 HMS 데이터 페이지네이션 조회
 *    - 다양한 쿼리 조건에 따른 HMS 데이터 필터링
 *    - HMS 데이터베이스 관리
 * 
 * 2. HMS 메시지 상태 관리
 *    - 읽지 않은 HMS 메시지 처리
 *    - HMS 메시지 읽음 상태 업데이트
 *    - HMS 알림 상태 관리
 * 
 * 3. 디바이스 상태 모니터링
 *    - 디바이스 건강 상태 추적
 *    - HMS 경고 및 오류 메시지 관리
 *    - 디바이스 유지보수 알림
 * 
 * 이 인터페이스는 DJI 디바이스의 건강 상태를
 * 체계적으로 모니터링하고 관리할 수 있도록 지원합니다.
 * 
 * @author sean
 * @version 1.1
 * @date 2022/7/6
 */
public interface IDeviceHmsService {

    /**
     * HMS 데이터 페이지네이션 조회
     * 
     * 쿼리 파라미터에 따라 디바이스의 HMS 데이터를
     * 페이지네이션과 함께 조회합니다.
     * 
     * @param param HMS 쿼리 파라미터 (디바이스 SN, 날짜 범위, 메시지 타입 등)
     * @return 페이지네이션된 HMS 데이터 목록
     */
    PaginationData<DeviceHmsDTO> getDeviceHmsByParam(DeviceHmsQueryParam param);

    /**
     * 읽지 않은 HMS 메시지 처리
     * 
     * 특정 디바이스의 읽지 않은 HMS 메시지를
     * 읽음 상태로 업데이트합니다.
     * 
     * @param deviceSn 디바이스 시리얼 번호
     */
    void updateUnreadHms(String deviceSn);
}
