package com.waypost.waypost.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "새로운 비밀번호 변경 요청 DTO")
@Data
public class NewPasswordReqDto {
    @Schema(description = "비밀번호를 변경할 이메일")
    private String email;
    @Schema(description = "새로운 비밀번호")
    private String newPassword;
}
