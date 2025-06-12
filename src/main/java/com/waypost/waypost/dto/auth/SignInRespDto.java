package com.waypost.waypost.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "로그인 응답 DTO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInRespDto {
    @Schema(description = "JWT 토큰")
    private String accessToken;

}
