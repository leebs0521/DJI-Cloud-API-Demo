package com.dji.sample.manage.controller;

import com.dji.sample.common.error.CommonErrorEnum;
import com.dji.sample.manage.model.dto.UserDTO;
import com.dji.sample.manage.model.dto.UserLoginDTO;
import com.dji.sample.manage.service.IUserService;
import com.dji.sdk.common.HttpResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.dji.sample.component.AuthInterceptor.PARAM_TOKEN;

@Tag(name = "[Manage] 로그인 관리", description = "사용자 인증 및 로그인 관련 API")
@RequiredArgsConstructor
@RequestMapping("${url.manage.prefix}${url.manage.version}")
@RestController
public class LoginController {

    private final IUserService userService;

    @Operation(summary = "사용자 로그인",
            description = "사용자명과 비밀번호를 통한 인증을 수행하고, 로그인 플래그를 통해 다양한 로그인 방식을 지원합니다.")
    @PostMapping("/login")
    public HttpResultResponse login(
            @Parameter(description = "로그인 요청 정보") @RequestBody UserLoginDTO loginDTO
    ) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        return userService.userLogin(username, password, loginDTO.getFlag());
    }

    @Operation(summary = "토큰 갱신",
            description = "기존 토큰을 사용하여 새로운 토큰을 발급받습니다.")
    @PostMapping("/token/refresh")
    public HttpResultResponse refreshToken(
            @Parameter(hidden = true) HttpServletRequest request,
            @Parameter(hidden = true) HttpServletResponse response
    ) {
        String token = request.getHeader(PARAM_TOKEN);
        Optional<UserDTO> user = userService.refreshToken(token);
        if (user.isEmpty()) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return HttpResultResponse.error(CommonErrorEnum.NO_TOKEN.getMessage());
        }
        return HttpResultResponse.success(user.get());
    }
}
