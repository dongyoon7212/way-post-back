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
public class EmailVerification {
    private Long emailVrfctId;
    private Integer userId;
    private String verificationCode;
    private LocalDateTime expiredDt;
    private Boolean isVerified;
    private Integer failCount;
    private LocalDateTime regDt;
    private LocalDateTime updDt;
}
