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
        LocalDateTime expiredDt = LocalDateTime.now().plusMinutes(5);

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
            helper.setText("<div><h3>아래 인증 코드를 입력해주세요</h3><h2>" + code + "</h2><p>유효시간: 5분</p></div>", true);
            mailSender.send(message);
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
