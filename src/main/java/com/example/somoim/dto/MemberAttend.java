package com.example.somoim.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class MemberAttend {
    @NonNull
    @JsonProperty(value = "memberSeq")
    private Long memberSeq;
    @JsonProperty(value = "attendCount")
    private Long attendCount;
    @JsonProperty(value = "attendCountMonth")
    private Long attendCountMonth;
}
