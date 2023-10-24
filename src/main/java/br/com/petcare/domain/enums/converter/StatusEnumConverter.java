package br.com.petcare.domain.enums.converter;

import br.com.petcare.domain.enums.StatusEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusEnumConverter implements AttributeConverter<StatusEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(StatusEnum attribute) {
        return attribute.getId();
    }

    @Override
    public StatusEnum convertToEntityAttribute(Integer dbData) {
        return StatusEnum.recuperarStatus(dbData);
    }
}
