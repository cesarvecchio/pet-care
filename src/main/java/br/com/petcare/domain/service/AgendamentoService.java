package br.com.petcare.domain.service;

import br.com.petcare.application.controller.exceptions.NaoEncontradoException;
import br.com.petcare.application.request.AgendamentoRequestDTO;
import br.com.petcare.application.response.AgendamentoResponseDTO;
import br.com.petcare.domain.entity.Agendamento;
import br.com.petcare.domain.enums.StatusEnum;
import br.com.petcare.infra.repository.AgendamentoRepository;
import br.com.petcare.utils.Utils;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final PetShopService petShopService;
    private final DonoService donoService;
    private final PetService petService;
    private final Utils utils;

    public AgendamentoService(AgendamentoRepository agendamentoRepository, PetShopService petShopService, DonoService donoService, PetService petService, Utils utils) {
        this.agendamentoRepository = agendamentoRepository;
        this.petShopService = petShopService;
        this.donoService = donoService;
        this.petService = petService;
        this.utils = utils;
    }

    public AgendamentoResponseDTO cadastrar(Integer idDono, Integer idPetShop, Integer idPet, AgendamentoRequestDTO agendamentoRequestDTO) {
        Agendamento agendamento = toEntity(agendamentoRequestDTO);
        agendamento.setDono(donoService.buscaPorId(idDono));
        agendamento.setPetShop(petShopService.buscarPorId(idPetShop));
        agendamento.setPet(petService.buscarPorId(idPet));

        return toResponseDTO(agendamentoRepository.save(agendamento));
    }

    public AgendamentoResponseDTO atualizar(Integer idAgendamento, AgendamentoRequestDTO agendamentoRequestDTO) {
        Agendamento agendamento = buscarPorId(idAgendamento);
        Agendamento request = toEntity(agendamentoRequestDTO);

        utils.copyNonNullProperties(request, agendamento);

        return toResponseDTO(agendamentoRepository.save(agendamento));
    }
    public Agendamento buscarPorId(Integer idAgendamento) {
        return agendamentoRepository.findById(idAgendamento)
                .orElseThrow(() -> new NaoEncontradoException(
                        String.format("Agendamento com o id '%d' não encontrado", idAgendamento)));
    }
    public Agendamento toEntity(AgendamentoRequestDTO agendamentoDTO) {
        return Agendamento.builder()
                .dataAgendamento(agendamentoDTO.dataAgendamento())
                .observacao(agendamentoDTO.observacao())
                .status(StatusEnum.recuperarStatus(agendamentoDTO.status()))
                .dono(isEmpty(agendamentoDTO.dono()) ? null : donoService.toEntity(agendamentoDTO.dono()))
                .petShop(isEmpty(agendamentoDTO.petShop()) ? null : petShopService.toEntity(agendamentoDTO.petShop()))
                .pet(isEmpty(agendamentoDTO.pet()) ? null : petService.toEntity(agendamentoDTO.pet()))
                .build();
    }

    public AgendamentoResponseDTO toResponseDTO(Agendamento agendamento) {
        return new AgendamentoResponseDTO(
                agendamento.getDataAgendamento(),
                agendamento.getObservacao(),
                isEmpty(agendamento.getStatus()) ? null : agendamento.getStatus().getDescricao(),
                isEmpty(agendamento.getDono()) ? null : donoService.toDto(agendamento.getDono()),
                isEmpty(agendamento.getPetShop()) ? null : petShopService.toResponseDTO(agendamento.getPetShop()),
                isEmpty(agendamento.getPetShop()) ? null : petService.toResponseDTO(agendamento.getPet())
        );
    }

}
