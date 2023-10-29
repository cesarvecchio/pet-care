package br.com.petcare.domain.service;

import br.com.petcare.application.controller.exceptions.NaoEncontradoException;
import br.com.petcare.application.request.AgendamentoRequestDTO;
import br.com.petcare.application.response.AgendamentoResponseDTO;
import br.com.petcare.domain.entity.Agendamento;
import br.com.petcare.domain.entity.Dono;
import br.com.petcare.domain.entity.Pet;
import br.com.petcare.domain.entity.PetShop;
import br.com.petcare.domain.enums.StatusEnum;
import br.com.petcare.infra.repository.AgendamentoRepository;
import br.com.petcare.utils.Utils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;
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

    public Page<AgendamentoResponseDTO> buscarTodos(Pageable pageable, Integer idDono, Integer idPet, Integer idPetShop) {
        Example<Agendamento> example = Example.of(Agendamento.builder()
                .dono(Dono.builder().id(idDono).build())
                .pet(Pet.builder().id(idPet).build())
                .petShop(PetShop.builder().id(idPetShop).build())
                .build());

        return agendamentoRepository.findAll(example, pageable).map(this::toResponseDTO);
    }

    public AgendamentoResponseDTO cadastrar(Integer idPetShop, Integer idPet, AgendamentoRequestDTO agendamentoRequestDTO) {
        Agendamento agendamento = toEntity(agendamentoRequestDTO);
        agendamento.setPet(petService.buscarPorId(idPet));
        agendamento.setDono(donoService.buscaPorId(agendamento.getPet().getDono().getId()));
        agendamento.setPetShop(petShopService.buscarPorId(idPetShop));

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
                agendamento.getId(),
                agendamento.getDataAgendamento(),
                agendamento.getObservacao(),
                isEmpty(agendamento.getStatus()) ? null : agendamento.getStatus().getDescricao(),
                isEmpty(agendamento.getDono()) ? null : donoService.toResponseDto(agendamento.getDono()),
                isEmpty(agendamento.getPetShop()) ? null : petShopService.toResponseDTO(agendamento.getPetShop()),
                isEmpty(agendamento.getPetShop()) ? null : petService.toResponseDTO(agendamento.getPet())
        );
    }

}
