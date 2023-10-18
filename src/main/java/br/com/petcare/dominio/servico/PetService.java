package br.com.petcare.dominio.servico;

import br.com.petcare.dominio.dto.PetDTO;
import br.com.petcare.dominio.entidade.Pet;
import br.com.petcare.dominio.enums.EspecieEnum;
import br.com.petcare.dominio.enums.GeneroEnum;
import br.com.petcare.dominio.enums.HumorEnum;
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
        return new PetDTO(
                pet.getId(),
                pet.getNome(),
                pet.getDataNascimento(),
                pet.getPeso(),
                pet.getTamanho(),
                ObjectUtils.isEmpty(pet.getEspecie())
                        ? null : pet.getEspecie().getDescricao() ,
                ObjectUtils.isEmpty(pet.getRaca())
                        ? null : pet.getRaca().getDescricao(),
                ObjectUtils.isEmpty(pet.getHumor())
                        ? null : pet.getHumor().getDescricao(),
                ObjectUtils.isEmpty(pet.getGenero())
                        ? null : pet.getGenero().getDescricao(),
                pet.getDono()
        );
    }

    private Pet toEntity(PetDTO petDTO) {
        return Pet.builder()
                .id(petDTO.id())
                .nome(petDTO.nome())
                .dataNascimento(petDTO.dataNascimento())
                .peso(petDTO.peso())
                .tamanho(petDTO.tamanho())
                .especie(ObjectUtils.isEmpty(petDTO.especie())
                        ? null : EspecieEnum.recuperarEspecie(petDTO.especie()))
                .raca(ObjectUtils.isEmpty(petDTO.raca())
                        ? null : RacaEnum.recuperarRaca(petDTO.raca()))
                .humor(ObjectUtils.isEmpty(petDTO.humor())
                        ? null : HumorEnum.recuperarHumor(petDTO.humor()))
                .genero(ObjectUtils.isEmpty(petDTO.genero())
                        ? null : GeneroEnum.recuperarGenero(petDTO.genero()))
                .dono(petDTO.dono())
                .build();
    }

}
