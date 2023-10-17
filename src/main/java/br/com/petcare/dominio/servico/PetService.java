package br.com.petcare.dominio.servico;

import br.com.petcare.dominio.dto.PetDTO;
import br.com.petcare.dominio.entidade.Pet;
import br.com.petcare.infra.repositorio.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    private final PetRepository petRepository;

    @Autowired
    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Page<PetDTO> findAll(Pageable pageable) {
        Page<Pet> pets = petRepository.findAll(pageable);
        return pets.map(this::toDTO);
    }

    private PetDTO toDTO(Pet pet) {
        return new PetDTO(pet.getId(),
                pet.getNome(),
                pet.getEspecie(),
                pet.getRaca(),
                pet.getDono()
        );
    }

    private Pet toEntity(PetDTO petDTO) {
        return Pet.builder()
                .id(petDTO.id())
                .nome(petDTO.nome())
                .especie(petDTO.especie())
                .raca(petDTO.raca())
                .dono(petDTO.dono())
                .build();
    }

}
