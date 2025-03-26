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
public class User {

    private Long userId;
    private String email;
    private String password;
    private String username;
    private String profileImg;
    private LocalDateTime regDt;
    private LocalDateTime updDt;
    private String sttsCd;

}
