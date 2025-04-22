package com.waypost.waypost.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhotoPost {
    private int photoPostId;
    private int userId;
    private String postText;
    private String imgUrl;
    private String cameraModel;
    private String locationAddress;
    private double latitude;
    private double longitude;
    private Point location;
    private LocalDateTime regDt;
    private LocalDateTime updDt;
    private User user;
}
