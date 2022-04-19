package com.example.w7_miniproject_backend.enums;

import ch.qos.logback.core.pattern.SpacePadder;

import javax.persistence.AttributeConverter;

public class SpaceConverter implements AttributeConverter<Space, String> {
    @Override
    public String convertToDatabaseColumn(Space attribute) {
        if (attribute == null)
            return null;
        return attribute.getValue();
    }

    @Override
    public Space convertToEntityAttribute(String dbData) {
        if (dbData == null)
            return null;
        return Space.enumOf(dbData);
    }
}