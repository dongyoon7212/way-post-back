package com.waypost.waypost.controller;

import com.waypost.waypost.dto.account.EditProfileImgReqDto;
import com.waypost.waypost.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/edit/profileimg")
    public ResponseEntity<?> editProfileImg(@RequestBody EditProfileImgReqDto editProfileImgReqDto) {
        return ResponseEntity.ok().body(accountService.editProfileImg(editProfileImgReqDto));
    }
}
