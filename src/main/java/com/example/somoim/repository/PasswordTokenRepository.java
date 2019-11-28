package com.example.somoim.repository;

import com.example.somoim.model.member.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordTokenRepository extends JpaRepository<PasswordResetToken,Long> {
}
