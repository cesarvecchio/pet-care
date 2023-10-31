package br.com.petcare.domain.service;

import br.com.petcare.application.controller.exceptions.CpfException;
import br.com.petcare.application.controller.exceptions.NaoEncontradoException;
import br.com.petcare.application.request.FuncionarioRequestDTO;
import br.com.petcare.application.response.FuncionarioResponseDTO;
import br.com.petcare.domain.entity.Funcionario;
import br.com.petcare.infra.repository.FuncionarioRepository;
import br.com.petcare.utils.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;
    private final PetShopService petShopService;
    private final Utils utils;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, PetShopService petShopService, Utils utils) {
        this.funcionarioRepository = funcionarioRepository;
        this.petShopService = petShopService;
        this.utils = utils;
    }

    public FuncionarioResponseDTO cadastrar(Integer idPetShop, FuncionarioRequestDTO funcionarioDTO) {
        Funcionario funcionario = toEntity(funcionarioDTO);

        if (cpfExistente(funcionarioDTO.cpf())) {
            throw new CpfException("Esse cpf já está sendo utilizado");
        }
        funcionario.setPetShop(petShopService.buscarPorId(idPetShop));

        return toResponseDTO(funcionarioRepository.save(funcionario));
    }

    public FuncionarioResponseDTO atualizar(Integer id, FuncionarioRequestDTO funcionarioRequestDTO) {
        Funcionario funcionario = buscaPorId(id);

        utils.copyNonNullProperties(funcionarioRequestDTO, funcionario);

        return toResponseDTO(funcionarioRepository.save(funcionario));
    }

    public void deletar(Integer id) {
        existePorId(id);

        funcionarioRepository.deleteById(id);
    }

    public Funcionario buscaPorId(Integer idFuncionario) {
        return funcionarioRepository.findById(idFuncionario)
                .orElseThrow(() -> new NaoEncontradoException(
                        String.format("Funcionário com o id '%d' não encontrado", idFuncionario)));
    }

    public Page<FuncionarioResponseDTO> consultarFuncionariosPorPetShop(Integer idPetShop, Pageable pageable) {
        petShopService.existePorId(idPetShop);
        Page<Funcionario> funcionarios = funcionarioRepository.findAllByPetShopId(idPetShop, pageable);

        return funcionarios.map(this::toResponseDTO);
    }

    private boolean cpfExistente(String cpf) {
        return funcionarioRepository.existsByCpf(cpf);
    }

    private void existePorId(Integer idFuncionario) {
        if (!funcionarioRepository.existsById(idFuncionario))
            throw new NaoEncontradoException(
                    String.format("Funcionário com o id '%d' não encontrado", idFuncionario));

    }

    public FuncionarioResponseDTO toResponseDTO(Funcionario funcionario) {
        return new FuncionarioResponseDTO(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getCpf(),
                funcionario.getRg()
        );
    }

    public Funcionario toEntity(FuncionarioRequestDTO funcionarioDTO) {
        return Funcionario.builder()
                .nome(funcionarioDTO.nome())
                .cpf(funcionarioDTO.cpf())
                .rg(funcionarioDTO.rg())
                .build();
    }
}
