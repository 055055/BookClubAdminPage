package com.example.somoim.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AdminUserDto {
    private String adminId;

    private String adminName;

    private String adminEmail;

    private String inputPassword;

    private String confirmPassword;
}