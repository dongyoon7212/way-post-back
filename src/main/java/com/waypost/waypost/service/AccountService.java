package com.waypost.waypost.service;

import com.waypost.waypost.dto.account.EditProfileImgReqDto;
import com.waypost.waypost.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private UserRepository userRepository;
    public int editProfileImg(EditProfileImgReqDto editProfileImgReqDto) {
        return userRepository.editProfileImg(editProfileImgReqDto);
    }
}
