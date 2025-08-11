package com.dji.sample.control.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * JWT ACL DTO 클래스
 * 
 * DRC(Direct Remote Control) 모드에서 사용하는 JWT ACL(Access Control List) 정보를 담는 DTO 클래스입니다.
 * 구독, 발행, 전체 토픽 목록을 포함합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2023/1/12
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtAclDTO {

    /** 구독 가능한 토픽 목록 */
    private List<String> sub;

    /** 발행 가능한 토픽 목록 */
    private List<String> pub;

    /** 전체 토픽 목록 */
    private List<String> all;
}
