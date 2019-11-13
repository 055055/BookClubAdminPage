package com.example.somoim.repository;

import com.example.somoim.model.admin.AdminUser;
import com.example.somoim.model.member.Member;
import com.example.somoim.model.member.MemberAttendHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MemberAttendHisRepository extends JpaRepository<MemberAttendHistory,Long> {
   @Query(value = "select * from"+
           "(select month,count(*) as membercount from" +
           " (select TO_CHAR(MEMBER_ATTEND_DAY,'yyyy-mm') as month from MEMBER_ATTEND_HISTORY where delete = false ) a " +
           "group by a.month "+
           "order by a.month desc "+
           "LIMIT  3) b "+
           "order by b.month", nativeQuery = true)
   List<Map<String,Integer>> findMonthlyRate();


   @Query("update MemberAttendHistory e set e.delete=true, e.modifyBy=?2, e.modifiedDate=?3 where e.memberAttendHisSeq=?1")
   @Transactional
   @Modifying
   void softDelete(Long memberAttendHisSeq, AdminUser adminUser, LocalDateTime localDateTime);

   Optional<List<MemberAttendHistory>> findByMemberOrderByMemberAttendHisSeqDesc(Member member);
}
