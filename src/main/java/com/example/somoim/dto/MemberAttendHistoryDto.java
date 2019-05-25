package com.example.somoim.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class MemberAttendHistoryDto {
    @JsonProperty("memberAttendHisSeq")
    private Long memberAttendHisSeq;

    @JsonProperty("memberSeq")
    private Long memberSeq;

    @JsonProperty("memberName")
    private String memberName;

    @JsonProperty("memberAttendDay")
    private LocalDate memberAttendDay;

    @JsonProperty("memberAttendPlace")
    private String memberAttendPlace;

}
