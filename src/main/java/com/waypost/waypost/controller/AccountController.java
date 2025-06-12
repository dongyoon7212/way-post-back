package com.waypost.waypost.controller;

import com.waypost.waypost.dto.account.EditIntroduceReqDto;
import com.waypost.waypost.dto.account.EditProfileImgReqDto;
import com.waypost.waypost.dto.account.FollowAddReqDto;
import com.waypost.waypost.dto.account.ChangePasswordReqDto;
import com.waypost.waypost.security.principal.PrincipalUser;
import com.waypost.waypost.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원관리", description = "회원 관련 API")
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Operation(summary = "회원 프로필 이미지 수정", description = "회원 프로필 이미지를 수정합니다.")
    @PostMapping("/edit/profileimg")
    public ResponseEntity<?> editProfileImg(@RequestBody EditProfileImgReqDto editProfileImgReqDto) {
        return ResponseEntity.ok().body(accountService.editProfileImg(editProfileImgReqDto));
    }

    @Operation(summary = "userId로 회원정보 조회", description = "userId로 회원정보를 조회합니다.")
    @GetMapping("/get/user")
    public ResponseEntity<?> getUserById(@RequestParam int userId, @AuthenticationPrincipal PrincipalUser principalUser) {
        if (principalUser != null) {
            return ResponseEntity.ok().body(accountService.getUserById(userId, principalUser.getUser().getUserId()));
        } else {
            return ResponseEntity.ok().body(accountService.getUserById(userId, null));
        }
    }

    @Operation(summary = "회원 소개글 수정", description = "회원 소개글을 수정합니다.")
    @PostMapping("/edit/introduce")
    public ResponseEntity<?> editIntroduce(@RequestBody EditIntroduceReqDto editIntroduceReqDto, @AuthenticationPrincipal PrincipalUser principalUser) {
        if (principalUser != null) {
            return ResponseEntity.ok().body(accountService.editIntroduce(editIntroduceReqDto.getIntroduce(), principalUser.getUser().getUserId()));
        }
        else {
            return ResponseEntity.ok().body(accountService.editIntroduce(editIntroduceReqDto.getIntroduce(), null));
        }
    }

    @Operation(summary = "회원 팔로우 추가", description = "회원 팔로우를 추가합니다.")
    @PostMapping("/follow/add")
    public ResponseEntity<?> follow(@AuthenticationPrincipal PrincipalUser principalUser, @RequestBody FollowAddReqDto followAddReqDto) {
        return ResponseEntity.ok().body(accountService.follow(principalUser.getUser().getUserId(), followAddReqDto.getFolloweeId()));
    }

    @Operation(summary = "회원 팔로우 제거", description = "회원 팔로우를 제거합니다.")
    @PostMapping("/follow/remove")
    public ResponseEntity<?> unfollow(@AuthenticationPrincipal PrincipalUser principalUser, @RequestBody FollowAddReqDto followAddReqDto) {
        return ResponseEntity.ok().body(accountService.unfollow(principalUser.getUser().getUserId(), followAddReqDto.getFolloweeId()));
    }

    @Operation(summary = "회원 팔로우 리스트 조회", description = "회원 팔로우 리스트를 조회합니다.")
    @GetMapping("/get/followerList")
    public ResponseEntity<?> getFollowerList(@RequestParam int userId) {
        return ResponseEntity.ok().body(accountService.getFollowerList(userId));
    }

    @Operation(summary = "회원 팔로잉 리스트 조회", description = "회원 팔로잉 리스트를 조회합니다.")
    @GetMapping("/get/followingList")
    public ResponseEntity<?> getFollowingList(@RequestParam int userId) {
        return ResponseEntity.ok().body(accountService.getFollowingList(userId));
    }

    @Operation(summary = "회원 비밀번호 변경", description = "비밀번호를 변경합니다.")
    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordReqDto changePasswordReqDto, @AuthenticationPrincipal PrincipalUser principalUser) {
        return ResponseEntity.ok().body(accountService.changePassword(changePasswordReqDto, principalUser));
    }

}
