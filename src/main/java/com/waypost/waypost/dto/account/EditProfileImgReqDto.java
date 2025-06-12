package com.waypost.waypost.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "회원 프로필 이미지 변경 요청 DTO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditProfileImgReqDto {
    @Schema(description = "사용자 고유 ID")
    private int userId;
    @Schema(description = "변경할 프로필 이미지 URL")
    private String profileImg;
}
