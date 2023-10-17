package br.com.petcare.dominio.enums.conversor;

import br.com.petcare.dominio.enums.HumorEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class HumorEnumConversor implements AttributeConverter<HumorEnum, String> {
    @Override
    public String convertToDatabaseColumn(HumorEnum attribute) {
        return attribute.getDescricao();
    }

    @Override
    public HumorEnum convertToEntityAttribute(String dbData) {
        return HumorEnum.recuperarHumor(dbData);
    }
}
