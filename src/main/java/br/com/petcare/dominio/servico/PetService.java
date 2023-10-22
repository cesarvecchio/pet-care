package br.com.petcare.dominio.servico;

import br.com.petcare.dominio.dto.PetDTO;
import br.com.petcare.dominio.entidade.Dono;
import br.com.petcare.dominio.entidade.Pet;
import br.com.petcare.dominio.enums.EspecieEnum;
import br.com.petcare.dominio.enums.GeneroEnum;
import br.com.petcare.dominio.enums.HumorEnum;
import br.com.petcare.dominio.enums.RacaEnum;
import br.com.petcare.infra.excecao.NaoEncontradoException;
import br.com.petcare.infra.excecao.PetNaoPertenceAoDonoException;
import br.com.petcare.infra.repositorio.PetRepository;
import br.com.petcare.infra.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final DonoService donoService;
    private final Utils utils;

    @Autowired
    public PetService(PetRepository petRepository, DonoService donoService, Utils utils) {
        this.petRepository = petRepository;
        this.donoService = donoService;
        this.utils = utils;
    }

    public Page<PetDTO> findAll(Pageable pageable) {
        Page<Pet> pets = petRepository.findAll(pageable);
        return pets.map(this::toDTO);
    }

    public PetDTO cadastrar(PetDTO petDTO, Integer idDono) {
        Pet pet = toEntity(petDTO);
        pet.setDono(this.donoService.buscaPorId(idDono));

        petDTO = this.toDTO(this.petRepository.save(pet));

        return petDTO;
    }

    public PetDTO atualizar(Integer idDono, Integer idPet, PetDTO petDTO) {
        Dono dono = this.donoService.buscaPorId(idDono);

        Pet pet = this.buscarPorId(idPet);

        if(!petPertenceAoDono(pet, dono))
            throw new PetNaoPertenceAoDonoException(
                    String.format("O pet com o id '%d' não pertence ao dono com id '%d'!",
                            idPet, idDono));

        this.utils.copyNonNullProperties(petDTO, pet);

        return this.toDTO(petRepository.save(pet));
    }

    public void deletar(Integer idDono, Integer idPet) {
        this.donoService.existePorId(idDono);
        this.existePorId(idPet);

        if(!this.petPertenceAoDono(idPet, idDono))
            throw new PetNaoPertenceAoDonoException(
                    String.format("O pet com o id '%d' não pertence ao dono com id '%d'!",
                            idPet, idDono));

        this.petRepository.deleteById(idPet);

    }

    public Pet buscarPorId(Integer idPet) {
        return this.petRepository.findById(idPet)
                .orElseThrow(() -> new NaoEncontradoException(
                        String.format("Pet com o id '%d' não encontrado", idPet)));
    }

    public Page<PetDTO> consultarPetsPorDono(Integer idDono, Pageable pageable) {
        this.donoService.existePorId(idDono);
        Page<Pet> pets = this.petRepository.findAllByDonoId(idDono, pageable);

        return pets.map(this::toDTO);
    }

    public void existePorId(Integer idPet) {
        if(!this.petRepository.existsById(idPet))
            throw new NaoEncontradoException(
                    String.format("Pet com o id '%d' não encontrado", idPet));
    }

    public boolean petPertenceAoDono(Pet pet, Dono dono) {
        return ObjectUtils.nullSafeEquals(pet.getDono().getId(), dono.getId());
    }

    public boolean petPertenceAoDono(Integer petId, Integer donoId) {
        return this.petRepository.existsPetByIdAndDonoId(petId, donoId);
    }

    public PetDTO toDTO(Pet pet) {
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
                ObjectUtils.isEmpty(pet.getDono())
                        ? null : this.donoService.toDto(pet.getDono())
        );
    }

    public Pet toEntity(PetDTO petDTO) {
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

                .dono(ObjectUtils.isEmpty(petDTO.dono())
                        ? null : this.donoService.toEntity(petDTO.dono()))
                .build();
    }
}
