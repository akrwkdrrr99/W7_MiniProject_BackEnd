package com.example.w7_miniproject_backend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, columnDefinition = "a")
    private String roomsize;

    @Column(nullable = false, columnDefinition = "b")
    private String roomstyle;

    @Column(nullable = false, columnDefinition = "c")
    private String space;



}
