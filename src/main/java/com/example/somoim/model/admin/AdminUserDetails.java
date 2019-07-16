package com.example.somoim.model.admin;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

@Getter
public class AdminUserDetails implements UserDetails {

    private AdminUser adminUser;

    private Collection<? extends GrantedAuthority> authorities;

    public AdminUserDetails(AdminUser adminUser, String role) {
        this.adminUser = adminUser;
        this.authorities = makeRole(role);
    }

    @Override
    public String getPassword() {
        return adminUser.getPassword();
    }

    @Override
    public String getUsername() {
        return adminUser.getAdminId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private Collection<? extends GrantedAuthority> makeRole(String role) {
        return Arrays.asList(new SimpleGrantedAuthority(role));
    }

}
