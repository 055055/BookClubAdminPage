package com.example.somoim.controller;

import com.example.somoim.dto.MemberAttendList;
import com.example.somoim.dto.MemberDto;
import com.example.somoim.dto.MemberListResDto;
import com.example.somoim.model.AdminUserDetails;
import com.example.somoim.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        log.info("dd");
        memberService.saveMember(memberDto);
    }

    @GetMapping("/memberList")
    public @ResponseBody
    Map<String,List> memberList(){
        List<MemberListResDto> result =  memberService.getMemberList();
        Map<String, List> data = new HashMap<>();
        data.put("data",result);
        log.info("xxx data xxx "+result.toString());
        return data;
    }

    @PostMapping("/memberList")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody void memberList(@RequestBody  List<Map<String,Long>> deleteList,
                                         @AuthenticationPrincipal AdminUserDetails adminUserDetails
                                         ){
        log.info("xxx delete xxx "+deleteList.toString());
        memberService.deleteMembers(deleteList,adminUserDetails);
        /* for (Object result : deleteList ) {
            log.info("xxx delete xxx "+result.toString());
        }*/
    }

    @PostMapping("/attend")
    public void attendMembers(@RequestBody MemberAttendList memberAttendList) throws Exception {
        log.debug(memberAttendList.toString());
        memberService.saveMemberAttend(memberAttendList);
    }

    @GetMapping("/tables")
    public String tables(){
        return "tables";
    }

    @GetMapping("/memberDetail/{memberSeq}")
    public void memberDetail(@PathVariable String memberSeq){
        log.debug("memberDtail : "+memberSeq);
    }

    @GetMapping("/test")
    public String test(){
        return "layoutTest";
    }
}
