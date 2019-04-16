package com.example.somoim.service;

import com.example.somoim.dto.MemberAttend;
import com.example.somoim.dto.MemberAttendList;
import com.example.somoim.dto.MemberListResDto;
import com.example.somoim.model.AdminUserDetails;
import com.example.somoim.repository.MemberRepository;
import com.example.somoim.dto.MemberDto;
import com.example.somoim.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    public List<MemberListResDto> getMemberList() {
        List<Member> result =  memberRepository.findAll();
        List<MemberListResDto> resultList = new ArrayList<>();
        for (Member list: result) {
            MemberListResDto MemberListBuilder = MemberListResDto.builder()
                                                .memberSeq(list.getMemberSeq())
                                                .memberName(list.getMemberName())
                                                .memberNickName(list.getMemberNickName())
                                                .attendCount(list.getAttendCount())
                                                .attendCountMonth(list.getAttendCountMonth())
                                                .regDate(list.getCreatedDate())
                                              .build();
            resultList.add(MemberListBuilder);
        }


        return resultList;
    }

    public void saveMember(MemberDto memberDto) {
       Member member = Member.builder()
                        .memberName(memberDto.getMemberName())
                        .memberNickName(memberDto.getMemberNickName())
                        .somoimJoinDate(memberDto.getMemberJoinDate())
                        .build();
        memberRepository.save(member);
    }

    public void deleteMembers(List<Map<String, Long>> deleteLists, AdminUserDetails adminUserDetails) {

        for(Map<String, Long> result: deleteLists) {
             for(Map.Entry<String,Long> entry : result.entrySet()){
                 memberRepository.softDelete(entry.getValue(),adminUserDetails.getAdminUser(), LocalDateTime.now());
             }
        }
    }

    public void saveMemberAttend(MemberAttendList memberAttendList) throws Exception {
        for (MemberAttend result: memberAttendList.getMemberAttend()) {
            log.debug("result{}",result);
            Member mBuilder= Member.builder().memberSeq(result.getMemberSeq()).build();
            Optional<Member> m = memberRepository.findById(mBuilder.getMemberSeq());
                if(!m.isPresent()){
                    throw new Exception();
                }
            m.get().setLastAttend(memberAttendList.getMemberAttendDate());
            Long attendCountMonth =  m.get().getAttendCountMonth()==0?1L:m.get().getAttendCountMonth()+1L;
            m.get().setAttendCountMonth(attendCountMonth);
            Long attendCount =  m.get().getAttendCount()==0?1L:m.get().getAttendCount()+1L;
            m.get().setAttendCount(attendCount);
            memberRepository.save(m.get());
        }
    }

    public void getMemberDetail(String memberSeq) {


    }
}