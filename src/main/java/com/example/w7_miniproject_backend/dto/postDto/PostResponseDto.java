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
        private Long id;
        private String nickname;
        private String category;
        private String roomimg;
        private String roomurl;
        private Long liketotal;
        private Long scraptotal;
        private String des;
        private String modifiedAt;
//        private Category category;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class DetailResponse {
        private String nickname;
        private String category;
        private String roomimg;
        private String roomurl;
        private Long liketotal;
        private Long scraptotal;
        private String des;
        private String modifiedAt;
//        private Category category;
        List<Comment> comment;
    }
}
