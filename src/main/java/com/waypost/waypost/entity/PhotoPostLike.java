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
public class PhotoPostLike {
    private int userId;
    private int photoPostId;
    private LocalDateTime regDt;
}
