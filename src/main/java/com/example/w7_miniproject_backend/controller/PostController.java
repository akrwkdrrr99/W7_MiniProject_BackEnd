package com.example.w7_miniproject_backend.controller;


import com.example.w7_miniproject_backend.domain.Category;
import com.example.w7_miniproject_backend.domain.Post;
import com.example.w7_miniproject_backend.dto.userDto.CategoryRequestDto;
import com.example.w7_miniproject_backend.dto.userDto.PostRequestDto;
import com.example.w7_miniproject_backend.repository.PostRepository;
import com.example.w7_miniproject_backend.security.UserDetailsImpl;
import com.example.w7_miniproject_backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Store;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;
//@AuthenticationPrincipal 로그인한 사용자 정보 받아오기  @RequestPart는 이미지를 가져올때 사용 @RequestBody 섞어도 되나?
//@RequestHeader 토큰에서 유저관련을 가져오는것
//아마존 s3에 들어가려면 멀티파인
    // 1 윤관을 잡는다
    // 2 포스트를 아마존 s3로 연결 하는 내용을 서치후 작업할 것.
//    @RequestPart("file")
//    MultipartFile multipartFile , @RequestPart("information") PostDto.SaveRequest postDto , @RequestHeader("Authorization") String user 관련 내용을 사용하여.

//    @PostMapping("/post/new")
//    public Post createPost(@RequestPart PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody CategoryRequestDto categoryRequestDto){
//
//        String username = userDetails.getUsername();
//        String roomimg = postRequestDto.getRoomimg(); // 룸이미지와 룸 url로 나눠서 받아야한다. 나누는게 더 손이 많이감 파일명 유알엘을 따로 가져가서 사용?
////        Category category = categoryRequestDto.getRoomsize();
//// 이걸 리스트로 저장하면 안되나?. 자동으로 저장이 되나? 질러줘야 하는부분인데
//        // 흐름을 프론트가 누르면 서비스로 가서 딱딱딱 한다.
//
//        String roomsize = categoryRequestDto.getRoomsize();
//        String roomstyle = categoryRequestDto.getRoomstyle();
//        String space = categoryRequestDto.getSpace();  // 서비스단으로 가져가서 꺼내 쓰는 방향으로 진행
//
//        return postService.createPost(postRequestDto, username, categoryRequestDto);
//
//    }
    @ResponseBody
    @PostMapping("/posts/new")  // 포스트 생성
    public Post createPost(@RequestPart PostRequestDto postRequestDto,
                           @AuthenticationPrincipal UserDetailsImpl userDetails,
                           @RequestBody CategoryRequestDto categoryRequestDto) {
        return postService.createPost(postRequestDto, userDetails, categoryRequestDto);
}
// 상세 진행은 서비스단으로 가져가서 꺼내 iomja쓰는 방향으로 진행

    @ResponseBody
    @GetMapping("/posts/{postId}") //특정 포스트 조회
    public List<Post> readPost(@RequestPart Post post){
        Long postId = post.getId();
        return postService.getPosts(postId);
    }


    @ResponseBody
    @PutMapping("/posts/{postId}")
    public Post updatePost(@PathVariable Long postId,
                           @RequestPart PostRequestDto postRequestDto,
                           @AuthenticationPrincipal UserDetailsImpl userDetails,
                           @RequestBody CategoryRequestDto categoryRequestDto){
        return postService.updatePost(postId, postRequestDto, userDetails, categoryRequestDto);


    }

//    @DeleteMapping("/posts/{postId}")
//    public String deletePost(@PathVariable Long postId){
//        postRepository.deleteById(postId);
//        return "포스트를 삭제함";
//    } 아니 삭제 왜 안됨?














}
