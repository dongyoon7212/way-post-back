package com.waypost.waypost.service;

import com.waypost.waypost.dto.emailVerification.VerifyCodeReqDto;
import com.waypost.waypost.entity.User;
import com.waypost.waypost.mapper.UserMapper;
import com.waypost.waypost.repository.UserRepository;
import com.waypost.waypost.repository.UserRoleRepository;
import com.waypost.waypost.security.jwt.JwtUtil;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.Random;

@Service
public class EmailVerificationService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private JwtUtil jwtUtil;

    public void sendVerificationCode(int userId, String email, String purpose) {
        String code = String.format("%06d", new Random().nextInt(999999));
        String codeKey = purpose + "-verification:" + userId;

        redisTemplate.opsForValue().set(codeKey, code, Duration.ofMinutes(2));

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(email);
            helper.setSubject("WayPost 이메일 인증 코드입니다.");
            helper.setText(
                    "<div style=\"font-family: 'Arial', sans-serif; background-color: #f9f9f9; padding: 30px; border-radius: 10px; border: 1px solid #ddd; max-width: 500px; margin: auto;\">" +
                            "<div style=\"text-align: center; margin-bottom: 20px;\">" +
                            "<img src='https://firebasestorage.googleapis.com/v0/b/way-post-9f21b.firebasestorage.app/o/logo-assets%2Flogo1.png?alt=media&token=c1da33dc-5e94-4e5f-bb9b-112c749ae0c6' alt='WayPost 로고' style='height: 150px;'/>" +
                            "</div>" +
                            "<h2 style=\"color: #1E90FF; text-align: center;\">WayPost 이메일 인증</h2>" +
                            "<p style=\"font-size: 16px; color: #333; text-align: center;\">아래 인증 코드를 입력하여 이메일 인증을 완료해주세요.</p>" +
                            "<div style=\"margin: 30px auto; text-align: center; padding: 20px; border-radius: 8px; background-color: #f1f8ff; width: fit-content; border: 1px dashed #1E90FF;\">" +
                            "<span style=\"font-size: 28px; font-weight: bold; letter-spacing: 4px; color: #1E90FF;\">" + code + "</span>" +
                            "</div>" +
                            "<p style=\"font-size: 14px; color: #666; text-align: center;\">인증 코드는 <strong>2분간 유효</strong>합니다.</p>" +
                            "<hr style=\"margin: 30px 0;\">" +
                            "<p style=\"font-size: 12px; color: #999; text-align: center;\">본 메일은 WayPost 인증용으로 발송되었으며, 회신하지 마세요.</p>" +
                            "</div>",
                    true
            );
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace(); // 로깅 필요
        }
    }

    public Map<String, Object> verifyCode(VerifyCodeReqDto dto) {
        String purpose = dto.getPurpose(); // "signup" or "reset"
        int userId = dto.getUserId();
        String email = dto.getEmail();
        String code = dto.getCode();

        String codeKey = purpose + "-verification:" + userId;
        String failKey = purpose + "-verification:fail:" + userId;

        String savedCode = (String) redisTemplate.opsForValue().get(codeKey);
        if (savedCode == null) {
            return Map.of("status", false, "code", 4001, "message", "인증 코드가 만료되었습니다.");
        }

        if (!savedCode.equals(code)) {
            Long fails = redisTemplate.opsForValue().increment(failKey);
            if (fails == 1) redisTemplate.expire(failKey, Duration.ofMinutes(2));
            if (fails >= 5) {
                redisTemplate.delete(codeKey);
                redisTemplate.delete(failKey);
                return Map.of("status", false, "code", 4002, "message", "인증 실패 횟수를 초과했습니다.");
            }
            return Map.of("status", false, "code", 4003, "message", "인증 코드가 올바르지 않습니다.");
        }

        // 인증 성공
        redisTemplate.delete(codeKey);
        redisTemplate.delete(failKey);

        if (purpose.equals("signup")) {
            userMapper.setRole(userId, 2); // 일반 사용자로 승격
            return Map.of("status", true, "code", 2000, "message", "이메일 인증이 완료되었습니다.");
        }

        if (purpose.equals("reset")) {
            redisTemplate.opsForValue().set("reset-allowed:" + email, "ok", Duration.ofMinutes(5));
            return Map.of("status", true, "code", 2000, "message", "이메일 인증이 완료되었습니다.");
        }

        return Map.of("status", false, "code", 4004, "message", "알 수 없는 인증 요청입니다.");
    }

}
