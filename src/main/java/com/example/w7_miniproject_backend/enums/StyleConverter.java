package com.example.w7_miniproject_backend.enums;

import javax.persistence.AttributeConverter;

public class StyleConverter implements AttributeConverter<Style, String> {
    @Override
    public String convertToDatabaseColumn(Style attribute) {
        if (attribute == null)
            return null;
        return attribute.getValue();
    }

    @Override
    public Style convertToEntityAttribute(String dbData) {
        if (dbData == null)
            return null;
        return Style.enumOf(dbData);
    }
}