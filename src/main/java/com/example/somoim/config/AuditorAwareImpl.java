package com.example.somoim.config;


import com.example.somoim.model.admin.AdminUser;
import com.example.somoim.model.admin.AdminUserDetails;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;


public class AuditorAwareImpl implements AuditorAware<AdminUser> {

    public Optional<AdminUser> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(null == authentication || !authentication.isAuthenticated()){
            return null;
        }

        return Optional.of(((AdminUserDetails)authentication.getPrincipal()).getAdminUser());
        //userdetail로 안하니까 자꾸 case error 가 나타남. 그래서 userdetail로 principal 받고 그안에 user로 리턴
    }
}
