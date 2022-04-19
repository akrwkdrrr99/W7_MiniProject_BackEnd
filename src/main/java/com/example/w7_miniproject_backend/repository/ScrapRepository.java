package com.example.w7_miniproject_backend.repository;

import com.example.w7_miniproject_backend.domain.Post;
import com.example.w7_miniproject_backend.domain.Scrapbook;
import com.example.w7_miniproject_backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScrapRepository extends JpaRepository<Scrapbook, Long> {
    Optional<Scrapbook> findByPostAndUser(Post post, User user);
    void deleteByPostAndUser(Post post, User user);
    Long countAllByPostId(Long postId);
    List<Scrapbook> findAllByUser(User user);
}