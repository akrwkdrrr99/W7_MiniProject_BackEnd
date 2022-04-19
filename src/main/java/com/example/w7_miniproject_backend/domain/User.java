package com.example.w7_miniproject_backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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


    //user를 삭제하는 기능이 없기때문에, post랑 comment onetomany cascade remove 필요없음!
//    //게시물 조회 할때쓸거임
//    @JsonIgnoreProperties({"user"})
//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
//    private List<Post> posts;

    public User(String username, String encodedPassword, String nickname) {
        this.username = username;
        this.password = encodedPassword;
        this.nickname = nickname;
    }

}
