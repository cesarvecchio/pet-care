package br.com.petcare.dominio.enums.conversor;

import br.com.petcare.dominio.enums.TipoServicoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoServicoEnumConversor implements AttributeConverter<TipoServicoEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(TipoServicoEnum attribute) {
        return attribute.getId();
    }

    @Override
    public TipoServicoEnum convertToEntityAttribute(Integer dbData) {
        return TipoServicoEnum.recuperarServico(dbData);
    }
}
