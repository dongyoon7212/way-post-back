package com.waypost.waypost.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PhotoPostLikeMapper {
    public int addLike(int userId, int photoPostId);
    public int removeLike(int userId, int photoPostId);
}
