package com.waypost.waypost.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "비밀번호 변경 요청 DTO")
@Data
public class ChangePasswordReqDto {
    @Schema(description = "기존의 비밀번호")
    private String password;
    @Schema(description = "새 비밀번호")
    private String newPassword;
}
