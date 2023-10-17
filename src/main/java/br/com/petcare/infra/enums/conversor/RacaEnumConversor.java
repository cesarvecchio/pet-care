package br.com.petcare.infra.enums.conversor;

import br.com.petcare.infra.enums.RacaEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RacaEnumConversor implements AttributeConverter<RacaEnum, String> {

    @Override
    public String convertToDatabaseColumn(RacaEnum attribute) {
        return attribute.getDescricao();
    }

    @Override
    public RacaEnum convertToEntityAttribute(String dbData) {
        return RacaEnum.recuperarRaca(dbData);
    }
}
