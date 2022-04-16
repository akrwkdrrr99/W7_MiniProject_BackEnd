package com.example.w7_miniproject_backend.controller;

import com.example.w7_miniproject_backend.dto.commentDto.CommentDto;
import com.example.w7_miniproject_backend.security.jwt.JwtDecoder;
import com.example.w7_miniproject_backend.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final JwtDecoder jwtDecoder;
    //댓글작성
    @PostMapping("/comment")
    public ResponseEntity registerComment(@RequestBody CommentDto commentDto,
                                          @RequestHeader("Authorization") String user
    ){
        // 로그인 되어 있는 ID
        if (user != null) {
            String username = jwtDecoder.decodeUsername(user);
            return commentService.registerComment(commentDto, username);
        }
        return new ResponseEntity("실패", HttpStatus.BAD_REQUEST);
    }

    //댓글수정
    @PutMapping("/comment/{commentId}")
    public ResponseEntity updateComment(@PathVariable Long commentId,
                                        @RequestBody CommentDto commentDto,
                                        @RequestHeader("Authorization") String user
    ) {
        String username = jwtDecoder.decodeUsername(user);
        return commentService.updateComment(commentId, commentDto, username);
    }

    //댓글삭제
    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity deleteComment(@PathVariable Long commentId,
                                        @RequestHeader("Authorization") String user
                                        ){
        String username = jwtDecoder.decodeUsername(user);
        return commentService.deleteComment(commentId, username);
    }
}
