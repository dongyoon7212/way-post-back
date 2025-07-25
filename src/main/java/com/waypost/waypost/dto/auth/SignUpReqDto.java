package com.waypost.waypost.dto.auth;

import com.waypost.waypost.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Schema(description = "회원가입 요청 DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpReqDto {
    @Schema(description = "이메일")
    private String email;
    @Schema(description = "비밀번호")
    private String password;
    @Schema(description = "비밀번호 확인")
    private String passwordConfirm;
    @Schema(description = "사용자 이름")
    private String username;

    public User toEntity(BCryptPasswordEncoder passwordEncoder) {
        return User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();
    }
}
