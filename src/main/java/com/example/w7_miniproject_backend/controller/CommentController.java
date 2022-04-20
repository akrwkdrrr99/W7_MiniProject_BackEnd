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
    //댓글작성
    @PostMapping("/comment")
    public ResponseEntity registerComment(@RequestBody CommentDto commentDto,
                                          @RequestHeader("Authorization") String user
    ){
        return commentService.registerComment(commentDto, user);
    }

    //댓글수정
    @PutMapping("/comment/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable Long commentId,
                                        @RequestBody CommentDto commentDto,
                                        @RequestHeader("Authorization") String user
    ) {
        return commentService.updateComment(commentId, commentDto, user);
    }

    //댓글삭제
    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId,
                                        @RequestHeader("Authorization") String user
                                        ){
        return commentService.deleteComment(commentId, user);
    }
}
