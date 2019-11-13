package com.example.somoim.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
public class MemberAttendList {
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate memberAttendDate;

    @NotEmpty
    @JsonProperty(value = "memberAttend")
    private List<MemberAttend> memberAttend;

    @NotBlank
    @JsonProperty(value = "memberAttendPlace")
    private String memberAttendPlace;
}
