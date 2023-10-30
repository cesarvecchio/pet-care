package br.com.petcare.domain.enums.converter;

import br.com.petcare.domain.enums.TipoBanhoTosaEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoBanhoTosaConverter implements AttributeConverter<TipoBanhoTosaEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(TipoBanhoTosaEnum attribute) {
        return attribute.getId();
    }

    @Override
    public TipoBanhoTosaEnum convertToEntityAttribute(Integer dbData) {
        return TipoBanhoTosaEnum.recuperarTipoBanhoTosa(dbData);
    }
}
