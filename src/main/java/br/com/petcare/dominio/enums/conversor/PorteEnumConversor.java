package br.com.petcare.dominio.enums.conversor;

import br.com.petcare.dominio.enums.PorteEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PorteEnumConversor implements AttributeConverter<PorteEnum, String> {
    @Override
    public String convertToDatabaseColumn(PorteEnum attribute) {
        return attribute.getDescricao();
    }

    @Override
    public PorteEnum convertToEntityAttribute(String dbData) {
        return PorteEnum.recuperarPorte(dbData);
    }
}
