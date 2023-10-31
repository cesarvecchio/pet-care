package br.com.petcare.domain.service;

import br.com.petcare.application.controller.exceptions.NaoEncontradoException;
import br.com.petcare.application.request.AgendamentoRequestDTO;
import br.com.petcare.application.response.AgendamentoResponseDTO;
import br.com.petcare.domain.entity.*;
import br.com.petcare.domain.enums.StatusEnum;
import br.com.petcare.infra.repository.AgendamentoRepository;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
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

    public Agendamento criarAgendamentoFilter(Integer idDono, Integer idPet, Integer idPetShop) {
        return Agendamento.builder()
                .dono(Dono.builder().id(idDono).build())
                .pet(Pet.builder().id(idPet).build())
                .petShop(PetShop.builder().id(idPetShop).build()).build();
    }


    public Agendamento criarAgendamento(Integer idPetShop, Integer idPet, Servico servico) {
        Agendamento agendamento = servico.getAgendamento();
        agendamento.setPet(petService.buscarPorId(idPet));
        agendamento.setDono(donoService.buscaPorId(agendamento.getPet().getDono().getId()));
        agendamento.setPetShop(petShopService.buscarPorId(idPetShop));

        return agendamento;
    }

    public AgendamentoResponseDTO atualizarStatus(Integer idAgendamento, Integer status) {
        Agendamento agendamento = buscarPorId(idAgendamento);
        agendamento.setStatus(StatusEnum.recuperarStatus(status));

        return toResponseDTO(agendamentoRepository.save(agendamento));
    }

    public AgendamentoResponseDTO reagendar(Integer idAgendamento, AgendamentoRequestDTO agendamentoRequestDTO) {
        Agendamento agendamento = buscarPorId(idAgendamento);

        Agendamento reagendado = Agendamento.builder()
                .dataAgendamento(agendamentoRequestDTO.dataAgendamento())
                .status(StatusEnum.REAGENDADO)
                .dono(agendamento.getDono())
                .petShop(agendamento.getPetShop())
                .pet(agendamento.getPet())
                .build();

        return toResponseDTO(agendamentoRepository.save(reagendado));
    }

    public Agendamento buscarPorId(Integer idAgendamento) {
        return agendamentoRepository.findById(idAgendamento)
                .orElseThrow(() -> new NaoEncontradoException(
                        String.format("Agendamento com o id '%d' não encontrado", idAgendamento)));
    }

    public Agendamento toEntity(AgendamentoRequestDTO agendamentoDTO) {
        return Agendamento.builder()
                .dataAgendamento(agendamentoDTO.dataAgendamento())
                .status(StatusEnum.recuperarStatus(agendamentoDTO.status()))
                .dono(isEmpty(agendamentoDTO.dono()) ? null : donoService.toEntity(agendamentoDTO.dono()))
                .petShop(isEmpty(agendamentoDTO.petShop()) ? null : petShopService.toEntity(agendamentoDTO.petShop()))
                .pet(isEmpty(agendamentoDTO.pet()) ? null : petService.toEntity(agendamentoDTO.pet()))
                .build();
    }

    public AgendamentoResponseDTO toResponseDTO(Agendamento agendamento) {
        return new AgendamentoResponseDTO(
                agendamento.getId(),
                agendamento.getDataAgendamento(),
                isEmpty(agendamento.getStatus()) ? null : agendamento.getStatus().getDescricao(),
                isEmpty(agendamento.getDono()) ? null : donoService.toResponseDto(agendamento.getDono()),
                isEmpty(agendamento.getPetShop()) ? null : petShopService.toResponseDTO(agendamento.getPetShop()),
                isEmpty(agendamento.getPetShop()) ? null : petService.toResponseDTO(agendamento.getPet())
        );
    }

}
