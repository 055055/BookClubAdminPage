package com.example.somoim;

import com.example.somoim.dto.AdminUserDto;
import com.example.somoim.model.AdminUser;
import com.example.somoim.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {
    @Autowired
    private AdminUserService adminUserService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        AdminUserDto adminUserDto = new AdminUserDto();
        adminUserDto.setAdminEmail("055055@055055.com");
        adminUserDto.setAdminId("055055");
        adminUserDto.setInputPassword("055055");
        adminUserDto.setAdminName("055055");

      AdminUser result = adminUserService.createAdminUser(adminUserDto);
        System.out.println(result);
    }
}
