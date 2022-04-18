package com.example.w7_miniproject_backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post extends Timestamped {
//    statement.getGerenatedkey() 사용하면 데이터를 저장하면서 동시에 기본 키 값을 얻어와서 db를 한번만 조회 .
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    //columndefinition ="TEXT"로 해야하는가?
    @Column(columnDefinition = "TEXT")
    private String roomimg;
    private String roomUrl;

    @JsonIgnoreProperties({"post"})
    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name= "user_id")
    private User user;
    //     포스트 정보
    private String des;

}
