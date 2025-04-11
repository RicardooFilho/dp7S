package com.provaBonam.validator;

import com.provaBonam.domain.ItemMagico;
import com.provaBonam.interfaces.Validator;
import org.springframework.stereotype.Component;

@Component
public class ItemMagicoValidator implements Validator<ItemMagico> {

    @Override
    public void valid(ItemMagico itemMagico) {
    }


}
