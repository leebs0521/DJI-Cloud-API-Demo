package com.dji.sample.media.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 미디어 파일 카운트 데이터 전송 객체
 * 미디어 파일의 업로드 진행 상황과 관련된 통계 정보를 담는 DTO입니다.
 * 비행 작업이나 작업 단위별로 미디어 파일의 총 개수와 업로드된 개수를 추적하는데 사용됩니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MediaFileCountDTO {

    /**
     * 작업 ID (Task ID)
     * 특정 작업을 식별하는 고유한 ID입니다.
     */
    private String tid;

    /**
     * 배치 ID (Batch ID)
     * 배치 작업을 식별하는 고유한 ID입니다.
     */
    private String bid;

    /**
     * 이전 작업 ID (Previous Job ID)
     * 현재 작업 이전에 실행된 작업의 ID입니다.
     */
    private String preJobId;

    /**
     * 작업 ID (Job ID)
     * 현재 실행 중인 작업의 고유한 ID입니다.
     */
    private String jobId;

    /**
     * 미디어 파일 총 개수
     * 해당 작업에서 처리해야 할 미디어 파일의 전체 개수입니다.
     */
    private Integer mediaCount;

    /**
     * 업로드된 파일 개수
     * 현재까지 성공적으로 업로드된 미디어 파일의 개수입니다.
     */
    private Integer uploadedCount;

    /**
     * 디바이스 시리얼 번호
     * 미디어 파일을 생성한 드론이나 디바이스의 시리얼 번호입니다.
     */
    private String deviceSn;
}
