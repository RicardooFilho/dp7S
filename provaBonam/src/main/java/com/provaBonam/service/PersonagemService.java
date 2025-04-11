package com.provaBonam.service;

import com.provaBonam.repository.PersonagemRepository;
import com.provaBonam.validator.PersonagemValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonagemService {

    private final PersonagemRepository repository;

}
