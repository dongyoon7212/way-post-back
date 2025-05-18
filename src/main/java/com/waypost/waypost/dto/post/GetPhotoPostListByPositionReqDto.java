package com.waypost.waypost.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPhotoPostListByPositionReqDto {
    private double latitude;
    private double longitude;
}
