package com.example.somoim.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
@Setter
public class MemberPwdResetDto {

    @NotBlank(message = "ID를 입력해주세요.")
    private String userId;

    @NotBlank(message = "이메일을 입력해주세요.")
    private String userEmail;



}
