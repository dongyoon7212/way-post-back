package com.waypost.waypost.mapper;

import com.waypost.waypost.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    int insertAll(List<Role> roles);
}
