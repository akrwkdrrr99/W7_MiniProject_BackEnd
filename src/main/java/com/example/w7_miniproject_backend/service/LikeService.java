package com.example.w7_miniproject_backend.service;

import com.example.w7_miniproject_backend.domain.Like;
import com.example.w7_miniproject_backend.domain.Post;
import com.example.w7_miniproject_backend.domain.User;
import com.example.w7_miniproject_backend.dto.LikeDto;
import com.example.w7_miniproject_backend.repository.LikeRepository;
import com.example.w7_miniproject_backend.repository.PostRepository;
import com.example.w7_miniproject_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public LikeDto registerLike(Long postId, String username){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다."));
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("유저가 없습니다."));
        Like like = Like.builder()
                .post(post)
                .user(user)
                .build();
        //있어? 지우고 0줘
        if (likeRepository.findByPostIdAndUsername(like)){
            likeRepository.deleteByPostIdAndUsername(like);
            Long cnt = likeRepository.countAllByPostId(postId);
            return LikeDto.builder()
                    .likebool(false)
                    .likecnt(cnt)
                    .build();
        }
        //없어? 만들고 1줘
        else {
            likeRepository.save(like);
            Long cnt = likeRepository.countAllByPostId(postId);
            return LikeDto.builder()
                    .likebool(true)
                    .likecnt(cnt)
                    .build();
        }

    }
}
