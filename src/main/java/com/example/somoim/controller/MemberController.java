package com.example.somoim.controller;

import com.example.somoim.dto.MemberDto;
import com.example.somoim.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@Controller
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/member/all")
    public String memberList(ModelMap modelMap){

      /*  List<Member> result=  memberService.getMemberList();
        modelMap.addAttribute("memberList",result);*/
        return "index";
    }

    @GetMapping("/member")
    public String member(){
        return "register";
    }

    @PostMapping("/member")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void member(MemberDto memberDto){
        log.info(memberDto.toString());
        memberService.saveMember(memberDto);
        log.info("dd");
    }
}
