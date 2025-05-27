package com.waypost.waypost.repository;

import com.waypost.waypost.entity.EmailVerification;
import com.waypost.waypost.mapper.EmailVerificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmailVerifiCationRepository {
    @Autowired
    private EmailVerificationMapper mapper;

    public void save(EmailVerification verification) {
        mapper.saveVerificationCode(verification);
    }

    public EmailVerification findLatestByUserId(int userId) {
        return mapper.findLatestByUserId(userId);
    }

    public void incrementFailCount(long id) {
        mapper.incrementFailCount(id);
    }

    public void markAsVerified(long id) {
        mapper.markAsVerified(id);
    }

    public void invalidatePreviousCodes(int userId) {
        mapper.invalidatePreviousCodes(userId);
    }
}

