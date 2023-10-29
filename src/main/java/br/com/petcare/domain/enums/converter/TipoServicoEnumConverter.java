package br.com.petcare.domain.enums.converter;

import br.com.petcare.domain.enums.TipoServicoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoServicoEnumConverter implements AttributeConverter<TipoServicoEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(TipoServicoEnum attribute) {
        return attribute.getId();
    }

    @Override
    public TipoServicoEnum convertToEntityAttribute(Integer dbData) {
        return TipoServicoEnum.recuperarServico(dbData);
    }
}
