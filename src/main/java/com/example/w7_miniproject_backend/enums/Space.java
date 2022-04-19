package com.example.w7_miniproject_backend.enums;

import java.util.Arrays;

public enum Space {
    ONE_ROOM("0"),
    LIVING_ROOM("1"),
    BED_ROOM("2"),
    KITCHEN("3"),
    BATH_ROOM("4"),
    KIDS_ROOM("5"),
    DRESS_ROOM("6"),
    LIBRARY("7"),
    VERANDA("8"),
    OFFICE_2("9"),
    COMMERCIAL_2("10"),
    FURNITURE("11"),
    DOOR("12"),
    OUTSIDE("13");

    private String value;

    Space(String value) {
        this.value = value;
    }
    public static Space enumOf(String n) {
        return Arrays.stream(Space.values())
                .filter(t -> t.getValue().equals(n))
                .findAny()
                .orElse(null);
    }

    public String getValue() {
        return this.value;
    }
}