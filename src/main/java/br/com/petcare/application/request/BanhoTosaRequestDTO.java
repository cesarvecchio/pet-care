package br.com.petcare.application.request;

public record BanhoTosaRequestDTO(
        String servicosAdicionais,
        String cuidadosEspeciais,
        Integer tipoBanhoTosa,
        FuncionarioRequestDTO funcionarioResponsavel,
        AgendamentoRequestDTO agendamento
) {
}
