package com.waypost.waypost.security.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.waypost.waypost.entity.User;
import com.waypost.waypost.mapper.UserMapper;
import com.waypost.waypost.repository.UserRepository;
import com.waypost.waypost.security.jwt.JwtUtil;
import com.waypost.waypost.security.principal.PrincipalUser;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter implements Filter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String bearerToken = getAuthorization((HttpServletRequest) servletRequest);

        if(isValidToken(bearerToken)) {
            String accessToken = removeBearer(bearerToken);
            Claims claims = jwtUtil.parseToken(accessToken);
            if(claims != null) {
                String email = claims.getSubject();
                User user = getUser(email);
                setAuthentication(user);
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    // redis, mysql
    private User getUser(String email) throws JsonProcessingException {
        User user = null;

        user = userRepository.findByEmail(email).get();
//        if(user != null) {
//            String jsonUser = objectMapper.writeValueAsString(user);
//            redisTemplate.opsForValue().set("user:" + userId, jsonUser, Duration.ofMinutes(10));
//        }

        return user;
    }

    private void setAuthentication(User user) {
        if(user == null) {
            return;
        }
        PrincipalUser principalUser = new PrincipalUser(user);
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        principalUser,
                        user,
                        principalUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getAuthorization(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    private boolean isValidToken(String token) {
        return token != null && token.startsWith("Bearer ");
    }

    private String removeBearer(String bearerToken) {
        return bearerToken.substring(7);
    }

}