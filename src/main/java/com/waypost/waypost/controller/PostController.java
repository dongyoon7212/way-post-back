package com.waypost.waypost.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController {

    @PostMapping("/photo/upload")
    public ResponseEntity<?> photoPostUpload() {
        return ResponseEntity.ok(null);
    }
}
