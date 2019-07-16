package com.example.somoim.controller;

import com.example.somoim.dto.*;
import com.example.somoim.model.admin.AdminUserDetails;
import com.example.somoim.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public  @ResponseBody void attendMembers(@RequestBody MemberAttendList memberAttendList) throws Exception {
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

    @GetMapping("/attend")
    public String test(){
        return "attend";
    }

    @GetMapping("/memberAttendHistoryList")
    public @ResponseBody
    MemberAttendHistoryCriteriaRes memberAttendHistoryList(MemberAttendHistoryCriteriaReq memberAttendHistoryCriteria){

        log.info(memberAttendHistoryCriteria.toString());
        //첫 조회시 start가 0이나, next page시 start+length 값으로 됨.
        //https://datatables.net/forums/discussion/56424/serverside-paging-problem-start-value-legnth-value#latest
        int currentPage = 0;
        if(memberAttendHistoryCriteria.getStart() !=0){
            currentPage = memberAttendHistoryCriteria.getStart() / memberAttendHistoryCriteria.getLength();
        }

        String draw = memberAttendHistoryCriteria.getDraw();

        Pageable pageable = PageRequest.of(currentPage,memberAttendHistoryCriteria.getLength(), Sort.Direction.DESC,"memberAttendDay");

        MemberAttendHistoryCriteriaRes result =  memberService.getMemberAttendHistoryList(pageable, draw);

        log.info("xxx memberAttendHistoryList  data xxx "+result.toString());
        return result;
    }


}
