package com.waypost.waypost.mapper;

import com.waypost.waypost.entity.OAuth2User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OAuth2UserMapper {
    OAuth2User findByProviderAndProviderUserId(String provider, String providerUserId);
    int insertOAuth2User(OAuth2User oAuth2User);
}
