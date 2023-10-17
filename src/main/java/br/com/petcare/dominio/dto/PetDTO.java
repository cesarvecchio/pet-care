package br.com.petcare.dominio.dto;

import br.com.petcare.dominio.entidade.Dono;

public record PetDTO(
         Integer id,
         String nome,
         String especie,
         String raca,
         Dono dono
) {
}
