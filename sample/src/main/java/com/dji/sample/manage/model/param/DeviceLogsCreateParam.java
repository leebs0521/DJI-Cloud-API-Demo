package com.dji.sample.manage.model.param;

import com.dji.sdk.cloudapi.log.FileUploadStartFile;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

/**
 * 디바이스 로그 생성 파라미터 클래스
 * 
 * DJI Cloud API의 디바이스 로그 생성 요청을 위한 파라미터 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 로그 정보 관리
 *    - 로그 정보 내용 관리
 *    - 로그 발생 시간 관리
 *    - 로그 관련 파일 목록 관리
 * 
 * 2. 로그 파일 업로드 지원
 *    - FileUploadStartFile을 활용한 파일 업로드 정보 관리
 *    - 다중 파일 업로드 지원
 *    - 로그 파일과 로그 정보의 연동
 * 
 * 3. 로그 생성 프로세스 관리
 *    - 로그 생성 시점 추적
 *    - 로그 파일 업로드와 로그 정보 생성의 동기화
 *    - 로그 데이터의 일관성 보장
 * 
 * 이 클래스는 로그 관리 시스템에서 새로운 로그를
 * 안전하고 효율적으로 생성할 수 있도록
 * 지원합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/9/8
 */
@Data
@Schema(name = "DeviceLogsCreateParam", description = "디바이스 로그 생성 파라미터")
public class DeviceLogsCreateParam {

    @Schema(name = "logsInformation", description = "생성할 로그의 상세 정보 내용")
    private String logsInformation;

    @Schema(name = "happenTime", description = "로그가 발생한 시간 (밀리초)")
    private Long happenTime;

    @Schema(name = "files", description = "로그와 관련된 파일들의 업로드 정보 목록")
    private List<FileUploadStartFile> files;
}
