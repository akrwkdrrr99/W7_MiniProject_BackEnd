package com.example.w7_miniproject_backend.repository;

import com.example.w7_miniproject_backend.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>  {
    List<Comment> findByPostId(Long postId);
}
