package com.example.somoim.repository;

import com.example.somoim.model.member.MemberAttendHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface MemberAttendHisRepository extends JpaRepository<MemberAttendHistory,Long> {
   @Query(value = "select month,count(*) as membercount from" +
           " (select TO_CHAR(MEMBER_ATTEND_DAY,'yyyy-mm') as month from MEMBER_ATTEND_HISTORY) a " +
           "group by a.month "+
           "order by a.month asc " +
           "LIMIT  6", nativeQuery = true)
   List<Map<String,Integer>> findMonthlyRate();
}
