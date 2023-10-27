package br.com.petcare.domain.service;

import br.com.petcare.application.controller.exceptions.NaoEncontradoException;
import br.com.petcare.application.request.FuncionarioRequestDTO;
import br.com.petcare.application.request.PetShopRequestDTO;
import br.com.petcare.application.response.FuncionarioResponseDTO;
import br.com.petcare.application.response.PetShopResponseDTO;
import br.com.petcare.domain.valueObject.Endereco;
import br.com.petcare.domain.entity.Funcionario;
import br.com.petcare.domain.entity.PetShop;
import br.com.petcare.domain.enums.TipoServicoEnum;
import br.com.petcare.infra.repository.PetShopRepository;
import br.com.petcare.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class PetShopService {
    private final PetShopRepository petShopRepository;
    private final FuncionarioService funcionarioService;
    private final Utils utils;

    @Autowired
    public PetShopService(PetShopRepository petShopRepository, FuncionarioService funcionarioService, Utils utils) {
        this.petShopRepository = petShopRepository;
        this.funcionarioService = funcionarioService;
        this.utils = utils;
    }

    public Page<PetShopResponseDTO> buscarTodos(Pageable pageable, String nome, String cnpj,
                                                Integer tipoServico, Endereco endereco) {

        Example<PetShop> example = Example.of(PetShop.builder()
                .nome(nome)
                .cnpj(cnpj)
                .tipoServico(TipoServicoEnum.recuperarServico(tipoServico))
                .endereco(endereco)
                .build());

        Page<PetShop> prestadorServicoPage = petShopRepository.findAll(example, pageable);

        return prestadorServicoPage.map(this::toResponseDTO);
    }

    public PetShopResponseDTO cadastrar(PetShopRequestDTO petShopDTO) {
        PetShop petShop = toEntity(petShopDTO);

        return toResponseDTO(petShopRepository.save(petShop));
    }

    public PetShopResponseDTO atualizar(Integer idPetShop, PetShopRequestDTO petShopRequestDTO) {
        PetShop petShop = this.buscarPorId(idPetShop);
        PetShop request = toEntity(petShopRequestDTO);

        this.utils.copyNonNullProperties(request, petShop);

        return this.toResponseDTO(petShopRepository.save(petShop));
    }

    public void deletar(Integer idPetShop) {
        existePorId(idPetShop);

        petShopRepository.deleteById(idPetShop);
    }

    public PetShop buscarPorId(Integer idPetShop) {
        return this.petShopRepository.findById(idPetShop)
                .orElseThrow(() -> new NaoEncontradoException(
                        String.format("PetShop com o id '%d' não encontrado", idPetShop)));
    }

    public void existePorId(Integer idPetShop) {
        if(!petShopRepository.existsById(idPetShop))
            throw new NaoEncontradoException(
                    String.format("Pet Shop com o id '%d' não encontrado", idPetShop));
    }

    public PetShopResponseDTO toResponseDTO(PetShop petShop) {
        return new PetShopResponseDTO(
                petShop.getId(),
                petShop.getNome(),
                petShop.getCnpj(),
                isEmpty(petShop.getFuncionarios()) ? null : getListOfResponseDTOFuncionarios(petShop.getFuncionarios()),
                isEmpty(petShop.getTipoServico())
                        ? null : petShop.getTipoServico().getDescricao(),
                petShop.getEndereco()
        );
    }

    public PetShopRequestDTO toRequestDTO(PetShop petShop) {
        return new PetShopRequestDTO(
                petShop.getId(),
                petShop.getNome(),
                petShop.getEmail(),
                petShop.getSenha(),
                petShop.getCnpj(),
                isEmpty(petShop.getFuncionarios()) ? null : getListOfRequestDTOFuncionarios(petShop.getFuncionarios()),
                isEmpty(petShop.getTipoServico())
                        ? null : petShop.getTipoServico().getId(),
                petShop.getEndereco()
        );
    }

    public PetShop toEntity(PetShopRequestDTO dto) {
        return PetShop.builder()
                .id(dto.id())
                .nome(dto.nome())
                .email(dto.email())
                .senha(dto.senha())
                .cnpj(dto.cnpj())
                .funcionarios(getListOfFuncionarios(dto.funcionarios()))
                .tipoServico(isEmpty(dto.tipoServico())
                        ? null : TipoServicoEnum.recuperarServico(dto.tipoServico()))
                .endereco(dto.endereco())
                .build();
    }

    private List<FuncionarioResponseDTO> getListOfResponseDTOFuncionarios(List<Funcionario> funcionarios) {
        return funcionarios.stream().map(this.funcionarioService::toResponseDTO).toList();
    }

    private List<FuncionarioRequestDTO> getListOfRequestDTOFuncionarios(List<Funcionario> funcionarios) {
        return funcionarios.stream().map(this.funcionarioService::toRequestDTO).toList();
    }

    private List<Funcionario> getListOfFuncionarios(List<FuncionarioRequestDTO> funcionariosDTO) {
        return funcionariosDTO.stream().map(this.funcionarioService::toEntity).toList();
    }
}
