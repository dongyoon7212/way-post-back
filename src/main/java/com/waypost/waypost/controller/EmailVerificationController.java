package com.waypost.waypost.controller;

import com.waypost.waypost.dto.emailVerification.SendCodeReqDto;
import com.waypost.waypost.dto.emailVerification.VerifyCodeReqDto;
import com.waypost.waypost.entity.User;
import com.waypost.waypost.service.AccountService;
import com.waypost.waypost.service.AuthService;
import com.waypost.waypost.service.EmailVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/mail")
public class EmailVerificationController {
    @Autowired
    private EmailVerificationService verificationService;

    @Autowired
    private AccountService accountService;

    @PostMapping("/send-code")
    public ResponseEntity<?> sendCode(@RequestBody SendCodeReqDto sendCodeReqDto) {
        int userId = sendCodeReqDto.getUserId();
        String email = sendCodeReqDto.getEmail();
        verificationService.sendVerificationCode(userId, email);
        return ResponseEntity.ok().body("인증 코드가 발송되었습니다.");
    }

    @PostMapping("/send-reset-code")
    public ResponseEntity<?> sendResetCode(@RequestBody SendCodeReqDto sendCodeReqDto) {
        Optional<User> user = accountService.getUserByEmail(sendCodeReqDto.getEmail());
        user.ifPresent(value -> verificationService.sendVerificationCode(value.getUserId(), value.getEmail()));
        return ResponseEntity.ok().body("인증 코드가 발송되었습니다.");
    }

    @PostMapping("/verify-code")
    public ResponseEntity<?> verifyCode(@RequestBody VerifyCodeReqDto verifyCodeReqDto) {
        int userId = verifyCodeReqDto.getUserId();
        if(userId == 0) {
            Optional<User> user = accountService.getUserByEmail(verifyCodeReqDto.getEmail());
            userId = user.get().getUserId();
        }
        String code = verifyCodeReqDto.getCode();
        return ResponseEntity.ok().body(verificationService.verifyCode(userId, code));
    }
}
