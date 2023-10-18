package br.com.petcare.dominio.dto;

import br.com.petcare.dominio.entidade.Dono;

import java.time.LocalDate;

public record PetDTO(
         Integer id,
         String nome,
         LocalDate dataNascimento,
         Double peso,
         Double tamanho,
         String especie,
         String raca,
         String humor,
         String genero,
         Dono dono
) {
}
