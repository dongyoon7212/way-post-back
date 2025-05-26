package com.waypost.waypost.mapper;

import com.waypost.waypost.dto.account.FindByUserIdRespDto;
import com.waypost.waypost.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    int insert(User user);
    User findByEmail(String email);
    User findByUsername(String username);
    FindByUserIdRespDto findByUserId(int userId, Integer currentUserId);
    int editProfileImg(int userId, String profileImg);
    int editIntroduce(String introduce, Integer currentUserId);
    int deactivateAccount(Integer userId);
    int activateAccount(Integer userId);
    List<User> getFollowerList(int userId);
    List<User> getFollowingList(int userId);

}
