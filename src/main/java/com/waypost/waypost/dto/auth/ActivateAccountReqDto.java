package com.waypost.waypost.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "계정 활성화 요청 DTO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivateAccountReqDto {
    @Schema(description = "계정 활성화 이메일")
    private String email;
    @Schema(description = "계정 활성화 비밀번호")
    private String password;
}
