package com.example.w7_miniproject_backend.controller;

import com.example.w7_miniproject_backend.dto.postDto.PostRequestDto;
import com.example.w7_miniproject_backend.dto.postDto.PostResponseDto;
import com.example.w7_miniproject_backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public ResponseEntity<PostResponseDto.MainResponse> getAllPost(){
        return postService.getAllPost();
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostResponseDto.DetailResponse> getPostDetails(@PathVariable(value="postId") Long postId,
                                                           @RequestHeader("Authorization") String user
    ){
        return new ResponseEntity(postService.getPostDeatils(postId, user) , HttpStatus.OK);
    }

    @PostMapping("/post/new")
    public ResponseEntity postSave(@RequestPart("file") MultipartFile multipartFile ,
                                   @RequestPart("jsons") PostRequestDto.SaveRequest postDto ,
                                   @RequestHeader("Authorization") String user){
        return postService.save(multipartFile , postDto , user);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<HttpStatus> postUpdate(@PathVariable Long postId ,
                                                 @RequestPart("file") MultipartFile multipartFile,
                                                 @RequestPart("jsons") PostRequestDto.PutRequest postDto,
                                                 @RequestHeader("Authorization") String user){
        return postService.update(postId , multipartFile , postDto , user);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<HttpStatus> postDelete(@PathVariable Long postId , @RequestHeader("Authorization") String user){
        return postService.delete(postId , user);
    }

}
