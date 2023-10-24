package br.com.petcare.domain.service;

import br.com.petcare.application.request.DonoRequestDTO;
import br.com.petcare.application.response.DonoResponseDTO;
import br.com.petcare.domain.entity.Dono;
import br.com.petcare.application.controller.exceptions.CpfException;
import br.com.petcare.application.controller.exceptions.NaoEncontradoException;
import br.com.petcare.infra.repository.DonoRepository;
import br.com.petcare.infra.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonoService {
    private final DonoRepository donoRepository;
    private final Utils utils;

    @Autowired
    public DonoService(DonoRepository donoRepository, Utils utils) {
        this.donoRepository = donoRepository;
        this.utils = utils;
    }

    public DonoResponseDTO cadastrar(DonoRequestDTO donoDTO) {
        Dono dono = this.toEntity(donoDTO);

        if (this.cpfExistente(donoDTO.cpf()))
            throw new CpfException("Esse cpf já está sendo utilizado");

        return this.toDto(donoRepository.save(dono));
    }

    public DonoResponseDTO atualizar(Integer id, DonoResponseDTO donoResponseDTO) {
        Dono dono = buscaPorId(id);

        utils.copyNonNullProperties(donoResponseDTO, dono);

        return this.toDto(donoRepository.save(dono));
    }

    public void deletar(Integer id) {
        this.existePorId(id);

        this.donoRepository.deleteById(id);
    }

    public Dono buscaPorId(Integer idDono) {
        return this.donoRepository.findById(idDono)
                .orElseThrow(() -> new NaoEncontradoException(
                        String.format("Dono com o id '%d' não encontrado", idDono)));
    }

    public boolean cpfExistente(String cpf) {
        return donoRepository.existsByCpf(cpf);
    }

    public void existePorId(Integer idDono){
        if(!this.donoRepository.existsById(idDono))
            throw new NaoEncontradoException(
                    String.format("Dono com o id '%d' não encontrado", idDono));

    }

    public DonoResponseDTO toDto(Dono dono) {
        return new DonoResponseDTO(
                dono.getId(),
                dono.getNome(),
                dono.getCpf(),
                dono.getRg()
        );
    }

    public Dono toEntity(DonoResponseDTO donoResponseDTO) {
        return Dono.builder()
                .id(donoResponseDTO.id())
                .nome(donoResponseDTO.nome())
                .cpf(donoResponseDTO.cpf())
                .rg(donoResponseDTO.rg())
                .build();
    }

    public Dono toEntity(DonoRequestDTO donoDTO) {
        return Dono.builder()
                .id(donoDTO.id())
                .nome(donoDTO.nome())
                .cpf(donoDTO.cpf())
                .rg(donoDTO.rg())
                .email(donoDTO.email())
                .senha(donoDTO.senha())
                .endereco(donoDTO.endereco())
                .build();
    }

}
