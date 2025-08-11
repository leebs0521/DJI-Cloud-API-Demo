package com.dji.sample.manage.model.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 디바이스 펌웨어 업로드 파라미터 클래스
 * 
 * DJI Cloud API의 디바이스 펌웨어 업로드 요청을 위한 파라미터 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 펌웨어 업로드 정보 관리
 *    - 펌웨어 릴리즈 노트 관리
 *    - 펌웨어 상태 설정 (활성/비활성)
 *    - 지원 디바이스 목록 관리
 * 
 * 2. 다중 디바이스 지원
 *    - 여러 디바이스에 대한 펌웨어 호환성 관리
 *    - 디바이스별 펌웨어 지원 정보 제공
 *    - 펌웨어 업로드 시 호환성 검증
 * 
 * 3. 데이터 검증 및 직렬화
 *    - @NotNull 어노테이션으로 필수 파라미터 검증
 *    - @JsonProperty 어노테이션으로 JSON 필드명 매핑
 *    - API 요청의 안정성 및 호환성 보장
 * 
 * 이 클래스는 펌웨어 관리 시스템에서 새로운 펌웨어를
 * 안전하고 효율적으로 업로드할 수 있도록
 * 지원합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/12/1
 */
@Data
public class DeviceFirmwareUploadParam {

    /**
     * 릴리즈 노트
     * 펌웨어 업데이트 내용에 대한 설명
     * 필수 입력 파라미터입니다.
     */
    @NotNull
    @JsonProperty("release_note")
    private String releaseNote;
    
    /**
     * 펌웨어 상태
     * 펌웨어의 활성/비활성 상태 (true: 활성, false: 비활성)
     * 필수 입력 파라미터입니다.
     */
    @NotNull
    private Boolean status;

    /**
     * 디바이스 이름 목록
     * 이 펌웨어가 지원하는 디바이스들의 이름 목록
     * 필수 입력 파라미터입니다.
     */
    @NotNull
    @JsonProperty("device_name")
    private List<String> deviceName;
}
