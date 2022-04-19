package com.example.w7_miniproject_backend.controller;


import com.example.w7_miniproject_backend.dto.LikeDto;
import com.example.w7_miniproject_backend.security.UserDetailsImpl;
import com.example.w7_miniproject_backend.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/like/{postId}")
    public LikeDto registerLike(@PathVariable Long postId,
                                @RequestHeader("Authorization") String user){
        return likeService.registerLike(postId,user);
    }
}

