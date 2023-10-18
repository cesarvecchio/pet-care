package br.com.petcare.dominio.servico;

import br.com.petcare.dominio.dto.DonoDTO;
import br.com.petcare.dominio.entidade.Dono;
import br.com.petcare.infra.excecao.CpfException;
import br.com.petcare.infra.repositorio.DonoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonoService {
    private final DonoRepository donoRepository;

    @Autowired
    public DonoService(DonoRepository donoRepository) {
        this.donoRepository = donoRepository;
    }

    public DonoDTO cadastrar(DonoDTO donoDTO) {
        Dono dono = this.toEntity(donoDTO);

        if (this.cpfExistente(donoDTO.cpf()))
            throw new CpfException("Esse cpf já está sendo utilizado");

        return this.toDto(donoRepository.save(dono));
    }

    public Boolean cpfExistente(String cpf) {
        return donoRepository.existsByCpf(cpf);
    }

    public DonoDTO toDto(Dono dono) {
        return new DonoDTO(
                dono.getId(),
                dono.getNome(),
                dono.getCpf(),
                dono.getRg(),
                dono.getEmail(),
                dono.getSenha(),
                dono.getCep(),
                dono.getLogradouro(),
                dono.getComplemento(),
                dono.getBairro(),
                dono.getCidade(),
                dono.getUf(),
                dono.getNumero()
        );
    }

    private Dono toEntity(DonoDTO donoDTO) {
        return Dono.builder()
                .id(donoDTO.id())
                .nome(donoDTO.nome())
                .cpf(donoDTO.cpf())
                .rg(donoDTO.rg())
                .email(donoDTO.email())
                .senha(donoDTO.senha())
                .cep(donoDTO.cep())
                .logradouro(donoDTO.logradouro())
                .complemento(donoDTO.complemento())
                .bairro(donoDTO.bairro())
                .cidade(donoDTO.cidade())
                .uf(donoDTO.uf())
                .numero(donoDTO.numero())
                .build();
    }
}
