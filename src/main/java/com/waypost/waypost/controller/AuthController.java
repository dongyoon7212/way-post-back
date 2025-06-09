package com.waypost.waypost.controller;

import com.waypost.waypost.dto.auth.*;
import com.waypost.waypost.security.jwt.JwtUtil;
import com.waypost.waypost.security.principal.PrincipalUser;
import com.waypost.waypost.service.AuthService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

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

    //비밀번호 찾기, 변경 - 토큰
    @PostMapping("/new-password")
    public ResponseEntity<?> newPassword(@RequestBody NewPasswordReqDto newPasswordReqDto, HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰이 없습니다.");
        }

        String token = bearerToken.substring(7);
        Claims claims = jwtUtil.parseToken(token);

        if (claims == null || !Boolean.TRUE.equals(claims.get("isTemp"))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 토큰입니다.");
        }

        int userId = Integer.parseInt(claims.getId());
        authService.newPassword(userId, newPasswordReqDto.getNewPassword());
        return ResponseEntity.ok().body(Map.of("status", true, "code", 2000, "message", "비밀번호가 변경되었습니다."));
    }


}
