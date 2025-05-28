package com.waypost.waypost.controller;

import com.waypost.waypost.dto.auth.OAuth2MergeRequestDto;
import com.waypost.waypost.dto.auth.OAuth2SignupRequestDto;
import com.waypost.waypost.service.OAuth2AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/oauth")
public class OAuth2AuthController {

    @Autowired
    private OAuth2AuthService oAuth2AuthService;

    @PostMapping("/merge")
    public ResponseEntity<?> mergeAccount(@RequestBody OAuth2MergeRequestDto dto) {
        return ResponseEntity.ok().body(oAuth2AuthService.mergeAccount(dto));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody OAuth2SignupRequestDto dto) {
        return ResponseEntity.ok().body(oAuth2AuthService.signup(dto));
    }
}
