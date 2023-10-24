package br.com.petcare.dominio.servico;

import br.com.petcare.aplicacao.controller.excecao.NaoEncontradoException;
import br.com.petcare.dominio.dto.PetShopRequisicaoDTO;
import br.com.petcare.dominio.dto.PetShopRespostaDTO;
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
    private final PetShopRepository petShopRepository;
    private final Utils utils;

    @Autowired
    public PetShopService(PetShopRepository petShopRepository, Utils utils) {
        this.petShopRepository = petShopRepository;
        this.utils = utils;
    }

    public Page<PetShopRespostaDTO> buscarTodos(Pageable pageable) {
        Page<PetShop> prestadorServicoPage = petShopRepository.findAll(pageable);

        return prestadorServicoPage.map(this::toDTOResposta);
    }

    public PetShopRespostaDTO cadastrar(PetShopRequisicaoDTO petShopDTO) {
        PetShop petShop = toEntity(petShopDTO);

        return toDTOResposta(petShopRepository.save(petShop));
    }

    public PetShopRespostaDTO atualizar(Integer idPetShop, PetShopRequisicaoDTO petShopRequisicaoDTO) {
        PetShop petShop = this.buscarPorId(idPetShop);

        this.utils.copyNonNullProperties(petShopRequisicaoDTO, petShop);

        return this.toDTOResposta(petShopRepository.save(petShop));
    }

    public PetShop buscarPorId(Integer idPetShop) {
        return this.petShopRepository.findById(idPetShop)
                .orElseThrow(() -> new NaoEncontradoException(
                        String.format("PetShop com o id '%d' não encontrado", idPetShop)));
    }

    public PetShopRespostaDTO toDTOResposta(PetShop petShop) {
        return new PetShopRespostaDTO(
                petShop.getId(),
                petShop.getNome(),
                petShop.getCpf(),
                petShop.getCnpj(),
                petShop.getListaFuncionarios(),
                ObjectUtils.isEmpty(petShop.getTipoServico())
                        ? null : petShop.getTipoServico().getDescricao(),
                petShop.getEndereco()
        );
    }

    public PetShopRequisicaoDTO toDTO(PetShop petShop) {
        return new PetShopRequisicaoDTO(
                petShop.getId(),
                petShop.getNome(),
                petShop.getCpf(),
                petShop.getCnpj(),
                petShop.getListaFuncionarios(),
                ObjectUtils.isEmpty(petShop.getTipoServico())
                        ? null : petShop.getTipoServico().getId(),
                petShop.getEndereco()
        );
    }

    public PetShop toEntity(PetShopRequisicaoDTO dto) {
        return PetShop.builder()
                .id(dto.id())
                .nome(dto.nome())
                .cpf(dto.cpf())
                .cnpj(dto.cnpj())
                .listaFuncionarios(dto.listaFuncionarios())
                .tipoServico(ObjectUtils.isEmpty(dto.tipoServico())
                        ? null : TipoServicoEnum.recuperarServico(dto.tipoServico()))
                .endereco(dto.endereco())
                .build();
    }
}
