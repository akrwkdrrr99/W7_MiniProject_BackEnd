package com.example.w7_miniproject_backend.enums;

import java.util.Arrays;

public enum Style {
    MODERN("0"),
    NORTHEU("1"),
    VINTAGE("2"),
    NATURAL("3"),
    PROVINCE("4"),
    CLASSIC("5"),
    KOREA("6"),
    UNIQUE("7");

    private String value;

    Style(String value) {
        this.value = value;
    }
    public static Style enumOf(String n) {
        return Arrays.stream(Style.values())
                .filter(t -> t.getValue().equals(n))
                .findAny()
                .orElse(null);
    }

    public String getValue() {
        return this.value;
    }
}