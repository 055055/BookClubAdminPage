package com.example.somoim.service;

import com.example.somoim.dto.ChartMonthlyAttendRankDto;
import com.example.somoim.model.member.Member;
import com.example.somoim.repository.MemberAttendHisRepository;
import com.example.somoim.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ChartService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberAttendHisRepository memberAttendHisRepository;

    public List<ChartMonthlyAttendRankDto> getMonthlyAttendRank() {
   // List<Member> members=  memberRepository.findTop4ByOrderByAttendCountMonthDesc();
        int a = 0;
        List<Member> members=  memberRepository.findTop4ByAttendCountMonthGreaterThanOrderByAttendCountMonthDesc(a);
    List<ChartMonthlyAttendRankDto> list = new ArrayList<>();
    for (Member member: members) {
        ChartMonthlyAttendRankDto result = ChartMonthlyAttendRankDto.builder()
                                            .memberName(member.getMemberName())
                                            .memberCount(member.getAttendCountMonth())
                                            .build();
        list.add(result);
        }
    log.info("getMonthly Attend Rank : "+list.toString());
    return list;
    }

    public List<Map<String,Integer>> getMonthlyAttendRate(){
    List<Map<String,Integer>>  found =  memberAttendHisRepository.findMonthlyRate();

        return found;
    }
}
