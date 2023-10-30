package br.com.petcare.application.response;

public record BanhoTosaResponseDTO(
        Integer id,
        String servicosAdicionais,
        String cuidadosEspeciais,
        String tipoBanhoTosa,
        FuncionarioResponseDTO funcionarioResponsavel,
        AgendamentoResponseDTO agendamento
) {
}