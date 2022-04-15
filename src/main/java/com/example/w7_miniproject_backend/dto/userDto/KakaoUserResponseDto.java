package com.example.todayerror.dto.UserDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class KakaoUserResponseDto {
    private String JWtToken;
    private String nickname;
    private boolean result;
}
