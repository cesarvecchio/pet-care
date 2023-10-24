package br.com.petcare.dominio.enums.conversor;

import br.com.petcare.dominio.enums.StatusEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusEnumConversor implements AttributeConverter<StatusEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(StatusEnum attribute) {
        return attribute.getId();
    }

    @Override
    public StatusEnum convertToEntityAttribute(Integer dbData) {
        return StatusEnum.recuperarStatus(dbData);
    }
}
