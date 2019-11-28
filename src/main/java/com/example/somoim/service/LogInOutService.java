package com.example.somoim.service;


import com.example.somoim.dto.MemberPwdResetDto;
import com.example.somoim.model.admin.AdminUser;
import com.example.somoim.model.member.PasswordResetToken;
import com.example.somoim.repository.AdminUserRepository;
import com.example.somoim.repository.PasswordTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LogInOutService {
    private final AdminUserRepository adminUserRepository;
    private final PasswordTokenRepository passwordTokenRepository;
    public final AdminUser findByEmail(MemberPwdResetDto memberPwdResetDto) {

      return adminUserRepository.findByEmail(memberPwdResetDto.getUserEmail());

    }

    public PasswordResetToken createPasswordResetTokenForUser(String token, AdminUser adminUser) {
        PasswordResetToken passwordResetToken    =     PasswordResetToken.builder()
                                                        .adminUser(adminUser)
                                                        .token(token)
                                                        .expiryDateTime(LocalDateTime.now().plusHours(1))
                                                        .build();
        return passwordTokenRepository.save(passwordResetToken);
    }
}
