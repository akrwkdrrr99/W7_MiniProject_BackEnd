package com.example.w7_miniproject_backend.controller;

import com.example.w7_miniproject_backend.domain.Post;
import com.example.w7_miniproject_backend.domain.Scrapbook;
import com.example.w7_miniproject_backend.dto.LikeDto;
import com.example.w7_miniproject_backend.dto.ScrapDto;
import com.example.w7_miniproject_backend.security.UserDetailsImpl;
import com.example.w7_miniproject_backend.service.ScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScrapController  {
    private final ScrapService scrapService;

    //스크랩 등록(스크랩 버튼)
    //버튼 누르면 post하고 scrap에 저장
    //스크랩 버튼 불린도 좋아요처럼 진행 하면 될듯?
    //스크랩북보기 누르면 get하고 scrap list싸버리기
    @PostMapping("/scrap/{postId}")
    public ScrapDto createScrap(@PathVariable Long postId, @RequestHeader("Authorization") String user) {
        return scrapService.createScrap(postId, user);
    }

    //userid와 postid로 찾아서 보내주장
    @GetMapping("/scrap")
    public List<Post> readScrap(@RequestHeader("Authorization") String user){
        return scrapService.readScrap(user);
    }

}