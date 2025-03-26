package com.waypost.waypost.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResDto {

    private Long userId;         // 유저 ID (회원가입 성공 후 DB에 저장된 유저 ID)
    private String email;        // 유저 이메일
    private String username;     // 유저 이름
    private String message;      // 회원가입 성공 메시지 (예: "회원가입이 완료되었습니다.")
}
