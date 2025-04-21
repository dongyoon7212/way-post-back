package com.waypost.waypost.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPhotoPostListReqDto {
    private double minLat;
    private double maxLat;
    private double minLng;
    private double maxLng;
}
