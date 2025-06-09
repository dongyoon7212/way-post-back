package com.waypost.waypost.service;

import com.waypost.waypost.dto.account.ChangePasswordReqDto;
import com.waypost.waypost.dto.account.EditProfileImgReqDto;
import com.waypost.waypost.dto.account.FindByUserIdRespDto;
import com.waypost.waypost.entity.User;
import com.waypost.waypost.repository.UserRepository;
import com.waypost.waypost.security.principal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Optional<FindByUserIdRespDto> getUserById(int userId, Integer currentUserId) {
        Optional<FindByUserIdRespDto> findByUserIdRespDto = userRepository.findByUserId(userId, currentUserId);
        return findByUserIdRespDto;
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public int editProfileImg(EditProfileImgReqDto editProfileImgReqDto) {
        return userRepository.editProfileImg(editProfileImgReqDto);
    }

    public int editIntroduce(String introduce, Integer currentUserId) {
        return userRepository.editIntroduce(introduce, currentUserId);
    }

    public int follow(Integer currentUserId, int followeeId) {
        return userRepository.follow(currentUserId, followeeId);
    }

    public int unfollow(Integer currentUserId, int followeeId) {
        return userRepository.unfollow(currentUserId, followeeId);
    }

    public List<User> getFollowerList(int userId) {
        return userRepository.getFollowerList(userId);
    }

    public List<User> getFollowingList(int userId) {
        return userRepository.getFollowingList(userId);
    }


    public int changePassword(ChangePasswordReqDto changePasswordReqDto, PrincipalUser principalUser){
        User foundUser = userRepository.findByEmail(principalUser.getUser().getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("사용자 정보를 확인하세요."));
        if (!passwordEncoder.matches(changePasswordReqDto.getPassword(), foundUser.getPassword())) {
            throw new BadCredentialsException("사용자 정보를 확인하세요.");
        }

        return userRepository.newPassword(foundUser.getUserId(), changePasswordReqDto.getNewPassword());
    }


}
