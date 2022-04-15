package com.example.w7_miniproject_backend.repository;

import com.example.w7_miniproject_backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String nickname);

    Optional<User> findByKakaoId(Long kakaoId);
    Optional<User> findByUsername(String username);
}