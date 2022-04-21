package com.example.w7_miniproject_backend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Builder
@Entity
@AllArgsConstructor
public class Scrapbook extends Timestamped{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @ManyToOne
    private Post post;

    @ManyToOne
    @JoinColumn(name= "user_id")
    private User user;
}