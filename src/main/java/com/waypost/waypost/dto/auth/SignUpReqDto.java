package com.waypost.waypost.dto.auth;

import com.waypost.waypost.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpReqDto {

    private String email;         // 이메일 (아이디로 사용)
    private String password;      // 비밀번호
    private String passwordConfirm;  // 비밀번호 확인
    private String username;      // 사용자 이름

    public User toEntity(BCryptPasswordEncoder passwordEncoder) {
        return User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();
    }
}
