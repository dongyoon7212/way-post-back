package com.waypost.waypost.service;

import com.waypost.waypost.dto.post.*;
import com.waypost.waypost.entity.Comment;
import com.waypost.waypost.entity.PhotoPost;
import com.waypost.waypost.repository.PhotoPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PhotoPostRepository photoPostRepository;

    public int uploadPhotoPost(UploadPhotoPostReqDto uploadPhotoPostReqDto) {
        PhotoPost photoPost = PhotoPost.builder()
                .userId(uploadPhotoPostReqDto.getUserId())
                .postText(uploadPhotoPostReqDto.getPostText())
                .imgUrl(uploadPhotoPostReqDto.getImgUrl())
                .cameraModel(uploadPhotoPostReqDto.getCameraModel())
                .locationAddress(uploadPhotoPostReqDto.getLocationAddress())
                .latitude(uploadPhotoPostReqDto.getLatitude())
                .longitude(uploadPhotoPostReqDto.getLongitude())
                .build();
        return photoPostRepository.uploadPhotoPost(photoPost);
    }

    public List<PhotoPost> getPhotoPostList(GetPhotoPostListReqDto getPhotoPostListReqDto, Integer currentUserId) {
        return photoPostRepository.getPhotoPostList(getPhotoPostListReqDto, currentUserId);
    }

    public List<PhotoPost> getPhotoPostListByUserId(int userId, Integer currentUserId) {
        return photoPostRepository.getPhotoPostListByUserId(userId, currentUserId);
    }

    public int addComment(AddCommentReqDto addCommentReqDto) {
        Comment comment = Comment.builder()
                .photoPostId(addCommentReqDto.getPhotoPostId())
                .userId(addCommentReqDto.getUserId())
                .content(addCommentReqDto.getContent())
                .build();
        return photoPostRepository.addComment(comment);
    }

    public int removePostByPhotoPostId(int photoPostId) {
        return photoPostRepository.removePostByPhotoPostId(photoPostId);
    }

    public int addLike(AddLikeReqDto addLikeReqDto) {
        return photoPostRepository.addLike(addLikeReqDto);
    }

    public int removeLike(AddLikeReqDto addLikeReqDto) {
        return photoPostRepository.removeLike(addLikeReqDto);
    }

    public List<PhotoPost> getHotPhotoPostList(Integer currentUserId) {
        return photoPostRepository.getHotPhotoPostList(currentUserId);
    }

    public List<PhotoPost> getRecentPhotoPostList(Integer currentUserId) {
        return photoPostRepository.getRecentPhotoPostList(currentUserId);
    }

    public List<PhotoPost> getPhotoPostListByPosition(GetPhotoPostListByPositionReqDto getPhotoPostListByPositionReqDto, Integer currentUserId) {
        return photoPostRepository.getPhotoPostListByPosition(getPhotoPostListByPositionReqDto, currentUserId);
    }

    public int editPhotoPostText(int photoPostId, String text) {
        System.out.println(photoPostId);
        System.out.println(text);
        return photoPostRepository.editPhotoPostText(photoPostId, text);
    }
}
