package com.waypost.waypost.mapper;

import com.waypost.waypost.dto.post.GetPhotoPostListReqDto;
import com.waypost.waypost.entity.Comment;
import com.waypost.waypost.entity.PhotoPost;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PhotoPostMapper {
    public int uploadPhotoPost(PhotoPost photoPost);
    public List<PhotoPost> getPhotoPostList(GetPhotoPostListReqDto getPhotoPostListReqDto);
    public List<PhotoPost> getPhotoPostListByUserId(int userId);
    public int addComment(Comment comment);
    public int removePostByPhotoPostId(int photoPostId);
}
