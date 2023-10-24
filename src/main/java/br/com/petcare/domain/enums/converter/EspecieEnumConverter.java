package br.com.petcare.domain.enums.converter;

import br.com.petcare.domain.enums.EspecieEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EspecieEnumConverter implements AttributeConverter<EspecieEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(EspecieEnum attribute) {
        return attribute.getId();
    }

    @Override
    public EspecieEnum convertToEntityAttribute(Integer dbData) {
        return EspecieEnum.recuperarEspecie(dbData);
    }
}
