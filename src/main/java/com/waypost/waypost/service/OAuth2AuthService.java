package com.waypost.waypost.service;

import com.waypost.waypost.dto.auth.OAuth2MergeRequestDto;
import com.waypost.waypost.dto.auth.OAuth2SignupRequestDto;
import com.waypost.waypost.entity.Role;
import com.waypost.waypost.entity.User;
import com.waypost.waypost.entity.UserRole;
import com.waypost.waypost.repository.UserRepository;
import com.waypost.waypost.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private BCryptPasswordEncoder passwordEncoder;

    public Map<String, Object> mergeAccount(OAuth2MergeRequestDto oAuth2MergeRequestDto) {
        Optional<User> user = userRepository.findByEmail(oAuth2MergeRequestDto.getEmail());
        if (user == null) {
            return Map.of(
                    "status", HttpStatus.BAD_REQUEST.value(),
                    "code", 4001,
                    "message", "존재하지 않는 이메일입니다."
            );
        }

        userRepository.updateOAuth2Info(user.get().getUserId(), oAuth2MergeRequestDto.getProvider(), oAuth2MergeRequestDto.getProviderId());
        return Map.of(
                "status", HttpStatus.OK.value(),
                "code", 2000,
                "message", "계정이 성공적으로 통합되었습니다."
        );
    }

    public Map<String, Object> signup(OAuth2SignupRequestDto oAuth2SignupRequestDto) {
        Optional<User> existingUser = userRepository.findByEmail(oAuth2SignupRequestDto.getEmail());
        if (existingUser != null) {
            return Map.of(
                    "status", HttpStatus.CONFLICT.value(),
                    "code", 4002,
                    "message", "이미 존재하는 이메일입니다."
            );
        }

        Optional<User> user = userRepository.save(oAuth2SignupRequestDto.toEntity(passwordEncoder));
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
        return Map.of(
                "status", HttpStatus.OK.value(),
                "code", 2000,
                "message", "회원가입이 완료되었습니다."
        );
    }
}
