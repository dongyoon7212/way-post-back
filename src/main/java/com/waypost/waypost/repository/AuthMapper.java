package com.waypost.waypost.repository;

import com.waypost.waypost.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper {

    public int signUp(User user);
    public String findByEmail(String email);
}
