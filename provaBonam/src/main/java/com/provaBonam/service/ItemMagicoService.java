package com.provaBonam.service;

import com.provaBonam.domain.ItemMagico;
import com.provaBonam.domain.Personagem;
import com.provaBonam.enums.TipoItem;
import com.provaBonam.exception.RPGException;
import com.provaBonam.repository.ItemMagicoRepository;
import com.provaBonam.repository.PersonagemRepository;
import com.provaBonam.validator.ItemMagicoValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemMagicoService {

    private final ItemMagicoRepository repository;
    private final PersonagemRepository personagemRepository;
    private final ItemMagicoValidator validator;

    public List<ItemMagico> findAll() {
        return repository.findAll();
    }

    public ItemMagico findById(Long id) {
        return repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public ItemMagico create(ItemMagico newItemMagico) {

        validator.valid(newItemMagico);

        return repository.save(newItemMagico);
    }

    public ItemMagico update(Long id, ItemMagico newItemMagico) {

        return repository.findById(id)
                .map(personagem -> repository.save(newItemMagico))
                .orElseThrow(EntityNotFoundException::new);
    }

    public void delete(Long id) {

        repository.deleteById(id);
    }

    public void removeItemFromPersonagem(Long id) {

        repository.findById(id)
                .map(item -> {
                    item.setPersonagem(null);

                    return repository.save(item);
                })
                .orElseThrow(EntityNotFoundException::new);
    }

    public ItemMagico findAmuletoOfPersonagem(Long idPersonagem) {

        return repository.findAmuletoOfPersonagem(idPersonagem)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Não foi encontrado nenhum item do tipo Amuleto para o personagem de ID [%s]", idPersonagem)));
    }

    public List<ItemMagico> findItemsByPersonagem(Long idPersonagem) {

        return personagemRepository.findById(idPersonagem)
                .map(Personagem::getItens)
                .orElseThrow(EntityNotFoundException::new);
    }

    public void addItemOnPersonagem(Long idItem, Long idPersonagem) {

        ItemMagico item = repository.findById(idItem).orElseThrow(EntityNotFoundException::new);

        if (Objects.nonNull(item.getPersonagem())) {
            throw new RPGException("Este item já pertence a outro personagem.");
        }

        Personagem personagem = personagemRepository.findById(idPersonagem).orElseThrow(EntityNotFoundException::new);

        boolean hasAmuleto = personagem.getItens().stream().anyMatch(itemMagico -> TipoItem.AMULETO.equals(itemMagico.getTipoItem()));

        if (TipoItem.AMULETO.equals(item.getTipoItem()) && hasAmuleto) {
            throw new RPGException(String.format("O personagem [%s] já possui um item do tipo Amuleto", personagem.getNome()));
        }

        item.setPersonagem(personagem);

        repository.save(item);
    }
}
