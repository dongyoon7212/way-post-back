package com.waypost.waypost.dto.auth;

import lombok.Data;

@Data
public class OAuth2MergeRequestDto {
    private String email;
    private String provider;
    private String providerId;
}
