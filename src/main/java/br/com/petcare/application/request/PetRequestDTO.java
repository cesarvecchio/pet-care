package br.com.petcare.application.request;

import java.time.LocalDate;

public record PetRequestDTO(
         Integer id,
         String nome,
         LocalDate dataNascimento,
         Double peso,
         Double tamanho,
         Integer especie,
         Integer raca,
         Integer humor,
         Integer genero,
         DonoRequestDTO dono
) {
}
