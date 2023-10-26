package br.com.petcare.application.response;

import java.time.LocalDate;

public record PetResponseDTO(
        Integer id,
        String nome,
        LocalDate dataNascimento,
        Double peso,
        Double tamanho,
        String especie,
        String raca,
        String humor,
        String genero,
        DonoResponseDTO dono
)  {
}
