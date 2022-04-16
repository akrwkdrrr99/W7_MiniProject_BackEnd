package com.example.w7_miniproject_backend.repository;

import com.example.w7_miniproject_backend.domain.Like;
import com.example.w7_miniproject_backend.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like,Long> {
    Boolean findByPostIdAndUsername(Like like);
    void deleteByPostIdAndUsername(Like like);
    Long countAllByPostId(Long id);
}

