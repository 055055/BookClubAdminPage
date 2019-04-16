package com.example.somoim.service;

import com.example.somoim.repository.MemberRepository;
import com.example.somoim.dto.MemberDto;
import com.example.somoim.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    public List<Member> getMemberList() {

        List<Member> result =  memberRepository.findAll();
        return result;
    }

    public void saveMember(MemberDto memberDto) {
       Member member = Member.builder()
                        .memberName(memberDto.getMemberName())
                        .memberNickName(memberDto.getMemberNickName())
                        .somoinJoinDate(memberDto.getMemberJoinDate())
                        .build();
        memberRepository.save(member);
    }
}
