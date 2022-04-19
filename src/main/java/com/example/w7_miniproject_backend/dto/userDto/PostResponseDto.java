package com.example.w7_miniproject_backend.dto.userDto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PostResponseDto {

        private String userName;
        private String category;
        private String roomimg;
        private String roomUrl;
        private Long likeTotal;
        private Long scrapTotal;
        private String des;
        private String modifiedAt;

}