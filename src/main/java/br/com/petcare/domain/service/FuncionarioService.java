package br.com.petcare.domain.service;

import br.com.petcare.application.controller.exceptions.CpfException;
import br.com.petcare.application.controller.exceptions.NaoEncontradoException;
import br.com.petcare.application.request.FuncionarioRequestDTO;
import br.com.petcare.application.response.FuncionarioResponseDTO;
import br.com.petcare.domain.entity.Funcionario;
import br.com.petcare.infra.repository.FuncionarioRepository;
import br.com.petcare.infra.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;
    private final Utils utils;

    @Autowired
    public FuncionarioService(FuncionarioRepository funcionarioRepository, Utils utils) {
        this.funcionarioRepository = funcionarioRepository;
        this.utils = utils;
    }

    public FuncionarioResponseDTO cadastrar(FuncionarioRequestDTO funcionarioDTO) {
        Funcionario funcionario = this.toEntity(funcionarioDTO);

        if (this.cpfExistente(funcionarioDTO.cpf()))
            throw new CpfException("Esse cpf já está sendo utilizado");

        return this.toDto(funcionarioRepository.save(funcionario));
    }

    public FuncionarioResponseDTO atualizar(Integer id, FuncionarioResponseDTO funcionarioResponseDTO) {
        Funcionario funcionario = buscaPorId(id);

        utils.copyNonNullProperties(funcionarioResponseDTO, funcionario);

        return this.toDto(funcionarioRepository.save(funcionario));
    }

    public void deletar(Integer id) {
        this.existePorId(id);

        this.funcionarioRepository.deleteById(id);
    }

    public Funcionario buscaPorId(Integer idFuncionario) {
        return this.funcionarioRepository.findById(idFuncionario)
                .orElseThrow(() -> new NaoEncontradoException(
                        String.format("Funcionário com o id '%d' não encontrado", idFuncionario)));
    }

    public boolean cpfExistente(String cpf) {
        return funcionarioRepository.existsByCpf(cpf);
    }

    public void existePorId(Integer idFuncionario){
        if(!this.funcionarioRepository.existsById(idFuncionario))
            throw new NaoEncontradoException(
                    String.format("Funcionário com o id '%d' não encontrado", idFuncionario));

    }

    public FuncionarioResponseDTO toDto(Funcionario funcionario) {
        return new FuncionarioResponseDTO(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getCpf(),
                funcionario.getRg()
        );
    }

    public Funcionario toEntity(FuncionarioResponseDTO funcionarioResponseDTO) {
        return Funcionario.builder()
                .id(funcionarioResponseDTO.id())
                .nome(funcionarioResponseDTO.nome())
                .cpf(funcionarioResponseDTO.cpf())
                .rg(funcionarioResponseDTO.rg())
                .build();
    }

    public Funcionario toEntity(FuncionarioRequestDTO funcionarioDTO) {
        return Funcionario.builder()
                .id(funcionarioDTO.id())
                .nome(funcionarioDTO.nome())
                .cpf(funcionarioDTO.cpf())
                .rg(funcionarioDTO.rg())
                .build();
    }

}
