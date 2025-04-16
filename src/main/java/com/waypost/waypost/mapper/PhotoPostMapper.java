package com.waypost.waypost.mapper;

import com.waypost.waypost.entity.PhotoPost;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PhotoPostMapper {
    public int photoPostUpload(PhotoPost photoPost);
}
