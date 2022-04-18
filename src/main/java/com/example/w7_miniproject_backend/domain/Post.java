package com.example.w7_miniproject_backend.domain;

import com.example.w7_miniproject_backend.dto.userDto.CategoryRequestDto;
import com.example.w7_miniproject_backend.dto.userDto.PostRequestDto;
import com.example.w7_miniproject_backend.security.UserDetailsImpl;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Post extends Timestamped {
//    statement.getGerenatedkey() 사용하면 데이터를 저장하면서 동시에 기본 키 값을 얻어와서 db를 한번만 조회 .
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    //columndefinition ="TEXT"로 해야하는가?
    @Column(columnDefinition = "TEXT")
    private String roomimg;

    @JsonIgnoreProperties({"post"})
    @OneToMany(mappedBy = " post", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private  List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
//ManyToOne으로 연관관계 생성시 자동으로 데이터를 참조한다
    @ManyToOne
    @JoinColumn(name= "user_id")
    private User user;
    //     포스트 정보
    private String des;
    // 사진 밑에 들어가는 문구

    public Post(PostRequestDto postRequestDto, User user, Category category){
        this.roomimg = postRequestDto.getRoomimg();
        this.category = category;
        this.user = user;
        this.des = postRequestDto.getDes();

    }

    public Post(String username, String roomimg, String des, String roomsize, String roomstyle, String space) {
        super();
    }

    public void update(PostRequestDto postRequestDto, UserDetailsImpl userDetails, CategoryRequestDto categoryRequestDto) {
    }





    // 굳이 like가 이곳에 저장 될필요가 없다.
    // 윤s dto를 따로 만들어서 전달
    // post는 굳이 누가 눌렀는지 확인할필요가 없다고 생각하는데
    // 좋아요 수를 따로 보내줄 때 따로 실어서 보내준다.
    // 엔티티에다가 굳이

//    likecnt
//    tags

//    private c

}
