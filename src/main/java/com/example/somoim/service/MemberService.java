package com.example.somoim.service;

import com.example.somoim.dto.*;
import com.example.somoim.model.admin.AdminUserDetails;
import com.example.somoim.model.member.MemberAttendHistory;
import com.example.somoim.repository.MemberAttendHisRepository;
import com.example.somoim.repository.MemberRepository;
import com.example.somoim.model.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
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

    MemberAttendHisRepository memberAttendHisRepository;
    public MemberService(MemberAttendHisRepository memberAttendHisRepository) {
        this.memberAttendHisRepository = memberAttendHisRepository;
    }

    @Scheduled(cron="0 0 0 1 * *")
    public void resetMonthlyAttendCount(){
      List<Member> found =  memberRepository.findAll();
      found.stream().forEach(t->t.setAttendCountMonth(0));
    }




    public List<MemberListResDto> getMemberList() {
        List<Member> result =  memberRepository.findAllByOrderBySomoimJoinDateAsc();

        List<MemberListResDto> resultList = new ArrayList<>();
        for (Member list: result) {
            MemberListResDto MemberListBuilder = MemberListResDto.builder()
                                                .memberSeq(list.getMemberSeq())
                                                .memberName(list.getMemberName())
                                                .memberNickName(list.getMemberNickName())
                                                .attendCount(list.getAttendCount())
                                                .attendCountMonth(list.getAttendCountMonth())
                                                .lastAttend(list.getLastAttend())
                                                .somoimJoinDate(list.getSomoimJoinDate())
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
            int attendCountMonth =  m.get().getAttendCountMonth()==0?1:m.get().getAttendCountMonth()+1;
            m.get().setAttendCountMonth(attendCountMonth);
            Long attendCount =  m.get().getAttendCount()==0?1L:m.get().getAttendCount()+1L;
            m.get().setAttendCount(attendCount);
            memberRepository.save(m.get());
            MemberAttendHistory memberAttendHis = MemberAttendHistory.builder()
                                                .memberSeq(m.get().getMemberSeq())
                                                .memberName(m.get().getMemberName())
                                                .memberAttendDay(m.get().getLastAttend())
                                                .memberAttendPlae(memberAttendList.getMemberAttendPlace())
                                                .build();
            memberAttendHisRepository.save(memberAttendHis);
        }
    }

    public void getMemberDetail(String memberSeq) {


    }

    public MemberAttendHistoryCriteriaRes getMemberAttendHistoryList(Pageable pageable, String draw) {
        Page<MemberAttendHistory>  result =  memberAttendHisRepository.findAll(pageable);
        List<MemberAttendHistoryDto> attendHistroyList = new ArrayList<>();
        for (MemberAttendHistory memberAttendHistory: result) {
            MemberAttendHistoryDto memberAttendHistoryDto = MemberAttendHistoryDto.builder()
                                                            .memberAttendHisSeq(memberAttendHistory.getMemberAttendHisSeq())
                                                            .memberSeq(memberAttendHistory.getMemberSeq())
                                                            .memberName(memberAttendHistory.getMemberName())
                                                            .memberAttendDay(memberAttendHistory.getMemberAttendDay())
                                                            .memberAttendPlace(memberAttendHistory.getMemberAttendPlace())
                                                            .build();
            attendHistroyList.add(memberAttendHistoryDto);
        }

        log.info("xxx memberAttendHistoryList  reuslt xxx "+result.toString());
        MemberAttendHistoryCriteriaRes  memberAttendHistoryCriteriaRes = MemberAttendHistoryCriteriaRes.builder()
                                                                        .draw(draw)
                                                                        .recordsTotal(result.getTotalElements())
                                                                        .recordsFiltered(result.getTotalElements())
                                                                        .data(attendHistroyList)
                                                                        .build();

        return memberAttendHistoryCriteriaRes;
    }



}