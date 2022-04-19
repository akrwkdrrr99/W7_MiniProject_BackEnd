package com.example.w7_miniproject_backend.domain;

import com.example.w7_miniproject_backend.enums.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Category {
//카테고리도 createcategory로 service만들기?

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Convert(converter = ResidenceConverter.class)
    private Residence residence;

    @Convert(converter = SizeConverter.class)
    private Size roomsize;

    @Convert(converter = SpaceConverter.class)
    private Space space;

    @Convert(converter = StyleConverter.class)
    private Style roomstyle;
}
