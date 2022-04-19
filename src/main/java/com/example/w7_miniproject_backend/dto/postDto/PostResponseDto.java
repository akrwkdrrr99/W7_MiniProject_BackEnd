package com.example.w7_miniproject_backend.dto.postDto;

import com.example.w7_miniproject_backend.domain.Category;
import com.example.w7_miniproject_backend.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


public class PostResponseDto {

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class MainResponse {
        private String userName;
        private String category;
        private String roomimg;
        private String roomUrl;
        private Long likeTotal;
        private Long scrapTotal;
        private String des;
        private String modifiedAt;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class DetailResponse {
        private String userName;
        private String category;
        private String roomimg;
        private String roomUrl;
        private Long likeTotal;
        private Long scrapTotal;
        private String des;
        private String modifiedAt;
        List<Comment> comment;
    }
}
