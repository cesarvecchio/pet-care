package br.com.petcare.dominio.enums.conversor;

import br.com.petcare.dominio.enums.GeneroEnum;
import jakarta.persistence.AttributeConverter;

public class GeneroEnumConversor implements AttributeConverter<GeneroEnum, String> {
    @Override
    public String convertToDatabaseColumn(GeneroEnum attribute) {
        return attribute.getDescricao();
    }

    @Override
    public GeneroEnum convertToEntityAttribute(String dbData) {
        return GeneroEnum.recuperarGenero(dbData);
    }
}
