package com.example.w7_miniproject_backend.dto.userDto;

import lombok.Getter;

@Getter
public class SignupRequestDto {
    private String username;
    private String password;
    private String passwordCheck;
}