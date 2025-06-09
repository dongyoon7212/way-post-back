package com.waypost.waypost.controller;

import com.waypost.waypost.dto.account.EditIntroduceReqDto;
import com.waypost.waypost.dto.account.EditProfileImgReqDto;
import com.waypost.waypost.dto.account.FollowAddReqDto;
import com.waypost.waypost.dto.account.ChangePasswordReqDto;
import com.waypost.waypost.security.principal.PrincipalUser;
import com.waypost.waypost.service.AccountService;
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
    public ResponseEntity<?> getUserById(@RequestParam int userId, @AuthenticationPrincipal PrincipalUser principalUser) {
        if (principalUser != null) {
            return ResponseEntity.ok().body(accountService.getUserById(userId, principalUser.getUser().getUserId()));
        } else {
            return ResponseEntity.ok().body(accountService.getUserById(userId, null));
        }
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

    @PostMapping("/follow/add")
    public ResponseEntity<?> follow(@AuthenticationPrincipal PrincipalUser principalUser, @RequestBody FollowAddReqDto followAddReqDto) {
        return ResponseEntity.ok().body(accountService.follow(principalUser.getUser().getUserId(), followAddReqDto.getFolloweeId()));
    }

    @PostMapping("/follow/remove")
    public ResponseEntity<?> unfollow(@AuthenticationPrincipal PrincipalUser principalUser, @RequestBody FollowAddReqDto followAddReqDto) {
        return ResponseEntity.ok().body(accountService.unfollow(principalUser.getUser().getUserId(), followAddReqDto.getFolloweeId()));
    }

    @GetMapping("/get/followerList")
    public ResponseEntity<?> getFollowerList(@RequestParam int userId) {
        return ResponseEntity.ok().body(accountService.getFollowerList(userId));
    }

    @GetMapping("/get/followingList")
    public ResponseEntity<?> getFollowingList(@RequestParam int userId) {
        return ResponseEntity.ok().body(accountService.getFollowingList(userId));
    }

    //일반 비밀번호 변경
    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordReqDto changePasswordReqDto, @AuthenticationPrincipal PrincipalUser principalUser) {
        return ResponseEntity.ok().body(accountService.changePassword(changePasswordReqDto, principalUser));
    }


}
