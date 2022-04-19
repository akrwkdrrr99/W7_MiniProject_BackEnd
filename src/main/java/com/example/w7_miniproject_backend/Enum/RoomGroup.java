package com.example.w7_miniproject_backend.Enum;

import java.util.Arrays;

public enum RoomGroup {

    RESIDENCE("주거형태", new com.example.w7_miniproject_backend.Enum.RoomOption[]{
            com.example.w7_miniproject_backend.Enum.RoomOption.ONE_OFFI, com.example.w7_miniproject_backend.Enum.RoomOption.APARTMENT,
            com.example.w7_miniproject_backend.Enum.RoomOption.VILLA, com.example.w7_miniproject_backend.Enum.RoomOption.SINGLE_HOUSE,
            com.example.w7_miniproject_backend.Enum.RoomOption.OFFICE, com.example.w7_miniproject_backend.Enum.RoomOption.COMMERCIAL,
            com.example.w7_miniproject_backend.Enum.RoomOption.ETC
    }),

    SPACE("공간", new com.example.w7_miniproject_backend.Enum.RoomOption[]{
            com.example.w7_miniproject_backend.Enum.RoomOption.ONE_ROOM, com.example.w7_miniproject_backend.Enum.RoomOption.LIVING_ROOM,
            com.example.w7_miniproject_backend.Enum.RoomOption.BED_ROOM, com.example.w7_miniproject_backend.Enum.RoomOption.KITCHEN,
            com.example.w7_miniproject_backend.Enum.RoomOption.BATH_ROOM, com.example.w7_miniproject_backend.Enum.RoomOption.KIDS_ROOM,
            com.example.w7_miniproject_backend.Enum.RoomOption.DRESS_ROOM, com.example.w7_miniproject_backend.Enum.RoomOption.LIBRARY,
            com.example.w7_miniproject_backend.Enum.RoomOption.VERANDA, com.example.w7_miniproject_backend.Enum.RoomOption.OFFICE_2,
            com.example.w7_miniproject_backend.Enum.RoomOption.COMMERCIAL_2, com.example.w7_miniproject_backend.Enum.RoomOption.FURNITURE,
            com.example.w7_miniproject_backend.Enum.RoomOption.DOOR, com.example.w7_miniproject_backend.Enum.RoomOption.OUTSIDE
    }),

    SIZE("평수", new com.example.w7_miniproject_backend.Enum.RoomOption[]{
            com.example.w7_miniproject_backend.Enum.RoomOption.TEN_LOW, com.example.w7_miniproject_backend.Enum.RoomOption.TEN,
            com.example.w7_miniproject_backend.Enum.RoomOption.TWENTY, com.example.w7_miniproject_backend.Enum.RoomOption.THIRTY,
            com.example.w7_miniproject_backend.Enum.RoomOption.FORTY, com.example.w7_miniproject_backend.Enum.RoomOption.FIFTY
    }),

    STYLE("스타일", new com.example.w7_miniproject_backend.Enum.RoomOption[]{
            com.example.w7_miniproject_backend.Enum.RoomOption.MODERN, com.example.w7_miniproject_backend.Enum.RoomOption.NORTHEU,
            com.example.w7_miniproject_backend.Enum.RoomOption.VINTAGE, com.example.w7_miniproject_backend.Enum.RoomOption.NATURAL,
            com.example.w7_miniproject_backend.Enum.RoomOption.PROVINCE, com.example.w7_miniproject_backend.Enum.RoomOption.CLASSIC,
            com.example.w7_miniproject_backend.Enum.RoomOption.KOREA, com.example.w7_miniproject_backend.Enum.RoomOption.UNIQUE
    }),

    EMPTY("없음", new com.example.w7_miniproject_backend.Enum.RoomOption[]{});
    //???

    private String viewName;
    private com.example.w7_miniproject_backend.Enum.RoomOption[] containRoom;

    RoomGroup(String viewName, com.example.w7_miniproject_backend.Enum.RoomOption[] containRoom) {
        this.viewName = viewName;
        this.containRoom = containRoom;
    }

    public static RoomGroup findGroup(com.example.w7_miniproject_backend.Enum.RoomOption searchTarget){
        return Arrays.stream(RoomGroup.values())
                .filter(group -> hasRoomOption(group, searchTarget))
                .findAny()
                .orElse(RoomGroup.EMPTY);
    }

    private static boolean hasRoomOption(RoomGroup from, com.example.w7_miniproject_backend.Enum.RoomOption searchTarget){
        return Arrays.stream(from.containRoom)
                .anyMatch(containRoom -> containRoom == searchTarget);
    }

    public String getViewName() {
        return viewName;
    }
}