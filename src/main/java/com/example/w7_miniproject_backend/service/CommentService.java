package com.example.w7_miniproject_backend.service;

import com.example.w7_miniproject_backend.domain.Comment;
import com.example.w7_miniproject_backend.domain.Post;
import com.example.w7_miniproject_backend.domain.User;
import com.example.w7_miniproject_backend.dto.userDto.CommentDto;
import com.example.w7_miniproject_backend.repository.CommentRepository;
import com.example.w7_miniproject_backend.repository.PostRepository;
import com.example.w7_miniproject_backend.repository.UserRepository;
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

    public ResponseEntity registerComment(CommentDto commentDto, String username) {
        Post post = postRepository.findById(commentDto.getPost().getId()).orElseThrow(
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
                .commentId(comment.getId())
                .createdAt(commentFormmater(comment.getCreatedAt()))
                .build();

        return ResponseEntity.ok().body(commentResponseDto);
    }

    public ResponseEntity updateComment(Long commentId, CommentDto commentDto, String username) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NullPointerException("해당 글이 존재하지 않습니다."));
        if(!username.equals(comment.getUser().getUsername())) {
            throw new IllegalArgumentException("회원정보가 존재하지 안습니다.");
        }
        comment.update(commentDto);
        return ResponseEntity.ok().body("수정완료");
    }

    public ResponseEntity deleteComment(Long commentId, String username) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NullPointerException("댓글이 존재하지 않습니다."));
        User findUser = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("유효한 회원을 찾을 수 없습니다.")
        );
        validateCheckUser(findUser, comment);
        if(comment.getUser().getUsername().equals(findUser)) {
            // 댓글 삭제 후 삭제한 댓글의 ID 리턴
            HashMap<String, Long> responseId = new HashMap<>();
            responseId.put("commentId", comment.getId());
            commentRepository.deleteById(commentId);
            return ResponseEntity.ok().body("삭제 완료");
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