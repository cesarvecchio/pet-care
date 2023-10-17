package br.com.petcare.dominio.servico;

import br.com.petcare.dominio.dto.PetDTO;
import br.com.petcare.dominio.entidade.Pet;
import br.com.petcare.dominio.enums.EspecieEnum;
import br.com.petcare.dominio.enums.RacaEnum;
import br.com.petcare.infra.repositorio.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
                ObjectUtils.isEmpty(pet.getEspecie())
                        ? null : pet.getEspecie().getDescricao() ,
                ObjectUtils.isEmpty(pet.getRaca())
                        ? null : pet.getRaca().getDescricao(),
                pet.getDono()
        );
    }

    private Pet toEntity(PetDTO petDTO) {
        return Pet.builder()
                .id(petDTO.id())
                .nome(petDTO.nome())
                .especie(ObjectUtils.isEmpty(petDTO.especie())
                        ? null : EspecieEnum.recuperarEspecie(petDTO.especie()))
                .raca(ObjectUtils.isEmpty(petDTO.raca())
                        ? null : RacaEnum.recuperarRaca(petDTO.raca()))
                .dono(petDTO.dono())
                .build();
    }

}
