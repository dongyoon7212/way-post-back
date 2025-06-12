package com.waypost.waypost.dto.post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "게시물 업로드 요청 DTO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadPhotoPostReqDto {
    @Schema(description = "사용자 고유 ID")
    private int userId;
    @Schema(description = "게시물 글 내용")
    private String postText;
    @Schema(description = "게시물 사진 URL")
    private String imgUrl;
    @Schema(description = "사진 카메라 모델")
    private String cameraModel;
    @Schema(description = "사진 위치 정보")
    private String locationAddress;
    @Schema(description = "위도")
    private double latitude;
    @Schema(description = "경도")
    private double longitude;
}
