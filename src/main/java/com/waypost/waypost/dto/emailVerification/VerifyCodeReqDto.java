package com.waypost.waypost.dto.emailVerification;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "인증 코드 확인 요청 DTO")
@Data
public class VerifyCodeReqDto {
    @Schema(description = "이메일")
    private String email;
    @Schema(description = "사용자 고유 ID")
    private int userId;
    @Schema(description = "인증 코드")
    private String code;
    @Schema(description = "인증 구분")
    private String purpose;
}
