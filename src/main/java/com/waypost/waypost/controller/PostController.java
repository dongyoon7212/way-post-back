package com.waypost.waypost.controller;

import com.waypost.waypost.dto.post.*;
import com.waypost.waypost.security.principal.PrincipalUser;
import com.waypost.waypost.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "게시물", description = "게시물 관련 API")
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Operation(summary = "사진 게시물 업로드", description = "사진 게시물을 업로드 합니다.")
    @PostMapping("/photo/upload")
    public ResponseEntity<?> uploadPhotoPost(@RequestBody UploadPhotoPostReqDto uploadPhotoPostReqDto) {
        return ResponseEntity.ok(postService.uploadPhotoPost(uploadPhotoPostReqDto));
    }

    @Operation(summary = "사진 게시물 전체조회", description = "사진 게시물을 전체조회 합니다.")
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

    @Operation(summary = "userId로 게시물 조회", description = "userId로 게시물을 조회합니다.")
    @GetMapping("/photo/getList/{userId}")
    public ResponseEntity<?> getPhotoPostListByUserId(@PathVariable int userId, @AuthenticationPrincipal PrincipalUser principalUser) {
        if (principalUser != null) {
            return ResponseEntity.ok().body(postService.getPhotoPostListByUserId(userId, principalUser.getUser().getUserId()));
        }
        else {
            return ResponseEntity.ok().body(postService.getPhotoPostListByUserId(userId, null));
        }
    }

    @Operation(summary = "사진 게시물 댓글 추가", description = "사진 게시물의 댓글을 추가 합니다.")
    @PostMapping("/photo/comment/add")
    public ResponseEntity<?> addComment(@RequestBody AddCommentReqDto addCommentReqDto) {
        return ResponseEntity.ok(postService.addComment(addCommentReqDto));
    }

    @Operation(summary = "사진 게시물 삭제", description = "사진 게시물을 삭제 합니다.")
    @PostMapping("/photo/remove/{photoPostId}")
    public ResponseEntity<?> removePostByPhotoPostId(@PathVariable int photoPostId) {
        return ResponseEntity.ok().body(postService.removePostByPhotoPostId(photoPostId));
    }

    @Operation(summary = "사진 게시물 좋아요 추가", description = "사진 게시물의 좋아요를 추가 합니다.")
    @PostMapping("/photo/like/add")
    public ResponseEntity<?> addLike(@RequestBody AddLikeReqDto addLikeReqDto) {
        return ResponseEntity.ok(postService.addLike(addLikeReqDto));
    }

    @Operation(summary = "사진 게시물 좋아요 취소", description = "사진 게시물을 좋아요를 취소 합니다.")
    @PostMapping("/photo/like/remove")
    public ResponseEntity<?> removeLike(@RequestBody AddLikeReqDto addLikeReqDto) {
        return ResponseEntity.ok(postService.removeLike(addLikeReqDto));
    }

    @Operation(summary = "인기 사진 게시물 조회", description = "좋아요 갯수순으로 인기 사진 게시물을 조회 합니다..")
    @GetMapping("/photo/getList/hot")
    public ResponseEntity<?> getHotPhotoPostList(@AuthenticationPrincipal PrincipalUser principalUser) {
        if (principalUser != null) {
            return ResponseEntity.ok().body(postService.getHotPhotoPostList(principalUser.getUser().getUserId()));
        }
        else {
            return ResponseEntity.ok().body(postService.getHotPhotoPostList(null));
        }
    }

    @Operation(summary = "최근 사진 게시물 조회", description = "업로드 일자로 최근 사진 게시물을 조회 합니다.")
    @GetMapping("/photo/getList/recent")
    public ResponseEntity<?> getRecentPhotoPostList(@AuthenticationPrincipal PrincipalUser principalUser) {
        if(principalUser != null) {
            return ResponseEntity.ok().body(postService.getRecentPhotoPostList(principalUser.getUser().getUserId()));
        } else {
            return ResponseEntity.ok().body(postService.getRecentPhotoPostList(null));
        }
    }

    @Operation(summary = "위치 기반 사진 게시물 조회", description = "좌표값으로 해당 위치의 게시물을 조회 합니다.")
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

    @Operation(summary = "사진 게시물 글 수정", description = "사진 게시물의 내용을 수정 합니다.")
    @PostMapping("/photo/edit/text")
    public ResponseEntity<?> editPhotoPostText(@RequestBody editPhotoPostTextReqDto editPhotoPostTextReqDto) {
        return ResponseEntity.ok().body(postService.editPhotoPostText(editPhotoPostTextReqDto.getPhotoPostId(), editPhotoPostTextReqDto.getText()));
    }

}
