package com.waypost.waypost.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "회원 소개글 변경 요청 DTO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditIntroduceReqDto {
    @Schema(description = "변경할 소개글")
    private String introduce;
}
