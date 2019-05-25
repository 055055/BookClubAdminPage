package com.example.somoim.repository;

import com.example.somoim.model.MemberAttendHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberAttendHisRepository extends JpaRepository<MemberAttendHistory,Long> {
}
