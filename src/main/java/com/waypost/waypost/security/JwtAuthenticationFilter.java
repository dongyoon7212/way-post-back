package com.waypost.waypost.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 🔴 인증이 필요 없는 경로 예외 처리
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/auth/signup") || requestURI.startsWith("/auth/duplChk")) {  // ✅ 회원가입 요청은 필터링 안 함
            filterChain.doFilter(request, response);
            return;
        }

        String token = getJwtFromRequest(request);
        if (token != null && jwtUtil.validateToken(token, getEmailFromToken(token))) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    getEmailFromToken(token), null, null);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    // HTTP 요청에서 JWT 토큰을 추출하는 메서드
    private String getJwtFromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7); // "Bearer " 뒤에 있는 토큰을 반환
        }
        return null;
    }

    // JWT에서 이메일을 추출하는 메서드
    private String getEmailFromToken(String token) {
        return jwtUtil.extractEmail(token);
    }
}
