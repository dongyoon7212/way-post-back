package com.waypost.waypost.dto.post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "댓글 추가 요청 DTO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddCommentReqDto {
    @Schema(description = "게시물 고유 ID")
    private int photoPostId;
    @Schema(description = "사용자 고유 ID")
    private int userId;
    @Schema(description = "댓글 내용")
    private String content;
}
