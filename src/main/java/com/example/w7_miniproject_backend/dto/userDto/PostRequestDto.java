package com.example.w7_miniproject_backend.dto.userDto;

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

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class originRequest {
        private String roomsize;
        private String roomstyle;
        private String space;
        private String des;
    }

}

