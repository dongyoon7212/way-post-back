package com.waypost.waypost.repository;

import com.waypost.waypost.entity.PhotoPost;
import com.waypost.waypost.mapper.PhotoPostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PhotoPostRepository {

    @Autowired
    private PhotoPostMapper photoPostMapper;

    public int photoPostUpload(PhotoPost photoPost) {
        return photoPostMapper.photoPostUpload(photoPost);
    }
}
