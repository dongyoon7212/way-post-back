package com.waypost.waypost.controller;

import com.waypost.waypost.dto.post.GetPhotoPostListReqDto;
import com.waypost.waypost.dto.post.UploadPhotoPostReqDto;
import com.waypost.waypost.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/photo/getlist")
    public ResponseEntity<?> getPhotoPostList(@RequestParam double minLat, @RequestParam double maxLat, @RequestParam double minLng, @RequestParam double maxLng) {
        return ResponseEntity.ok().body(postService.getPhotoPostList(GetPhotoPostListReqDto.builder()
                        .minLat(minLat)
                        .maxLat(maxLat)
                        .minLng(minLng)
                        .maxLng(maxLng)
                .build()));
    }
}
