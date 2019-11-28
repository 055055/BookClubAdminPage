package com.example.somoim.controller;

import com.example.somoim.dto.MemberPwdResetDto;
import com.example.somoim.model.admin.AdminUser;
import com.example.somoim.model.member.PasswordResetToken;
import com.example.somoim.repository.AdminUserRepository;
import com.example.somoim.service.LogInOutService;
import com.example.somoim.service.SendMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class LogInOutController {
    private final LogInOutService logInOutService;
    private final SendMailService sendMailService;

    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }

    @GetMapping("/forgot-password")
    public String forgotPasswordForm(){
        return "forgot-password";
    }

    @PostMapping("/reset-password")
    public String resetPassword(MemberPwdResetDto memberPwdResetDto){
        System.out.println("xoxo : "+memberPwdResetDto.toString());
       AdminUser adminUser = logInOutService.findByEmail(memberPwdResetDto);
       if(adminUser == null || !adminUser.getAdminId().equals(memberPwdResetDto.getUserId())){
           return "redirect:forgot-password?error";
       }

        String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = logInOutService.createPasswordResetTokenForUser(token,adminUser);
                                                sendMailService.sendPasswordResetMail(passwordResetToken);




        return "forgot-password";
    }



}
