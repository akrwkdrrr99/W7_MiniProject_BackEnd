package com.example.w7_miniproject_backend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Liken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Post post;

    @ManyToOne
    @JoinColumn(name= "user_id")
    private User user;


    //repostitory countby postid
}
// 1번라이크는 누가 좋아요 2번에는 또 다른 누군가
// 좋아요를 나중에
// 좋아요를 눌렀으면 좋아요가 있는지 유저가 해당 아이디의 좋아요를 눌렀는지 확인 후 상태를 확인할수 있게 불린 값으로 판단후 추가로 넣든지 말든지 한다.
