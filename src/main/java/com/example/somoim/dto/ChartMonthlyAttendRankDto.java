package com.example.somoim.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@Builder
@Getter
@ToString
public class ChartMonthlyAttendRankDto {
    @JsonProperty("memberName")
    private String memberName;
    @JsonProperty("memberCount")
    private int      memberCount;

}
