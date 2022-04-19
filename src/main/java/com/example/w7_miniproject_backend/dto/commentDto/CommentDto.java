package com.example.w7_miniproject_backend.dto.commentDto;

import com.example.w7_miniproject_backend.domain.Post;
import lombok.*;

@Setter
@Getter
public class CommentDto {
    private String comments;
    private Long postId;

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private Long commentId;
        private String comments;
        private String createdAt;
        private String userName;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class commentResponse {
        private Long commentId;
        private String createdAt;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class commentUpdateResponse {
        private Long commentId;
        private String modifiedAt;
    }
}
