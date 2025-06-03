package com.waypost.waypost.dto.auth;

import com.waypost.waypost.entity.OAuth2User;
import lombok.Data;

@Data
public class OAuth2MergeRequestDto {
    private String email;
    private String password;
    private String provider;
    private String providerUserId;

    public OAuth2User toOAuth2User(int userId) {
        return OAuth2User.builder()
                .userId(userId)
                .provider(provider)
                .providerUserId(providerUserId)
                .build();
    }
}
