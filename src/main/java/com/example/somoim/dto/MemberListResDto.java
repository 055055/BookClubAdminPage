package com.example.somoim.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@ToString
public class MemberListResDto {

    @JsonProperty(value = "memberSeq")
    private Long memberSeq;

    @JsonProperty(value = "memberName")
    private String memberName;

    @JsonProperty(value = "memberNickName")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String memberNickName;

    @JsonProperty(value = "attendCount")
    private Long attendCount;

    @JsonProperty(value = "attendCountMonth")
    private Long attendCountMonth;

    @JsonProperty(value = "lastAttend")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDate lastAttend;

    @JsonProperty(value = "somoimJoinDate")
    private LocalDate somoimJoinDate;

    @JsonProperty(value = "regDate")
    private LocalDateTime regDate;
}
