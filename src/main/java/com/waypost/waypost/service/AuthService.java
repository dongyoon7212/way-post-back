package com.waypost.waypost.service;

import com.waypost.waypost.dto.SignUpReqDto;
import com.waypost.waypost.entity.User;
import com.waypost.waypost.repository.AuthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthMapper authMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public int signUp(SignUpReqDto signUpReqDto) {
        // 비밀번호 확인 => 웹에서 처리
//        if (!signUpReqDto.getPassword().equals(signUpReqDto.getPasswordConfirm())) {
//            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
//        }

//         이메일 중복 검사 => 웹에서 중복확인 처리
//        if (authMapper.findByEmail(signUpReqDto.getEmail()) != null) {
//            throw new IllegalArgumentException("이미 등록된 이메일입니다.");
//        }

        return authMapper.signUp(signUpReqDto.toEntity(passwordEncoder));
    }

    public int emailDuplChk(String email) {
        User user = authMapper.findByEmail(email);
        if (user == null) {
            return 0;
        } else {
            return 1;
        }
    }

    public int usernameDuplChk(String username) {
        User user = authMapper.findByUsername(username);
        if (user == null) {
            return 0;
        } else {
            return 1;
        }
    }
}
