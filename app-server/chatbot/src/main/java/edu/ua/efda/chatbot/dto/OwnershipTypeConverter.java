package edu.ua.efda.chatbot.dto;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class OwnershipTypeConverter implements AttributeConverter<OwnershipType, String> {
 
    @Override
    public String convertToDatabaseColumn(OwnershipType category) {
        if (category == null) {
            return null;
        }
        return category.getCode();
    }

    @Override
    public OwnershipType convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(OwnershipType.values())
          .filter(c -> c.getCode().equals(code))
          .findFirst()
          .orElseThrow(IllegalArgumentException::new);
    }
}

