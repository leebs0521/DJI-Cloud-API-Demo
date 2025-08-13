package com.dji.sample.manage.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Schema(description = "사용자 로그인 정보 전송 객체")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {

    @Schema(description = "로그인할 사용자의 사용자명")
    @NonNull
    private String username;

    @Schema(description = "로그인할 사용자의 비밀번호")
    @NonNull
    private String password;

    @Schema(description = "로그인 타입이나 모드를 나타내는 플래그")
    @NonNull
    private Integer flag;
}
