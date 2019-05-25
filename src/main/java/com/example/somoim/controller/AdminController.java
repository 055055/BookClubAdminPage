package com.example.somoim.controller;

import com.example.somoim.dto.AdminUserDto;
import com.example.somoim.service.AdminUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@Controller
public class AdminController {
    @Autowired
    private AdminUserService adminUserService;

    @GetMapping("/admin")
    public String registerAdmin(ModelMap modelMap){

        return "adminRegister";
    }

    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void registerAdmin(AdminUserDto adminUserDto){
        log.debug("### : "+adminUserDto.toString());
        adminUserService.createAdminUser(adminUserDto);
    }
}
