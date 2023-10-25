package br.com.petcare.application.request;

import br.com.petcare.application.response.DonoResponseDTO;

import java.time.LocalDate;

public record PetRequestDTO(
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
) {
}
