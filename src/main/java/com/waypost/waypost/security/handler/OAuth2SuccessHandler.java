package com.waypost.waypost.security.handler;

import com.waypost.waypost.dto.account.FindByUserIdRespDto;
import com.waypost.waypost.entity.OAuth2User;
import com.waypost.waypost.mapper.OAuth2UserMapper;
import com.waypost.waypost.mapper.UserMapper;
import com.waypost.waypost.security.jwt.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    @Value("${client.deploy-address}")
    private String clientAddress;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OAuth2UserMapper oAuth2UserMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("요청은 옴?");
        DefaultOAuth2User defaultOAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
        System.out.println("defaultOauth2User" + defaultOAuth2User);
        String provider = defaultOAuth2User.getAttribute("provider").toString();
        String providerUserId = defaultOAuth2User.getAttribute("id").toString();

        // OAuth2 정보로 연동된 계정 찾기
        OAuth2User oAuth2User = oAuth2UserMapper.findByProviderAndProviderUserId(provider, providerUserId);

        // OAuth2 로그인을 통해 회원가입이 되어있지 않은 상태 (연동이 된적이 없는 경우)
        // OAuth2 동기화
        if (oAuth2User == null) { //죠랄남 개같은거
            // oAuth2User 않은 경우: 프론트로 provider 정보와 providerUserId 전달
            response.sendRedirect("http://" + clientAddress + "/auth/oauth2?provider=" + provider + "&providerUserId=" + providerUserId);
            return;
        }

        FindByUserIdRespDto findByUserIdRespDto = userMapper.findByUserId(oAuth2User.getUserId(), null);

        // OAuth2 로그인을 통해 회원가입을 진행한 기록이 있는지 (연동이 된 경우)
        String accessToken = jwtUtil.generateToken(Integer.toString(findByUserIdRespDto.getUser().getUserId()), findByUserIdRespDto.getUser().getEmail(), false);
        response.sendRedirect("http://" + clientAddress + "/auth/oauth2/signin?accessToken=" + accessToken);

    }
}
