package br.com.petcare.dominio.servico;

import br.com.petcare.dominio.dto.AgendamentoCadastroDTO;
import br.com.petcare.dominio.dto.AgendamentoDTO;
import br.com.petcare.dominio.entidade.Agendamento;
import br.com.petcare.dominio.enums.StatusEnum;
import br.com.petcare.infra.repositorio.AgendamentoRepository;

import static org.springframework.util.ObjectUtils.isEmpty;

public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final PetShopService petShopService;
    private final DonoService donoService;
    private final PetService petService;

    public AgendamentoService(AgendamentoRepository agendamentoRepository, PetShopService petShopService, DonoService donoService, PetService petService) {
        this.agendamentoRepository = agendamentoRepository;
        this.petShopService = petShopService;
        this.donoService = donoService;
        this.petService = petService;
    }

    public AgendamentoDTO cadastrar(Integer idDono, Integer idPetShop, Integer idPet, AgendamentoCadastroDTO agendamentoCadastroDTO) {
        return null;
    }

    public Agendamento toEntity(AgendamentoDTO agendamentoDTO) {
        return Agendamento.builder()
                .dataAgendamento(agendamentoDTO.dataAgendamento())
                .observacao(agendamentoDTO.observacao())
                .status(StatusEnum.recuperarStatus(agendamentoDTO.status()))
                .dono(isEmpty(agendamentoDTO.dono()) ? null : donoService.toEntity(agendamentoDTO.dono()))
                .petShop(isEmpty(agendamentoDTO.petShop()) ? null : petShopService.toEntity(agendamentoDTO.petShop()))
                .pet(isEmpty(agendamentoDTO.pet()) ? null : petService.toEntity(agendamentoDTO.pet()))
                .build();
    }

    public AgendamentoDTO toDTO(Agendamento agendamento) {
        return new AgendamentoDTO(
                agendamento.getDataAgendamento(),
                agendamento.getObservacao(),
                isEmpty(agendamento.getStatus()) ? null : agendamento.getStatus().getDescricao(),
                isEmpty(agendamento.getDono()) ? null : donoService.toDto(agendamento.getDono()),
                isEmpty(agendamento.getPetShop()) ? null : petShopService.toDTO(agendamento.getPetShop()),
                isEmpty(agendamento.getPetShop()) ? null : petService.toDTO(agendamento.getPet())
        );
    }


}
