package com.example.somoim.repository;

import com.example.somoim.model.admin.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {
    Optional<AdminUser> findByAdminId(String adminId);

    AdminUser findByEmail(String userEmail);
}
