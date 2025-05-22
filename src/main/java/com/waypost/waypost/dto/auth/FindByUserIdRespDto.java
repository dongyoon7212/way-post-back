package com.waypost.waypost.dto.auth;

import com.waypost.waypost.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindByUserIdRespDto {
    private User user;
    private int isFollowed;
    private int followerCount;
    private int followingCount;
}
