package com.waypost.waypost.service;

import com.waypost.waypost.dto.SignInReqDto;
import com.waypost.waypost.dto.SignUpReqDto;
import com.waypost.waypost.entity.Role;
import com.waypost.waypost.entity.User;
import com.waypost.waypost.entity.UserRole;
import com.waypost.waypost.mapper.UserMapper;
import com.waypost.waypost.repository.UserRepository;
import com.waypost.waypost.repository.UserRoleRepository;
import com.waypost.waypost.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtProvider;

    public Optional<User> signUp(SignUpReqDto signUpReqDto) {
        Optional<User> user = userRepository.save(signUpReqDto.toEntity(passwordEncoder));
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

        return user;
    }

    public int emailDuplChk(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            return 0;
        } else {
            return 1;
        }
    }
//
    public int usernameDuplChk(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            return 0;
        } else {
            return 1;
        }
    }

//    public String signIn(SignInReqDto signInReqDto) {
//        // 1. 이메일로 유저 찾기
//        User user = userMapper.findByEmail(signInReqDto.getEmail());
//        if (user == null) {
//            throw new IllegalArgumentException("존재하지 않는 이메일입니다.");
//        }
//
//        // 2. 비밀번호 검증
//        if (!passwordEncoder.matches(signInReqDto.getPassword(), user.getPassword())) {
//            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
//        }
//
//        // 3. JWT 토큰 생성
//        return jwtProvider.generateToken(user);
//    }
}
