package com.waypost.waypost.controller;

import com.waypost.waypost.dto.auth.OAuth2MergeRequestDto;
import com.waypost.waypost.dto.auth.OAuth2SignupRequestDto;
import com.waypost.waypost.service.OAuth2AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "OAuth2", description = "OAuth2 관련 API")
@RestController
@RequestMapping("/auth/oauth")
public class OAuth2AuthController {

    @Autowired
    private OAuth2AuthService oAuth2AuthService;

    @Operation(summary = "OAuth2 계정 통합", description = "OAuth2 계정과 일반 계정을 통합합니다.")
    @PostMapping("/merge")
    public ResponseEntity<?> mergeAccount(@RequestBody OAuth2MergeRequestDto dto) {
        return ResponseEntity.ok().body(oAuth2AuthService.mergeAccount(dto));
    }

    @Operation(summary = "OAuth2 신규 계정 가입", description = "OAuth2 계정을 통해 신규 회원가입을 합니다.")
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody OAuth2SignupRequestDto dto) {
        return ResponseEntity.ok().body(oAuth2AuthService.signup(dto));
    }
}
