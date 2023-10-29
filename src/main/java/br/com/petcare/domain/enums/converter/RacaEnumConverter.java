package br.com.petcare.domain.enums.converter;

import br.com.petcare.domain.enums.RacaEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RacaEnumConverter implements AttributeConverter<RacaEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(RacaEnum attribute) {
        return attribute.getId();
    }

    @Override
    public RacaEnum convertToEntityAttribute(Integer dbData) {
        return RacaEnum.recuperarRaca(dbData);
    }
}
