package com.waypost.waypost.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddCommentReqDto {
    private int photoPostId;
    private int userId;
    private String content;
}
