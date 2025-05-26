package com.waypost.waypost.service;

import com.waypost.waypost.dto.auth.*;
import com.waypost.waypost.entity.Role;
import com.waypost.waypost.entity.User;
import com.waypost.waypost.entity.UserRole;
import com.waypost.waypost.repository.UserRepository;
import com.waypost.waypost.repository.UserRoleRepository;
import com.waypost.waypost.security.jwt.JwtUtil;
import com.waypost.waypost.security.principal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
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
    private JwtUtil jwtUtil;

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

    public SignInRespDto signIn(SignInReqDto signInReqDto) {
        User foundUser = userRepository.findByEmail(signInReqDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("사용자 정보를 확인하세요."));
        if (!passwordEncoder.matches(signInReqDto.getPassword(), foundUser.getPassword())) {
            throw new BadCredentialsException("사용자 정보를 확인하세요.");
        }

        String accessToken = jwtUtil
                .generateToken(Integer.toString(
                                foundUser.getUserId()),
                        foundUser.getEmail(),
                        false);

        return SignInRespDto.builder()
                .accessToken(accessToken)
                .build();
    }

    public int deactivateAccount(DeactivateAccountReqDto deactivateAccountReqDto) {
        User foundUser = userRepository.findByEmail(deactivateAccountReqDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("사용자 정보를 확인하세요."));
        if (!passwordEncoder.matches(deactivateAccountReqDto.getPassword(), foundUser.getPassword())) {
            throw new BadCredentialsException("사용자 정보를 확인하세요.");
        }

        return userRepository.deactivateAccount(foundUser.getUserId());
    }

    public int activateAccount(ActivateAccountReqDto activateAccountReqDto) {
        User foundUser = userRepository.findByEmail(activateAccountReqDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("사용자 정보를 확인하세요."));
        if (!passwordEncoder.matches(activateAccountReqDto.getPassword(), foundUser.getPassword())) {
            throw new BadCredentialsException("사용자 정보를 확인하세요.");
        }

        return userRepository.activateAccount(foundUser.getUserId());
    }

}
