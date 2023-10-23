package br.com.petcare.dominio.servico;

import br.com.petcare.dominio.dto.PetShopDTO;
import br.com.petcare.dominio.entidade.PetShop;
import br.com.petcare.dominio.enums.TipoServicoEnum;
import br.com.petcare.infra.repositorio.PetShopRepository;
import br.com.petcare.infra.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class PetShopService {
    private final PetShopRepository prestadorServicoRepository;
    private final Utils utils;

    @Autowired
    public PetShopService(PetShopRepository prestadorServicoRepository, Utils utils) {
        this.prestadorServicoRepository = prestadorServicoRepository;
        this.utils = utils;
    }

    public Page<PetShopDTO> buscarTodos(Pageable pageable) {
        Page<PetShop> prestadorServicoPage = prestadorServicoRepository.findAll(pageable);

        return prestadorServicoPage.map(this::toDTO);
    }

    public PetShopDTO cadastrar(PetShopDTO petShopDTO) {
        PetShop petShop = toEntity(petShopDTO);

        return null;
    }

    public PetShopDTO toDTO(PetShop prestadorServico) {
        return new PetShopDTO(
                prestadorServico.getId(),
                prestadorServico.getNome(),
                prestadorServico.getCpf(),
                prestadorServico.getCnpj(),
                prestadorServico.getListaFuncionarios(),
                ObjectUtils.isEmpty(prestadorServico.getTipoServico())
                        ? null : prestadorServico.getTipoServico().getDescricao()
        );
    }

    public PetShop toEntity(PetShopDTO dto) {
        return PetShop.builder()
                .id(dto.id())
                .nome(dto.nome())
                .cpf(dto.cpf())
                .cnpj(dto.cnpj())
                .listaFuncionarios(dto.listaFuncionarios())
                .tipoServico(ObjectUtils.isEmpty(dto.tipoServico())
                        ? null : TipoServicoEnum.recuperarServico(dto.tipoServico()))
                .build();
    }
}
