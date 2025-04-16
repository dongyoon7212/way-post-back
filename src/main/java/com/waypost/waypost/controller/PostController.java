package com.waypost.waypost.controller;

import com.waypost.waypost.dto.post.PhotoPostUploadReqDto;
import com.waypost.waypost.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/photo/upload")
    public ResponseEntity<?> photoPostUpload(@RequestBody PhotoPostUploadReqDto photoPostUploadReqDto) {
        return ResponseEntity.ok(postService.photoPostUpload(photoPostUploadReqDto));
    }
}
