package com.example.somoim.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class MemberAttendHistoryCriteriaReq {
    //datatable key 값,  req/res 같은 draw 번호여야지 됨.
    @JsonProperty(value = "draw")
    private String draw;
    //몆번째 페이지
    @JsonProperty(value = "start")
    private Integer start;
    //랜더링 갯수
    @JsonProperty(value = "length")
    private Integer length;
}
