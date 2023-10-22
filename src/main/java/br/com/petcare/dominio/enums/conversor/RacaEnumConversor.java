package br.com.petcare.dominio.enums.conversor;

import br.com.petcare.dominio.enums.RacaEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RacaEnumConversor implements AttributeConverter<RacaEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(RacaEnum attribute) {
        return attribute.getId();
    }

    @Override
    public RacaEnum convertToEntityAttribute(Integer dbData) {
        return RacaEnum.recuperarRaca(dbData);
    }
}
