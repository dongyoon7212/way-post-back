package com.waypost.waypost.controller;

import com.waypost.waypost.dto.auth.ActivateAccountReqDto;
import com.waypost.waypost.dto.auth.DeactivateAccountReqDto;
import com.waypost.waypost.dto.auth.SignInReqDto;
import com.waypost.waypost.dto.auth.SignUpReqDto;
import com.waypost.waypost.security.principal.PrincipalUser;
import com.waypost.waypost.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/principal")
    public ResponseEntity<?> getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
        return ResponseEntity.ok(principalUser);
    }

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpReqDto signUpReqDto) {
        return ResponseEntity.ok(authService.signUp(signUpReqDto));
    }

    //이메일 중복검사
    @GetMapping("/duplChk/email")
    public ResponseEntity<?> emailDuplChk(@RequestParam String email) {
        return ResponseEntity.ok(authService.emailDuplChk(email));
    }

    //이름 중복검사
    @GetMapping("/duplChk/username")
    public ResponseEntity<?> usernameDuplChk(@RequestParam String username) {
        return ResponseEntity.ok(authService.usernameDuplChk(username));
    }

    //로그인
    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInReqDto signInReqDto) {
        return ResponseEntity.ok().body(authService.signIn(signInReqDto));
    }

    //계정 비활성화
    @PostMapping("/account/deactivate")
    public ResponseEntity<?> deactivateAccount(@RequestBody DeactivateAccountReqDto deactivateAccountReqDto) {
        return ResponseEntity.ok().body(authService.deactivateAccount(deactivateAccountReqDto));
    }

    //계정 활성화
    @PostMapping("/account/activate")
    public ResponseEntity<?> activateAccount(@RequestBody ActivateAccountReqDto activateAccountReqDto) {
        return ResponseEntity.ok().body(authService.activateAccount(activateAccountReqDto));
    }


}
