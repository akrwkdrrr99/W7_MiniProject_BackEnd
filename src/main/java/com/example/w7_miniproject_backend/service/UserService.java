package com.example.w7_miniproject_backend.service;

import com.example.w7_miniproject_backend.domain.User;
import com.example.w7_miniproject_backend.dto.userDto.SignupRequestDto;
import com.example.w7_miniproject_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public ResponseEntity<String> registerUser(SignupRequestDto signupRequestDto) {

        // username
        String username = signupRequestDto.getUsername();

        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("중복된 닉네임입니다.");
        }

        // 패스워드 암호화
        String enPassword = passwordEncoder.encode(signupRequestDto.getPassword());

        //닉네임 추가
        String nickname = signupRequestDto.getNickname();
        // 유저 생성 후 DB 저장
        User user = new User(username, enPassword, nickname);
        userRepository.save(user);

        return ResponseEntity.ok().body("회원가입 완료");
    }

    // 닉네임 중복 확인
    public ResponseEntity nicknameCheck(String username) {
        return  ResponseEntity.ok().body(userRepository.existsByUsername(username));
    }
}