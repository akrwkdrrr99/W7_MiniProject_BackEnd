package com.example.w7_miniproject_backend.repository;

import com.example.w7_miniproject_backend.domain.Like;
import com.example.w7_miniproject_backend.domain.Post;
import com.example.w7_miniproject_backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like,Long> {
    Optional<Like> findByUserAndPost(User user, Post post);
    void deleteByPostId(Post post);
    Long countAllByPostId(Long id);
}
