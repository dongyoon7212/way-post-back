package com.waypost.waypost.dto.account;

import lombok.Data;

@Data
public class ChangePasswordReqDto {
    private String password;
    private String newPassword;
}
