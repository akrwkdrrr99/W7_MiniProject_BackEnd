package com.example.todayerror.service;

import com.example.todayerror.domain.User;
import com.example.todayerror.dto.UserDto.SignupRequestDto;
import com.example.todayerror.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public boolean registerUser(SignupRequestDto signupRequestDto) {

        // nickname이 username
        String nickname = signupRequestDto.getNickname();

        if (userRepository.existsByUsername(nickname)) {
            throw new IllegalArgumentException("중복된 닉네임입니다.");
        }

        // 패스워드 암호화
        String enPassword = passwordEncoder.encode(signupRequestDto.getPassword());

        // 유저 생성 후 DB 저장
        User user = new User(signupRequestDto, enPassword);
        userRepository.save(user);

        return true;
    }

    // 닉네임 중복 확인
    public boolean nicknameCheck(String nickname) {
        return userRepository.existsByUsername(nickname);
    }
}