package com.waypost.waypost.dto.auth;

import lombok.Data;

@Data
public class NewPasswordReqDto {
    private String email;
    private String newPassword;
}
