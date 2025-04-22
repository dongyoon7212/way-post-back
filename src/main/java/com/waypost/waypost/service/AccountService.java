package com.waypost.waypost.service;

import com.waypost.waypost.dto.account.EditProfileImgReqDto;
import com.waypost.waypost.entity.User;
import com.waypost.waypost.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserById(int userId) {
        Optional<User> user = userRepository.findByUserId(userId);
        if (user.isEmpty()) {
            return user;
        } else {
            return user;
        }
    }

    public int editProfileImg(EditProfileImgReqDto editProfileImgReqDto) {
        return userRepository.editProfileImg(editProfileImgReqDto);
    }

}
