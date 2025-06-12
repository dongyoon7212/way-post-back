package com.waypost.waypost.dto.emailVerification;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "인증 메일 전송 요청 DTO")
@Data
public class SendCodeReqDto {
    @Schema(description = "이메일")
    private String email;
    @Schema(description = "사용자 고유 ID")
    private int userId;
}
