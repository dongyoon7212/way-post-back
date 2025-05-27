package com.waypost.waypost.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    private int userRoleId;
    private int userId;
    private int roleId;
    private LocalDateTime regDt;
    private LocalDateTime updDt;

    private Role role;
}







