package com.provaBonam.validator;

import com.provaBonam.domain.Personagem;
import com.provaBonam.interfaces.Validator;
import org.springframework.stereotype.Component;

@Component
public class PersonagemValidator implements Validator<Personagem> {

    @Override
    public void valid(Personagem entity) {

    }
}
