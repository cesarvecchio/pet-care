package br.com.petcare.domain.enums.converter;

import br.com.petcare.domain.enums.HumorEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class HumorEnumConverter implements AttributeConverter<HumorEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(HumorEnum attribute) {
        return attribute.getId();
    }

    @Override
    public HumorEnum convertToEntityAttribute(Integer dbData) {
        return HumorEnum.recuperarHumor(dbData);
    }
}
