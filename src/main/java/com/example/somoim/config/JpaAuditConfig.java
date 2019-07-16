package com.example.somoim.config;

import com.example.somoim.model.admin.AdminUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaAuditConfig {

    @Bean
    public AuditorAware<AdminUser> auditorProvider() {
        return new AuditorAwareImpl(); // AuditorAware 의 구현체 객체 생성
    }
}
