package com.example.w7_miniproject_backend.service;

import com.example.w7_miniproject_backend.domain.Category;
import com.example.w7_miniproject_backend.domain.Comment;
import com.example.w7_miniproject_backend.domain.Post;
import com.example.w7_miniproject_backend.domain.User;
import com.example.w7_miniproject_backend.dto.postDto.PostRequestDto;
import com.example.w7_miniproject_backend.dto.postDto.PostResponseDto;
import com.example.w7_miniproject_backend.repository.*;
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

@Service
@RequiredArgsConstructor
public class PostService {
    private final AwsS3Service awsS3Service;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;
    private final CategoryRepository categoryRepository;
    private final ScrapRepository scrapRepository;
    private final JwtDecoder jwtDecoder;

    @Transactional
    public ResponseEntity save(MultipartFile multipartFile, PostRequestDto.SaveRequest postDto, String user) {
        String username = jwtDecoder.decodeUsername(user);
//        PostValidator.validatePostSaveRegister(postDto, multipartFile, username);
        Map<String, String> map = awsS3Service.uploadFile(multipartFile);
        User joinUser = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("유효한 회원을 찾을 수 없습니다."));
//        Category category = postDto.getCategory();
        Post post = Post.builder()
                .roomimg(map.get("fileName"))
                .roomurl(map.get("url"))
                .des(postDto.getDes())
                .user(joinUser)
//                .category(category)
                .build();
        postRepository.save(post);
        // 추가로 카테고리 저장.
        return ResponseEntity.ok().body("등록 완료");
    }

    public ResponseEntity<PostResponseDto.DetailResponse> getPostDeatils(Long postId, String user) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("찾으시는 게시물이 존재하지 않습니다.")
        );
        String username = jwtDecoder.decodeUsername(user);
        User joinUser = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("유효한 회원을 찾을 수 없습니다."));
        List<Comment> commentList = commentRepository.findByPostId(postId);
//        Category category =
        Long Likes = 0L;
        Long Scraps = 0L;
        if (likeRepository.findByUserAndPost(joinUser, post).orElse(null) == null){
            Likes = likeRepository.countAllByPostId(postId);
        }

        if (scrapRepository.findByPostAndUser(post, joinUser).orElse(null) == null){
            Scraps = scrapRepository.countAllByPostId(postId);
        }

        PostResponseDto.DetailResponse detailDto = PostResponseDto.DetailResponse
                .builder()
                .nickname(post.getUser().getUsername())
                .des(post.getDes())
                .modifiedAt(formmater(post.getModifiedAt()))
                .roomurl(post.getRoomurl())
                .liketotal(Likes)
                .scraptotal(Scraps)
//                .category()
                .comment(commentList)
                .build();
        return new ResponseEntity(detailDto, HttpStatus.OK);
    }

    public ResponseEntity<PostResponseDto.MainResponse> getAllPost() {
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        List<PostResponseDto.MainResponse> postReponse = new ArrayList<>();
        for (Post post : posts) {
            Long LikeTotal = likeRepository.countAllByPostId(post.getId());
            Long ScrapTotal = scrapRepository.countAllByPostId(post.getId());
            PostResponseDto.MainResponse postDto = PostResponseDto.MainResponse
                    .builder()
                    .id(post.getId())
                    .nickname(post.getUser().getUsername())
                    .des(post.getDes())
                    .modifiedAt(formmater(post.getModifiedAt()))
                    .roomurl(post.getRoomurl())
                    .liketotal(LikeTotal)
                    .scraptotal(ScrapTotal)
//                    .category()
                    .build();
            postReponse.add(postDto);
        }
        return new ResponseEntity(postReponse, HttpStatus.OK);
    }

    public String formmater(LocalDateTime localDateTime) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDateTime);
    }
}

