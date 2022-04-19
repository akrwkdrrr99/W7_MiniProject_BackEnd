package com.example.w7_miniproject_backend.controller;


import com.example.w7_miniproject_backend.domain.Post;
import com.example.w7_miniproject_backend.dto.userDto.PostRequestDto;
import com.example.w7_miniproject_backend.dto.userDto.PostResponseDto;
import com.example.w7_miniproject_backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("/post/new")
    public ResponseEntity postSave(@RequestPart("file") MultipartFile multipartFile ,
                                   @RequestPart("jsons") PostRequestDto.SaveRequest postDto ,
                                   @RequestHeader("Authorization") String user){
        return postService.save(multipartFile , postDto , user);
    }

// 상세 진행은 서비스단으로 가져가서 꺼내 iomja쓰는 방향으로 진행

    @GetMapping("/")
    public ResponseEntity<PostResponseDto> getAllPost(){ return postService.getAllPost();}

    @ResponseBody
    @GetMapping("/posts/{postId}") //특정 포스트 조회
    public List<Post> readPost(@RequestPart Post post){
        Long postId = post.getId();
        return postService.getPosts(postId);
    }


//    @ResponseBody
//    @PutMapping("/posts/{postId}")
//    public Post updatePost(@PathVariable Long postId,
//                           @RequestPart PostRequestDto postRequestDto,
//                           @AuthenticationPrincipal UserDetailsImpl userDetails){
//        return postService.updatePost(postId, postRequestDto, userDetails);
//
//
//    }

//    @DeleteMapping("/posts/{postId}")
//    public String deletePost(@PathVariable Long postId){
//        postRepository.deleteById(postId);
//        return "포스트를 삭제함";
//    } 아니 삭제 왜 안됨?














}
