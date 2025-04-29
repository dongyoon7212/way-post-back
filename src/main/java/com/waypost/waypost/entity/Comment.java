package com.waypost.waypost.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private int commentId;
    private int photoPostId;
    private int userId;
    private String content;
    private LocalDateTime regDt;
    private LocalDateTime updDt;
    private User user;
}
