package com.example.somoim.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@ToString
@Getter
@Setter
@Builder
public class MemberAttendHistoryCriteriaRes {
    //datatable key 값,  req/res 같은 draw 번호여야지 됨.
    @JsonProperty(value = "draw")
    private String draw;
    @JsonProperty(value = "recordsTotal")
    private long recordsTotal;
    @JsonProperty(value = "recordsFiltered")
    private long recordsFiltered;
    @JsonProperty(value = "data")
    private List<MemberAttendHistoryDto> data;

}
