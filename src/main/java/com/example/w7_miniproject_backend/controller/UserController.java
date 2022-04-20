package com.example.w7_miniproject_backend.controller;

import com.example.w7_miniproject_backend.dto.userDto.SignupRequestDto;
import com.example.w7_miniproject_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //회원가입
    @PostMapping("/user/signup")
    public ResponseEntity<String> registerUser(@RequestBody SignupRequestDto signupRequestDto){
        return userService.registerUser(signupRequestDto);
    }

}
