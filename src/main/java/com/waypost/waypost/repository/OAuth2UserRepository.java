package com.waypost.waypost.repository;

import com.waypost.waypost.entity.OAuth2User;
import com.waypost.waypost.mapper.OAuth2UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OAuth2UserRepository {

    @Autowired
    private OAuth2UserMapper oAuth2UserMapper;

    public OAuth2User findByProviderAndProviderUserId(String provider, String providerUserId) {
        return oAuth2UserMapper.findByProviderAndProviderUserId(provider, providerUserId);
    }
    public int insertOAuth2User(OAuth2User oAuth2User) {
        return oAuth2UserMapper.insertOAuth2User(oAuth2User);
    }
}
