package com.example.todayerror.dto.UserDto;

import lombok.Getter;

@Getter
public class SignupRequestDto {
    private String nickname;
    private String password;
    private String passwordCheck;
}