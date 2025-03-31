package com.waypost.waypost.mapper;

import com.waypost.waypost.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRoleMapper {
    int insert(UserRole userRole);
    int insertSelective(@Param("userId") int userId, @Param("roleName") String roleName);
}