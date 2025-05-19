package com.waypost.waypost.controller;

import com.waypost.waypost.dto.account.EditIntroduceReqDto;
import com.waypost.waypost.dto.account.EditProfileImgReqDto;
import com.waypost.waypost.dto.post.GetPhotoPostListByPositionReqDto;
import com.waypost.waypost.security.principal.PrincipalUser;
import com.waypost.waypost.service.AccountService;
import com.waypost.waypost.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/edit/profileimg")
    public ResponseEntity<?> editProfileImg(@RequestBody EditProfileImgReqDto editProfileImgReqDto) {
        return ResponseEntity.ok().body(accountService.editProfileImg(editProfileImgReqDto));
    }

    @GetMapping("/get/user")
    public ResponseEntity<?> getUserById(@RequestParam int userId) {
        return ResponseEntity.ok().body(accountService.getUserById(userId));
    }

    @PostMapping("/edit/introduce")
    public ResponseEntity<?> editIntroduce(@RequestBody EditIntroduceReqDto editIntroduceReqDto, @AuthenticationPrincipal PrincipalUser principalUser) {
        if (principalUser != null) {
            return ResponseEntity.ok().body(accountService.editIntroduce(editIntroduceReqDto.getIntroduce(), principalUser.getUser().getUserId()));
        }
        else {
            return ResponseEntity.ok().body(accountService.editIntroduce(editIntroduceReqDto.getIntroduce(), null));
        }
    }
}
