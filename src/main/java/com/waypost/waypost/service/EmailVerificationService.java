package com.waypost.waypost.service;

import com.waypost.waypost.entity.EmailVerification;
import com.waypost.waypost.mapper.UserMapper;
import com.waypost.waypost.repository.EmailVerifiCationRepository;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

@Service
public class EmailVerificationService {
    @Autowired
    private EmailVerifiCationRepository repository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationCode(int userId, String email) {
        String code = String.format("%06d", new Random().nextInt(999999));
        LocalDateTime expiredDt = LocalDateTime.now().plusMinutes(2);

        repository.invalidatePreviousCodes(userId);

        EmailVerification emailVerification = new EmailVerification();
        emailVerification.setUserId(userId);
        emailVerification.setVerificationCode(code);
        emailVerification.setExpiredDt(expiredDt);
        repository.save(emailVerification);

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");
            helper.setTo(email);
            helper.setSubject("WayPost 이메일 인증 코드");
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
            );            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, Object> verifyCode(int userId, String inputCode) {
        EmailVerification emailVerification = repository.findLatestByUserId(userId);
        if (emailVerification == null || emailVerification.getIsVerified()) {
            return Map.of("status", false, "code", 4001, "message", "잘못된 요청입니다.");
        }
        if (emailVerification.getExpiredDt().isBefore(LocalDateTime.now())) {
            return Map.of("status", false, "code", 4002, "message", "코드가 만료되었습니다.");
        }
        if (emailVerification.getFailCount() >= 5) {
            return Map.of("status", false, "code", 4003, "message", "5회 이상 실패로 인증이 차단되었습니다.");
        }
        if (!emailVerification.getVerificationCode().equals(inputCode)) {
            repository.incrementFailCount(emailVerification.getEmailVrfctId());
            return Map.of("status", false, "code", 4004, "message", "인증 코드가 일치하지 않습니다.");
        }
        userMapper.setRole(userId, 2); // role_id = 2로 변경
        repository.markAsVerified(emailVerification.getEmailVrfctId());
        return Map.of("status", true, "code", 2000, "message", "인증 성공");
    }

}
