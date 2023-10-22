package br.com.petcare.dominio.enums.conversor;

import br.com.petcare.dominio.enums.EspecieEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EspecieEnumConversor implements AttributeConverter<EspecieEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(EspecieEnum attribute) {
        return attribute.getId();
    }

    @Override
    public EspecieEnum convertToEntityAttribute(Integer dbData) {
        return EspecieEnum.recuperarEspecie(dbData);
    }
}
