package com.example.w7_miniproject_backend.enums;

import java.util.Arrays;

public enum Size {
    TEN_LOW("0"),
    TEN("1"),
    TWENTY("2"),
    THIRTY("3"),
    FORTY("4"),
    FIFTY("5");

    private String value;

    Size(String value) {
        this.value = value;
    }
    public static Size enumOf(String n) {
        return Arrays.stream(Size.values())
                .filter(t -> t.getValue().equals(n))
                .findAny()
                .orElse(null);
    }

    public String getValue() {
        return this.value;
    }
}