package com.example.w7_miniproject_backend.dto.postDto;

import lombok.*;


public class PostRequestDto {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class SaveRequest {
        private String des;
    }
}
