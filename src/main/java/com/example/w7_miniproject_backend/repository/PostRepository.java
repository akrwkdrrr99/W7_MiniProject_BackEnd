package com.example.w7_miniproject_backend.repository;

import com.example.w7_miniproject_backend.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

}
