package com.example.w7_miniproject_backend.enums;

import com.amazonaws.services.ec2.model.PaymentOption;

import java.util.Arrays;

public enum RoomGroup {

    RESIDENCE("주거형태", new RoomOption[]{
            RoomOption.ONE_OFFI, RoomOption.APARTMENT,
            RoomOption.VILLA, RoomOption.SINGLE_HOUSE,
            RoomOption.OFFICE, RoomOption.COMMERCIAL,
            RoomOption.ETC
    }),

    SPACE("공간", new RoomOption[]{
            RoomOption.ONE_ROOM, RoomOption.LIVING_ROOM,
            RoomOption.BED_ROOM, RoomOption.KITCHEN,
            RoomOption.BATH_ROOM, RoomOption.KIDS_ROOM,
            RoomOption.DRESS_ROOM, RoomOption.LIBRARY,
            RoomOption.VERANDA, RoomOption.OFFICE_2,
            RoomOption.COMMERCIAL_2, RoomOption.FURNITURE,
            RoomOption.DOOR, RoomOption.OUTSIDE
    }),

    SIZE("평수", new RoomOption[]{
            RoomOption.TEN_LOW, RoomOption.TEN,
            RoomOption.TWENTY, RoomOption.THIRTY,
            RoomOption.FORTY, RoomOption.FIFTY
    }),

    STYLE("스타일", new RoomOption[]{
            RoomOption.MODERN, RoomOption.NORTHEU,
            RoomOption.VINTAGE, RoomOption.NATURAL,
            RoomOption.PROVINCE, RoomOption.CLASSIC,
            RoomOption.KOREA, RoomOption.UNIQUE
    }),

    EMPTY("없음", new RoomOption[]{});
    //???

    private String viewName;
    private RoomOption[] containRoom;

    RoomGroup(String viewName, RoomOption[] containRoom) {
        this.viewName = viewName;
        this.containRoom = containRoom;
    }

    public static RoomGroup findGroup(RoomOption searchTarget){
        return Arrays.stream(RoomGroup.values())
                .filter(group -> hasRoomOption(group, searchTarget))
                .findAny()
                .orElse(RoomGroup.EMPTY);
    }

    private static boolean hasRoomOption(RoomGroup from, RoomOption searchTarget){
        return Arrays.stream(from.containRoom)
                .anyMatch(containRoom -> containRoom == searchTarget);
    }

    public String getViewName() {
        return viewName;
    }
}
