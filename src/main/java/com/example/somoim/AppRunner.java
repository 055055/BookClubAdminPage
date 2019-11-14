package com.example.somoim;

import com.example.somoim.dto.AdminUserDto;
import com.example.somoim.model.admin.AdminUser;
import com.example.somoim.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

//@Component
public class AppRunner implements ApplicationRunner {
    @Autowired
    private AccountService accountService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        AdminUserDto adminUserDto = new AdminUserDto();
        adminUserDto.setAdminEmail("055055@055055.com");
        adminUserDto.setAdminId("055055");
        adminUserDto.setInputPassword("055055");
        adminUserDto.setAdminName("055055");

      AdminUser result = accountService.createAdminUser(adminUserDto);
        System.out.println(result);
    }
}
