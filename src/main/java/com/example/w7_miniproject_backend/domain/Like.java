package com.example.w7_miniproject_backend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name= "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name= "user_id")
    private User user;

    //repostitory countbypostid
}
