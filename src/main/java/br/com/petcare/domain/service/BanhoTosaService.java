package br.com.petcare.domain.service;

import br.com.petcare.application.controller.exceptions.NaoEncontradoException;
import br.com.petcare.application.request.AgendamentoRequestDTO;
import br.com.petcare.application.request.BanhoTosaRequestDTO;
import br.com.petcare.application.response.BanhoTosaResponseDTO;
import br.com.petcare.domain.entity.Agendamento;
import br.com.petcare.domain.entity.BanhoTosa;
import br.com.petcare.domain.enums.StatusEnum;
import br.com.petcare.domain.enums.TipoBanhoTosaEnum;
import br.com.petcare.domain.enums.TipoServicoEnum;
import br.com.petcare.infra.repository.BanhoTosaRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class BanhoTosaService {

    private final BanhoTosaRepository banhoTosaRepository;
    private final AgendamentoService agendamentoService;
    private final FuncionarioService funcionarioService;

    public BanhoTosaService(BanhoTosaRepository banhoTosaRepository,
                            AgendamentoService agendamentoService,
                            FuncionarioService funcionarioService) {
        this.banhoTosaRepository = banhoTosaRepository;
        this.agendamentoService = agendamentoService;
        this.funcionarioService = funcionarioService;
    }

    public Page<BanhoTosaResponseDTO> buscarTodos(Pageable pageable, Integer idDono, Integer idPet, Integer idPetShop, Integer tipoBanhoTosa) {
        Agendamento agendamentoFilter = agendamentoService.criarAgendamentoFilter(idDono, idPet, idPetShop);
        Example<BanhoTosa> example = Example.of(BanhoTosa.builder()
                .tipoServico(TipoServicoEnum.BANHO_TOSA)
                .tipoBanhoTosa(TipoBanhoTosaEnum.recuperarTipoBanhoTosa(tipoBanhoTosa))
                .agendamento(agendamentoFilter)
                .build());

        return banhoTosaRepository.findAll(example, pageable).map(this::toResponseDTO);
    }

    public BanhoTosaResponseDTO cadastrar(Integer idPetShop, Integer idPet, BanhoTosaRequestDTO banhoTosaRequestDTO) {
        BanhoTosa banhoTosa = toEntity(banhoTosaRequestDTO);
        Agendamento agendamento = agendamentoService.criarAgendamento(idPetShop, idPet, banhoTosa);

        banhoTosa.setAgendamento(agendamento);

        return toResponseDTO(banhoTosaRepository.save(banhoTosa));
    }

    public BanhoTosaResponseDTO reagendar(Integer idBanhoTosa, AgendamentoRequestDTO agendamentoRequestDTO) {
        BanhoTosa banhoTosa = buscaPorId(idBanhoTosa);

        Agendamento novoAgendamento = Agendamento.builder()
                .dataAgendamento(agendamentoRequestDTO.dataAgendamento())
                .status(StatusEnum.REAGENDADO)
                .dono(banhoTosa.getAgendamento().getDono())
                .petShop(banhoTosa.getAgendamento().getPetShop())
                .pet(banhoTosa.getAgendamento().getPet())
                .build();
        BanhoTosa reagendado = BanhoTosa.builder()
                .servicosAdicionais(banhoTosa.getServicosAdicionais())
                .cuidadosEspeciais(banhoTosa.getCuidadosEspeciais())
                .tipoBanhoTosa(banhoTosa.getTipoBanhoTosa())
                .tipoServico(banhoTosa.getTipoServico())
                .funcionarioResponsavel(banhoTosa.getFuncionarioResponsavel())
                .agendamento(novoAgendamento)
                .build();

        return toResponseDTO(banhoTosaRepository.save(reagendado));

    }

    public BanhoTosa buscaPorId(Integer idBanhoTosa) {
        return banhoTosaRepository.findById(idBanhoTosa)
                .orElseThrow(() -> new NaoEncontradoException(
                        String.format("Serviço de banho e tosa com o id '%d' não encontrado", idBanhoTosa)));
    }

    public BanhoTosa toEntity(BanhoTosaRequestDTO banhoTosaRequestDTO) {
        return BanhoTosa.builder()
                .servicosAdicionais(banhoTosaRequestDTO.servicosAdicionais())
                .cuidadosEspeciais(banhoTosaRequestDTO.cuidadosEspeciais())
                .tipoBanhoTosa(TipoBanhoTosaEnum.recuperarTipoBanhoTosa(banhoTosaRequestDTO.tipoBanhoTosa()))
                .tipoServico(TipoServicoEnum.BANHO_TOSA)
                .funcionarioResponsavel(isEmpty(banhoTosaRequestDTO.funcionarioResponsavel()) ? null : funcionarioService.toEntity(banhoTosaRequestDTO.funcionarioResponsavel()))
                .agendamento(isEmpty(banhoTosaRequestDTO.agendamento()) ? null : agendamentoService.toEntity(banhoTosaRequestDTO.agendamento()))
                .build();
    }

    public BanhoTosaResponseDTO toResponseDTO(BanhoTosa banhoTosa) {
        return new BanhoTosaResponseDTO(
                banhoTosa.getId(),
                banhoTosa.getServicosAdicionais(),
                banhoTosa.getCuidadosEspeciais(),
                banhoTosa.getTipoBanhoTosa().getDescricao(),
                isEmpty(banhoTosa.getFuncionarioResponsavel()) ? null : funcionarioService.toResponseDTO(banhoTosa.getFuncionarioResponsavel()),
                agendamentoService.toResponseDTO(banhoTosa.getAgendamento())
        );
    }

}
