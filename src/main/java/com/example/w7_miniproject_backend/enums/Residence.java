package com.example.w7_miniproject_backend.enums;

import java.util.Arrays;

public enum Residence {
    ONE_OFFI("0"),
    APARTMENT("1"),
    VILLA("2"),
    SINGLE_HOUSE("3"),
    OFFICE("4"),
    COMMERCIAL("5"),
    ETC("6");

    private String value;

    Residence(String value) {
        this.value = value;
    }

    public static Residence enumOf(String n) {
        return Arrays.stream(Residence.values())
                .filter(t -> t.getValue().equals(n))
                .findAny()
                .orElse(null);
    }

    public String getValue() {
        return this.value;
    }
}
