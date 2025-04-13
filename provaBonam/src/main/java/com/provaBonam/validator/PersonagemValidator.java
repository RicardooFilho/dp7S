package com.provaBonam.validator;

import com.provaBonam.domain.ItemMagico;
import com.provaBonam.domain.Personagem;
import com.provaBonam.enums.TipoItem;
import com.provaBonam.exception.RPGException;
import com.provaBonam.interfaces.RPGValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonagemValidator implements RPGValidator<Personagem> {

    private final ItemMagicoValidator itemMagicoValidator;

    @Override
    public void valid(Personagem personagem) {

        validateTotalPoints(personagem);
        validateAmuletos(personagem);
        validateItens(personagem);
    }

    private void validateTotalPoints(Personagem personagem) {

        Long totalPoints = personagem.getForca() + personagem.getDefesa();

        if (totalPoints.compareTo(10L) != 0) {
            throw new RPGException(String.format("A soma dos atributos de Força e Defesa do personagem [%s] não está de acordo com o limite definido (10)", personagem.getNome()));
        }
    }

    private void validateAmuletos(Personagem personagem) {

        List<ItemMagico> amuletos = personagem.getItens()
                .stream()
                .filter(item -> TipoItem.AMULETO.equals(item.getTipoItem()))
                .toList();

        if (amuletos.size() > 1) {
            throw new RPGException(String.format("O personagem [%s] já possui um item do tipo Amuleto", personagem.getNome()));
        }
    }

    private void validateItens(Personagem personagem) {

        personagem.getItens()
                .forEach(itemMagicoValidator::valid);
    }
}
