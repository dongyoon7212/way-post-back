package com.waypost.waypost.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "사용자 팔로우 요청 DTO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FollowAddReqDto {
    @Schema(description = "팔로우하는 사용자 고유 ID")
    private int followeeId;
}
