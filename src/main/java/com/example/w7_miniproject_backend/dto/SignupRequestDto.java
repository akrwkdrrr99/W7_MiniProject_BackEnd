package com.example.w7_miniproject_backend.dto;

import lombok.Getter;

@Getter
public class SignupRequestDto {
    private String nickname;
    private String password;
    private String passwordCheck;
}