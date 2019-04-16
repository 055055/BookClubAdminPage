package com.example.somoim.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Getter
@Setter
@ToString
public class MemberDto {

    private String memberName;

    private String memberNickName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate memberJoinDate;
}
