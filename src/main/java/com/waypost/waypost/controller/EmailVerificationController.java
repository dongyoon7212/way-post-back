package com.waypost.waypost.controller;

import com.waypost.waypost.dto.account.FindByUserIdRespDto;
import com.waypost.waypost.dto.emailVerification.SendCodeReqDto;
import com.waypost.waypost.dto.emailVerification.VerifyCodeReqDto;
import com.waypost.waypost.entity.User;
import com.waypost.waypost.service.AccountService;
import com.waypost.waypost.service.EmailVerificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Tag(name = "이메일 인증", description = "이메일 인증 관련 API")
@RestController
@RequestMapping("/mail")
public class  EmailVerificationController {

    @Autowired
    private EmailVerificationService verificationService;

    @Autowired
    private AccountService accountService;

    @Operation(summary = "일반 사용자 전환 인증 메일 전송", description = "일반 사용자 전환을 위한 인증 메일을 전송합니다.")
    @PostMapping("/send-code")
    public ResponseEntity<?> sendCode(@RequestBody SendCodeReqDto sendCodeReqDto) {
        int userId = sendCodeReqDto.getUserId();
        String email = sendCodeReqDto.getEmail();

        // 사용자 존재 확인
        Optional<FindByUserIdRespDto> user = accountService.getUserById(userId, null);
        if (user.isPresent() && user.get().getUser().getEmail().equals(email)) {
            verificationService.sendVerificationCode(userId, email, "signup");
        }

        // 존재하지 않더라도 응답은 동일하게
        return ResponseEntity.ok().body("인증 코드가 발송되었습니다.");
    }

    @Operation(summary = "비밀번호 찾기 인증 메일 전송", description = "비밀번호 찾기를 위한 인증 메일을 전송합니다.")
    @PostMapping("/send-reset-code")
    public ResponseEntity<?> sendResetCode(@RequestBody SendCodeReqDto sendCodeReqDto) {
        String email = sendCodeReqDto.getEmail();

        Optional<User> user = accountService.getUserByEmail(email);
        user.ifPresent(value -> verificationService.sendVerificationCode(value.getUserId(), value.getEmail(), "reset"));

        // 존재 여부 관계 없이 동일한 응답
        return ResponseEntity.ok().body("인증 코드가 발송되었습니다.");
    }

    @Operation(summary = "인증 메일 확인", description = "인증 메일이 유효한지 체크합니다.")
    @PostMapping("/verify-code")
    public ResponseEntity<?> verifyCode(@RequestBody VerifyCodeReqDto verifyCodeReqDto) {
        Optional<User> user = accountService.getUserByEmail(verifyCodeReqDto.getEmail());
        user.ifPresent(value -> verifyCodeReqDto.setUserId(value.getUserId()));
        return ResponseEntity.ok().body(verificationService.verifyCode(verifyCodeReqDto));
    }
}
