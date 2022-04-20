package com.example.w7_miniproject_backend.domain;

import com.example.w7_miniproject_backend.dto.commentDto.CommentDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name= "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="post_id", nullable = false)
    private Post post;

    private String comments;

    public void update(CommentDto commentDto) {
        this.comments = commentDto.getComments();
    }
}
