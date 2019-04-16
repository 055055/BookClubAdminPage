package com.example.somoim.service;

import com.example.somoim.dto.AdminUserDto;
import com.example.somoim.repository.AdminUserRepository;
import com.example.somoim.model.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
public class AdminUserService implements UserDetailsService {
    @Autowired
    private AdminUserRepository adminUserRepository;

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
        //일치하는 username이 없으면 exception 처리
        return new User(AdminUser.getAdminId(), AdminUser.getPassword(), authorities());
        //return 값으로 User반환, 이건 스프링시큐리티에서 UserDetails라는 인터페이스에 정의되어 있다. User안에 정의 되어 있음.
    }

    //3번쨰 인자인 authorities는 어떤 권한을 가진 유저라는 것을 셋팅을 해줌.
    private Collection<? extends GrantedAuthority> authorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

}
