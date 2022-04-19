package com.example.w7_miniproject_backend.enums;

import javax.persistence.AttributeConverter;

public class SizeConverter implements AttributeConverter<Size, String> {
    @Override
    public String convertToDatabaseColumn(Size attribute) {
        if (attribute == null)
            return null;
        return attribute.getValue();
    }

    @Override
    public Size convertToEntityAttribute(String dbData) {
        if (dbData == null)
            return null;
        return Size.enumOf(dbData);
    }
}