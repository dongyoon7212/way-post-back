package com.waypost.waypost.dto.post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "게시물 리스트 조회 요청 DTO")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPhotoPostListReqDto {
    @Schema(description = "최소 위도")
    private double minLat;
    @Schema(description = "최대 위도")
    private double maxLat;
    @Schema(description = "최소 경도")
    private double minLng;
    @Schema(description = "최대 경도")
    private double maxLng;
}
