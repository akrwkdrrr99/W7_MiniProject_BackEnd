package com.example.w7_miniproject_backend.service;

import com.example.w7_miniproject_backend.domain.Post;
import com.example.w7_miniproject_backend.domain.User;
import com.example.w7_miniproject_backend.dto.userDto.PostRequestDto;
import com.example.w7_miniproject_backend.dto.userDto.PostResponseDto;
import com.example.w7_miniproject_backend.repository.LikeRepository;
import com.example.w7_miniproject_backend.repository.PostRepository;
import com.example.w7_miniproject_backend.repository.UserRepository;
import com.example.w7_miniproject_backend.security.jwt.JwtDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class PostService {
    private final AwsS3Service awsS3Service;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final JwtDecoder jwtDecoder;

    @Transactional
    public ResponseEntity save(MultipartFile multipartFile, PostRequestDto.SaveRequest postDto, String user) {
        String username = jwtDecoder.decodeUsername(user);
//        PostValidator.validatePostSaveRegister(postDto, multipartFile, username);
        Map<String, String> map = awsS3Service.uploadFile(multipartFile);
        User joinUser = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("유효한 회원을 찾을 수 없습니다."));
        Post post = Post.builder()
                .roomimg(map.get("fileName"))
                .roomUrl(map.get("url"))
                .des(postDto.getDes())
                .user(joinUser)
                .build();
        postRepository.save(post);
        // 추가로 카테고리 저장.
        return ResponseEntity.ok().body("등록 완료");
    }

/*
    //// 흐름을 프론트가 누르면 서비스로 가서 딱딱딱 한다.
    @Transactional
    public Post createPost(@RequestPart PostRequestDto postRequestDto,
                           @AuthenticationPrincipal UserDetailsImpl userDetails
                          ) {
//파트랑 바디랑 둘다 못불러온다.
        String username = userDetails.getUsername();
        String roomimg = postRequestDto.getRoomimg();
        String des = postRequestDto.getDes();
        // 이걸 리스트로 저장하면 안되나?. 자동으로 저장이 되나? 질러줘야 하는부분인데
        String roomsize = postRequestDto.getRoomsize();
        String roomstyle = postRequestDto.getRoomstyle();
        String space = postRequestDto.getSpace();
        Category category  = new Category(roomsize, roomstyle, space);
        Post createPost = new Post(username, roomimg, des, category);

        categoryRepository.save(category);
        postRepository.save(createPost);

        return createPost;
    }*/

    public List<Post> getPosts(Long postId){
        return postRepository.findAllById(postId);
    }

//    public Post updatePost(Long postId, PostRequestDto postRequestDto,
//                           UserDetailsImpl userDetails){
//
//        Post post = postRepository.findById(postId).orElseThrow(
//                () -> new NullPointerException("그런 포스트는 없어요")
//        );
//
//        post.update(postRequestDto, userDetails);
////        postRepository.save(post); // 생략이 가능 쓸필요 X 53번줄이 들어갈거면 굳이 save가 들어갈 필요가 없음.
//        return post;
//    }
    public ResponseEntity<PostResponseDto> getAllPost() {
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        List<PostResponseDto> postReponse = new ArrayList<>();
        for (Post post : posts) {
            Long LikeTotal = likeRepository.countAllByPostId(post.getId());
            PostResponseDto postDto = PostResponseDto
                    .builder()
                    .userName(post.getUser().getUsername())
                    .des(post.getDes())
                    .modifiedAt(formmater(post.getModifiedAt()))
                    .roomUrl(post.getRoomUrl())
                    .likeTotal(LikeTotal)
                    .build();
            postReponse.add(postDto);
        }
        return new ResponseEntity(postReponse, HttpStatus.OK);
    }

    public String formmater(LocalDateTime localDateTime) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDateTime);
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



