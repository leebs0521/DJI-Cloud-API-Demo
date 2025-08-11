package com.dji.sample.manage.model.receiver;

import com.dji.sdk.cloudapi.log.FileUploadProgressExt;
import lombok.Data;

/**
 * 로그 출력 진행 상황 수신기 클래스
 * 
 * DJI Cloud API의 로그 출력 진행 상황 정보를 수신하는 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 로그 업로드 진행 상황 관리
 *    - FileUploadProgressExt를 활용한 상세 진행 상황 관리
 *    - 로그 업로드 상태 정보 관리
 *    - 로그 업로드 진행률 추적
 * 
 * 2. 로그 업로드 상태 모니터링
 *    - 로그 업로드 상태 문자열 관리
 *    - 업로드 진행 상황 실시간 추적
 *    - 업로드 완료/실패 상태 관리
 * 
 * 3. 로그 업로드 최적화
 *    - 로그 업로드 진행 상황 모니터링
 *    - 업로드 상태별 적절한 처리 로직 제공
 *    - 업로드 실패 시 재시도 관리
 * 
 * 이 클래스는 DJI 디바이스의 로그 업로드 진행 상황을
 * 실시간으로 모니터링하고 관리할 수 있도록
 * 지원합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/9/9
 */
@Data
public class OutputLogsProgressReceiver {

    /**
     * 파일 업로드 진행 상황 확장 정보
     * 로그 파일 업로드의 상세한 진행 상황 정보
     */
    private FileUploadProgressExt ext;

    /**
     * 로그 업로드 상태
     * 로그 업로드의 현재 상태 (예: "uploading", "completed", "failed" 등)
     */
    private String status;
}
