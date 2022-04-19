package com.example.w7_miniproject_backend.domain;

import com.example.w7_miniproject_backend.dto.commentDto.CommentDto;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Comment extends Timestamped{

    @Id
    //user와 comment generationtype설정 확인!
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name= "user_id")
    private User user;

    @ManyToOne
    private Post post;

    private String comments;

    public void update(CommentDto commentDto) {
        this.comments = commentDto.getComments();
    }
}
