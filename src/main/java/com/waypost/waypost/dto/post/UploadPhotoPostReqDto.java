package com.waypost.waypost.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadPhotoPostReqDto {
    private int userId;
    private String postText;
    private String imgUrl;
    private String cameraModel;
    private String locationAddress;
    private double latitude;
    private double longitude;
}
