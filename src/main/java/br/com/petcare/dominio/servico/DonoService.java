package br.com.petcare.dominio.servico;

import br.com.petcare.dominio.dto.DonoCadastroDTO;
import br.com.petcare.dominio.dto.DonoDTO;
import br.com.petcare.dominio.entidade.Dono;
import br.com.petcare.aplicacao.controller.excecao.CpfException;
import br.com.petcare.aplicacao.controller.excecao.NaoEncontradoException;
import br.com.petcare.infra.repositorio.DonoRepository;
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

    public DonoDTO cadastrar(DonoCadastroDTO donoDTO) {
        Dono dono = this.toEntity(donoDTO);

        if (this.cpfExistente(donoDTO.cpf()))
            throw new CpfException("Esse cpf já está sendo utilizado");

        return this.toDto(donoRepository.save(dono));
    }

    public DonoDTO atualizar(Integer id, DonoDTO donoDTO) {
        Dono dono = buscaPorId(id);

        utils.copyNonNullProperties(donoDTO, dono);

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

    public DonoDTO toDto(Dono dono) {
        return new DonoDTO(
                dono.getId(),
                dono.getNome(),
                dono.getCpf(),
                dono.getRg()
        );
    }

    public Dono toEntity(DonoDTO donoDTO) {
        return Dono.builder()
                .id(donoDTO.id())
                .nome(donoDTO.nome())
                .cpf(donoDTO.cpf())
                .rg(donoDTO.rg())
                .build();
    }

    public Dono toEntity(DonoCadastroDTO donoDTO) {
        return Dono.builder()
                .id(donoDTO.id())
                .nome(donoDTO.nome())
                .cpf(donoDTO.cpf())
                .rg(donoDTO.rg())
                .email(donoDTO.email())
                .senha(donoDTO.senha())
                .build();
    }

}
