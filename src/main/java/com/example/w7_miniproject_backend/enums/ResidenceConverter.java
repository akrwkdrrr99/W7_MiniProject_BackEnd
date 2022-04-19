package com.example.w7_miniproject_backend.enums;

//import com.sun.jmx.defaults.ServiceName;

import javax.persistence.AttributeConverter;

public class ResidenceConverter implements AttributeConverter<Residence, String> {
    @Override
    public String convertToDatabaseColumn(Residence attribute) {
        if (attribute == null)
            return null;
        return attribute.getValue();
    }

    @Override
    public Residence convertToEntityAttribute(String dbData) {
        if (dbData == null)
            return null;
        return Residence.enumOf(dbData);
    }
}