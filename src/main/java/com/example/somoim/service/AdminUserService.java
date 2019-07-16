package com.example.somoim.service;

import com.example.somoim.dto.AdminUserDto;
import com.example.somoim.model.admin.AdminUserAuthority;
import com.example.somoim.model.admin.AdminUserDetails;
import com.example.somoim.repository.AdminUserAuthorityRepository;
import com.example.somoim.repository.AdminUserRepository;
import com.example.somoim.model.admin.AdminUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AdminUserService implements UserDetailsService {
    @Autowired
    private AdminUserRepository adminUserRepository;

    @Autowired
    private AdminUserAuthorityRepository adminUserAuthorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AdminUser createAdminUser(AdminUserDto adminUserDto){
        AdminUser admin = AdminUser.builder()
                                    .email(adminUserDto.getAdminEmail())
                                    .adminId(adminUserDto.getAdminId())
                                    .adminUserName(adminUserDto.getAdminName())
                                    .password(passwordEncoder.encode(adminUserDto.getInputPassword()))
                                    .build();
        return adminUserRepository.save(admin);
    }

    @Override
    public UserDetails loadUserByUsername(String adminUserId) throws UsernameNotFoundException {
        Optional<AdminUser> byUserId = adminUserRepository.findByAdminId(adminUserId);
        AdminUser AdminUser = byUserId.orElseThrow(()-> new UsernameNotFoundException(adminUserId));
        Optional<AdminUserAuthority> role = adminUserAuthorityRepository.findByAuthorityId(AdminUser.getAuthority());

        //일치하는 username이 없으면 exception 처리
        log.debug(AdminUser.getAdminId());
        log.info(role.get().getAuthorityId());
        AdminUserDetails adminUserDetails = new AdminUserDetails(AdminUser,role.get().getAuthorityId());
        return adminUserDetails;
        //return 값으로 User반환, 이건 스프링시큐리티에서 UserDetails라는 인터페이스에 정의되어 있다. User안에 정의 되어 있음.
    }



}
