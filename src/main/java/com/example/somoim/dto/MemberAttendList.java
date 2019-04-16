package com.example.somoim.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
public class MemberAttendList {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate memberAttendDate;
    @JsonProperty(value = "memberAttend")
    private List<MemberAttend> memberAttend;
    @JsonProperty(value = "memberAttendPlace")
    private String memberAttendPlace;
}
