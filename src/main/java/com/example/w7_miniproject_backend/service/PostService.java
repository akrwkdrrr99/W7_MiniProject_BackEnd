package com.example.w7_miniproject_backend.service;

import com.example.w7_miniproject_backend.domain.Category;
import com.example.w7_miniproject_backend.domain.Post;
import com.example.w7_miniproject_backend.domain.User;
import com.example.w7_miniproject_backend.dto.userDto.CategoryRequestDto;
import com.example.w7_miniproject_backend.dto.userDto.PostRequestDto;
import com.example.w7_miniproject_backend.repository.PostRepository;
import com.example.w7_miniproject_backend.repository.UserRepository;
import com.example.w7_miniproject_backend.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Store;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

//// 흐름을 프론트가 누르면 서비스로 가서 딱딱딱 한다.
    @Transactional
    public Post createPost(@RequestPart PostRequestDto postRequestDto,
                           @AuthenticationPrincipal UserDetailsImpl userDetails,
                           @RequestBody CategoryRequestDto categoryRequestDto) {

        String username = userDetails.getUsername();
        String roomimg = postRequestDto.getRoomimg();
        String des = postRequestDto.getDes();
        // 이걸 리스트로 저장하면 안되나?. 자동으로 저장이 되나? 질러줘야 하는부분인데
        String roomsize = categoryRequestDto.getRoomsize();
        String roomstyle = categoryRequestDto.getRoomstyle();
        String space = categoryRequestDto.getSpace();

        Post createPost = new Post(username, roomimg, des, roomsize, roomstyle, space);

        postRepository.save(createPost);

        return createPost;
    }

    public List<Post> getPosts(Long postId){
        return postRepository.findAllBypostid(postId);
    }

    public Post updatePost(Long postId, PostRequestDto postRequestDto,
                           UserDetailsImpl userDetails,
                           CategoryRequestDto categoryRequestDto){
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("그런 포스트는 없어요")
        );
        post.update(postRequestDto, userDetails, categoryRequestDto);
        postRepository.save(post);
        return post;
    }









}






//        Long storeId = commentDto.getStoreId();
//        String userName = commentDto.getUserName();
//        String comment = commentDto.getComment();
//
//        Comment createComment = new Comment(storeId, userName, comment);
//
//        commentRepository.save(createComment);
//        return createComment;

//    public Post createPost(PostRequestDto postRequestDto, String username, CategoryRequestDto categoryRequestDto){
//        User user = userRepository.findByUsername(username).orElseThrow(()
//                -> new IllegalArgumentException("이게 뜰일이 있나?.")); //토큰이 만료되었을때를 대비하여 있어야한다.
//
//        Post post = new Post(postRequestDto, user, categoryRequestDto);
//        return postRepository.save(post);
//
//    }
//        String username = userDetails.getUsername();
//        String roomimg = postRequestDto.getRoomimg(); // 룸이미지와 룸 url로 나눠서 받아야한다. 나누는게 더 손이 많이감 파일명 유알엘을 따로 가져가서 사용?
////        Category category = categoryRequestDto.getRoomsize();
//// 이걸 리스트로 저장하면 안되나?. 자동으로 저장이 되나? 질러줘야 하는부분인데
//
//
//        String roomsize = categoryRequestDto.getRoomsize();
//        String roomstyle = categoryRequestDto.getRoomstyle();
//        String space = categoryRequestDto.getSpace();
//
//        return postService.createPost(postRequestDto, username, categoryRequestDto);



