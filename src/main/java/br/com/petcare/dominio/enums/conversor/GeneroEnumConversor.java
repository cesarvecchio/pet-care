package br.com.petcare.dominio.enums.conversor;

import br.com.petcare.dominio.enums.GeneroEnum;
import jakarta.persistence.AttributeConverter;

public class GeneroEnumConversor implements AttributeConverter<GeneroEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(GeneroEnum attribute) {
        return attribute.getId();
    }

    @Override
    public GeneroEnum convertToEntityAttribute(Integer dbData) {
        return GeneroEnum.recuperarGenero(dbData);
    }
}
