package com.example.w7_miniproject_backend.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LikeDto {
    private boolean likebool;
    private Long likecnt;
}
