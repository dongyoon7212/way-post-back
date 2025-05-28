package com.waypost.waypost.dto.auth;

import com.waypost.waypost.entity.User;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
public class OAuth2SignupRequestDto {
    private String email;
    private String username;
    private String password;
    private String provider;
    private String providerId;

    public User toEntity(BCryptPasswordEncoder passwordEncoder) {
        return User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .provider(provider)
                .providerId(providerId)
                .build();
    }
}
