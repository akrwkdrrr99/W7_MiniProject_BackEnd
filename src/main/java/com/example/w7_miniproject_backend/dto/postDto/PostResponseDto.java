package com.example.w7_miniproject_backend.dto.postDto;

import lombok.*;


public class PostResponseDto {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    public class MainResponse {
        private String roomimg;
        private String roomUrl;
        private String des;
        private int likes;
        private int scraps;
    }
}
