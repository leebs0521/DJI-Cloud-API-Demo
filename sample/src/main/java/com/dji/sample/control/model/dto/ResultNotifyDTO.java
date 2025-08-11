package com.dji.sample.control.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 결과 알림 DTO 클래스
 * 
 * 제어 작업의 결과를 알림으로 전송하기 위한 DTO 클래스입니다.
 * 작업 결과, 메시지, 디바이스 시리얼 번호를 포함합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/1
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultNotifyDTO {

    /** 작업 결과 코드 */
    private Integer result;

    /** 결과 메시지 */
    private String message;

    /** 디바이스 시리얼 번호 */
    private String sn;
}
