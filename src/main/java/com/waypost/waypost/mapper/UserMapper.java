package com.waypost.waypost.mapper;

import com.waypost.waypost.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {

    int insert(User user);
    User findByEmail(String email);
    User findByUsername(String username);
    User findByUserId(int userId);

}
