package com.example.somoim.repository;

import com.example.somoim.model.AdminUserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AdminUserAuthorityRepository extends JpaRepository<AdminUserAuthority, Long> {
    Optional<AdminUserAuthority> findByAuthorityId(String authorityId);

}
