package com.waypost.waypost.dto.post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "위치기반 게시물 리스트 조회 요청 DTO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPhotoPostListByPositionReqDto {
    @Schema(description = "위도")
    private double latitude;
    @Schema(description = "경도")
    private double longitude;
}
