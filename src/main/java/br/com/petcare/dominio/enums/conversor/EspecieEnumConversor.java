package br.com.petcare.dominio.enums.conversor;

import br.com.petcare.dominio.enums.EspecieEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EspecieEnumConversor implements AttributeConverter<EspecieEnum, String> {

    @Override
    public String convertToDatabaseColumn(EspecieEnum attribute) {
        return attribute.getDescricao();
    }

    @Override
    public EspecieEnum convertToEntityAttribute(String dbData) {
        return EspecieEnum.recuperarEspecie(dbData);
    }
}
