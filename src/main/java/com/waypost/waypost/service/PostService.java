package com.waypost.waypost.service;

import com.waypost.waypost.dto.post.GetPhotoPostListReqDto;
import com.waypost.waypost.dto.post.UploadPhotoPostReqDto;
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

    public List<PhotoPost> getPhotoPostList(GetPhotoPostListReqDto getPhotoPostListReqDto) {
        return photoPostRepository.getPhotoPostList(getPhotoPostListReqDto);
    }
}
