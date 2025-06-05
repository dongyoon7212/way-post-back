package com.waypost.waypost.dto.emailVerification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class SendCodeReqDto {
    private String email;
    private int userId;
}
