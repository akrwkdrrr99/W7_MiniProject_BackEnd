package com.example.w7_miniproject_backend.dto.postDto;

import com.example.w7_miniproject_backend.domain.Category;
import lombok.*;


public class PostRequestDto {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class SaveRequest {
        private String des;
        private Category category;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class PutRequest {
        private String des;
//        private String category;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class GetAll {
        private String des;
//        private String category;
    }
}
