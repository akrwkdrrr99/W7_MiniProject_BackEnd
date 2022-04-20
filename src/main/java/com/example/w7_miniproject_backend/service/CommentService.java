package com.example.w7_miniproject_backend.service;

import com.example.w7_miniproject_backend.domain.Comment;
import com.example.w7_miniproject_backend.domain.Post;
import com.example.w7_miniproject_backend.domain.User;
import com.example.w7_miniproject_backend.dto.commentDto.CommentDto;
import com.example.w7_miniproject_backend.repository.CommentRepository;
import com.example.w7_miniproject_backend.repository.PostRepository;
import com.example.w7_miniproject_backend.repository.UserRepository;
import com.example.w7_miniproject_backend.security.jwt.JwtDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final JwtDecoder jwtDecoder;


    public ResponseEntity registerComment(CommentDto commentDto, String usernameDecode) {
        String username = jwtDecoder.decodeUsername(usernameDecode);
        Post post = postRepository.findById(commentDto.getPostid()).orElseThrow(
                () -> new NullPointerException("게시글이 존재하지 않습니다.")
        );
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NullPointerException("회원이 아닙니다."));

        Comment comment = Comment.builder()
                .comments(commentDto.getComments())
                .post(post)
                .user(user)
                .build();
        commentRepository.save(comment);

        CommentDto.commentResponse commentResponseDto = CommentDto.commentResponse.builder()
                .commentid(comment.getId())
                .createdAt(commentFormmater(comment.getCreatedAt()))
                .build();

        return ResponseEntity.ok().body(commentResponseDto);
    }

    public ResponseEntity<String> updateComment(Long commentId, CommentDto commentDto, String usernameDecode) {
        String username = jwtDecoder.decodeUsername(usernameDecode);
        System.out.println(commentDto.getComments());
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NullPointerException("해당 글이 존재하지 않습니다."));
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("유효한 회원을 찾을 수 없습니다."));

        if(!comment.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("회원정보가 존재하지 안습니다.");
        }

        comment.update(commentDto);
        commentRepository.save(comment);
        return ResponseEntity.ok().body("수정완료");
    }

    public ResponseEntity<String> deleteComment(Long commentId, String usernameDecode) {
        String username = jwtDecoder.decodeUsername(usernameDecode);
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NullPointerException("댓글이 존재하지 않습니다."));
        User findUser = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("유효한 회원을 찾을 수 없습니다.")
        );
//        validateCheckUser(findUser, comment);
        if(comment.getUser().getId().equals(findUser.getId())) {
            // 댓글 삭제 후 삭제한 댓글의 ID 리턴
            HashMap<String, Long> responseId = new HashMap<>();
            responseId.put("commentid", comment.getId());
            commentRepository.deleteById(commentId);
            return ResponseEntity.ok("삭제 완료");
        } else {
            throw new IllegalArgumentException("삭제 실패");
        }
    }

    public String commentFormmater(LocalDateTime localDateTime){
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDateTime);
    }

    private void validateCheckUser(User user, Comment comment) {
        if (!user.equals(comment.getUser())){
            throw new IllegalArgumentException("권한이 없는 유저 입니다.");
        }
    }
}
