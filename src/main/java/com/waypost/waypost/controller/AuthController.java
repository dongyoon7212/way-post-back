package com.waypost.waypost.controller;

import com.waypost.waypost.dto.SignUpReqDto;
import com.waypost.waypost.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpReqDto signUpReqDto) {
        return ResponseEntity.ok(authService.signUp(signUpReqDto));
    }

    @GetMapping("/duplChk/email")
    public ResponseEntity<?> emailDuplChk(@RequestParam String email) {
        return ResponseEntity.ok(authService.emailDuplChk(email));
    }

    @GetMapping("/duplChk/username")
    public ResponseEntity<?> usernameDuplChk(@RequestParam String username) {
        return ResponseEntity.ok(authService.usernameDuplChk(username));
    }
}
