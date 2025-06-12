package com.waypost.waypost.dto.post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(description = "게시물 글 수정 요청 DTO")
@Data
@AllArgsConstructor
public class editPhotoPostTextReqDto {
    @Schema(description = "게시물 고유 ID")
    private int photoPostId;
    @Schema(description = "게시물 글")
    private String text;
}
