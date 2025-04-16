package com.waypost.waypost.service;

import com.waypost.waypost.dto.post.PhotoPostUploadReqDto;
import com.waypost.waypost.entity.PhotoPost;
import com.waypost.waypost.repository.PhotoPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PhotoPostRepository photoPostRepository;

    public int photoPostUpload(PhotoPostUploadReqDto photoPostUploadReqDto) {
        PhotoPost photoPost = PhotoPost.builder()
                .userId(photoPostUploadReqDto.getUserId())
                .postText(photoPostUploadReqDto.getPostText())
                .imgUrl(photoPostUploadReqDto.getImgUrl())
                .cameraModel(photoPostUploadReqDto.getCameraModel())
                .locationAddress(photoPostUploadReqDto.getLocationAddress())
                .latitude(photoPostUploadReqDto.getLatitude())
                .longitude(photoPostUploadReqDto.getLongitude())
                .build();
        return photoPostRepository.photoPostUpload(photoPost);
    }
}
