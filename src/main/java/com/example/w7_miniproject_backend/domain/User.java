package com.example.w7_miniproject_backend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class User extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column
    private String nickname;

    @Column(nullable = false)
    private String password;


    public User(String username, String encodedPassword, String nickname) {
        this.username = username;
        this.password = encodedPassword;
        this.nickname = nickname;
    }

}
