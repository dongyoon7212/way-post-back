package com.waypost.waypost.controller;

import com.waypost.waypost.dto.SignInReqDto;
import com.waypost.waypost.dto.SignUpReqDto;
import com.waypost.waypost.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

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

//    //로그인
//    @PostMapping("/signin")
//    public ResponseEntity<?> signIn(@RequestBody SignInReqDto signInReqDto) {
//        try {
//            String token = authService.signIn(signInReqDto);
//            return ResponseEntity.ok().body(Map.of("token", token));
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
//        }
//    }


}
