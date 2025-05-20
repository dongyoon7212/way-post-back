package com.waypost.waypost.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserFollowMapper {
    public int follow(Integer currentUserId, int followeeId);
    public int unfollow(Integer currentUserId, int followeeId);
}
