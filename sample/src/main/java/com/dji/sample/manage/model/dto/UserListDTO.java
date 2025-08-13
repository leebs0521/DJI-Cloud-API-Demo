package com.dji.sample.manage.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Schema(description = "사용자 목록 정보 전송 객체")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserListDTO {

    @Schema(description = "사용자를 고유하게 식별하는 ID")
    private String userId;

    @Schema(description = "사용자의 로그인명 또는 표시명")
    private String username;

    @Schema(description = "사용자가 속한 워크스페이스의 이름")
    private String workspaceName;

    @Schema(description = "사용자의 권한 레벨이나 역할을 나타내는 타입")
    private String userType;

    @Schema(description = "MQTT 브로커에 연결하기 위한 사용자명")
    private String mqttUsername;

    @Schema(description = "MQTT 브로커에 연결하기 위한 비밀번호")
    private String mqttPassword;

    @Schema(description = "사용자 계정이 생성된 시간")
    private LocalDateTime createTime;
}
