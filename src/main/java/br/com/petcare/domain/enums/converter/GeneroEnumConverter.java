package br.com.petcare.domain.enums.converter;

import br.com.petcare.domain.enums.GeneroEnum;
import jakarta.persistence.AttributeConverter;

public class GeneroEnumConverter implements AttributeConverter<GeneroEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(GeneroEnum attribute) {
        return attribute.getId();
    }

    @Override
    public GeneroEnum convertToEntityAttribute(Integer dbData) {
        return GeneroEnum.recuperarGenero(dbData);
    }
}
