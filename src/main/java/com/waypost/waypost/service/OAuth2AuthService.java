package com.waypost.waypost.service;

import com.waypost.waypost.dto.auth.OAuth2MergeReqDto;
import com.waypost.waypost.dto.auth.OAuth2SignupReqDto;
import com.waypost.waypost.entity.Role;
import com.waypost.waypost.entity.User;
import com.waypost.waypost.entity.UserRole;
import com.waypost.waypost.repository.OAuth2UserRepository;
import com.waypost.waypost.repository.UserRepository;
import com.waypost.waypost.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class OAuth2AuthService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OAuth2UserRepository oAuth2UserRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Map<String, Object> mergeAccount(OAuth2MergeReqDto oAuth2MergeReqDto) {
        User foundUser = userRepository.findByEmail(oAuth2MergeReqDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("사용자 정보를 확인하세요."));
        if (!passwordEncoder.matches(oAuth2MergeReqDto.getPassword(), foundUser.getPassword())) {
            throw new BadCredentialsException("사용자 정보를 확인하세요.");
        }

//        userRepository.updateOAuth2Info(foundUser.getUserId(), oAuth2MergeRequestDto.getProvider(), oAuth2MergeRequestDto.getProviderUserId());
        oAuth2UserRepository.insertOAuth2User(oAuth2MergeReqDto.toOAuth2User(foundUser.getUserId()));

        return Map.of(
                "status", HttpStatus.OK.value(),
                "code", 2000,
                "message", "계정이 성공적으로 통합되었습니다."
        );
    }

    public Map<String, Object> signup(OAuth2SignupReqDto oAuth2SignupReqDto) {
        Optional<User> existingUser = userRepository.findByEmail(oAuth2SignupReqDto.getEmail());
        if (existingUser.isPresent()) {
            return Map.of(
                    "status", HttpStatus.CONFLICT.value(),
                    "code", 4002,
                    "message", "이미 존재하는 이메일입니다."
            );
        }

        Optional<User> user = userRepository.save(oAuth2SignupReqDto.toEntity(passwordEncoder));
        Role role = Role.builder()
                .roleId(3)
                .roleName("ROLE_TEMPORARY")
                .build();
        UserRole userRole = UserRole.builder()
                .userId(user.get().getUserId())
                .roleId(role.getRoleId())
                .role(role)
                .build();
        userRoleRepository.save(userRole);
        oAuth2UserRepository.insertOAuth2User(oAuth2SignupReqDto.toOAuth2User(user.get().getUserId()));

        return Map.of(
                "status", HttpStatus.OK.value(),
                "code", 2000,
                "message", "회원가입이 완료되었습니다."
        );
    }
}
