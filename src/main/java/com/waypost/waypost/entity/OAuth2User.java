package com.waypost.waypost.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OAuth2User {
    private int oauth2Id;
    private int userId;
    private String provider;
    private String providerUserId;
    private LocalDateTime regDt;
    private LocalDateTime updDt;
}
