package com.waypost.waypost.dto.auth;

import com.waypost.waypost.entity.OAuth2User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "OAuth2 계정 통합 요청 DTO")
@Data
public class OAuth2MergeReqDto {
    @Schema(description = "통합할 일반 계정 이메일")
    private String email;
    @Schema(description = "통합할 일반 계정 비밀번호")
    private String password;
    @Schema(description = "OAuth2 Provider")
    private String provider;
    @Schema(description = "OAuth2 고유 ID")
    private String providerUserId;

    public OAuth2User toOAuth2User(int userId) {
        return OAuth2User.builder()
                .userId(userId)
                .provider(provider)
                .providerUserId(providerUserId)
                .build();
    }
}
