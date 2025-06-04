package com.waypost.waypost.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class editPhotoPostTextReqDto {
    private int photoPostId;
    private String text;
}
