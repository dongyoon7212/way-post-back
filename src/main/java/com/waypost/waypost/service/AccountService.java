package com.waypost.waypost.service;

import com.waypost.waypost.dto.account.EditProfileImgReqDto;
import com.waypost.waypost.dto.account.FindByUserIdRespDto;
import com.waypost.waypost.entity.User;
import com.waypost.waypost.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private UserRepository userRepository;

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


}
