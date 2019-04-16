package com.example.somoim.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberAttend {
    @JsonProperty(value = "memberSeq")
    private Long memberSeq;
    @JsonProperty(value = "attendCount")
    private Long attendCount;
    @JsonProperty(value = "attendCountMonth")
    private Long attendCountMonth;
}
