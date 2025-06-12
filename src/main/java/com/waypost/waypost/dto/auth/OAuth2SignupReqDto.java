package com.waypost.waypost.dto.auth;

import com.waypost.waypost.entity.OAuth2User;
import com.waypost.waypost.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Schema(description = "OAuth2 계정 신규 회원가입 요청 DTO")
@Data
public class OAuth2SignupReqDto {
    @Schema(description = "이메일")
    private String email;
    @Schema(description = "회원이름")
    private String username;
    @Schema(description = "비밀번호")
    private String password;
    @Schema(description = "OAuth2 Provider")
    private String provider;
    @Schema(description = "OAuth2 고유 ID")
    private String providerUserId;

    public User toEntity(BCryptPasswordEncoder passwordEncoder) {
        return User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();
    }

    public OAuth2User toOAuth2User(int userId) {
        return OAuth2User.builder()
                .userId(userId)
                .provider(provider)
                .providerUserId(providerUserId)
                .build();
    }
}
