package com.dji.sample.manage.model.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 로그 파일 업데이트 파라미터 클래스
 * 
 * DJI Cloud API의 로그 파일 업데이트 요청을 위한 파라미터 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 로그 파일 상태 업데이트
 *    - 로그 파일의 상태 변경 (업로드 중, 완료, 실패 등)
 *    - 로그 파일 상태의 일괄 업데이트
 *    - 로그 파일 상태 변경 추적
 * 
 * 2. 디바이스 모듈 도메인 관리
 *    - 디바이스 모듈 도메인별 로그 파일 관리
 *    - 다중 모듈 도메인 지원
 *    - 모듈별 로그 파일 분류 및 처리
 * 
 * 3. JSON 직렬화 지원
 *    - @JsonProperty 어노테이션으로 JSON 필드명 매핑
 *    - API 요청/응답과의 호환성 보장
 *    - 표준화된 데이터 형식 제공
 * 
 * 이 클래스는 로그 파일 관리 시스템에서 로그 파일의
 * 상태를 안전하고 효율적으로 업데이트할 수 있도록
 * 지원합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/9/9
 */
@Data
public class LogsFileUpdateParam {

    /**
     * 로그 파일 상태
     * 업데이트할 로그 파일의 상태 (예: "uploading", "completed", "failed" 등)
     */
    private String status;

    /**
     * 디바이스 모듈 도메인 목록
     * 업데이트할 로그 파일이 속한 디바이스 모듈 도메인들의 목록
     */
    @JsonProperty("module_list")
    private List<String> deviceModelDomainList;

}
