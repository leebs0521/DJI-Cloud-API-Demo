package com.dji.sample.manage.model.enums;

import com.dji.sdk.cloudapi.log.FileUploadStatusEnum;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

/**
 * 디바이스 로그 상태 열거형
 * 
 * DJI Cloud API의 디바이스 로그 상태를 정의하는 열거형 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 로그 상태 정의
 *    - 업로드 중 (UPLOADING)
 *    - 완료 (DONE)
 *    - 취소됨 (CANCELED)
 *    - 실패 (FAILED)
 *    - 알 수 없는 상태 (UNKNOWN)
 * 
 * 2. 파일 업로드 상태 매핑
 *    - FileUploadStatusEnum과의 매핑 관계
 *    - 여러 업로드 상태를 하나의 로그 상태로 그룹화
 *    - 업로드 상태별 세분화된 처리
 * 
 * 3. 로그 업로드 프로세스 관리
 *    - 로그 파일 업로드 진행 상황 추적
 *    - 업로드 상태별 적절한 처리 로직 제공
 *    - 업로드 실패 및 재시도 관리
 * 
 * 이 열거형은 DJI 디바이스의 로그 관리 시스템에서
 * 다양한 로그 상태를 표준화된 방식으로
 * 관리하고 추적할 수 있도록 지원합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/9/8
 */
@Getter
public enum DeviceLogsStatusEnum {

    /**
     * 업로드 중
     * 로그 파일이 현재 업로드 진행 중인 상태
     */
    UPLOADING(1, FileUploadStatusEnum.FILE_PULL, FileUploadStatusEnum.FILE_ZIP,
            FileUploadStatusEnum.FILE_UPLOADING, FileUploadStatusEnum.IN_PROGRESS, FileUploadStatusEnum.PAUSED),

    /**
     * 완료
     * 로그 파일 업로드가 성공적으로 완료된 상태
     */
    DONE(2, FileUploadStatusEnum.OK),

    /**
     * 취소됨
     * 로그 파일 업로드가 사용자에 의해 취소된 상태
     */
    CANCELED(3, FileUploadStatusEnum.CANCELED),

    /**
     * 실패
     * 로그 파일 업로드가 실패한 상태
     */
    FAILED(4, FileUploadStatusEnum.FAILED, FileUploadStatusEnum.REJECTED, FileUploadStatusEnum.TIMEOUT),

    /**
     * 알 수 없는 상태
     * 정의되지 않은 로그 상태에 대한 기본값
     */
    UNKNOWN(-1);

    /**
     * 로그 상태 값
     * 각 로그 상태에 대응하는 정수 값
     */
    int val;

    /**
     * 파일 업로드 상태 집합
     * 해당 로그 상태에 포함되는 파일 업로드 상태들의 집합
     */
    HashSet<FileUploadStatusEnum> status;

    /**
     * 생성자
     * @param val 로그 상태 값
     * @param status 파일 업로드 상태들
     */
    DeviceLogsStatusEnum(int val, FileUploadStatusEnum... status) {
        this.status = new HashSet<>();
        Collections.addAll(this.status, status);
        this.val = val;
    }

    /**
     * 파일 업로드 상태로부터 열거형 상수를 찾습니다.
     * 
     * 주어진 파일 업로드 상태에 해당하는 열거형 상수를 반환합니다.
     * 일치하는 값이 없으면 UNKNOWN을 반환합니다.
     * 
     * @param status 찾을 파일 업로드 상태
     * @return 해당하는 열거형 상수 또는 UNKNOWN
     */
    public static DeviceLogsStatusEnum find(FileUploadStatusEnum status) {
        return Arrays.stream(DeviceLogsStatusEnum.values()).filter(element -> element.status.contains(status)).findAny().orElse(UNKNOWN);
    }
}
