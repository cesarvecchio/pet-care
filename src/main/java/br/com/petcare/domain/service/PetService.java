package br.com.petcare.domain.service;

import br.com.petcare.application.controller.exceptions.NaoEncontradoException;
import br.com.petcare.application.controller.exceptions.PetNaoPertenceAoDonoException;
import br.com.petcare.application.request.PetRequestDTO;
import br.com.petcare.application.response.PetResponseDTO;
import br.com.petcare.domain.entity.Dono;
import br.com.petcare.domain.entity.Pet;
import br.com.petcare.domain.enums.EspecieEnum;
import br.com.petcare.domain.enums.GeneroEnum;
import br.com.petcare.domain.enums.HumorEnum;
import br.com.petcare.domain.enums.RacaEnum;
import br.com.petcare.infra.repository.PetRepository;
import br.com.petcare.utils.Utils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final DonoService donoService;
    private final Utils utils;

    public PetService(PetRepository petRepository, DonoService donoService, Utils utils) {
        this.petRepository = petRepository;
        this.donoService = donoService;
        this.utils = utils;
    }

    public Page<PetResponseDTO> findAll(Pageable pageable,
                                        String nome,
                                        LocalDate dataNascimento,
                                        Double peso,
                                        Double tamanho,
                                        Integer especie,
                                        Integer raca,
                                        Integer genero,
                                        Integer humor) {
        Example<Pet> example = Example.of(Pet.builder()
                .nome(nome)
                .dataNascimento(dataNascimento)
                .peso(peso)
                .tamanho(tamanho)
                .especie(EspecieEnum.recuperarEspecie(especie))
                .raca(RacaEnum.recuperarRaca(raca))
                .genero(GeneroEnum.recuperarGenero(genero))
                .humor(HumorEnum.recuperarHumor(humor))
                .build()
        );

        Page<Pet> pets = petRepository.findAll(example, pageable);
        return pets.map(this::toResponseDTO);
    }

    public PetResponseDTO cadastrar(PetRequestDTO petDTO, Integer idDono) {
        Pet pet = toEntity(petDTO);
        pet.setDono(donoService.buscaPorId(idDono));

        return toResponseDTO(petRepository.save(pet));
    }

    public PetResponseDTO atualizar(Integer idDono, Integer idPet, PetRequestDTO petDTO) {
        Dono dono = donoService.buscaPorId(idDono);
        Pet request = toEntity(petDTO);

        Pet pet = buscarPorId(idPet);

        if (!petPertenceAoDono(pet, dono))
            throw new PetNaoPertenceAoDonoException(
                    String.format("O pet com o id '%d' não pertence ao dono com id '%d'!",
                            idPet, idDono));

        utils.copyNonNullProperties(request, pet);

        return toResponseDTO(petRepository.save(pet));
    }

    public void deletar(Integer idDono, Integer idPet) {
        donoService.existePorId(idDono);
        existePorId(idPet);

        if (!petPertenceAoDono(idPet, idDono))
            throw new PetNaoPertenceAoDonoException(
                    String.format("O pet com o id '%d' não pertence ao dono com id '%d'!",
                            idPet, idDono));

        petRepository.deleteById(idPet);

    }

    public Pet buscarPorId(Integer idPet) {
        return petRepository.findById(idPet)
                .orElseThrow(() -> new NaoEncontradoException(
                        String.format("Pet com o id '%d' não encontrado", idPet)));
    }

    public Page<PetResponseDTO> consultarPetsPorDono(Integer idDono, Pageable pageable) {
        donoService.existePorId(idDono);
        Page<Pet> pets = petRepository.findAllByDonoId(idDono, pageable);

        return pets.map(this::toResponseDTO);
    }

    public void existePorId(Integer idPet) {
        if (!petRepository.existsById(idPet))
            throw new NaoEncontradoException(
                    String.format("Pet com o id '%d' não encontrado", idPet));
    }

    public boolean petPertenceAoDono(Pet pet, Dono dono) {
        return ObjectUtils.nullSafeEquals(pet.getDono().getId(), dono.getId());
    }

    public boolean petPertenceAoDono(Integer petId, Integer donoId) {
        return petRepository.existsPetByIdAndDonoId(petId, donoId);
    }

    public PetResponseDTO toResponseDTO(Pet pet) {
        return new PetResponseDTO(
                pet.getId(),
                pet.getNome(),
                pet.getDataNascimento(),
                pet.getPeso(),
                pet.getTamanho(),
                isEmpty(pet.getEspecie()) ? null : pet.getEspecie().getDescricao(),
                isEmpty(pet.getRaca()) ? null : pet.getRaca().getDescricao(),
                isEmpty(pet.getHumor()) ? null : pet.getHumor().getDescricao(),
                isEmpty(pet.getGenero()) ? null : pet.getGenero().getDescricao(),
                isEmpty(pet.getDono()) ? null : donoService.toResponseDto(pet.getDono())
        );
    }

    public Pet toEntity(PetRequestDTO petDTO) {
        return Pet.builder()
                .id(petDTO.id())
                .nome(petDTO.nome())
                .dataNascimento(petDTO.dataNascimento())
                .peso(petDTO.peso())
                .tamanho(petDTO.tamanho())
                .especie(isEmpty(petDTO.especie()) ? null : EspecieEnum.recuperarEspecie(petDTO.especie()))
                .raca(isEmpty(petDTO.raca()) ? null : RacaEnum.recuperarRaca(petDTO.raca()))
                .humor(isEmpty(petDTO.humor()) ? null : HumorEnum.recuperarHumor(petDTO.humor()))
                .genero(isEmpty(petDTO.genero()) ? null : GeneroEnum.recuperarGenero(petDTO.genero()))
                .dono(isEmpty(petDTO.dono()) ? null : donoService.toEntity(petDTO.dono()))
                .build();
    }
}
