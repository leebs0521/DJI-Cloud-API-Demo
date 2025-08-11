package com.dji.sample.control.model.param;

import com.dji.sample.control.model.enums.PayloadCommandsEnum;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 페이로드 명령 파라미터 클래스
 * 
 * 드론의 페이로드에 명령을 전송할 때 사용하는 파라미터를 정의합니다.
 * 명령 타입과 페이로드 데이터를 포함합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/2
 */
@Data
public class PayloadCommandsParam {

    /** 디바이스 시리얼 번호 */
    private String sn;

    /** 페이로드 명령 */
    @NotNull
    @Valid
    private PayloadCommandsEnum cmd;

    /** 페이로드 데이터 */
    @Valid
    @NotNull
    private DronePayloadParam data;

}
