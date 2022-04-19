package com.example.w7_miniproject_backend.Enum;

public enum RoomOption {
    ONE_OFFI("원룸&오피스텔"),
    APARTMENT("아파트"),
    VILLA("빌라&연립"),
    SINGLE_HOUSE("단독주택"),
    OFFICE("사무공간"),
    COMMERCIAL("상업공간"),
    ETC("기타"),
    ONE_ROOM("원룸"),
    LIVING_ROOM("거실"),
    BED_ROOM("침실"),
    KITCHEN("주방"),
    BATH_ROOM("욕실"),
    KIDS_ROOM("아이방"),
    DRESS_ROOM("드레스룸"),
    LIBRARY("서재&작업실"),
    VERANDA("베란다"),
    OFFICE_2("사무공간"),
    COMMERCIAL_2("상업공간"),
    FURNITURE("가구&소품"),
    DOOR("현관"),
    OUTSIDE("외관&기타"),
    TEN_LOW("10평 미만"),
    TEN("10평대"),
    TWENTY("20평대"),
    THIRTY("30평대"),
    FORTY("40평대"),
    FIFTY("50평 이상"),
    MODERN("모던"),
    NORTHEU("북유럽"),
    VINTAGE("빈티지"),
    NATURAL("내추럴"),
    PROVINCE("프로방스&로맨틱"),
    CLASSIC("클래식&앤틱"),
    KOREA("한국&아시아"),
    UNIQUE("유니크");

    private String viewName;

    RoomOption(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }
}