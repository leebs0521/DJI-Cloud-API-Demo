package com.dji.sample.manage.model.dto;

import com.dji.sample.control.model.enums.DroneAuthorityEnum;
import com.dji.sdk.cloudapi.device.ControlSourceEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 디바이스 권한 정보 전송 객체 (DTO) 클래스
 * 
 * DJI Cloud API의 디바이스 권한 정보를 전송하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 디바이스 권한 관리
 *    - 디바이스 시리얼 번호 기반 권한 관리
 *    - 드론 권한 열거형을 통한 권한 타입 관리
 *    - 디바이스별 권한 설정 및 제어
 * 
 * 2. 제어 소스 관리
 *    - 디바이스 제어 권한의 출처 관리
 *    - 제어 소스별 권한 분류 및 관리
 *    - 제어 권한의 출처 추적 및 관리
 * 
 * 3. 권한 타입 분류
 *    - DroneAuthorityEnum을 통한 권한 타입 분류
 *    - 다양한 권한 레벨 및 타입 지원
 *    - 권한별 세분화된 제어 기능 제공
 * 
 * 4. API 통신 지원
 *    - Lombok 어노테이션을 통한 자동 getter/setter 생성
 *    - 빌더 패턴을 통한 편리한 객체 생성
 *    - API 요청/응답에서 사용되는 표준화된 형식 제공
 * 
 * 이 클래스는 디바이스의 권한 설정 및 제어를 위한 모든 정보를
 * 포함하며, 디바이스 접근 권한과 제어 권한을 관리하는
 * 표준화된 데이터 형식을 제공합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/2
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceAuthorityDTO {

    /**
     * 디바이스 시리얼 번호
     * 권한을 설정할 디바이스의 시리얼 번호
     */
    private String sn;

    /**
     * 드론 권한 타입
     * 디바이스에 부여할 권한의 타입 (DroneAuthorityEnum)
     */
    private DroneAuthorityEnum type;

    /**
     * 제어 소스
     * 디바이스 제어 권한의 출처 (예: PILOT, DOCK 등)
     */
    private ControlSourceEnum controlSource;

}
