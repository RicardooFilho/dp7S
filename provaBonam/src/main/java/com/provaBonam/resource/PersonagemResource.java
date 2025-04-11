package com.provaBonam.resource;

import com.provaBonam.service.PersonagemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/personagens")
@RequiredArgsConstructor
public class PersonagemResource {

    private final PersonagemService service;
}
