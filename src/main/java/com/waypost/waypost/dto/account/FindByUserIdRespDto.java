package com.waypost.waypost.dto.account;

import com.waypost.waypost.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "사용자 고유 ID를 통한 회원정보 조회 응답 DTO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindByUserIdRespDto {
    @Schema(description = "사용자 정보 객체")
    private User user;
    @Schema(description = "사용자 팔로우 상태")
    private int isFollowed;
    @Schema(description = "사용자 팔로워 수")
    private int followerCount;
    @Schema(description = "사용자 팔로잉 수")
    private int followingCount;
}
