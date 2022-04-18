package com.example.w7_miniproject_backend.repository;

import com.example.w7_miniproject_backend.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBypostid(Long post);

}
