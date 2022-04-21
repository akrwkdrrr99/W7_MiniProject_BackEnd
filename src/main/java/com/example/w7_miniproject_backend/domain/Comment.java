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
    private Long userid;

    @ManyToOne
    @JoinColumn(name="post_id", nullable = false)
    private Long postid;

    private String comments;

    public void update(CommentDto commentDto) {
        this.comments = commentDto.getComments();
    }
}
