package br.com.petcare.dominio.enums.conversor;

import br.com.petcare.dominio.enums.HumorEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class HumorEnumConversor implements AttributeConverter<HumorEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(HumorEnum attribute) {
        return attribute.getId();
    }

    @Override
    public HumorEnum convertToEntityAttribute(Integer dbData) {
        return HumorEnum.recuperarHumor(dbData);
    }
}
