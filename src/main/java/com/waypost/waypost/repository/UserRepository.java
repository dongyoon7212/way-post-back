package com.waypost.waypost.repository;

import com.waypost.waypost.dto.account.EditProfileImgReqDto;
import com.waypost.waypost.dto.account.FindByUserIdRespDto;
import com.waypost.waypost.entity.User;
import com.waypost.waypost.mapper.UserFollowMapper;
import com.waypost.waypost.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserFollowMapper userFollowMapper;

    public Optional<User> save(User user) {
        try {
            userMapper.insert(user);
        } catch (DuplicateKeyException e) {
            return Optional.empty();
        }
        return Optional.of(user);
    }

    public Optional<FindByUserIdRespDto> findByUserId(int userId, Integer currentUserId) {
        return Optional.ofNullable(userMapper.findByUserId(userId, currentUserId));
    }

    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(userMapper.findByEmail(email));
    }

    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(userMapper.findByUsername(username));
    }

    public int editProfileImg(EditProfileImgReqDto editProfileImgReqDto) {
        return userMapper.editProfileImg(editProfileImgReqDto.getUserId(), editProfileImgReqDto.getProfileImg());
    }

    public int editIntroduce(String introduce, Integer currentUserId) {
        return userMapper.editIntroduce(introduce, currentUserId);
    }

    public int follow(Integer currentUserId, int followeeId) {
        return userFollowMapper.follow(currentUserId, followeeId);
    }

    public int unfollow(Integer currentUserId, int followeeId) {
        return userFollowMapper.unfollow(currentUserId, followeeId);
    }

    public int deactivateAccount(Integer userId) {
        return userMapper.deactivateAccount(userId);
    }

    public int activateAccount(Integer userId) {
        return userMapper.activateAccount(userId);
    }

    public List<User> getFollowerList(int userId) {
        return userMapper.getFollowerList(userId);
    }

    public List<User> getFollowingList(int userId) {
        return userMapper.getFollowingList(userId);
    }
}
