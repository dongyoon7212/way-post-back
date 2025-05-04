package com.waypost.waypost.repository;

import com.waypost.waypost.dto.post.AddCommentReqDto;
import com.waypost.waypost.dto.post.GetPhotoPostListReqDto;
import com.waypost.waypost.entity.Comment;
import com.waypost.waypost.entity.PhotoPost;
import com.waypost.waypost.mapper.PhotoPostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PhotoPostRepository {

    @Autowired
    private PhotoPostMapper photoPostMapper;

    public int uploadPhotoPost(PhotoPost photoPost) {
        return photoPostMapper.uploadPhotoPost(photoPost);
    }

    public List<PhotoPost> getPhotoPostList(GetPhotoPostListReqDto getPhotoPostListReqDto) {
        return photoPostMapper.getPhotoPostList(getPhotoPostListReqDto);
    }

    public List<PhotoPost> getPhotoPostListByUserId(int userId) {
        return photoPostMapper.getPhotoPostListByUserId(userId);
    }

    public int addComment(Comment comment) {
        return photoPostMapper.addComment(comment);
    }

    public int removePostByPhotoPostId(int photoPostId) {
        return photoPostMapper.removePostByPhotoPostId(photoPostId);
    }
}
