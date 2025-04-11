package com.provaBonam.service;

import com.provaBonam.repository.ItemMagicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemMagicoService {

    private final ItemMagicoRepository repository;
}
