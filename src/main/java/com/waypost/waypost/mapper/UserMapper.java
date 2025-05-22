package com.waypost.waypost.mapper;

import com.waypost.waypost.dto.account.EditProfileImgReqDto;
import com.waypost.waypost.dto.auth.FindByUserIdRespDto;
import com.waypost.waypost.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {

    int insert(User user);
    User findByEmail(String email);
    User findByUsername(String username);
    FindByUserIdRespDto findByUserId(int userId, Integer currentUserId);
    int editProfileImg(int userId, String profileImg);
    int editIntroduce(String introduce, Integer currentUserId);

}
