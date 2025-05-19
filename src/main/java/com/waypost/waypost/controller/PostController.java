package com.waypost.waypost.controller;

import com.waypost.waypost.dto.post.*;
import com.waypost.waypost.security.principal.PrincipalUser;
import com.waypost.waypost.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/photo/upload")
    public ResponseEntity<?> uploadPhotoPost(@RequestBody UploadPhotoPostReqDto uploadPhotoPostReqDto) {
        return ResponseEntity.ok(postService.uploadPhotoPost(uploadPhotoPostReqDto));
    }

    @GetMapping("/photo/getList")
    public ResponseEntity<?> getPhotoPostList(@RequestParam double minLat, @RequestParam double maxLat, @RequestParam double minLng, @RequestParam double maxLng, @AuthenticationPrincipal PrincipalUser principalUser) {
        if (principalUser != null) {
            return ResponseEntity.ok().body(postService.getPhotoPostList(GetPhotoPostListReqDto.builder()
                    .minLat(minLat)
                    .maxLat(maxLat)
                    .minLng(minLng)
                    .maxLng(maxLng)
                    .build(), principalUser.getUser().getUserId()));
        }
        else {
            return ResponseEntity.ok().body(postService.getPhotoPostList(GetPhotoPostListReqDto.builder()
                    .minLat(minLat)
                    .maxLat(maxLat)
                    .minLng(minLng)
                    .maxLng(maxLng)
                    .build(), null));
        }
    }

    @GetMapping("/photo/getList/{userId}")
    public ResponseEntity<?> getPhotoPostListByUserId(@PathVariable int userId, @AuthenticationPrincipal PrincipalUser principalUser) {
        if (principalUser != null) {
            return ResponseEntity.ok().body(postService.getPhotoPostListByUserId(userId, principalUser.getUser().getUserId()));
        }
        else {
            return ResponseEntity.ok().body(postService.getPhotoPostListByUserId(userId, null));
        }
    }

    @PostMapping("/photo/comment/add")
    public ResponseEntity<?> addComment(@RequestBody AddCommentReqDto addCommentReqDto) {
        return ResponseEntity.ok(postService.addComment(addCommentReqDto));
    }

    @PostMapping("/photo/remove/{photoPostId}")
    public ResponseEntity<?> removePostByPhotoPostId(@PathVariable int photoPostId) {
        return ResponseEntity.ok().body(postService.removePostByPhotoPostId(photoPostId));
    }

    @PostMapping("/photo/like/add")
    public ResponseEntity<?> addLike(@RequestBody AddLikeReqDto addLikeReqDto) {
        return ResponseEntity.ok(postService.addLike(addLikeReqDto));
    }

    @PostMapping("/photo/like/remove")
    public ResponseEntity<?> removeLike(@RequestBody AddLikeReqDto addLikeReqDto) {
        return ResponseEntity.ok(postService.removeLike(addLikeReqDto));
    }

    @GetMapping("/photo/getList/hot")
    public ResponseEntity<?> getHotPhotoPostList(@AuthenticationPrincipal PrincipalUser principalUser) {
        if (principalUser != null) {
            return ResponseEntity.ok().body(postService.getHotPhotoPostList(principalUser.getUser().getUserId()));
        }
        else {
            return ResponseEntity.ok().body(postService.getHotPhotoPostList(null));
        }
    }

    @GetMapping("/photo/getList/recent")
    public ResponseEntity<?> getRecentPhotoPostList(@AuthenticationPrincipal PrincipalUser principalUser) {
        if(principalUser != null) {
            return ResponseEntity.ok().body(postService.getRecentPhotoPostList(principalUser.getUser().getUserId()));
        } else {
            return ResponseEntity.ok().body(postService.getRecentPhotoPostList(null));
        }
    }

    @GetMapping("/photo/getList/position")
    public ResponseEntity<?> getPhotoPostListByPosition(@RequestParam double latitude, @RequestParam double longitude, @AuthenticationPrincipal PrincipalUser principalUser) {
        if (principalUser != null) {
            return ResponseEntity.ok().body(postService.getPhotoPostListByPosition(GetPhotoPostListByPositionReqDto.builder()
                            .latitude(latitude)
                            .longitude(longitude)
                            .build(), principalUser.getUser().getUserId()));
        }
        else {
            return ResponseEntity.ok().body(postService.getPhotoPostListByPosition(GetPhotoPostListByPositionReqDto.builder()
                    .latitude(latitude)
                    .longitude(longitude)
                    .build(), null));
        }
    }

}
