package com.waypost.waypost.repository;

import com.waypost.waypost.dto.post.AddCommentReqDto;
import com.waypost.waypost.dto.post.AddLikeReqDto;
import com.waypost.waypost.dto.post.GetPhotoPostListByPositionReqDto;
import com.waypost.waypost.dto.post.GetPhotoPostListReqDto;
import com.waypost.waypost.entity.Comment;
import com.waypost.waypost.entity.PhotoPost;
import com.waypost.waypost.mapper.PhotoPostLikeMapper;
import com.waypost.waypost.mapper.PhotoPostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PhotoPostRepository {

    @Autowired
    private PhotoPostMapper photoPostMapper;

    @Autowired
    private PhotoPostLikeMapper photoPostLikeMapper;

    public int uploadPhotoPost(PhotoPost photoPost) {
        return photoPostMapper.uploadPhotoPost(photoPost);
    }

    public List<PhotoPost> getPhotoPostList(GetPhotoPostListReqDto getPhotoPostListReqDto, Integer currentUserId) {
        return photoPostMapper.getPhotoPostList(getPhotoPostListReqDto, currentUserId);
    }

    public List<PhotoPost> getPhotoPostListByUserId(int userId, Integer currentUserId) {
        return photoPostMapper.getPhotoPostListByUserId(userId, currentUserId);
    }

    public int addComment(Comment comment) {
        return photoPostMapper.addComment(comment);
    }

    public int removePostByPhotoPostId(int photoPostId) {
        return photoPostMapper.removePostByPhotoPostId(photoPostId);
    }

    public int addLike(AddLikeReqDto addLikeReqDto) {
        return photoPostLikeMapper.addLike(addLikeReqDto.getUserId(), addLikeReqDto.getPhotoPostId());
    }

    public int removeLike(AddLikeReqDto addLikeReqDto) {
        return photoPostLikeMapper.removeLike(addLikeReqDto.getUserId(), addLikeReqDto.getPhotoPostId());
    }

    public List<PhotoPost> getHotPhotoPostList(Integer currentUserId) {
        return photoPostMapper.getHotPhotoPostList(currentUserId);
    }

    public List<PhotoPost> getPhotoPostListByPosition(GetPhotoPostListByPositionReqDto getPhotoPostListByPositionReqDto, Integer currentUserId) {
        return photoPostMapper.getPhotoPostListByPosition(getPhotoPostListByPositionReqDto, currentUserId);
    }
}
