package br.com.petcare.dominio.servico;

import br.com.petcare.dominio.dto.PrestadorServicoDTO;
import br.com.petcare.dominio.entidade.PrestadorServico;
import br.com.petcare.dominio.enums.TipoServicoEnum;
import br.com.petcare.infra.repositorio.PrestadorServicoRepository;
import br.com.petcare.infra.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class PrestadorServicoService {
    private final PrestadorServicoRepository prestadorServicoRepository;
    private final Utils utils;

    @Autowired
    public PrestadorServicoService(PrestadorServicoRepository prestadorServicoRepository, Utils utils) {
        this.prestadorServicoRepository = prestadorServicoRepository;
        this.utils = utils;
    }

    public Page<PrestadorServicoDTO> buscarTodos(Pageable pageable) {
        Page<PrestadorServico> prestadorServicoPage = prestadorServicoRepository.findAll(pageable);

        return prestadorServicoPage.map(this::toDTO);
    }

    public PrestadorServicoDTO toDTO(PrestadorServico prestadorServico) {
        return new PrestadorServicoDTO(
                prestadorServico.getId(),
                ObjectUtils.isEmpty(prestadorServico.getTipoServico())
                        ? null : prestadorServico.getTipoServico().getDescricao()
        );
    }

    public PrestadorServico toEntity(PrestadorServicoDTO dto) {
        return PrestadorServico.builder()
                .id(dto.id())

                .tipoServico(ObjectUtils.isEmpty(dto.tipoServico())
                        ? null : TipoServicoEnum.recuperarServico(dto.tipoServico()))
                .build();
    }
}
