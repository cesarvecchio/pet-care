package br.com.petcare.domain.service;

import br.com.petcare.application.controller.exceptions.CpfException;
import br.com.petcare.application.controller.exceptions.NaoEncontradoException;
import br.com.petcare.application.request.DonoRequestDTO;
import br.com.petcare.application.response.DonoResponseDTO;
import br.com.petcare.domain.entity.Dono;
import br.com.petcare.domain.valueObject.Endereco;
import br.com.petcare.infra.repository.DonoRepository;
import br.com.petcare.utils.Utils;
import org.springframework.stereotype.Service;

@Service
public class DonoService {
    private final DonoRepository donoRepository;
    private final Utils utils;

    public DonoService(DonoRepository donoRepository, Utils utils) {
        this.donoRepository = donoRepository;
        this.utils = utils;
    }

    public DonoResponseDTO cadastrar(DonoRequestDTO donoDTO) {
        Dono dono = toEntity(donoDTO);

        if (cpfExistente(donoDTO.cpf()))
            throw new CpfException("Esse cpf já está sendo utilizado");

        return toResponseDto(donoRepository.save(dono));
    }

    public DonoResponseDTO atualizar(Integer id, DonoRequestDTO donoRequestDTO) {
        Dono dono = buscaPorId(id);
        Endereco endereco = dono.getEndereco();

        Dono donoRequest = toEntity(donoRequestDTO);
        Endereco enderecoRequest = donoRequest.getEndereco();

        utils.copyNonNullProperties(donoRequest, dono);
        utils.copyNonNullProperties(enderecoRequest, endereco);

        dono.setEndereco(endereco);

        return toResponseDto(donoRepository.save(dono));
    }

    public void deletar(Integer id) {
        existePorId(id);

        donoRepository.deleteById(id);
    }

    public Dono buscaPorId(Integer idDono) {
        return donoRepository.findById(idDono)
                .orElseThrow(() -> new NaoEncontradoException(
                        String.format("Dono com o id '%d' não encontrado", idDono)));
    }

    private boolean cpfExistente(String cpf) {
        return donoRepository.existsByCpf(cpf);
    }

    public void existePorId(Integer idDono) {
        if (!donoRepository.existsById(idDono))
            throw new NaoEncontradoException(
                    String.format("Dono com o id '%d' não encontrado", idDono));

    }

    public DonoResponseDTO toResponseDto(Dono dono) {
        return new DonoResponseDTO(
                dono.getId(),
                dono.getNome(),
                dono.getCpf(),
                dono.getRg(),
                dono.getEndereco()
        );
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
