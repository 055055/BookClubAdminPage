package com.example.somoim.repository;

import com.example.somoim.model.admin.AdminUser;
import com.example.somoim.model.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {

    @Query("update Member e set e.delete=true, e.modifyBy=?2, e.modifiedDate=?3 where e.memberSeq=?1")
    @Transactional
    @Modifying
     void softDelete(Long memberSeq, AdminUser adminUser, LocalDateTime localDateTime);

    @Query("update Member e set e.attendCountMonth=?2, e.attendCount=?3 where e.memberSeq=?1")
    @Transactional
    @Modifying
    void saveMemberAttend(Long memberSeq, Long attendCountMonth, Long attendCount);

    List<Member> findAllByOrderBySomoimJoinDateAsc();

   //안씀List<Member> findTop4ByOrderByAttendCountMonthDesc();

    List<Member> findTop4ByAttendCountMonthGreaterThanOrderByAttendCountMonthDesc(int attendCountMonth);

}
