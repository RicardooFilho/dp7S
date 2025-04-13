package com.provaBonam.service;

import com.provaBonam.domain.Personagem;
import com.provaBonam.repository.PersonagemRepository;
import com.provaBonam.validator.PersonagemValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonagemService {

    private final PersonagemRepository repository;
    private final PersonagemValidator validator;

    public List<Personagem> findAll() {
        return repository.findAll()
                .stream()
                .map(Personagem::toExibicao)
                .toList();
    }

    public Personagem findById(Long id) {
        return repository.findById(id)
                .map(Personagem::toExibicao)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Personagem create(Personagem newPersonagem) {

        newPersonagem.getItens().forEach(itemMagico -> itemMagico.setPersonagem(newPersonagem));

        validator.valid(newPersonagem);

        return repository.save(newPersonagem).toExibicao();
    }

    public Personagem update(Long id, String newNomeAventureiro) {

        return repository.findById(id)
                .map(personagem -> {

                    personagem.setNomeAventureiro(newNomeAventureiro);

                    return repository.save(personagem).toExibicao();
                })
                .orElseThrow(EntityNotFoundException::new);
    }

    public void delete(Long id) {

        repository.deleteById(id);
    }

}
