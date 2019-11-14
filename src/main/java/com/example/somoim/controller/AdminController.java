package com.example.somoim.controller;

import com.example.somoim.dto.AdminUserDto;
import com.example.somoim.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class AdminController{
    @Autowired
    private AccountService accountService;


    //@RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login(){
        System.out.println("ddddd");
        return "login";
    }

   // @RequestMapping(path = "/fail", method = RequestMethod.GET)
    public String loginaaa(){
        System.out.println("aaaaaa");
        return "member";
    }

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String main(@RequestParam String pageName, ModelMap model){
        model.addAttribute("pageName",pageName);

        return "index";
    }


    @GetMapping("/admin")
    public String registerAdmin(@RequestParam String pageName, ModelMap modelMap){
        modelMap.addAttribute("pageName",pageName);
        return "adminRegister";
    }

    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void registerAdmin(AdminUserDto adminUserDto){
        log.debug("### : "+adminUserDto.toString());
        accountService.createAdminUser(adminUserDto);
    }

}
