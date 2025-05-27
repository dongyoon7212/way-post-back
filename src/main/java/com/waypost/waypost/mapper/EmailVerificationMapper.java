package com.waypost.waypost.mapper;

import com.waypost.waypost.entity.EmailVerification;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmailVerificationMapper {
    void saveVerificationCode(EmailVerification verification);
    EmailVerification findLatestByUserId(int userId);
    void incrementFailCount(long emailVrfctId);
    void markAsVerified(long emailVrfctId);
    void invalidatePreviousCodes(int userId);
}
