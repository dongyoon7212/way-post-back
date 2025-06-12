package com.waypost.waypost.controller;

import com.waypost.waypost.dto.auth.*;
import com.waypost.waypost.entity.User;
import com.waypost.waypost.repository.UserRepository;
import com.waypost.waypost.security.jwt.JwtUtil;
import com.waypost.waypost.security.principal.PrincipalUser;
import com.waypost.waypost.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "계정관리", description = "계정 관련 API")
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

    @Operation(summary = "로그인된 사용자 정보 조회", description = "로그인된 사용자 정보를 조회합니다.")
    @GetMapping("/principal")
    public ResponseEntity<?> getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
        return ResponseEntity.ok(principalUser);
    }

    @Operation(summary = "회원가입", description = "신규 회원가입을 처리합니다.")
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpReqDto signUpReqDto) {
        return ResponseEntity.ok(authService.signUp(signUpReqDto));
    }

    @Operation(summary = "이메일 중복 체크", description = "가입되어있는 이메일인지 확인합니다.")
    @GetMapping("/duplChk/email")
    public ResponseEntity<?> emailDuplChk(@RequestParam String email) {
        return ResponseEntity.ok(authService.emailDuplChk(email));
    }

    @Operation(summary = "사용자 이름 중복 체크", description = "가입되어있는 사용자 이름인지 확인합니다.")
    @GetMapping("/duplChk/username")
    public ResponseEntity<?> usernameDuplChk(@RequestParam String username) {
        return ResponseEntity.ok(authService.usernameDuplChk(username));
    }

    @Operation(summary = "로그인", description = "로그인을 처리합니다.")
    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInReqDto signInReqDto) {
        return ResponseEntity.ok(authService.signIn(signInReqDto));
    }

    @Operation(summary = "계정 비활성화", description = "해당 계정을 비활성화합니다.")
    @PostMapping("/account/deactivate")
    public ResponseEntity<?> deactivateAccount(@RequestBody DeactivateAccountReqDto deactivateAccountReqDto) {
        return ResponseEntity.ok(authService.deactivateAccount(deactivateAccountReqDto));
    }

    @Operation(summary = "계정 활성화", description = "해당 계정을 활성화합니다.")
    @PostMapping("/account/activate")
    public ResponseEntity<?> activateAccount(@RequestBody ActivateAccountReqDto activateAccountReqDto) {
        return ResponseEntity.ok(authService.activateAccount(activateAccountReqDto));
    }

    @Operation(summary = "비밀번호 재설정", description = "비밀번호 찾기를 통해 인증을 하고 새로운 비밀번호를 재설정합니다.")
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
