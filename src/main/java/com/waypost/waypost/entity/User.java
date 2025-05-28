package com.waypost.waypost.entity;

import com.waypost.waypost.security.principal.PrincipalUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int userId;
    private String email;
    private String password;
    private String username;
    private String profileImg;
    private String introduce;
    private LocalDateTime regDt;
    private LocalDateTime updDt;
    private LocalDateTime dctvdDt; // 탈퇴 시점
    private int isAccountNonExpired;
    private int isAccountNonLocked;
    private int isCredentialsNonExpired;
    private int isEnabled;
    private String provider;
    private String providerId;

    List<UserRole> userRoles;

}
