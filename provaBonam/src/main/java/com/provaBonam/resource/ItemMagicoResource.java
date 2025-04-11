package com.provaBonam.resource;

import com.provaBonam.service.ItemMagicoService;
import com.provaBonam.service.PersonagemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/itens-magicos")
@RequiredArgsConstructor
public class ItemMagicoResource {

    private final ItemMagicoService service;
}
