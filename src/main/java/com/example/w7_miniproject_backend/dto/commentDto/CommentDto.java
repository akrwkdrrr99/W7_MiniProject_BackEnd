package com.example.w7_miniproject_backend.dto.commentDto;

import com.example.w7_miniproject_backend.domain.Comment;
import com.example.w7_miniproject_backend.domain.Post;
import com.example.w7_miniproject_backend.dto.postDto.PostResponseDto;
import lombok.*;

import java.util.List;

@Setter
@Getter
public class CommentDto {
    private String comments;
    private Long postid;

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Getter
    public static class Response {
        private Long commentid;
        private String comments;
        private String createdAt;
        private String username;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Getter
    public static class commentResponse {
        private Long commentid;
        private String createdAt;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Getter
    public static class commentUpdateResponse {
        private Long commentid;
        private String modifiedAt;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Getter
    public static class listDto {
        private Long postid;
        private String nickname;
        private String comments;
    }
}