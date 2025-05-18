package com.waypost.waypost.mapper;

import com.waypost.waypost.dto.post.GetPhotoPostListByPositionReqDto;
import com.waypost.waypost.dto.post.GetPhotoPostListReqDto;
import com.waypost.waypost.entity.Comment;
import com.waypost.waypost.entity.PhotoPost;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PhotoPostMapper {
    public int uploadPhotoPost(PhotoPost photoPost);
    public List<PhotoPost> getPhotoPostList(GetPhotoPostListReqDto getPhotoPostListReqDto, Integer currentUserId);
    public List<PhotoPost> getPhotoPostListByUserId(int userId, Integer currentUserId);
    public int addComment(Comment comment);
    public int removePostByPhotoPostId(int photoPostId);
    public List<PhotoPost> getHotPhotoPostList(Integer currentUserId);
    public List<PhotoPost> getPhotoPostListByPosition(GetPhotoPostListByPositionReqDto getPhotoPostListByPositionReqDto, Integer currentUserId);
}
