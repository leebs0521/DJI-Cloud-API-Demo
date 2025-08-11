package com.dji.sample.manage.controller;

import com.dji.sample.common.error.CommonErrorEnum;
import com.dji.sample.manage.model.dto.UserDTO;
import com.dji.sample.manage.model.dto.UserLoginDTO;
import com.dji.sample.manage.service.IUserService;
import com.dji.sdk.common.HttpResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.dji.sample.component.AuthInterceptor.PARAM_TOKEN;

/**
 * 로그인 관리 컨트롤러
 * 
 * DJI Cloud API의 사용자 인증 및 로그인 기능을 제공하는 REST API 컨트롤러입니다.
 * 이 컨트롤러는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 사용자 로그인
 *    - 사용자명과 비밀번호를 통한 인증
 *    - 로그인 플래그를 통한 다양한 로그인 방식 지원
 *    - 로그인 성공 시 토큰 발급
 * 
 * 2. 토큰 갱신
 *    - 기존 토큰을 사용한 새로운 토큰 발급
 *    - 토큰 만료 시 자동 갱신
 *    - 토큰 유효성 검증
 * 
 * 3. 인증 관리
 *    - 사용자 인증 상태 확인
 *    - 토큰 기반 세션 관리
 *    - 보안 인증 처리
 * 
 * 4. 오류 처리
 *    - 인증 실패 시 적절한 오류 응답
 *    - 토큰 만료 시 HTTP 상태 코드 설정
 *    - 보안 관련 오류 메시지 제공
 * 
 * 모든 API는 보안을 최우선으로 하며, 적절한 HTTP 상태 코드와
 * 표준화된 응답 형식을 사용합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/15
 */
@RestController
@RequestMapping("${url.manage.prefix}${url.manage.version}")
public class LoginController {

    /** 사용자 서비스 - 사용자 인증 및 관리 비즈니스 로직 */
    @Autowired
    private IUserService userService;

    /**
     * 사용자 로그인을 처리합니다.
     * 
     * 이 API는 사용자명과 비밀번호를 받아 사용자 인증을 수행합니다.
     * 로그인 플래그를 통해 다양한 로그인 방식을 지원하며,
     * 인증 성공 시 사용자 정보와 토큰을 반환합니다.
     * 
     * @param loginDTO 로그인 정보 (사용자명, 비밀번호, 로그인 플래그)
     * @return 로그인 결과 (성공 시 사용자 정보 및 토큰, 실패 시 오류 메시지)
     */
    @PostMapping("/login")
    public HttpResultResponse login(@RequestBody UserLoginDTO loginDTO) {

        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        return userService.userLogin(username, password, loginDTO.getFlag());
    }

    /**
     * 토큰을 갱신합니다.
     * 
     * 이 API는 기존 토큰을 사용하여 새로운 토큰을 발급받습니다.
     * 토큰이 유효하지 않거나 만료된 경우 401 Unauthorized 상태 코드를 반환합니다.
     * 
     * @param request HTTP 요청 객체 (토큰 추출용)
     * @param response HTTP 응답 객체 (상태 코드 설정용)
     * @return 토큰 갱신 결과 (성공 시 새로운 사용자 정보, 실패 시 오류 메시지)
     */
    @PostMapping("/token/refresh")
    public HttpResultResponse refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader(PARAM_TOKEN);
        Optional<UserDTO> user = userService.refreshToken(token);

        if (user.isEmpty()) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return HttpResultResponse.error(CommonErrorEnum.NO_TOKEN.getMessage());
        }

        return HttpResultResponse.success(user.get());
    }
}
