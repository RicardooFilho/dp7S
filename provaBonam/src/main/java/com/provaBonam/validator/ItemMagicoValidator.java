package com.provaBonam.validator;

import com.provaBonam.domain.ItemMagico;
import com.provaBonam.enums.TipoItem;
import com.provaBonam.exception.RPGException;
import com.provaBonam.interfaces.RPGValidator;
import org.springframework.stereotype.Component;

@Component
public class ItemMagicoValidator implements RPGValidator<ItemMagico> {

    @Override
    public void valid(ItemMagico itemMagico) {

        validateDefesaAndForcaForArma(itemMagico);
        validateForcaAndDefesaForArmadura(itemMagico);
        validateDefesa(itemMagico);
        validateForca(itemMagico);
        validateDefesaAndForcaForAmuleto(itemMagico);
    }

    private void validateDefesaAndForcaForArma(ItemMagico itemMagico) {

        if (TipoItem.ARMA.equals(itemMagico.getTipoItem())) {

            if (itemMagico.getDefesa().compareTo(0L) != 0) {
                throw new RPGException("Para Itens Mágicos do tipo Arma a defesa deve ser obrigatoriamente zero (0)");
            }

            if (itemMagico.getForca().compareTo(0L) == 0) {
                throw new RPGException(String.format("Informe um atributo de Força válido para o item [%s]", itemMagico.getNome()));
            }
        }
    }

    private void validateForcaAndDefesaForArmadura(ItemMagico itemMagico) {

        if (TipoItem.ARMADURA.equals(itemMagico.getTipoItem())) {

            if (itemMagico.getForca().compareTo(0L) != 0) {
                throw new RPGException("Para Itens Mágicos do tipo Armadura a força deve ser obrigatoriamente zero (0)");
            }

            if (itemMagico.getDefesa().compareTo(0L) == 0) {
                throw new RPGException(String.format("Informe um atributo de Defesa válido para o item [%s]", itemMagico.getNome()));
            }
        }
    }

    private void validateForca(ItemMagico itemMagico) {

        if (itemMagico.getForca().compareTo(10L) > 0) {
            throw new RPGException(String.format("O atributo Força do Item Mágico [%s] está execedento o limite definido (10)", itemMagico.getNome()));
        }
    }

    private void validateDefesa(ItemMagico itemMagico) {

        if (itemMagico.getDefesa().compareTo(10L) > 0) {
            throw new RPGException(String.format("O atributo Defesa do Item Mágico [%s] está execedento o limite definido (10)", itemMagico.getNome()));
        }
    }

    private void validateDefesaAndForcaForAmuleto(ItemMagico itemMagico) {
        if (TipoItem.AMULETO.equals(itemMagico.getTipoItem())) {

            if (itemMagico.getDefesa().compareTo(0L) == 0 && itemMagico.getForca().compareTo(0L) == 0) {

                throw new RPGException(String.format("Informe um valor válido para o atributo de Defesa ou Força para o item [%s]", itemMagico.getNome()));
            }
        }
    }
}
