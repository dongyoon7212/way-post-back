package com.waypost.waypost.controller;

import com.waypost.waypost.dto.auth.*;
import com.waypost.waypost.entity.User;
import com.waypost.waypost.repository.UserRepository;
import com.waypost.waypost.security.jwt.JwtUtil;
import com.waypost.waypost.security.principal.PrincipalUser;
import com.waypost.waypost.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private UserRepository userRepository;

    // 🔐 로그인된 사용자 정보 조회
    @GetMapping("/principal")
    public ResponseEntity<?> getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
        return ResponseEntity.ok(principalUser);
    }

    // 👤 회원가입
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpReqDto signUpReqDto) {
        return ResponseEntity.ok(authService.signUp(signUpReqDto));
    }

    // 📧 이메일 중복 확인
    @GetMapping("/duplChk/email")
    public ResponseEntity<?> emailDuplChk(@RequestParam String email) {
        return ResponseEntity.ok(authService.emailDuplChk(email));
    }

    // 👤 사용자 이름 중복 확인
    @GetMapping("/duplChk/username")
    public ResponseEntity<?> usernameDuplChk(@RequestParam String username) {
        return ResponseEntity.ok(authService.usernameDuplChk(username));
    }

    // 🔑 로그인
    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInReqDto signInReqDto) {
        return ResponseEntity.ok(authService.signIn(signInReqDto));
    }

    // 🔒 계정 비활성화
    @PostMapping("/account/deactivate")
    public ResponseEntity<?> deactivateAccount(@RequestBody DeactivateAccountReqDto deactivateAccountReqDto) {
        return ResponseEntity.ok(authService.deactivateAccount(deactivateAccountReqDto));
    }

    // 🔓 계정 활성화
    @PostMapping("/account/activate")
    public ResponseEntity<?> activateAccount(@RequestBody ActivateAccountReqDto activateAccountReqDto) {
        return ResponseEntity.ok(authService.activateAccount(activateAccountReqDto));
    }

    // 🔄 비밀번호 재설정 (Redis 인증 상태 기반)
    @PostMapping("/new-password")
    public ResponseEntity<?> newPassword(@RequestBody NewPasswordReqDto newPasswordReqDto) {
        String email = newPasswordReqDto.getEmail();
        String newPassword = newPasswordReqDto.getNewPassword();

        String redisKey = "reset-allowed:" + email;

        if (!redisTemplate.hasKey(redisKey)) {
            return ResponseEntity.ok()
                    .body(Map.of("status", false, "code", 4001, "message", "잘못된 접근입니다."));
        }

        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            return ResponseEntity.ok()
                    .body(Map.of("status", false, "code", 4001, "message", "잘못된 접근입니다."));
        }

        User user = userOpt.get();
        authService.newPassword(user.getUserId(), newPassword);
        redisTemplate.delete(redisKey); // 인증 완료 후 키 제거

        return ResponseEntity.ok(Map.of("status", true, "code", 2000, "message", "비밀번호가 변경되었습니다."));
    }
}
