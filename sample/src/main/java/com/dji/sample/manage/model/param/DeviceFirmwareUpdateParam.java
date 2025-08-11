package com.dji.sample.manage.model.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 디바이스 펌웨어 업데이트 파라미터 클래스
 * 
 * DJI Cloud API의 디바이스 펌웨어 업데이트 요청을 위한 파라미터 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 펌웨어 상태 업데이트
 *    - 펌웨어의 활성/비활성 상태 변경
 *    - 펌웨어 상태의 필수 입력 검증
 *    - 펌웨어 관리 시스템의 상태 제어
 * 
 * 2. 데이터 검증 지원
 *    - @NotNull 어노테이션으로 필수 파라미터 검증
 *    - 입력 데이터의 유효성 검사
 *    - API 요청의 안정성 보장
 * 
 * 3. 펌웨어 관리 기능
 *    - 펌웨어 활성화/비활성화 제어
 *    - 펌웨어 상태 변경 추적
 *    - 펌웨어 관리 시스템의 일관성 유지
 * 
 * 이 클래스는 펌웨어 관리 시스템에서 펌웨어의
 * 상태를 안전하고 효율적으로 업데이트할 수 있도록
 * 지원합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/12/6
 */
@Data
public class DeviceFirmwareUpdateParam {

    /**
     * 펌웨어 상태
     * 펌웨어의 활성/비활성 상태 (true: 활성, false: 비활성)
     * 필수 입력 파라미터입니다.
     */
    @NotNull
    private Boolean status;
}
