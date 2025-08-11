package com.dji.sample.manage.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * 사용자 로그인 정보 전송 객체 (DTO) 클래스
 * 
 * DJI Cloud API의 사용자 로그인 정보를 전송하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 사용자 인증 정보 관리
 *    - 사용자명 및 비밀번호 관리
 *    - 로그인 인증을 위한 필수 정보 제공
 *    - @NonNull 어노테이션을 통한 필수 필드 검증
 * 
 * 2. 로그인 플래그 관리
 *    - 로그인 타입 또는 모드를 나타내는 플래그 관리
 *    - 다양한 로그인 방식을 구분하는 플래그 제공
 *    - 로그인 컨텍스트 정보 관리
 * 
 * 3. 입력 검증 지원
 *    - Lombok @NonNull 어노테이션을 통한 null 검증
 *    - 필수 입력 필드의 유효성 검증
 *    - 안전한 로그인 데이터 전송 보장
 * 
 * 4. API 통신 지원
 *    - Lombok 어노테이션을 통한 자동 getter/setter 생성
 *    - API 요청/응답에서 사용되는 표준화된 형식 제공
 *    - 로그인 요청의 표준화된 데이터 구조 제공
 * 
 * 이 클래스는 사용자 로그인 과정에서 필요한 모든 정보를
 * 포함하며, 안전하고 표준화된 로그인 데이터 전송을
 * 위한 데이터 형식을 제공합니다.
 * 
 * @author sean
 * @version 1.0
 * @date 2022/1/1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {

    /**
     * 사용자명
     * 로그인할 사용자의 사용자명 (필수 필드)
     */
    @NonNull
    private String username;

    /**
     * 비밀번호
     * 로그인할 사용자의 비밀번호 (필수 필드)
     */
    @NonNull
    private String password;

    /**
     * 로그인 플래그
     * 로그인 타입이나 모드를 나타내는 플래그 (필수 필드)
     */
    @NonNull
    private Integer flag;
}
