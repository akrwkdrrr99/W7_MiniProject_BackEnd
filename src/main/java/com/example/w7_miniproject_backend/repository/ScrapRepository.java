package com.example.w7_miniproject_backend.repository;

import com.example.w7_miniproject_backend.domain.Post;
import com.example.w7_miniproject_backend.domain.Scrapbook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScrapRepository extends JpaRepository<Scrapbook, Long> {
    boolean findByPostIdAndUsername(Scrapbook scrap);

    void deleteByPostIdAndUsername(Scrapbook scrap);
    Long countAllByPostId(Long postId);
    List<Scrapbook> findAllByUsername(String username);
}
